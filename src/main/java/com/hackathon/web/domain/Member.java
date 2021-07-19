package com.hackathon.web.domain;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @Size(min = 2, max = 255)
    @NotBlank
    @NotNull
    @NotEmpty
    private String name;

    @Email
    @NotBlank
    @NotNull
    @NotEmpty
    private String mail;

    @Size(min = 2, max = 255)
    @NotBlank
    @NotNull
    @NotEmpty
    private String lastName;

    @Size(min = 2, max = 255)
    @NotBlank
    @NotNull
    @NotEmpty
    private String role;

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="teamid",insertable = false, updatable = false )
    private Team team;
}
