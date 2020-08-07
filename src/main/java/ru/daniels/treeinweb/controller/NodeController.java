package ru.daniels.treeinweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.daniels.treeinweb.models.Node;
import ru.daniels.treeinweb.services.NodeService;

@RestController
public class NodeController {
    private NodeService service;

    @Autowired
    @Qualifier(value = "service")
    public void setService(NodeService service) {
        this.service = service;
    }

    @GetMapping("/tree")
    public String listParent(Model model){
        model.addAttribute("nod", new Node());
        model.addAttribute("listParent", this.service.getParent());
        return "tree";
    }


}
