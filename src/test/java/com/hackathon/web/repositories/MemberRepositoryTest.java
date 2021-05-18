package com.hackathon.web.repositories;

import com.hackathon.web.domain.Member;
import com.hackathon.web.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    void findAllByTeam_TeamID() {
        Team team = teamRepository.findByTeamID(3L);
        List<Member> members = memberRepository.findAllByTeam_TeamID(team.getTeamID());
        for (Member m :
                members) {
            System.out.println(m.getName());
        }
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
        memberRepository.deleteById(7L);


    }

    @Test
    void deleteAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteByNameIsContaining() {
    }

    @Test
    void testFindAllByTeam_TeamID() {
    }

    @Test
    void testSave() {
    }

    @Test
    void testDeleteById() {
    }

    @Test
    void deleteById_MemberID() {

    }

    @Test
    void testDeleteAll() {
    }

    @Test
    void testDelete() {
    }

    @Test
    void testDeleteByNameIsContaining() {
    }
}