package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HackathonRepository extends CrudRepository<Hackathon,Long>{

    List<Hackathon> findAllByNameContains(String search);
    //List<Hackathon> findHackathonsByJudgehackathonsInAndJudgehackathonsContains(Judge judge);

    @Override
    <S extends Hackathon> S save(S entity);

    @Override
    void delete(Hackathon entity);

    //Hackathon findTopByOOrderByHackathonIDDesc();

}
