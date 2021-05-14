package com.hackathon.web.services;

import com.hackathon.web.domain.Member;

public interface MemberService {

    Member save(Member member);
    void delete(Member member);
    Member delete(String search);

}
