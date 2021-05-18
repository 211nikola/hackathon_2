package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorID;

    private String name;

    private String mail;

    private String lastName;

    private String profession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="administratorid",insertable = false, updatable = false)
    private Administrator administrator;


    @OneToMany(mappedBy="mentor",fetch = FetchType.EAGER)
    private Set<Team> teams;

}
