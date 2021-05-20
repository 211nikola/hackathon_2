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
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamID;

    private String name;



    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Member> members;




    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Mark> marks;


    @ManyToOne
    @JoinColumn(name = "mentorid",insertable = false, updatable = false)
    @ToString.Exclude
    private Mentor mentor;


    @ManyToOne
    @JoinColumn(name = "administratorid",insertable = false, updatable = false)
    @ToString.Exclude
    private Administrator administrator;


    @ManyToOne
    @JoinColumn(name = "hackathonid",insertable = false, updatable = false)
    @ToString.Exclude
    private Hackathon hackathon;
}
