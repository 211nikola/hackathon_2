package com.hackathon.web.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mentorID;

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @Email
    private String mail;

    @NotBlank
    @Size(min = 2, max = 255)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 255)
    private String profession;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="administratorid",insertable = true, updatable = true)
    private Administrator administrator;


    @OneToMany(mappedBy="mentor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Team> teams;

}
