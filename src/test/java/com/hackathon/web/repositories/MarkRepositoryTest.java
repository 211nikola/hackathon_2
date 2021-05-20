package com.hackathon.web.repositories;

import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Mark;
import com.hackathon.web.domain.MarkId;
import com.hackathon.web.domain.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
        Judge judge = judgeRepository.findByJudgeid(1L);
        System.out.println(judge.getJudgeid());
        Team team = teamRepository.findByTeamID(7L);
       // judge.toString();
       // team.toString();
        Mark mark = new Mark(
                new MarkId
                        (2L,team.getTeamID()),5,5,"ggg",5,judge,team);
        mark.setJudge(judge);
        markRepository.save(mark);

    }

    @Test
    void deleteById() {
        markRepository.deleteById(new MarkId(2L,7L));
    }

    @Test
    void deleteAll() {
    }

    @Test
    @Rollback(value = false)
    void delete() {
        Mark mark = markRepository.findByJudge_JudgeidAndTeamTeamID(1l,7l);
        markRepository.save(mark);

        markRepository.delete(mark);
    }

    @Test
    void deleteMarkByIdIs() {
        markRepository.deleteMarkByIdIs(2l);
    }
}