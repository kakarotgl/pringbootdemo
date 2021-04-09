package com.example.demo.controller;

import com.example.demo.service.Aopservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class AopController {
    @Autowired
    private Aopservice aopservice;

    @RequestMapping(value = "query")
    public void query(){
        aopservice.query();
    }
}
