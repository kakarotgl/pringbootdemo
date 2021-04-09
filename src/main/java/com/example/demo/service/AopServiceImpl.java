package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AopServiceImpl implements Aopservice{
    @Override
    public void query() {
        System.out.println("query--ing");
    }
}
