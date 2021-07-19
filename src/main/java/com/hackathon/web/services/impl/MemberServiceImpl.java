package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Member;
import com.hackathon.web.repositories.MemberRepository;
import com.hackathon.web.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

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

    @Override
    public List<Member> findAllByTeam_TeamID(Long teamid) {
        return repository.findAllByTeam_TeamID(teamid);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Member> findAllByNameContains(String search) {
        return repository.findAllByNameContains(search);
    }

    @Override
    public Member findById_MemberID(Long id) {
        return repository.findById_MemberID(id);
    }


}
