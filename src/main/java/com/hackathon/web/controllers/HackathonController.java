package com.hackathon.web.controllers;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.services.HackathonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HackathonController {
    private final HackathonService hackathonService;

    public HackathonController(HackathonService hackathonService) {
        this.hackathonService = hackathonService;
    }




    public void getHackathons(Model model){

        model.addAttribute("hackatons",hackathonService.findAll());
        System.out.println("triggered");


    }
}
