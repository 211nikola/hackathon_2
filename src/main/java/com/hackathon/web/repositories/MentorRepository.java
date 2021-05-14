package com.hackathon.web.repositories;

import com.hackathon.web.domain.Mentor;
import org.springframework.data.repository.CrudRepository;

public interface MentorRepository extends CrudRepository<Mentor,Long> {

    @Override
    <S extends Mentor> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();

    @Override
    void delete(Mentor entity);
}
