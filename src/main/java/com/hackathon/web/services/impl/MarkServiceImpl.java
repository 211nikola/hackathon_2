package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Mark;
import com.hackathon.web.repositories.MarkRepository;
import com.hackathon.web.services.MarkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarkServiceImpl implements MarkService {
    private final MarkRepository repository;

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

    @Override
    public void deleteMarkByIdIs(Long aLong) {
        repository.deleteMarkByIdIs(aLong);
    }

    @Override
    public List<Mark> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void deleteByJudge_Judgeid(Long id) {
        repository.deleteByJudge_Judgeid(id);
    }

    @Override
    public List<Mark> findAllByTeam_TeamID(Long teamid) {
        return repository.findAllByTeam_TeamID(teamid);
    }

    @Override
    public List<Mark> findAllByJudge_Judgeid(Long judgeid) {
        return repository.findAllByJudge_Judgeid(judgeid);
    }


}
