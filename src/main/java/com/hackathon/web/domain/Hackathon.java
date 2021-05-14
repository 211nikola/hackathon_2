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

    //bi-directional many-to-one association to Administrator
    @ManyToOne
    @JoinColumn(name="administratorid",insertable = true, updatable = true)
    private Administrator administrator;

    //bi-directional many-to-one association to Sudijahakaton
    @OneToMany(mappedBy="hackathon")
    private Set<Judgehackathon> judgehackathons;

    //bi-directional many-to-one association to Tim
    @OneToMany(mappedBy="hackathon")
    private Set<Team> teams;
}
