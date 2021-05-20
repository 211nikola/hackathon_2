package com.hackathon.web.repositories;

import com.hackathon.web.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    AdministratorRepository administratorRepository;

    @Test
    void save() {

        Hackathon hackathon = hackathonRepository.findByHackathonid(8L);
        Administrator administrator = administratorRepository.findByAdministratorid(12L);
        Judge judge = judgeRepository.findByJudgeid(1L);

        Judgehackathon judgehackathon = new Judgehackathon();

        judgehackathon.setId(new JudgehackathonId(judge.getJudgeid(),hackathon.getHackathonid(),administrator.getAdministratorid()));
        judgehackathon.setJudge(judge);
        judgehackathon.setAdministrator(administrator);
        judgehackathon.setHackathon(hackathon);

        judgehackathonRepository.save(judgehackathon);

        //System.out.println(judgehackathon);
    }

    @Test
    void delete() {
        /*
        judgehackathonRepository.delete(
                judgehackathonRepository.findByJudge(
                        judgeRepository.findByJudgeid(1L)));

         */
        Hackathon hackathon = hackathonRepository.findByHackathonid(8L);
        Administrator administrator = administratorRepository.findByAdministratorid(12L);
        Judge judge = judgeRepository.findByJudgeid(1L);
        Judgehackathon judgehackathon = new Judgehackathon();

        judgehackathon.setId(
                new JudgehackathonId(
                judge.getJudgeid(),
                hackathon.getHackathonid(),
                        administrator.getAdministratorid()));
        judgehackathon.setJudge(judge);
        judgehackathon.setAdministrator(administrator);
        judgehackathon.setHackathon(hackathon);

        System.out.println(judgehackathon.getJudge().getJudgeid());
        System.out.println(judgehackathon.getHackathon().getHackathonid());
        System.out.println(judgehackathon.getAdministrator().getAdministratorid());


        judgehackathonRepository.delete(judgehackathon);
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

    @Test
    //@Rollback(value = false)
    @Transactional
    void deleteJudgehackathonByAdministrator_AdministratoridAndJudge_JudgeidAndHackathon_Hackathonid() {
        judgehackathonRepository.
                deleteJudgehackathonByAdministrator_AdministratoridAndJudge_JudgeidAndHackathon_Hackathonid
                        (12L,1L,8L);
    }
}