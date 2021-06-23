package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface JudgehackathonRepository extends CrudRepository<Judgehackathon,Long> {

    @Override
    <S extends Judgehackathon> S save(S entity);

    List<Judgehackathon> findAll();

    @Modifying
    @Transactional
    @Query("delete from Judgehackathon jh " +
            "where  jh.id.judgeid=?1")
    void deleteJudgehackathonByJudge_Judgeid(Long judgeid);

    @Modifying
    @Query("delete from Judgehackathon jh " +
            "where jh.id.administratorid=?1 and jh.id.judgeid=?2 and jh.id.hackathonid=?3")
    void
    deleteJudgehackathonByAdministrator_AdministratoridAndJudge_JudgeidAndHackathon_Hackathonid
            (Long adminID,Long judgeID,Long hackathonID);

    @Transactional
    @Modifying
    @Override
    void delete(Judgehackathon entity);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long aLong);

    @Query("SELECT jh FROM Judgehackathon jh join Judge j on jh.judge.judgeid= j.judgeid WHERE jh.hackathon.hackathonid = ?1")
    List<Judgehackathon> findAllByHackathon_Hackathonid(Long hID);



    List<Judgehackathon> findAllByJudge_Judgeid(Long hID);
    //List<Judgehackathon> findAllByHackathon_HackathonidAndIdIs(Long id);
    List<Judgehackathon> findJudgehackathonsByHackathon_Hackathonid(Long id);
    Set<Judgehackathon> findJudgehackathonsByHackathon(Hackathon h);
    Judgehackathon findByJudge_Judgeid(Long id);
    Judgehackathon findByJudge(Judge judge);








}
