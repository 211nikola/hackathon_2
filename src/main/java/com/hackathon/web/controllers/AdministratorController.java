package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.AdministratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdministratorController {

    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/administratorLogin")
    public String redirectToAdminLogin(){
        return "administratorLogin";
    }

    @PostMapping("/administrator")
    public String getAdministrator(@RequestParam String username
            ,@RequestParam String password,
                          Model model) {

        Administrator administrator = administratorService.
                findAdministratorByUsernameAndPassword(username,password);



        if(administrator != null) {

            System.out.println("Administrator found!");

            model.addAttribute("administrator", administrator);
            System.out.println(administrator.getAdministratorid());

            return "administrator";
        }


        return "redirect:administratorLogin";
    }



}
