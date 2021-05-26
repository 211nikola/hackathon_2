package com.hackathon.web.services;

import com.hackathon.web.domain.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdministratorService {





    List<Administrator> findAdministratorsByName();
    Administrator saveAdministrator(Administrator administrator);
    Administrator findAdministratorByUsernameAndPassword(String username,String password);

}
