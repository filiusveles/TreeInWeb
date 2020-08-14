package ru.daniels.treeinweb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

@Repository
public class PostgreSQLDao implements Dao {
    private final String root = "'#'";

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public int addNode(Node node) {
        Session session = sessionFactory.getCurrentSession();
        String parent = node.getParentId();
        session.persist(node);
        if(!parent.equals(root.replaceAll("'","")))
            session.createQuery("UPDATE Node SET hasChild=true WHERE id=" + parent).executeUpdate();
        return node.getId();

    }

    @Override
    @Transactional
    public void updateNode(Node node) {
        Session session = sessionFactory.getCurrentSession();
        session.update(node);
    }

    @Override
    @Transactional
    public void deleteNode(int id) {
        Session session = sessionFactory.getCurrentSession();
        Node deletedNode = session.load(Node.class, id);
        if(deletedNode != null ) {
            int parentID = deletedNode.
                    getParentId().equals(root.replaceAll("'","")) ?
                    0: Integer.parseInt(deletedNode.getParentId());
            if(getChildren(parentID).size() == 1){
                session.createQuery("UPDATE Node SET hasChild=false WHERE id=" + parentID).executeUpdate();
            }
            session.delete(deletedNode);
        }

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


}
