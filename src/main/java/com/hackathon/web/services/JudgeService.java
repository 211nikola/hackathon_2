package com.hackathon.web.services;

import com.hackathon.web.domain.Judge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JudgeService {

    Judge save(Judge judge);
    void delete(Judge judge);
    Judge findByUsernameAndPassword(String username,String password);
    Judge findByJudgeid(Long id);
    List<Judge> findAllByJudgeidIsNotIn(List<Long> ids);
    Judge findByNameContains(String search);
    List<Judge> findAllByNameContains(String search);
    void deleteById(Long aLong);






}
