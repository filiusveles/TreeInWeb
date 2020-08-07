package ru.daniels.treeinweb.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

@Repository
public class PostgreSQLDao implements Dao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNode(Node node) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(node);
    }

    @Override
    public void updateNode(Node node) {
        Session session = sessionFactory.getCurrentSession();
        session.update(node);
    }

    @Override
    public void deleteNode(int id) {
        Session session = sessionFactory.getCurrentSession();
        Node deletedNode = (Node) session.load(Node.class, id);
        if(deletedNode != null) session.delete(deletedNode);
    }

    @Override
    @Transactional
    public List<Node> getParent() {
        Session session = sessionFactory.getCurrentSession();

        List<Node> parent = session.createQuery("from Node where parentId="+0).list();

        for(Node node: parent) System.out.println("Parent: " + node);
        return parent;
    }

    @Override
    @Transactional
    public List<Node> getChildren(int parentID) {
        System.out.println("Children");
        Session session = sessionFactory.getCurrentSession();
        List<Node> children = session.createQuery("from Node where parentId=" + parentID).list();

        for(Node node: children) System.out.println("Children of " + parentID + ": " + node);

        return children;
    }
}
