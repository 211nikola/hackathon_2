package com.hackathon.web.services;

import com.hackathon.web.domain.Team;

public interface TeamService {
    Team save(Team team);
    void delete(Team team);

}
