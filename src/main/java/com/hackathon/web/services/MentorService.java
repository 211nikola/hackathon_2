package com.hackathon.web.services;

import com.hackathon.web.domain.Mentor;

public interface MentorService {
    Mentor save(Mentor mentor);
    void delete(Mentor mentor);

}
