package com.hackathon.web.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor


@ToString
@Entity
@AllArgsConstructor
public class Hackathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hackathonid;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date date;

    private String name;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="administratorid",insertable = true, updatable = true)
    private Administrator administrator;


    @OneToMany(mappedBy="hackathon",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Judgehackathon> judgehackathons;


    @OneToMany(mappedBy="hackathon",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Team> teams;
}
