package com.hackathon.web.repositories;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class HackathonRepositoryTest {
    @Autowired
    HackathonRepository repository;
    @Autowired
    JudgeRepository judgeRepository;
    @Autowired
    JudgehackathonRepository judgehackathonRepository;
    @Autowired
    AdministratorRepository administratorRepository;

    @Test
    void findAllByNameContains() {
        List<Hackathon> hackathons = repository.findAllByNameContains("hak");
        for (Hackathon hackathon :
                hackathons) {
            System.out.println(hackathon.getName());
        }
    }


    @Test
    void save() {

        Administrator a =
                administratorRepository.
                        findAdministratorByUsernameAndPassword
                                ("test","test");

        Hackathon h = new Hackathon();
        h.setAdministrator(a);
        h.setDate(new Date());
        h.setName("hakaton4");


        Hackathon save = repository.save(h);


    }

    @Test
    void delete() {




    }

    @Test
    void findHackathonsByHackathonidIsInAndJudgehackathonsContains() {
/*
        Judge judge = judgeRepository.findByJudgeid(2L);
        List<Hackathon> hackathons =
                repository.findHackathonsByJudgehackathonsIs(judge.getJudgehackathons());
        for (Hackathon h:hackathons
             ) {
            System.out.println(h.getName());
        }


/*
        System.out.println(judge.getName());

        Administrator administrator =
                administratorRepository.
                        findAdministratorByUsernameAndPassword
                                ("test","test");




        Hackathon hackathon = repository.findHackathonByNameContains("hakaton3");

        List<Judgehackathon> judgehackathons = new ArrayList<>();

        Judgehackathon judgehackathon= new Judgehackathon();

        judgehackathon.setHackathon(hackathon);
        judgehackathon.setAdministrator(administrator);
        judgehackathon.setJudge(judge);

        judgehackathons.add(judgehackathon);
        System.out.println(judgehackathon.getJudge().getName());
        System.out.println(judgehackathon.getAdministrator().getName());
        System.out.println(judgehackathon.getHackathon().getName());

        List<Hackathon> hackathonList = new ArrayList<>();
/*
        for (Judgehackathon j:judgehackathons
             ) {
            Hackathon h  = new Hackathon();
            System.out.println(j.getHackathon().getHackathonid());
            System.out.println(j.getJudge().getJudgeid());
            h = repository.findHackathonByHackathonidIs(j.getHackathon().getHackathonid());

            hackathonList.add(h);
            System.out.println(h.getHackathonid());

        }


*/
    }

    @Test
    void findHackathonByNameContains() {
        Hackathon hackathon = repository.findHackathonByNameContains("test");
        System.out.println(hackathon.getName());
    }

    @Test
    void findAll() {
        List<Hackathon> hackathons = repository.findAll();
        for (Hackathon h :
                hackathons) {
            System.out.println(h.getName());
        }
    }


    @Test
    void deleteByHackathonid() {
        Hackathon hackathon = repository.findByHackathonid(3L);
        System.out.println(hackathon.getName());
        Hackathon hackathonDeleted =  repository.deleteByHackathonid(hackathon.getHackathonid());
    }
}