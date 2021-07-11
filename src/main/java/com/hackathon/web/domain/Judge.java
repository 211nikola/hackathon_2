package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank
    @Size(min = 2, max = 255)
    private String username;

    @NotBlank
    @Size(min = 2, max = 255)
    private String password;

    @Email
    @NotBlank
    @Size(min = 2, max = 255)
    private String mail;

    @NotBlank
    @Size(min = 2, max = 255)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 255)
    private String profession;

    @NotBlank
    @Size(min = 2, max = 255)
    private String country;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Mark> marks;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Judgehackathon> judgehackathons;

}
