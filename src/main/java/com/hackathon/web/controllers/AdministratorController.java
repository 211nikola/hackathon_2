package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.AdministratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@AllArgsConstructor
public class AdministratorController {

    private final AdministratorService administratorService;

    @GetMapping("/administrator/login")
    public String redirectToAdminLogin(){
        return "administratorLogin";
    }



    @PostMapping("/administrator")
    public String getAdministrator(
                        @RequestParam String username
                        ,@RequestParam String password,
                          HttpSession session,
                          RedirectAttributes atts,
                          Model model) {

        Administrator administrator = administratorService.
                findAdministratorByUsernameAndPassword(username,password);



        if(administrator != null) {

            System.out.println("Administrator found!");

            model.addAttribute("administrator", administrator);
            session.setAttribute("user_admin",administrator);
            System.out.println(administrator.getAdministratorid());

            return "administrator";
        }

        atts.addFlashAttribute("message_error","Invalid username or password.");
        log.warn("invalid message pass");

        return "redirect:/administratorLogin";
    }



}
