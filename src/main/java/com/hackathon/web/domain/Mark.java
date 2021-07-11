package com.hackathon.web.domain;


import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Mark {

    @EmbeddedId
    private MarkId id;

    @NotBlank
    @Min(value = 1)
    @Max(value = 5)
    private int design;

    @NotBlank
    @Min(value = 1)
    @Max(value = 5)
    private int efficiency;

    @NotBlank
    @Size(min = 2, max = 255)
    private String comment;

    @NotBlank
    @Min(value = 1)
    @Max(value = 5)
    private int complexity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="judgeid",insertable = true, updatable = true)
    @ToString.Exclude
    private Judge judge;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="teamid",insertable = false, updatable = false)
    @ToString.Exclude
    private Team team;



}
