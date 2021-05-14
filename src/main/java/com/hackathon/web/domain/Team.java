package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;

    private String name;



    @OneToMany(mappedBy = "team")
    private Set<Member> members;




    @OneToMany(mappedBy = "team")
    private Set<Mark> marks;


    @ManyToOne
    @JoinColumn(name = "mentorid",insertable = false, updatable = false)
    private Mentor mentor;


    @ManyToOne
    @JoinColumn(name = "administratorid",insertable = false, updatable = false)
    private Administrator administrator;


    @ManyToOne
    @JoinColumn(name = "hackathonid",insertable = false, updatable = false)
    private Hackathon hackathon;
}
