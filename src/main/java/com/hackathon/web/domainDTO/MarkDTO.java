package com.hackathon.web.domainDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarkDTO {

    private int design;

    private int efficiency;

    private String comment;

    private int complexity;
}
