package ru.daniels.treeinweb.services;

import org.springframework.stereotype.Service;
import ru.daniels.treeinweb.dao.Dao;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Node> getParent() {
        List<Node> parents = this.dao.getParent();
        for(Node parent: parents){
            parent.setParentId("#");
        }
        return parents;
    }

    @Override
    public List<Node> getChildren(int parentID) {
        return this.dao.getChildren(parentID);
    }

    @Override
    public void addNode(Node node) {
        this.dao.addNode(node);
    }

    @Override
    public void updateNode(Node node) {
        this.dao.updateNode(node);
    }

    @Override
    public void deleteNode(int id) {
        this.dao.deleteNode(id);
    }
}
