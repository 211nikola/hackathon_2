package com.hackathon.web.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MarkId implements Serializable {

    private Long markid;

    @Column(insertable=false, updatable=false)
    private Long teamid;

}
