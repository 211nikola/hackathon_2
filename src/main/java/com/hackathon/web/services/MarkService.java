package com.hackathon.web.services;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.domain.MarkId;

import java.util.List;

public interface MarkService {
    Mark save(Mark mark);
    void delete(Mark mark);
    Mark findByJudge_JudgeidAndTeamTeamID(Long judgeid,Long teamiD);
    void deleteMarkByIdIs(Long aLong);
    List<Mark> findAll();
    void deleteById(Long aLong);
    void deleteByJudge_Judgeid(Long id);





}
