package com.test.nameprojecet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class firstcontroller {
    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("userName","고객");
    return  "greetings";
    }
    @GetMapping("/bye")
    public String seeYou(Model model){
        model.addAttribute("userName","고객");
        return  "goodbye";
    }
}