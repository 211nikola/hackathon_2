package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Mentor;
import com.hackathon.web.repositories.MentorRepository;
import com.hackathon.web.services.MentorService;

public class MentorServiceImpl implements MentorService {

    private final MentorRepository repository;

    public MentorServiceImpl(MentorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mentor save(Mentor mentor) {
        return repository.save(mentor);
    }

    @Override
    public void delete(Mentor mentor) {
        repository.delete(mentor);
    }



}
