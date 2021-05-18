package com.hackathon.web.repositories;

import com.hackathon.web.domain.Mentor;
import com.hackathon.web.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    TeamRepository teamRepository;

    @Test
    void findAllByNameContains() {
    }

    @Test
    void findByTeamID() {
    }

    @Test
    void findAllByMentor_MentorID() {
        Mentor mentor = mentorRepository.findByMentorID(1L);
        List<Team> teams = teamRepository.findAllByMentor_MentorID(mentor.getMentorID());
        for (Team team: teams
             ) {
            System.out.println(team.getTeamID());
        }
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void deleteById() {
    }
}