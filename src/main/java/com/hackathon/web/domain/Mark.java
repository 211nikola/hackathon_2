package com.hackathon.web.domain;


import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Mark {

    @EmbeddedId
    private MarkId id;

    @Min(value = 1)
    @Max(value = 5)
    @NotEmpty
    @NotNull
    @NotBlank
    private int design;

    @Min(value = 1)
    @Max(value = 5)
    @NotEmpty
    @NotNull
    @NotBlank
    private int efficiency;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String comment;

    @NotBlank
    @NotEmpty
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
