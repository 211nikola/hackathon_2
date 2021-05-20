package com.hackathon.web.domain;


import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Mark {

    @EmbeddedId
    private MarkId id;

    private int design;

    private int efficiency;

    private String comment;

    private int complexity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="judgeid",insertable = true, updatable = true)
    @ToString.Exclude
    private Judge judge;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teamid",insertable = false, updatable = false)
    @ToString.Exclude
    private Team team;
}
