package com.hackathon.web.controllers;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.domain.Mark;
import com.hackathon.web.services.HackathonService;
import com.hackathon.web.services.JudgeService;
import com.hackathon.web.services.JudgehackathonService;
import com.hackathon.web.services.MarkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class JudgeController {
    private final JudgeService judgeService;
    private final HackathonService hackathonService;
    private final JudgehackathonService judgehackathonService;
    private final MarkService markService;


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
       model.addAttribute("message_error","Judge deleted successfully.");


        return "judgeOverview";
    }

    @GetMapping("/administrator/create/judge")
    public String addJudge(Model model){
        model.addAttribute("judge",new Judge());
        return "judgeOverview";
    }

    @PostMapping("/administrator/addJudge")
    public String saveJudge(Model model,
                            @Valid
                            @ModelAttribute Judge judge,
                            BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "judgeOverview";
        }
        Judge savedJudge = judgeService.save(judge);
        model.addAttribute("judge",savedJudge);
        model.addAttribute("message","Judge created successfully.");
        return "judgeOverview";
    }


    @GetMapping("/administrator/search/judges")
    public String getSearchJudges(Model model){
        model.addAttribute("judges",judgeService.findAll());
        return "searchJudges";
    }

    @PostMapping("/administrator/search/judges")
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
        if (bindingResult.hasErrors()) {
            log.warn("CANNOT UPDATE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "judgeOverview";
        }
        Judge judge = judgeService.findByJudgeid(Long.valueOf(id));
        judgeForUpdate.setJudgeid(judge.getJudgeid());

        Judge updatedJudge = judgeService.save(judgeForUpdate);
        model.addAttribute("judge",updatedJudge);
        model.addAttribute("message","Judge updated successfully.");
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

    @GetMapping("/judge/login")
    public String redirectToJudgeLogin(){
        return "judgeLogin";
    }

    @GetMapping("/judge/marks")
    public String getMarks(Model model,HttpServletRequest request){

        Judge judge = (Judge) request.getSession().getAttribute("user_judge");
        List<Mark> marks = markService.findAllByJudge_Judgeid(judge.getJudgeid());
        model.addAttribute("marks",marks);

        return "marks";
    }

    @GetMapping("/judge")
    public String judge(Model model, HttpServletRequest request){

        Judge judge = (Judge) request.getSession().getAttribute("user_judge");
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
            return "judge" ;
        }

        return "judge";
    }

    @PostMapping("/judge")
    public String getJudge(
            @RequestParam String username
                            , @RequestParam String password,
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes){

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
            session.setAttribute("user_judge",judge);
            return "judge" ;
        }

        redirectAttributes.addFlashAttribute("message_error","ERROR");

        return "redirect:/judgeLogin";
    }

}
