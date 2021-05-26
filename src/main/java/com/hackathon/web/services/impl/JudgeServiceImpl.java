package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Judge;
import com.hackathon.web.repositories.JudgeRepository;
import com.hackathon.web.services.JudgeService;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImpl implements JudgeService {
    private final JudgeRepository repository;

    public JudgeServiceImpl(JudgeRepository repository) {
        this.repository = repository;
    }


    @Override
    public Judge save(Judge judge) {
        return repository.save(judge);
    }

    @Override
    public void delete(Judge judge) {
        repository.delete(judge);
    }

    @Override
    public Judge findByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

}
