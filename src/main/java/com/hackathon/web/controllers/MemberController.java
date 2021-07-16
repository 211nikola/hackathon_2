package com.hackathon.web.controllers;

import com.hackathon.web.domain.Member;
import com.hackathon.web.domain.Mentor;
import com.hackathon.web.domain.Team;
import com.hackathon.web.services.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/administrator/search/members")
    public String searchMembers(Model model){
        model.addAttribute("members",memberService.findAll());
        return "searchMembers";
    }

    @PostMapping("/administrator/search/members")
    public String searchMembers(Model model,@RequestParam String search){
        model.addAttribute("members",memberService.findAllByNameContains(search));
        return "searchMembers";
    }

    @PostMapping("/administrator/delete/member/{id}")
    public String deleteMember(Model model, @PathVariable String id){

        memberService.deleteById(Long.valueOf(id));
        model.addAttribute("members",memberService.findAll());
        model.addAttribute("message_error","Member deleted successfully.");
        return "searchMembers";
    }

    @GetMapping("/administrator/searchMembers/member/{id}")
    public String getMember(Model model,
                            @PathVariable String id
    ) {

        Member member = memberService.findById_MemberID(Long.valueOf(id));
        model.addAttribute("member", member);


        return "member";
    }
}
