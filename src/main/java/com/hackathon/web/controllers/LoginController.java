package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private final AdministratorService administratorService;
    private final JudgeService judgeService;
    private final TeamService teamService;
    private final HackathonService hackathonService;
    private final JudgehackathonService judgehackathonService;

    public LoginController(AdministratorService administratorService, JudgeService judgeService, TeamService teamService, HackathonService hackathonService, JudgehackathonService judgehackathonService) {
        this.administratorService = administratorService;
        this.judgeService = judgeService;
        this.teamService = teamService;
        this.hackathonService = hackathonService;
        this.judgehackathonService = judgehackathonService;
    }

    @RequestMapping("/login")
    public String login(){

        return "login";

    }



    @PostMapping("/post_login")
    // @RequestMapping(method = RequestMethod.POST)
    public String getUser(@RequestParam String username
            ,@RequestParam String password,
                                   Model model) {

        Administrator administrator = administratorService.
                findAdministratorByUsernameAndPassword(username,password);

        Judge judge = judgeService.findByUsernameAndPassword(username, password);

        if(administrator == null){
            if(judge == null) {
                System.out.println("User not found!");
            } else {
                System.out.println("Judge found!" );
                model.addAttribute("judge", judge);
                System.out.println(judge.getJudgeid());

                List<Team> list = teamService.findAll();
                model.addAttribute("teams",list);


                List<Judgehackathon> judgehackathonList = judgehackathonService.
                        findAllByJudge_Judgeid(judge.getJudgeid());
                List<Hackathon> hackathonList = new ArrayList<>();

                for (Judgehackathon judgehackathon :
                        judgehackathonList) {
                    hackathonList.add(
                            hackathonService.findByHackathonid(
                                    judgehackathon.getHackathon().getHackathonid()));
                }





                //List<Hackathon> hackathonList = hackathonService.findAll();
                model.addAttribute("hackathons",hackathonList);




                return "/judge";
            }

        }else {

            System.out.println("Administrator found!");

            model.addAttribute("administrator", administrator);
            System.out.println(administrator.getAdministratorid());

            return "/administrator";
        }
        return "redirect:/login";
    }

}