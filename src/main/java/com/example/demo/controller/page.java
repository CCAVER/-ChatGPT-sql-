package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class page {
    @RequestMapping("/")
    public String toMain(){
        return "show";
    }
}
