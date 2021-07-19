package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Team;
import com.hackathon.web.repositories.TeamRepository;
import com.hackathon.web.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;


    @Override
    public Team save(Team team) {
        return repository.save(team);
    }

    @Override
    public void delete(Team team) {
        repository.delete(team);
    }

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Team> findAllByHackathon_Hackathonid(Long hackathonID) {
        return repository.findAllByHackathon_Hackathonid(hackathonID);
    }

    @Override
    public Team findByTeamID(Long teamid) {
        return repository.findByTeamID(teamid);
    }

    @Override
    public List<Team> findAllByMentor_MentorID(Long mentorid) {
        return repository.findAllByMentor_MentorID(mentorid);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public List<Team> findAllByNameContains(String search) {
        return repository.findAllByNameContains(search);
    }

    @Override
    public Team insertTeam(String name, Long adminID, Long hackathonID, Long mentorID) {
        return repository.insertTeam(name,adminID,hackathonID,mentorID);
    }
}
