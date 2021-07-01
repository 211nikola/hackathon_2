package com.hackathon.web.domain;

import lombok.*;
import org.hibernate.annotations.OptimisticLockType;

import javax.persistence.*;

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

    private String name;

    private String mail;

    private String lastName;

    private String role;

    @ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="teamid",insertable = false, updatable = false )
    private Team team;
}
