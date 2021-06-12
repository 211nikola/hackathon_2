package com.hackathon.web.controllers;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.JudgehackathonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JudgeController {
    private final JudgeService judgeService;
    private final HackathonService hackathonService;
    private final JudgehackathonService judgehackathonService;

    public JudgeController(JudgeService judgeService, HackathonService hackathonService, JudgehackathonService judgehackathonService) {
        this.judgeService = judgeService;
        this.hackathonService = hackathonService;
        this.judgehackathonService = judgehackathonService;
    }



    @GetMapping("/judgeLogin")
    public String redirectToJudgeLogin(){
        return "judgeLogin";
    }

    @PostMapping("/judge")
    public String getJudge(
                            @RequestParam String username
                            ,@RequestParam String password,
                            HttpSession session,
                            Model model){

        Judge judge = judgeService.findByUsernameAndPassword(username, password);

        if(judge != null){

            List<Judgehackathon> judgehackathonList = judgehackathonService.
                    findAllByJudge_Judgeid(judge.getJudgeid());
            List<Hackathon> hackathonList = new ArrayList<>();

            for (Judgehackathon judgehackathon :
                    judgehackathonList) {
                hackathonList.add(
                        hackathonService.findByHackathonid(
                                judgehackathon.getHackathon().getHackathonid()));
            }


            model.addAttribute("hackathons",hackathonList);
            model.addAttribute("judge",judge);
            session.setAttribute("user",judge);
            return "judge" ;
        }


        return "redirect:/judgeLogin";
    }

}
