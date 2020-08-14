package ru.daniels.treeinweb.dao;

import org.hibernate.SessionFactory;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

public interface Dao {

    public void setSessionFactory(SessionFactory sessionFactory);

    public int addNode(Node node);

    public void updateNode(Node node);

    public void deleteNode(int id);

    public List<Node> getParent();

    public List<Node> getChildren(int parentID);


}
