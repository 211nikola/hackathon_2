package com.hackathon.web.services;

import com.hackathon.web.domain.Judge;

public interface JudgeService {

    Judge save(Judge judge);
    void delete(Judge judge);
    Judge findByUsernameAndPassword(String username,String password);
    Judge findByJudgeid(Long id);

}
