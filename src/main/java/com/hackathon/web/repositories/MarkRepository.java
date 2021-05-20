package com.hackathon.web.repositories;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.domain.MarkId;
import com.hackathon.web.domain.Mentor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MarkRepository extends CrudRepository<Mark,Long> {

    Mark findByJudge_JudgeidAndTeamTeamID(Long judgeid,Long teamiD);

    @Transactional
    @Modifying
    @Query("delete from Mark m  where m.id.markid=?1")
    void deleteMarkByIdIs(Long aLong);

    @Override
    <S extends Mark> S save(S entity);

    @Override
    void deleteById(Long aLong);


    @Override
    void deleteAll();

    @Override
    @Transactional
    @Modifying
    void delete(Mark entity);

    @Transactional
    @Modifying
    void deleteById(MarkId markId);

}
