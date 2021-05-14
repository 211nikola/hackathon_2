package com.hackathon.web.repositories;

import com.hackathon.web.domain.Judgehackathon;
import org.springframework.data.repository.CrudRepository;

public interface JudgehackathonRepository extends CrudRepository<Judgehackathon,Long> {

    @Override
    <S extends Judgehackathon> S save(S entity);

    @Override
    void delete(Judgehackathon entity);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long aLong);


}
