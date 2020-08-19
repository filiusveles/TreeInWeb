package ru.daniels.treeinweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/tree")
    public String site(){
        return "nodes";
    }
}
