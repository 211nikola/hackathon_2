package com.hackathon.web.repositories;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Mentor;
import com.hackathon.web.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    MentorRepository mentorRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    HackathonRepository hackathonRepository;

    @Autowired
    AdministratorRepository administratorRepository;

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

        Administrator administrator = administratorRepository.findByAdministratorid(1L);
        Mentor mentor = mentorRepository.findByMentorID(8L);
        Hackathon hackathon = hackathonRepository.findByHackathonid(5L);

        Team team = new Team(0L,"tim test",new ArrayList<>(),new HashSet<>(),mentor,administrator,hackathon);
        Team savedTeam = teamRepository.save(team);
        System.out.println(savedTeam.getTeamID());
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