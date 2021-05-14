package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.repositories.HackathonRepository;
import com.hackathon.web.services.HackathonService;

public class HackathonServiceImpl implements HackathonService {

    private final HackathonRepository repository;

    public HackathonServiceImpl(HackathonRepository repository) {
        this.repository = repository;
    }


    @Override
    public Hackathon save(Hackathon hackathon) {
        return repository.save(hackathon);
    }

    @Override
    public void delete(Hackathon hackathon) {
         repository.delete(hackathon);
    }


}
