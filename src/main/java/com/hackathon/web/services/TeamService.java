package com.hackathon.web.services;

import com.hackathon.web.domain.Team;

import java.util.List;

public interface TeamService {
    Team save(Team team);
    void delete(Team team);
    List<Team> findAll();


}
