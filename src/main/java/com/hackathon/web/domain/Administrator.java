package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
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


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL)
    private Set<Hackathon> hackathons;


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL)
    private Set<Mentor> mentors;


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL)
    private Set<Judgehackathon> judgehackathons;


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL)
    private Set<Team> teams;




}
