package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@AllArgsConstructor
public class Judgehackathon {

   @EmbeddedId
   private JudgehackathonId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="hackathonid",insertable = false, updatable = false)
    private Hackathon hackathon;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="judgeid",insertable = false, updatable = false)
    private Judge judge;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="administratorid",insertable = false, updatable = false)
    private Administrator administrator;
}
