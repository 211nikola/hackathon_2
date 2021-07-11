package com.hackathon.web.controllers;

import com.hackathon.web.domain.*;
import com.hackathon.web.domainDTO.MarkDTO;
import com.hackathon.web.services.*;
import com.hackathon.web.utils.RandomIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        model.addAttribute("message","You successfully marked this team.");

        return "teamMark";


    }

    @GetMapping("/judge/findTeams/team/{id}")
    public String showTeamMarks(@PathVariable String id,
                                HttpServletRequest request,
                                Model model) {

        Judge loggedInJudge = (Judge) request.getSession().getAttribute("user");
        Long judgeid = loggedInJudge.getJudgeid();

        Long teamid = Long.valueOf(id);
        Team team = teamService.findByTeamID(teamid);


        Mark mark = markService.findByJudge_JudgeidAndTeamTeamID(judgeid, teamid);


        if (mark != null) {
            model.addAttribute("mark", mark);
            model.addAttribute("message","You have already marked this team.");
        } else {
            model.addAttribute("markDTO", new MarkDTO());
            model.addAttribute("mark", new Mark());
            model.addAttribute("team", team);
        }

     return "teamMark";

    }

    @PostMapping (value = "/judge/teams")
    public String findTeamsForHackathon(@RequestParam String hackathon, Model model, HttpSession session) {

        List<Team> teams = teamService.findAllByHackathon_Hackathonid(Long.valueOf(hackathon));
        Hackathon h = hackathonService.findByHackathonid(Long.valueOf(hackathon));

        model.addAttribute("teams", teams);
        session.setAttribute("hackathon",h);

        return "teamsInHackathon";

    }

    @GetMapping("/judge/teams")
    public String judge_teams(Model model,HttpServletRequest request){

        List<Team> teams = new ArrayList<>();
        if((Hackathon) request.getSession().getAttribute("hackathon") != null){
            Hackathon hackathon = (Hackathon) request.getSession().getAttribute("hackathon");
            teams = teamService.findAllByHackathon_Hackathonid(hackathon.getHackathonid());
        }

        model.addAttribute("teams",teams);

        return "teamsInHackathon";
    }

    @GetMapping("/administrator/search/teams")
    public String searchTeams() {

        return "searchTeams";
    }

    @PostMapping("/administrator/search/teams")
    public String searchTeams(Model model, @RequestParam String search) {
        model.addAttribute("teams", teamService.findAllByNameContains(search));
        return "searchTeams";
    }



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
        List<Mark> marks = markService.findAllByTeam_TeamID(team.getTeamID());

        if(!members.isEmpty()){
            for (Member member:
                 members) {
                memberService.deleteById(member.getId().getMemberID());
            }
        }

        if(!marks.isEmpty()){
            for (Mark mark :
                    marks) {
                markService.deleteMarkByIdIs(mark.getId().getMarkid());
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

        member.getId().setMemberID(RandomIdGenerator.randomNegativeId());
        team.getMembers().add(member);

        model.addAttribute("hackathons",hackathonService.findAll());
        model.addAttribute("mentors",mentorService.findAll());

        return "team";
    }

    @GetMapping("/administrator/create/team")
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
                           BindingResult bindingResult,
                           HttpServletRequest request){

        if (bindingResult.hasErrors()) {
            log.warn("CANNOT SAVE:");
            bindingResult.getAllErrors().forEach(objectError -> {
                log.warn(objectError.toString());
            });
            Team t = new Team();

            team.setTeamID(-1L);
            model.addAttribute("team",t);
            model.addAttribute("hackathons",hackathonService.findAll());
            model.addAttribute("mentors",mentorService.findAll());
            return "team";
        }

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
                m.setId(new MemberId(RandomIdGenerator.randomMemberId(),savedTeam.getTeamID()));

            }
        }

        teamService.save(savedTeam);

        model.addAttribute("members",team.getMembers());
        model.addAttribute("team",savedTeam);
        model.addAttribute("message", "Team created successfully");

        return "team";
    }


}
