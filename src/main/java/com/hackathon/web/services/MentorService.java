package com.hackathon.web.services;

import com.hackathon.web.domain.Mentor;

import java.util.List;

public interface MentorService {
    Mentor save(Mentor mentor);
    void delete(Mentor mentor);
    List<Mentor> findAllByNameContains(String search);
    Mentor findByMentorID(Long mentorid);
    void deleteById(Long aLong);


    List<Mentor> findAll();
}
