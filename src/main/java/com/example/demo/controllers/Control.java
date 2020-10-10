package com.example.demo.controllers;


import com.example.demo.service.ServiceTable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
@RequestMapping

public class Control {

    private ServiceTable serviceTable = new ServiceTable();

    @GetMapping("/hello")
    public ArrayList<String> hello(
            @RequestParam String first,
            @RequestParam String second
    ) {
        return serviceTable.testFun(first, second);
    }

}
