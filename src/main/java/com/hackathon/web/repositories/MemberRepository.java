package com.hackathon.web.repositories;

import com.hackathon.web.domain.Member;
import com.hackathon.web.domain.Mentor;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {

    @Override
    <S extends Member> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void deleteAll();

    @Override
    void delete(Member entity);

    Member deleteByNameIsContaining(String search);
}
