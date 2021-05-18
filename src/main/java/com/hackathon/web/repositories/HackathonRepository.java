package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface HackathonRepository extends CrudRepository<Hackathon,Long>{

    List<Hackathon> findAllByNameContains(String search);
    Hackathon findHackathonByNameContains(String search);
    Hackathon findByHackathonid(Long id);


    @Transactional
    @Modifying
    Hackathon deleteByHackathonid(Long id);


    List<Hackathon> findAll();

    //Hackathon findHackathonByHackathonidIs(Long hid);
    //List<Hackathon> findHackathonsByJudgehackathonsIsContaining(Long jID);
    //List<Hackathon> findHackathonsByJudgehackathonsIs(Set<Judgehackathon> param);
    //List<Hackathon> findHackathonsByHackathonidIsIn(Set<Judgehackathon> param);

    //List<Hackathon> findHackathonsByJudgehackathonsContains(Set<Judgehackathon> param);
    //Hackathon findHackathonByHackathonidIsAndJudgehackathonsContains(Long hID,Long jID);
   // Hackathon findHackathonByHackathonidIsInAndJudgehackathonsContains(Judgehackathon j,
                                                                    //   Long id);
    //List<Hackathon> findHackathonsByJudgehackathonsInAndJudgehackathonsContains(Judge judge);
/*
    List<Hackathon> findHackathonsByHackathonidIsInAndJudgehackathonsContains
            (
            Collection<Judgehackathon> judgehackathons,
            Long judgeID
            );
*/




    @Override
    <S extends Hackathon> S save(S entity);

    @Override
    void delete(Hackathon entity);

    //Hackathon findTopByOOrderByHackathonIDDesc();

}
