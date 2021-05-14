package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Administrator;
import com.hackathon.web.repositories.AdministratorRepository;
import com.hackathon.web.services.AdministratorService;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository repository;

    public AdministratorServiceImpl(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Administrator> findAdministratorsByName() {
        System.out.println(repository.findAdministratorsByName("Nikola"));
        return repository.findAdministratorsByName("Nikola");
    }

    @Override
    public Administrator saveAdministrator(Administrator administrator) {
        return repository.save(administrator);

    }
}