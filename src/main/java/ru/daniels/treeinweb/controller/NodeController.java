package ru.daniels.treeinweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.daniels.treeinweb.models.Node;
import ru.daniels.treeinweb.services.NodeService;

import java.util.List;

@Controller
public class NodeController {
    private NodeService service;

    @Autowired
    @Qualifier(value = "service")
    public void setService(NodeService service) {
        this.service = service;
    }


    @GetMapping("/tree/node")
    @ResponseBody
    public List<Node> nodeList(@RequestParam(value = "id") int id){
        System.out.println("nodeList()");
        if(id == 0)
            return service.getParent();
        else
            return service.getChildren(id);
    }
    @GetMapping("/tree")
    public String site(){
        return "tree";
    }

    





}
