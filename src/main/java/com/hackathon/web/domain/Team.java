package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teamid")
    private Long teamID;

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="teamid")
    private List<Member> members;




    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Mark> marks;


    @ManyToOne
    @JoinColumn(name = "mentorid",insertable = true, updatable = true)
    @ToString.Exclude
    private Mentor mentor;


    @ManyToOne
    @JoinColumn(name = "administratorid",insertable = true, updatable = true)
    @ToString.Exclude
    private Administrator administrator;


    @ManyToOne
    @JoinColumn(name = "hackathonid",insertable = true, updatable = true)
    @ToString.Exclude
    private Hackathon hackathon;
}
