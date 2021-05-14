package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Judgehackathon {

   @EmbeddedId
   private JudgehackathonId id;

    @ManyToOne
    @JoinColumn(name="hackathonid",insertable = false, updatable = false)
    private Hackathon hackathon;


    @ManyToOne
    @JoinColumn(name="judgeid",insertable = false, updatable = false)
    private Judge judge;


    @ManyToOne
    @JoinColumn(name="administratorid",insertable = false, updatable = false)
    private Administrator administrator;
}
