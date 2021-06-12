package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.repositories.MarkRepository;
import com.hackathon.web.services.MarkService;
import org.springframework.stereotype.Service;

@Service
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

    @Override
    public Mark findByJudge_JudgeidAndTeamTeamID(Long judgeid, Long teamiD) {
        return repository.findByJudge_JudgeidAndTeamTeamID(judgeid, teamiD);
    }
}
