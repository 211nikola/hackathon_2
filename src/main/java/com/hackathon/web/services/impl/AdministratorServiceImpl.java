package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.repositories.AdministratorRepository;
import com.hackathon.web.services.AdministratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository repository;

    @Override
    public List<Administrator> findAdministratorsByName() {
        System.out.println(repository.findAdministratorsByName("Nikola"));
        return repository.findAdministratorsByName("Nikola");
    }

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        return repository.save(administrator);

    }

    @Override
    public Administrator findAdministratorByUsernameAndPassword(String username, String password) {
        return repository.findAdministratorByUsernameAndPassword(username, password);
    }


}
