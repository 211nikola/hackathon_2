package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JudgeRepositoryTest {

    @Autowired
    JudgeRepository judgeRepository;

    @Autowired
    JudgehackathonRepository judgehackathonRepository;

    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void findAllByNameContains() {
    }

    @Test
    void findByNameContains() {
    }

    @Test
    void findByJudgeid() {
    }

    @Test
    void findAllByJudgeid() {
        Hackathon h = hackathonRepository.findHackathonByNameContains("hakaton3");
        List<Judgehackathon> judgehackathons = judgehackathonRepository.findJudgehackathonsByHackathon_Hackathonid(h.getHackathonid());
        List<Judge> judges = new ArrayList<>();

        for (Judgehackathon judgehackathon:judgehackathons
             ) {
            Judge judge = judgeRepository.findByJudgeid(judgehackathon.getJudge().getJudgeid());
            judges.add(judge);
            System.out.println(judge.getJudgeid());
            System.out.println(judge.getName());


         }
        }


    @Test
    void saveUpdate() {
        Judge judge = judgeRepository.findByJudgeid(3L);
        System.out.println(judge.getName());
        judge.setName("updateName");
        judgeRepository.save(judge);
        System.out.println(judgeRepository.findByJudgeid(3L).getName());
    }



    @Test
    void delete() {
        Judge judge = judgeRepository.findByJudgeid(3L);
        judgeRepository.delete(judge);
    }

    @Test
    void deleteById() {
    }
}