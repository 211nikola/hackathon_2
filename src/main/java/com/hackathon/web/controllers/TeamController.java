package com.hackathon.web.controllers;

import com.hackathon.web.domain.Team;
import com.hackathon.web.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping("/getTeams")
    public String getTeams(Model model){
            List<Team> list = teamService.findAll();
            model.addAttribute("teams",list);

        for (Team team:list
             ) {
            System.out.println(team.getTeamID());
        }

            return "/judge";
    }
}
