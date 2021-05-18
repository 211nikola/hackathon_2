package com.hackathon.web.repositories;

import com.hackathon.web.domain.Judge;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JudgeRepository extends CrudRepository<Judge,Long> {
    List<Judge> findAllByNameContains(String search);
    Judge findByNameContains(String search);
    Judge findByJudgeid(Long id);
    List<Judge> findAllByJudgeid(Long id);


    @Override
    <S extends Judge> S save(S entity);

    @Override
    void delete(Judge entity);

    @Override
    void deleteById(Long aLong);
}
