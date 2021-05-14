package com.hackathon.web.repositories;

import com.hackathon.web.domain.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

    @Override
    <S extends Team> S save(S entity);

    @Override
    void delete(Team entity);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long aLong);
}
