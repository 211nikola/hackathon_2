package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
