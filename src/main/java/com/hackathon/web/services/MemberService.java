package com.hackathon.web.services;

import com.hackathon.web.domain.Member;

import java.util.List;

public interface MemberService {

    Member save(Member member);
    void delete(Member member);
    Member delete(String search);
    List<Member> findAllByTeam_TeamID(Long teamid);
    void deleteById(Long aLong);
    List<Member> findAll();
    List<Member> findAllByNameContains(String search);
    Member findById_MemberID(Long id);



}
