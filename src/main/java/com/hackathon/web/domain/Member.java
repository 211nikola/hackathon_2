package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member {

    @EmbeddedId
    private MemberId id;

    private String name;

    private String mail;

    private String lastName;

    private String role;

    @ManyToOne
    @JoinColumn(name="teamid",insertable = false, updatable = false )
    private Team team;
}
