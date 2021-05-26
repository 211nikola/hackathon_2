package com.hackathon.web.repositories;

import com.hackathon.web.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Long> {

    List<Team> findAllByNameContains(String search);
    Team findByTeamID(Long teamid);
    List<Team> findAllByMentor_MentorID(Long mentorid);
    List<Team> findAll();

    @Override
    <S extends Team> S save(S entity);

    @Override
    void delete(Team entity);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long aLong);
}
