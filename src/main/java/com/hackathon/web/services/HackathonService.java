package com.hackathon.web.services;

import com.hackathon.web.domain.Hackathon;

import java.util.List;

public interface HackathonService {

    Hackathon save(Hackathon hackathon);
    void delete(Hackathon hackathon);
    List<Hackathon> findAll();
    Hackathon findByHackathonid(Long id);



}
