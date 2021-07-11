package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.services.MemberService;
import com.hackathon.web.services.MentorService;
import com.hackathon.web.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@Controller
public class MentorController {
    private final MentorService mentorService;
    private final TeamService teamService;
    private final MemberService memberService;

    public MentorController(MentorService mentorService, TeamService teamService, MemberService memberService) {
        this.mentorService = mentorService;
        this.teamService = teamService;
        this.memberService = memberService;
    }

    @GetMapping("/mentor/findMentors")
    public List<Mentor> findMentors(Model model){
        model.addAttribute("mentors",mentorService.findAll());
        return mentorService.findAll();
    }

    @GetMapping("/administrator/create/mentor")
    public String addMentor(Model model){
        model.addAttribute("mentor",new Mentor());
        return "mentor";
    }

    @PostMapping("/administrator/addMentor")
    public String saveJudge(Model model,
                            @Valid @ModelAttribute Mentor mentor,
                            BindingResult bindingResult,
                            HttpServletRequest request){

        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            return "mentor";
        }

        Administrator administrator = (Administrator) request.getSession().getAttribute("user");
        System.out.println(administrator.getName());
        Set<Team> teams = new HashSet<>();
        Mentor m = new Mentor(
                0l
                ,mentor.getName()
                ,mentor.getMail()
                ,mentor.getLastName()
                ,mentor.getProfession()
                ,administrator
                ,teams);

        m.setAdministrator(administrator);
        Mentor savedMentor = mentorService.save(m);
        model.addAttribute("mentor",savedMentor);
        model.addAttribute("message","Mentor created successfully.");

        return "mentor";
    }

    @PostMapping("/administrator/delete/mentor/{id}")
    public String deleteMentor(Model model,
                               @PathVariable String id){

        Mentor mentor = mentorService.findByMentorID(Long.valueOf(id));
        List<Team> teams = teamService.findAllByMentor_MentorID(mentor.getMentorID());
        List<Member> members = new ArrayList<>();
        for (Team team:teams
             ) {
            members = memberService.findAllByTeam_TeamID(team.getTeamID());
            if (!members.isEmpty()){
                for (Member member: members
                     ) {
                    MemberId mID = member.getId();
                     Long memberID = mID.getMemberID();
                    memberService.deleteById(memberID);
                }
            }
            teamService.deleteById(team.getTeamID());
        }
        mentorService.deleteById(mentor.getMentorID());

        model.addAttribute("mentor",mentor);
        model.addAttribute("teams",teams);

        return "mentor";
    }

    @GetMapping("/administrator/searchMentors/mentor/{id}")
    public String getMentor(Model model,
                            @PathVariable String id
                            ){

        Mentor mentor = mentorService.findByMentorID(Long.valueOf(id));
        model.addAttribute("mentor",mentor);
        List<Team> teams = new ArrayList<>();
        teams = teamService.findAllByMentor_MentorID(mentor.getMentorID());
        model.addAttribute("teams",teams);

        return "mentor";
    }

    @GetMapping("administrator/search/mentors")
    public String searchMentors(Model model){
        model.addAttribute("mentor",new Mentor());
        return "searchMentors";
    }

    @PostMapping("/administrator/search/mentors")
    public String searchMentors(Model model,@RequestParam String search){
        model.addAttribute("mentors", mentorService.findAllByNameContains(search));
        return "searchMentors";
    }
}
