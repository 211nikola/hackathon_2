package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Member;
import com.hackathon.web.repositories.MemberRepository;
import com.hackathon.web.services.MemberService;

public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    @Override
    public void delete(Member member) {
        repository.delete(member);

    }

    @Override
    public Member delete(String search) {
        return repository.deleteByNameIsContaining(search);
    }


}
