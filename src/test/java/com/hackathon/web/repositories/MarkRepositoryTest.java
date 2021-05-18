package com.hackathon.web.repositories;

import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Mark;
import com.hackathon.web.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarkRepositoryTest {

    @Autowired
    MarkRepository markRepository;
    @Autowired
    JudgeRepository judgeRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    void findByJudge_JudgeidAndTeamTeamID() {
        Judge judge = judgeRepository.findByJudgeid(2L);
        Team team = teamRepository.findByTeamID(3L);
        Mark mark = markRepository.findByJudge_JudgeidAndTeamTeamID(judge.getJudgeid(), team.getTeamID());
        System.out.println(mark.getComment());
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void delete() {
    }
}