package com.hackathon.web.services;

import com.hackathon.web.domain.Mark;

public interface MarkService {
    Mark save(Mark mark);
    void delete(Mark mark);
}
