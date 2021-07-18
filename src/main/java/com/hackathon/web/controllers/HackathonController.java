package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.JudgehackathonService;
import com.hackathon.web.services.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class HackathonController {
    private final HackathonService hackathonService;
    private final TeamService teamService;
    private final JudgehackathonService judgehackathonService;
    private final JudgeService judgeService;


    @GetMapping("/administrator/saveHackathon/getAvailableJudges")
    public List<Judge> getAvailableJudges(Model model){

        List<Judge> judges;
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

    @GetMapping("/administrator/create/hackathon")
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

    @GetMapping("/administrator/search/hackathons")
    public String getSearchHackathons(Model model){
        model.addAttribute("hackathons",hackathonService.findAll());
        return "searchHackathons";
    }


    @PostMapping("/administrator/create/hackathon")
    public String saveHackathon(Model model,
                                @Valid
                                @ModelAttribute Hackathon hackathon,
                                BindingResult bindingResult,
                                @RequestParam(value="checkedIds",required = false) long[] ids,
                                HttpServletRequest request
                                ){

        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "hackathon";
        }

        Hackathon h = new Hackathon();
        h.setName(hackathon.getName());
        h.setDate(hackathon.getDate());
        Administrator administrator = (Administrator) request.getSession().getAttribute("user_admin");
        h.setAdministrator((Administrator) request.getSession().getAttribute("user"));

        List<Judge> judges = new ArrayList<>();

        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                Judge judge = judgeService.findByJudgeid(Long.valueOf(ids[i]));
                judges.add(judge);
                log.warn(String.valueOf(ids[i]));
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
        model.addAttribute("message","Hackathon created successfully.");


        return "hackathon";
    }

    @GetMapping("/administrator/searchHackathons/hackathon/{id}")
    public String getHackathon(Model model,
                               @PathVariable String id){

        Hackathon hackathon = hackathonService.findByHackathonid(Long.valueOf(id));
        log.warn(hackathon.getDate().toString());
        List<Team> teams = teamService.findAllByHackathon_Hackathonid(hackathon.getHackathonid());
        List<Judgehackathon> judgehackathonList = judgehackathonService.
                findAllByHackathon_Hackathonid(hackathon.getHackathonid());
        List<Judge> judges = new ArrayList<>();

        for (Judgehackathon judgehackathon: judgehackathonList
             ) {
            Judge judge = judgeService.findByJudgeid(judgehackathon.getJudge().getJudgeid());
            judges.add(judge);
        }

        model.addAttribute("hackathon",hackathon);
        model.addAttribute("teams",teams);
        model.addAttribute("judges",judges);

        return "hackathon";
    }

    @PostMapping("/administrator/search/hackathons")
    public String searchHackathon(Model model,@RequestParam String search){
        model.addAttribute("hackathons",hackathonService.findAllByNameContains(search));
        return "searchHackathons";
    }
}
