package com.hackathon.web.domain;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member {

    @EmbeddedId
    private MemberId id;

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
    private String role;

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="teamid",insertable = false, updatable = false )
    private Team team;
}
