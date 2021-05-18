package com.hackathon.web.repositories;

import com.hackathon.web.domain.Member;
import com.hackathon.web.domain.Mentor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member,Long> {


    List<Member> findAllByTeam_TeamID(Long teamid);

    @Override
    <S extends Member> S save(S entity);

    @Override
    @Transactional
    @Modifying
    @Query("delete from Member m where m.id.memberID = ?1")
    void deleteById(Long aLong);




    @Override
    void deleteAll();

    @Override
    void delete(Member entity);

    @Transactional
    Member deleteByNameIsContaining(String search);
}
