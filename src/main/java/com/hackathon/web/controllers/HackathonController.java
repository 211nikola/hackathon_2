package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.JudgehackathonService;
import com.hackathon.web.services.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HackathonController {
    private final HackathonService hackathonService;
    private final TeamService teamService;
    private final JudgehackathonService judgehackathonService;
    private final JudgeService judgeService;

    public HackathonController(HackathonService hackathonService, TeamService teamService, JudgehackathonService judgehackathonService, JudgeService judgeService) {
        this.hackathonService = hackathonService;
        this.teamService = teamService;
        this.judgehackathonService = judgehackathonService;
        this.judgeService = judgeService;
    }


    @GetMapping("/administrator/saveHackathon/getAvailableJudges")
    public List<Judge> getAvailableJudges(Model model){

        List<Judge> judges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        List<Judgehackathon> judgehackathons = judgehackathonService.findAll();

        for (Judgehackathon judgehackathon :
                judgehackathons) {
            ids.add(judgehackathon.getJudge().getJudgeid());
        }

        judges = judgeService.findAllByJudgeidIsNotIn(ids);

        model.addAttribute("judges",judges);

        return judges;
    }

    @GetMapping("/administrator/addHackathon")
    public String addHackathon(Model model){
        model.addAttribute("hackathon",new Hackathon());
        List<Judge> judges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        List<Judgehackathon> judgehackathons = judgehackathonService.findAll();

        for (Judgehackathon judgehackathon :
                judgehackathons) {
            ids.add(judgehackathon.getJudge().getJudgeid());
        }

        judges = judgeService.findAllByJudgeidIsNotIn(ids);

        model.addAttribute("judges",judges);
        return "hackathon";
    }

    @GetMapping("/administrator/searchHackathons")
    public String getSearchHackathons(Model model){
        model.addAttribute("hackathon",new Hackathon());
        return "searchHackathons";
    }


    @PostMapping("/administrator/saveHackathon")
    public String saveHackathon(Model model,
                                @ModelAttribute Hackathon hackathon,
                                @RequestParam(value="checkedIds",required = false) long[] ids,
                                HttpServletRequest request,
                                BindingResult bindingResult){

        Hackathon h = new Hackathon();
        h.setName(hackathon.getName());
        h.setDate(hackathon.getDate());
        Administrator administrator = (Administrator) request.getSession().getAttribute("user");
        h.setAdministrator((Administrator) request.getSession().getAttribute("user"));

        List<Judge> judges = new ArrayList<>();

        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                Judge judge = judgeService.findByJudgeid(Long.valueOf(ids[i]));
                judges.add(judge);
                System.out.println(ids[i]);
            }

        }






        Hackathon savedHackathon = hackathonService.save(h);
        if(!judges.isEmpty()){
            for (Judge j: judges
            ) {
                Judgehackathon judgehackathon = new Judgehackathon(new JudgehackathonId(j.getJudgeid(), savedHackathon.getHackathonid(), administrator.getAdministratorid()),savedHackathon,j,administrator);
                judgehackathon.setAdministrator(administrator);
                judgehackathon.setHackathon(savedHackathon);
                judgehackathon.setJudge(j);

                judgehackathonService.save(judgehackathon);
            }
        }

        model.addAttribute("judges",judges);
        model.addAttribute("hackathon",h);


        return "hackathon";
    }

    @GetMapping("/administrator/searchHackathons/hackathon/{id}")
    public String getHackathon(Model model,
                               @PathVariable String id){

        Hackathon hackathon = hackathonService.findByHackathonid(Long.valueOf(id));
        List<Team> teams = teamService.findAllByHackathon_Hackathonid(hackathon.getHackathonid());
        List<Judgehackathon> judgehackathonList = judgehackathonService.
                findAllByHackathon_Hackathonid(hackathon.getHackathonid());
        List<Judge> judges = new ArrayList<>();
        for (Judgehackathon judgehackathon: judgehackathonList
             ) {
            Judge judge = judgeService.findByJudgeid(judgehackathon.getJudge().getJudgeid());
            judges.add(judge);
        }
        //List<Judge> judges = judgeService.

        model.addAttribute("hackathon",hackathon);
        model.addAttribute("teams",teams);
        model.addAttribute("judges",judges);

        return "hackathon";
    }

    @PostMapping("/administrator/searchHackathons")
    public String searchHackathon(Model model,@RequestParam String search){
        model.addAttribute("hackathons",hackathonService.findAllByNameContains(search));
        return "searchHackathons";
    }
}
