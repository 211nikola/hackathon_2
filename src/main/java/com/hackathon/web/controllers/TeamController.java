package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.domainDTO.MarkDTO;
import com.hackathon.web.services.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.LongType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

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

    @GetMapping("/administrator/searchTeams")
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


    @RequestMapping(value="/administrator/addTeam", params = {"removeMember"})
    public String removeMember(@Valid @ModelAttribute Team team, BindingResult result,
                               Model model,
                               HttpServletRequest request) {

        final Long memberID = Long.valueOf(request.getParameter("removeMember"));

        for (Member member : team.getMembers()) {
            if (member.getId().getMemberID().equals(memberID)) {
                team.getMembers().remove(member);
                break;
            }
        }

        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());

        return "team";
    }
    @RequestMapping(value="/administrator/addTeam", params = {"addMember"})
    public String addMember(@Valid @ModelAttribute Team team, BindingResult result,Model model,HttpServletRequest request) {

        System.out.println("add new member");
        team.setHackathon(team.getHackathon());
        team.setMentor(team.getMentor());

        if(team.getMembers() == null){
            team.setMembers(new ArrayList<>());
        }

        if(team.getAdministrator() == null){
            Administrator administrator = (Administrator) request.getSession().getAttribute("user");
            team.setAdministrator(administrator);
        }


        Member member = new Member();
        member.setId(new MemberId());
        member.setTeam(team);

        member.getId().setMemberID(randomNegativeId());
        team.getMembers().add(member);



        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());




        return "team";
    }

    @GetMapping("/administrator/addTeam")
    public String addTeam(Model model){

        Team team = new Team();

        team.setTeamID(-1L);



        model.addAttribute("team",team);
        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());

        return "team";
    }


    @PostMapping("/administrator/updateTeam")
    public String addMember(Model model,@Valid @RequestParam(required = false) Team team,
                            @ModelAttribute Team team1,@RequestParam(required = false) @Valid Member member
    ) {
        Member memberToAdd = member;

        Team teamToUpdate = new Team();
        teamToUpdate.setName("sadasdasd");
        teamToUpdate.setTeamID(-1L);
        teamToUpdate.setMembers(new ArrayList<>());
        teamToUpdate.getMembers().add(new Member());

        List<Member> members = new ArrayList<>();
        teamToUpdate.getMembers().iterator().forEachRemaining(members::add);


        model.addAttribute("team",teamToUpdate);
        model.addAttribute("members",teamToUpdate.getMembers());
        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());
        model.addAttribute("members",teamToUpdate.getMembers());

        return "team";
    }




    @PostMapping("/administrator/addTeam")
    public String saveTeam(Model model,
                          @NotNull @Valid
                           @ModelAttribute Team team,
                           RedirectAttributes atts,
                           BindingResult bindingResult,
                           HttpServletRequest request){

        if(team.getAdministrator() == null){
            Administrator administrator = (Administrator) request.getSession().getAttribute("user");
            team.setAdministrator(administrator);
        }


        Team teamForSave = new Team(0L,team.getName(),new ArrayList<>(),new HashSet<>(), team.getMentor(), team.getAdministrator(), team.getHackathon());



        Team savedTeam = teamService.save(teamForSave);
        savedTeam.setMembers(team.getMembers());


        if(team.getMembers() != null ) {
            for (Member m :
                    team.getMembers()) {

                m.setTeam(savedTeam);
                m.setId(new MemberId(Long.valueOf(randomMemberId()),savedTeam.getTeamID()));

            }
        }
        teamService.save(savedTeam);

        model.addAttribute("members",team.getMembers());
        model.addAttribute("team",savedTeam);
        model.addAttribute("message", "Team created successfully");

        return "team";
    }

    public Long randomNegativeId() {
        Random rand = new Random();
        return -1 * ((long) rand.nextInt(1000));
    }
    public Long randomMemberId() {
        Random rand = new Random();
        return (long) (rand.nextInt(1000) + 1);
    }
}
