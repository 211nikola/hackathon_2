package com.hackathon.web.repositories;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.domain.Mentor;
import org.springframework.data.repository.CrudRepository;

public interface MarkRepository extends CrudRepository<Mark,Long> {

    Mark findByJudge_JudgeidAndTeamTeamID(Long judgeid,Long teamiD);

    @Override
    <S extends Mark> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();

    @Override
    void delete(Mark entity);
}
