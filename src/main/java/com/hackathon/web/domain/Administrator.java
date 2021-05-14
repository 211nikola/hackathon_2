package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@NamedQuery(name="administrator.findAll", query="SELECT a FROM Administrator a")
public class Administrator {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long administratorid;

    @Size(min = 2, max = 255)
    private String username;

    private String password;

    @Size(min = 2, max = 255)
    private String name;

    @Size(min = 2, max = 255)
    private String lastName;

    @Email
    private String mail;

    private String adminType;

    //bi-directional many-to-one association to Hakaton
    @OneToMany(mappedBy="administrator")
    private Set<Hackathon> hackathons;

    //bi-directional many-to-one association to Mentor
    @OneToMany(mappedBy="administrator")
    private Set<Mentor> mentors;

    //bi-directional many-to-one association to Sudijahakaton
    @OneToMany(mappedBy="administrator")
    private Set<Judgehackathon> judgehackathons;

    //bi-directional many-to-one association to Tim
    @OneToMany(mappedBy="administrator")
    private Set<Team> teams;




}
