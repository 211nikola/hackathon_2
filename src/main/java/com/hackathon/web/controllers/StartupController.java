package com.hackathon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartupController {


    @RequestMapping({"/","/index", "/start", "startup"})
    public String index(){

        return "startup";
    }


    @PostMapping("/administratorLogin")
    public String goToLoginAdministrator(){
        return "administratorLogin";
    }

    @PostMapping("/judgeLogin")
    public String goToLoginJudge(){
        return "judgeLogin";
    }
}
