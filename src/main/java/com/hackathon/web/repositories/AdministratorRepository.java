package com.hackathon.web.repositories;

import com.hackathon.web.domain.Administrator;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Size;
import java.util.List;

public interface AdministratorRepository extends CrudRepository<Administrator,Long>{


    List<Administrator> findAdministratorByAdministratorid(Long administratorid);
    List<Administrator> findAdministratorsByName(@Size(min = 2, max = 255) String name);
    Administrator findAdministratorByUsernameAndPassword(String username,String password);


    @Override
    <S extends Administrator> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
