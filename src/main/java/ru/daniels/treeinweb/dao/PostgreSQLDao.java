package ru.daniels.treeinweb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.treeinweb.models.Node;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PostgreSQLDao implements Dao {
    private SessionFactory sessionFactory;

    private final String root = "'#'";

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Node> getParent() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Node where parentId=" + root).list();
    }

    @Override
    @Transactional
    public List<Node> getChildren(int parentID) {
        String parent = "'" + parentID + "'";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Node where parentId=" + parent).list();
    }

    @Override
    @Transactional
    public int create(Node node) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(node);
        changeThePresenceOfChildrenFormTheirParent(session, node.getParentId());
        return node.getId();

    }

    @Override
    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Node deletedNode = session.load(Node.class, id);
        if(deletedNode != null ) {
            deleteAllChildren(session, deletedNode);
            changeThePresenceOfChildrenFormTheirParent(session, deletedNode.getParentId());
        }
    }

    private void deleteAllChildren(Session session, Node root){
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        Node parent;
        while (!stack.isEmpty()){
            parent = stack.peek();
            if(parent.isHasChild()){
                for(Node child : getChildren(parent.getId())) stack.push(child);
                parent.setHasChild(false);
            }
            else {
                session.delete(stack.pop());
            }
        }
    }

    @Override
    @Transactional
    public void update(Node node) {
        Session session = sessionFactory.getCurrentSession();
        session.update(node);
        changeThePresenceOfChildrenFormTheirParent(session, node.getParentId());
    }

    private void changeThePresenceOfChildrenFormTheirParent(Session session, String parentID){
        if(parentID.equals(root.replaceAll("'",""))) return;
        int id = Integer.parseInt(parentID);
        if(getChildren(id).size() == 0)
            session.createQuery("UPDATE Node SET hasChild=false WHERE id=" + id).executeUpdate();
        else
            session.createQuery("UPDATE Node SET hasChild=true WHERE id=" + id).executeUpdate();
    }


}
