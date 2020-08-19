package ru.daniels.treeinweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.daniels.treeinweb.dao.Dao;
import ru.daniels.treeinweb.models.Node;

import java.util.List;

@Service
public class NodeServiceImpl implements NodeService {
    private Dao dao;

    @Autowired
    @Qualifier("dao")
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public List<Node> getListOfNodes(int id) {
        if(id == 0)
            return dao.getParent();
        else
            return dao.getChildren(id);
    }

    @Override
    public int addNode(Node node) {
         return this.dao.create(node);
    }

    @Override
    public void updateNode(Node node) {
        this.dao.update(node);
    }

    @Override
    public void deleteNode(int id) {
        this.dao.delete(id);
    }
}
