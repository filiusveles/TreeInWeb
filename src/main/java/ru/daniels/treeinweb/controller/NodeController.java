package ru.daniels.treeinweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.daniels.treeinweb.models.Node;
import ru.daniels.treeinweb.services.NodeService;

import java.util.List;

@RestController
@RequestMapping("/nodes")
public class NodeController {

    private NodeService service;

    @Autowired
    @Qualifier(value = "service")
    public void setService(NodeService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseBody
    public List<Node> nodeList(@RequestParam(value = "id") int id){
        return service.getListOfNodes(id);
    }


    @PostMapping(path = "/add")
    public int addNode(@RequestBody Node node){
        return service.addNode(node);
    }


    @DeleteMapping(path = "/delete")
    public void deleteNode(@RequestBody Node node){
        service.deleteNode(node.getId());
    }

    @PutMapping(path = "/update")
    public void renameNode(@RequestBody Node node){
        service.updateNode(node);
    }









}
