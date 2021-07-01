package com.hackathon.web.repositories;

import com.hackathon.web.domain.Team;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Long> {

    List<Team> findAllByNameContains(String search);
    Team findByTeamID(Long teamid);
    List<Team> findAllByMentor_MentorID(Long mentorid);
    List<Team> findAll();
    List<Team> findAllByHackathon_Hackathonid(Long hackathonID);




    @Modifying
    @Query(
            value =
                    "insert into Team (name, adminID, hackathonID, mentorID) values (:name, :adminID, :hackathonID, :mentorID)",
            nativeQuery = true)
    Team insertTeam(@Param("name") String name, @Param("adminID") Long adminID,
                    @Param("hackathonID") Long hackathonID, @Param("mentorID") Long mentorID);

    @Transactional
    @Override
    <S extends Team> S save(S entity);

    @Override
    void delete(Team entity);

    @Override
    void deleteAll();





    //custom @Query for delete all members by team ID
    @Override
    @Transactional
    @Modifying
    @Query("delete from Team t where t.teamID=?1")
    void deleteById(Long aLong);
}
