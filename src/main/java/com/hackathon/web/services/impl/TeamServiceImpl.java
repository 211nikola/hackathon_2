package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Team;
import com.hackathon.web.repositories.TeamRepository;
import com.hackathon.web.services.TeamService;

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
}