package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @NotEmpty
    @NotNull
    @NotBlank
    private String username;

    @Size(min = 2, max = 255)
    @NotEmpty
    @NotNull
    @NotBlank
    private String password;

    @Size(min = 2, max = 255)
    @NotEmpty
    @NotNull
    @NotBlank
    private String name;

    @Size(min = 2, max = 255)
    @NotEmpty
    @NotNull
    @NotBlank
    private String lastName;

    @Email
    @NotEmpty
    @NotNull
    @NotBlank
    private String mail;

    @Size(min = 2, max = 255)
    @NotEmpty
    @NotNull
    @NotBlank
    private String adminType;


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Hackathon> hackathons;

    @ToString.Exclude
    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Mentor> mentors;

    @ToString.Exclude
    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Judgehackathon> judgehackathons;


    @OneToMany(mappedBy="administrator",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Team> teams;




}
