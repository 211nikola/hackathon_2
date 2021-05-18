package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judgehackathon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface JudgehackathonRepository extends CrudRepository<Judgehackathon,Long> {

    @Override
    <S extends Judgehackathon> S save(S entity);

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








}
