package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Team;
import com.hackathon.web.repositories.TeamRepository;
import com.hackathon.web.services.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository repository;

    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }


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
}
