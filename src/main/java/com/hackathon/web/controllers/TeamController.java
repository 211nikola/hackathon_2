package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.domainDTO.MarkDTO;
import com.hackathon.web.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
public class TeamController {

    private final TeamService teamService;
    private final HackathonService hackathonService;
    private final MarkService markService;
    private final JudgeService judgeService;
    private final MemberService memberService;
    private final MentorService mentorService;

    public TeamController(TeamService teamService, HackathonService hackathonService, MarkService markService, JudgeService judgeService, MemberService memberService, MentorService mentorService) {
        this.teamService = teamService;
        this.hackathonService = hackathonService;
        this.markService = markService;
        this.judgeService = judgeService;
        this.memberService = memberService;
        this.mentorService = mentorService;
    }

    @PostMapping("/judge/findTeams/team/{id}/saveMark")
    public String saveMark(@Valid @ModelAttribute MarkDTO markDTO,
                           @PathVariable("id") String id,
                           HttpServletRequest request,
                           Model model, BindingResult bindingResult) {
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
                new MarkId(0L, teamid),
                markDTO.getDesign(),
                markDTO.getEfficiency()
                , markDTO.getComment()
                , markDTO.getComplexity()
                , loggedInJudge
                , team);

        Mark savedMark = markService.save(m);

        model.addAttribute("mark", savedMark);

        return "teamMark";


    }

    @GetMapping("/judge/findTeams/team/{id}")
    public String showTeamMarks(@PathVariable String id,
                                HttpServletRequest request,
                                Model model) {

        //String name = httpServletRequest.getUserPrincipal().getName();
        //System.out.println(name);

        //Long judgeid = judgeService.findByJudgeid(Long.valueOf(2)).getJudgeid();

        Judge loggedInJudge = (Judge) request.getSession().getAttribute("user");
        Long judgeid = loggedInJudge.getJudgeid();

        Long teamid = Long.valueOf(id);
        Team team = teamService.findByTeamID(teamid);


        Mark mark = markService.findByJudge_JudgeidAndTeamTeamID(judgeid, teamid);


        if (mark != null) {
            model.addAttribute("mark", mark);
        } else {
            model.addAttribute("markDTO", new MarkDTO());
            model.addAttribute("mark", new Mark());
            model.addAttribute("team", team);
        }


        return "teamMark";
    }

    @GetMapping(value = "/judge")
    public String findTeamsForHackathon(@RequestParam String hackathon, Model model) {

        //Hackathon h = hackathonService.findByHackathonid(Long.valueOf(hackathon));
        List<Team> teams = teamService.findAllByHackathon_Hackathonid(Long.valueOf(hackathon));
        model.addAttribute("teams", teams);

        return "teamsInHackathon";

    }

    @GetMapping("/administrator/teams")
    public String searchTeams() {

        return "searchTeams";
    }

    @PostMapping("/administrator/searchTeams")
    public String searchTeams(Model model, @RequestParam String search) {
        model.addAttribute("teams", teamService.findAllByNameContains(search));
        return "searchTeams";
    }



/*
    add team memebers view
    remove team member
    Get and Post clean up for Controllers (URL names, models etc.)
    Marko Ivanovic13:03
    add team member controller method
    input validation
    BindingResults

 */

    @GetMapping("/administrator/searchTeams/team/{id}")
    public String getTeam(Model model,
                          @PathVariable String id
    ) {

        Team team = teamService.findByTeamID(Long.valueOf(id));
        model.addAttribute("team", team);
        List<Member> members = new ArrayList<>();
        members = memberService.findAllByTeam_TeamID(Long.valueOf(id));
        model.addAttribute("members", members);

        return "team";
    }

    @PostMapping("/administrator/delete/team/{id}")
    public String deleteTeam(Model model,
                               @PathVariable String id){

        Team team = teamService.findByTeamID(Long.valueOf(id));

        List<Member> members = memberService.findAllByTeam_TeamID(team.getTeamID());

        if(!members.isEmpty()){
            for (Member member:
                 members) {
                memberService.deleteById(member.getId().getMemberID());
            }
        }

        teamService.deleteById(team.getTeamID());

        return "searchTeams";
    }

    @GetMapping("/administrator/addTeam")
    public String addTeam(Model model){

        model.addAttribute("team",new Team());
        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());
        model.addAttribute("member",new Member());
        return "team";
    }


    // smisliti kako kreirati nove clanove na formi i dadati u tim i zatim sve sacuvati
    //sa jedne stranice addTeam

    @PostMapping("/administrator/administrator/updateTeam")
    public String addMember(Model model,final Team team,@RequestParam @Valid Member member) {
        Member memberToAdd = member;
        Random random = new Random();
        memberToAdd.getId().setMemberID((long) (random.nextInt(-1) -100));

        team.getMembers().add(memberToAdd);
        model.addAttribute("team",team);
        model.addAttribute("members",team.getMembers());
        return "updateTeam";
    }

    @GetMapping("/administrator/administrator/updateTeam")
    public String updateTeam(Model model,@RequestParam Team team){
        model.addAttribute("team",team);
        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());
        model.addAttribute("members",team.getMembers());
     return "updateTeam";
    }
    @RequestMapping(value = "/administrator/updateTeam/", params = {
            "removeMember" }, method = RequestMethod.POST)
    public String removeRow(final Team team, final HttpServletRequest req,
                            Model model) {

        final Long memberID = Long.valueOf(req.getParameter("removeMember"));

        for (Member member : team.getMembers()) {
            if (member.getId().getMemberID().equals(memberID) ){
                team.getMembers().remove(member);
                break;
            }
        }

        if (memberID > 0)
            memberService.deleteById(memberID);

        model.addAttribute("team",team);
        model.addAttribute("members",team.getMembers());
        return "updateTeam";
    }

    @PostMapping ("/administrator/addTeam/addMember")
    public String addMemberToTeam(){

        return "redirect:/team";
    }
    @PostMapping("/administrator/addTeam")
    public String saveTeam(Model model,
                           BindingResult bindingResult,
                           @Valid
                           @RequestParam Team team,
                           @Valid
                           @RequestParam(required = false) Member member,
                           HttpServletRequest request){

        Administrator administrator = (Administrator) request.getSession().getAttribute("user");



        return "team";
    }
}
