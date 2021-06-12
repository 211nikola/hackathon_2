package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.domainDTO.MarkDTO;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.MarkService;
import com.hackathon.web.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class TeamController {

    private final TeamService teamService;
    private final HackathonService hackathonService;
    private final MarkService markService;
    private final JudgeService judgeService;

    public TeamController(TeamService teamService, HackathonService hackathonService, MarkService markService, JudgeService judgeService) {
        this.teamService = teamService;
        this.hackathonService = hackathonService;
        this.markService = markService;
        this.judgeService = judgeService;
    }

    @PostMapping("/judge/findTeams/team/{id}/saveMark")
    public String saveMark(@Valid @ModelAttribute MarkDTO markDTO,
                           @PathVariable("id") String id,
                           HttpServletRequest request,
                           Model model, BindingResult bindingResult){
        /*
        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "/teamMark";
        }
        mark = markService.save(mark);
        return "redirect:/teamMark/" + mark.getTeam().getName();



        Long judgeid = judgeService.findByJudgeid(Long.valueOf(2)).getJudgeid();
        Judge judge = judgeService.findByJudgeid(Long.valueOf(2L));
        Long teamid = Long.valueOf(id);
        Team team = teamService.findByTeamID(teamid);

        Mark m = new Mark(
                new MarkId(2l,teamid),
                mark.getDesign(),
                mark.getEfficiency()
                ,mark.getComment()
                ,mark.getComplexity()
                ,judge
                ,team);

         */

        MarkDTO mdto = markDTO;

        Judge loggedInJudge = (Judge) request.getSession().getAttribute("user");
        Long judgeid = loggedInJudge.getJudgeid();


        Long teamid = Long.parseLong(id);
        Team team = teamService.findByTeamID(teamid);

        Mark m = new Mark(
                new MarkId(0L,teamid),
                markDTO.getDesign(),
                markDTO.getEfficiency()
                ,markDTO.getComment()
                ,markDTO.getComplexity()
                ,loggedInJudge
                ,team);

        Mark savedMark = markService.save(m);

        model.addAttribute("mark",savedMark);

        return "teamMark";




    }

    @GetMapping("/judge/findTeams/team/{id}")
    public String showTeamMarks(@PathVariable String id,
                                 HttpServletRequest request,
                                 Model model){

        //String name = httpServletRequest.getUserPrincipal().getName();
        //System.out.println(name);

        //Long judgeid = judgeService.findByJudgeid(Long.valueOf(2)).getJudgeid();

        Judge loggedInJudge = (Judge) request.getSession().getAttribute("user");
        Long judgeid = loggedInJudge.getJudgeid();

        Long teamid = Long.valueOf(id);
        Team team = teamService.findByTeamID(teamid);





        Mark mark = markService.findByJudge_JudgeidAndTeamTeamID(judgeid,teamid);


        if(mark !=null){
             model.addAttribute("mark",mark);
        }else{
            model.addAttribute("markDTO",new MarkDTO());
            model.addAttribute("mark",new Mark());
            model.addAttribute("team",team);
        }




        return "teamMark";
    }

    @GetMapping(value="/judge")
    public String findTeamsForHackathon(@RequestParam String hackathon, Model model){

        //Hackathon h = hackathonService.findByHackathonid(Long.valueOf(hackathon));
        List<Team> teams = teamService.findAllByHackathon_Hackathonid(Long.valueOf(hackathon));
        model.addAttribute("teams",teams);

        return "teamsInHackathon";

    }

    /*
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

     */
}
