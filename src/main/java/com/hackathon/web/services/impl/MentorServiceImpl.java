package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Mentor;
import com.hackathon.web.repositories.MentorRepository;
import com.hackathon.web.services.MentorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository repository;

    @Override
    public Mentor save(Mentor mentor) {
        return repository.save(mentor);
    }

    @Override
    public void delete(Mentor mentor) {
        repository.delete(mentor);
    }

    @Override
    public List<Mentor> findAllByNameContains(String search) {
        return repository.findAllByNameContains(search);
    }

    @Override
    public Mentor findByMentorID(Long mentorid) {
        return repository.findByMentorID(mentorid);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public List<Mentor> findAll() {
        return repository.findAll();
    }


}
