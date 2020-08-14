package ru.daniels.treeinweb.services;

import ru.daniels.treeinweb.dao.Dao;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

public interface NodeService {

    public void setDao(Dao dao);

    public List<Node> getParent();

    public List<Node> getChildren(int parentID);

    public int addNode(Node node);

    public void updateNode(Node node);

    public void deleteNode(int id);
}
