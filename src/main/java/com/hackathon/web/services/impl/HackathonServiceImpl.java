package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.repositories.HackathonRepository;
import com.hackathon.web.services.HackathonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<Hackathon> findAll() {
        return repository.findAll();
    }

    @Override
    public Hackathon findByHackathonid(Long id) {
        return repository.findByHackathonid(id);
    }

    @Override
    public List<Hackathon> findAllByNameContains(String search) {
        return repository.findAllByNameContains(search);
    }


}
