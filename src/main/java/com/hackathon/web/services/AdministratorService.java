package com.hackathon.web.services;

import com.hackathon.web.domain.Administrator;

import java.util.List;

public interface AdministratorService {





    List<Administrator> findAdministratorsByName();
    Administrator saveAdministrator(Administrator administrator);

}
