package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hackathonid;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String name;


    @ManyToOne
    @JoinColumn(name="administratorid",insertable = true, updatable = true)
    private Administrator administrator;


    @OneToMany(mappedBy="hackathon")
    private Set<Judgehackathon> judgehackathons;


    @OneToMany(mappedBy="hackathon")
    private Set<Team> teams;
}
