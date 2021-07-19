package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Judge;
import com.hackathon.web.repositories.JudgeRepository;
import com.hackathon.web.services.JudgeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JudgeServiceImpl implements JudgeService {
    private final JudgeRepository repository;


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

    @Override
    public Judge findByJudgeid(Long id) {
        return repository.findByJudgeid(id);
    }

    @Override
    public List<Judge> findAllByJudgeidIsNotIn(List<Long> ids) {
        return repository.findAllByJudgeidIsNotIn(ids);
    }



    @Override
    public Judge findByNameContains(String search) {
        return repository.findByNameContains(search);
    }

    @Override
    public List<Judge> findAllByNameContains(String search) {
        return repository.findAllByNameContains(search);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public List<Judge> findAll() {
        return repository.findAll();
    }

    @Override
    public Judge findByUsername(String username) {
        return repository.findByUsername(username);
    }


}
