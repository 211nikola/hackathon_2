package com.hackathon.web.repositories;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.domain.Hackathon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdministratorRepositoryIntegrationTest {

    @Autowired
    AdministratorRepository administratorRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    public void whenFindByName_thenReturnAdministrator(){

        Administrator administrator = new Administrator();

        administrator.setAdminType("default");
        administrator.setMail("xxx@mail.com");
        administrator.setName("222222");
        administrator.setLastName("22222");
        administrator.setUsername("test");
        administrator.setPassword("test");

        Administrator savedAdministrator = administratorRepository.save(administrator);

        Hackathon hackathon = new Hackathon(1L,
                new Date(),
                "hakaton3",
                savedAdministrator,
                null,
                null);

        Hackathon savedHackathon = hackathonRepository.save(hackathon);
        assertNotNull(savedHackathon);
        assertThat(savedHackathon.getAdministrator().getAdministratorid()).isEqualTo(savedAdministrator.getAdministratorid());
        assertEquals(savedAdministrator.getAdministratorid(),savedHackathon.getAdministrator().getAdministratorid());

    }
}
