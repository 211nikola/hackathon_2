package com.hackathon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class StartupController {


    @RequestMapping({"/","/index", "/start", "startup"})
    public String index(){

        return "startup";
    }

    @GetMapping({"/logout"})
    public String logout(RedirectAttributes redirectAttributes,
                         HttpServletRequest request,
                         HttpServletResponse response){

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        resp.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
        resp.setHeader("Last-Modified", new Date().toString());
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");



        redirectAttributes.addFlashAttribute("message_logout","Logged out");
        return "redirect:/startup";
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
