package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long judgeid;

    private String name;

    private String username;


    private String password;

    @Email
    private String mail;

    private String lastName;

    private String profession;

    private String country;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Mark> marks;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Judgehackathon> judgehackathons;

}
