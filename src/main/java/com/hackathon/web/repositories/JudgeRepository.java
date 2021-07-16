package com.hackathon.web.repositories;

import com.hackathon.web.domain.Judge;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JudgeRepository extends CrudRepository<Judge,Long> {
    List<Judge> findAllByNameContains(String search);
    Judge findByNameContains(String search);
    List<Judge> findAll();
    Judge findByJudgeid(Long id);
    List<Judge> findAllByJudgeid(Long id);
    Judge findByUsernameAndPassword(String username,String password);
    List<Judge> findAllByJudgeidIsNotIn(List<Long> ids);

    @Transactional
    @Modifying

    Judge deleteByJudgeid(Long id);

    @Override
    <S extends Judge> S save(S entity);

    @Override
    void delete(Judge entity);

    @Transactional
    @Modifying
    @Override
    void deleteById(Long aLong);
}
