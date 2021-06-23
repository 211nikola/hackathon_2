package com.hackathon.web.repositories;

import com.hackathon.web.domain.Mentor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MentorRepository extends CrudRepository<Mentor,Long> {

    Mentor findByMentorID(Long mentorid);
    List<Mentor> findAllByNameContains(String search);
    List<Mentor> findAll();



    @Override
    <S extends Mentor> S save(S entity);

    @Override
    @Transactional
    @Modifying

    @Query("delete from Mentor m where m.mentorID=?1")
    void deleteById(Long aLong);

    @Override
    void deleteAll();

    @Override
    void delete(Mentor entity);
}
