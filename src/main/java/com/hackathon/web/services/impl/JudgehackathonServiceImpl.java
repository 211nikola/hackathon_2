package com.hackathon.web.services.impl;

import com.hackathon.web.domain.Judgehackathon;
import com.hackathon.web.repositories.JudgehackathonRepository;
import com.hackathon.web.services.JudgehackathonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgehackathonServiceImpl implements JudgehackathonService {
    private final JudgehackathonRepository repository;

    public JudgehackathonServiceImpl(JudgehackathonRepository repository) {
        this.repository = repository;
    }


    @Override
    public Judgehackathon save(Judgehackathon judgehackathon) {
        return repository.save(judgehackathon);
    }

    @Override
    public void delete(Judgehackathon judgehackathon) {
         repository.save(judgehackathon);
    }

    @Override
    public List<Judgehackathon> findAllByHackathon_Hackathonid(Long hID) {
        return repository.findAllByHackathon_Hackathonid(hID);
    }

    @Override
    public List<Judgehackathon> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Judgehackathon> findAllByJudge_Judgeid(Long hID) {
        return repository.findAllByJudge_Judgeid(hID);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void deleteJudgehackathonByJudge_Judgeid(Long judgeid) {
        repository.deleteJudgehackathonByJudge_Judgeid(judgeid);
    }
}
