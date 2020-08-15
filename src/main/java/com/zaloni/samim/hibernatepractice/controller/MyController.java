package com.zaloni.samim.hibernatepractice.controller;

import com.zaloni.samim.hibernatepractice.service.IMyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private IMyDataService iMyDataService;

    @GetMapping("/add-data")
    public void addData() {

        iMyDataService.addData();
    }

    @GetMapping("/")
    public String hello() {

        return "Hello World";
    }

}
