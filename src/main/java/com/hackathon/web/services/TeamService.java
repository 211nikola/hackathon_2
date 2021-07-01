package com.hackathon.web.services;

import com.hackathon.web.domain.Team;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamService {
    Team save(Team team);
    void delete(Team team);
    List<Team> findAll();
    List<Team> findAllByHackathon_Hackathonid(Long hackathonID);
    Team findByTeamID(Long teamid);
    List<Team> findAllByMentor_MentorID(Long mentorid);
    void deleteById(Long aLong);
    List<Team> findAllByNameContains(String search);
    Team insertTeam(@Param("name") String name, @Param("adminID") Long adminID,
                    @Param("hackathonID") Long hackathonID, @Param("mentorID") Long mentorID);





}
