package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.repositories.JudgehackathonRepository;
import com.hackathon.web.services.JudgehackathonService;

public class JudgehackathonServiceImpl implements JudgehackathonService {
    private final JudgehackathonRepository repository;

    public JudgehackathonServiceImpl(JudgehackathonRepository repository) {
        this.repository = repository;
    }


    @Override
    public Judgehackathon save(Judgehackathon judgehackathon) {
        return repository.save(judgehackathon);
    }

    @Override
    public void delete(Judgehackathon judgehackathon) {
         repository.save(judgehackathon);
    }
}
