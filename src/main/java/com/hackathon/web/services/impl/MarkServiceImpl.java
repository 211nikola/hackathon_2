package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.repositories.MarkRepository;
import com.hackathon.web.services.MarkService;

public class MarkServiceImpl implements MarkService {
    private final MarkRepository repository;

    public MarkServiceImpl(MarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mark save(Mark mark) {
        return repository.save(mark);
    }

    @Override
    public void delete(Mark mark) {

        repository.delete(mark);
    }
}
