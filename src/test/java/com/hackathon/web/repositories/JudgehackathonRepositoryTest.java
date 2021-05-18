package com.hackathon.web.repositories;

import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import com.hackathon.web.domain.Judgehackathon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class JudgehackathonRepositoryTest {

    @Autowired
    JudgehackathonRepository judgehackathonRepository;
    @Autowired
    HackathonRepository hackathonRepository;
    @Autowired
    JudgeRepository judgeRepository;

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

    @Test
    void deleteById() {
    }




    @Test
    void findAllByHackathon_Hackathonid() {
        Hackathon h = hackathonRepository.findHackathonByNameContains("hakaton3");
        //Set<Judgehackathon> list = judgehackathonRepository.findJudgehackathonsByHackathon(h);
        System.out.println(h.getHackathonid());
        List<Judgehackathon> list = judgehackathonRepository.findAllByHackathon_Hackathonid(h.getHackathonid());
        for (Judgehackathon j: list
             ) {

            System.out.println(j.getAdministrator().getAdministratorid());
            System.out.println(j.getHackathon().getHackathonid());
            System.out.println(j.getJudge().getJudgeid() );


        }
    }

    @Test
    void findAllByJudge_Judgeid() {
        Judge judge = judgeRepository.findByJudgeid(3L);
        List<Judgehackathon> judgehackathons =
                judgehackathonRepository.findAllByJudge_Judgeid(judge.getJudgeid());
        for (Judgehackathon judgehackathon :
                judgehackathons) {
            System.out.println(hackathonRepository.findByHackathonid(judgehackathon.getHackathon().getHackathonid()).getName());
        }

    }
}