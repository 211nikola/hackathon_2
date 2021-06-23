package com.hackathon.web.controllers;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.domain.Mark;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.JudgehackathonService;
import com.hackathon.web.services.MarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JudgeController {
    private final JudgeService judgeService;
    private final HackathonService hackathonService;
    private final JudgehackathonService judgehackathonService;
    private final MarkService markService;

    public JudgeController(JudgeService judgeService, HackathonService hackathonService, JudgehackathonService judgehackathonService, MarkService markService) {
        this.judgeService = judgeService;
        this.hackathonService = hackathonService;
        this.judgehackathonService = judgehackathonService;
        this.markService = markService;
    }



    @GetMapping("/administrator/delete/judge/{id}")
    public String getJudgeDelete(Model model,
                                 @PathVariable String id){

        model.addAttribute("judge",judgeService.findByJudgeid(Long.valueOf(id)));

        return "judgeDelete";
    }

    @PostMapping("/administrator/delete/judge/{id}")
    public String deleteJudge(Model model,
                              @PathVariable String id){

       Judge judge = judgeService.findByJudgeid(Long.valueOf(id));

       List<Judgehackathon> judgehackathons = judgehackathonService.findAll();
       List<Mark> marks = markService.findAll();
       
       //mora @Query za deletes
       
       if(!judgehackathons.isEmpty()){
           for (Judgehackathon jh: judgehackathons
                ) {
               judgehackathonService.deleteJudgehackathonByJudge_Judgeid(judge.getJudgeid());
           }
       }
       if(!marks.isEmpty()){
           for (Mark m :
                   marks) {
               markService.deleteByJudge_Judgeid(judge.getJudgeid());
           }
       }

       judgeService.deleteById(judge.getJudgeid());

       model.addAttribute("judge",judge);


        return "judgeOverview";
    }

    @GetMapping("/administrator/addJudge")
    public String addJudge(Model model){
        model.addAttribute("judge",new Judge());
        return "judgeOverview";
    }

    @PostMapping("/administrator/addJudge")
    public String saveJudge(Model model,
                            @ModelAttribute Judge judge,
                            BindingResult bindingResult){

        Judge savedJudge = judgeService.save(judge);
        model.addAttribute("judge",savedJudge);

        return "judgeOverview";
    }


    @GetMapping("/administrator/searchJudges")
    public String getSearchJudges(Model model){
        model.addAttribute("judge",new Judge());
        return "searchJudges";
    }

    @PostMapping("/administrator/searchJudges")
    public String searchJudges(Model model,@RequestParam String search){
        model.addAttribute("judges",judgeService.findAllByNameContains(search));
        return "searchJudges";
    }

    @GetMapping("/administrator/update/judge/{id}")
    public String getUpdateJudge(Model model,
                              @PathVariable String id,
                              @ModelAttribute Judge judgeForUpdate){

        Judge judge = judgeService.findByJudgeid(Long.valueOf(id));
        model.addAttribute("judge",judge);
        judgeForUpdate.setJudgeid(judge.getJudgeid());

        return "judgeUpdate";
    }

    @PostMapping("/administrator/update/judge/{id}")
    public String updateJudge(Model model,
                              @PathVariable String id,
                              @ModelAttribute Judge judgeForUpdate,
                              BindingResult bindingResult){
        Judge judge = judgeService.findByJudgeid(Long.valueOf(id));
        judgeForUpdate.setJudgeid(judge.getJudgeid());

        Judge updatedJudge = judgeService.save(judgeForUpdate);
        model.addAttribute("judge",updatedJudge);
        return "judgeOverview";
    }

    @GetMapping("/administrator/searchJudges/judge/{id}")
    public String getJudge(Model model, @PathVariable String id){
        Judge judge = judgeService.findByJudgeid(Long.valueOf(id));
        List<Hackathon> hackathons = new ArrayList<>();
        List<Judgehackathon> judgehackathons = judgehackathonService.findAllByJudge_Judgeid(judge.getJudgeid());

        for (Judgehackathon jh :
                judgehackathons) {
            hackathons.add(hackathonService.findByHackathonid(jh.getHackathon().getHackathonid()));
        }

        model.addAttribute("judge",judge);
        model.addAttribute("hackathons",hackathons);


        return "judgeOverview";
    }

    // za delete mora ona fora sa message!
    // uraditi   i update

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
