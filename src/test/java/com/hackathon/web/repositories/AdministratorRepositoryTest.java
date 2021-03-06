package com.hackathon.web.repositories;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.domain.Hackathon;
import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.domain.Team;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdministratorRepositoryTest {

    @Autowired
    AdministratorRepository repository;

    @Autowired
    HackathonRepository hackathonRepository;



    @Test
    void deleteById() {

    }

    @Test
    void findAdministratorByAdministratorID() {
    }

    @Test
    void findAdministratorsByName() {
    }

    @Test
    void save() {

        Administrator administrator = new Administrator();

        administrator.setAdminType("default");
        administrator.setMail("xxx@mail.com");
        administrator.setName("222222");
        administrator.setLastName("22222");
        administrator.setUsername("test");
        administrator.setPassword("test");

        Administrator savedAdministrator = repository.save(administrator);

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

       // sam test da napravi entitete
       // ili @BeforaAll init metoda
        //ili @BeforeEach koji bi "resetovao" podatke

    }

    @Test
    void testDeleteById() {
        repository.deleteById(3L);
    }


    @Test
    void findAdministratorByUsernameAndPassword() {
        repository.findAdministratorByUsernameAndPassword("cc","cc");

    }

}