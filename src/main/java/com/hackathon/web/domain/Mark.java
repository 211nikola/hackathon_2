package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Mark {

    @EmbeddedId
    private MarkId id;

    private int design;

    private int efficiency;

    private String comment;

    private int complexity;

    @ManyToOne
    @JoinColumn(name="judgeid",insertable = false, updatable = false)
    private Judge judge;


    @ManyToOne
    @JoinColumn(name="teamid",insertable = false, updatable = false)
    private Team team;
}
