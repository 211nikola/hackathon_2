package com.hackathon.web.services;

import com.hackathon.web.domain.Judgehackathon;

import java.util.List;

public interface JudgehackathonService {

    Judgehackathon save(Judgehackathon judgehackathon);
    void delete(Judgehackathon judgehackathon);
    List<Judgehackathon> findAllByHackathon_Hackathonid(Long hID);

    List<Judgehackathon> findAllByJudge_Judgeid(Long hID);

}
