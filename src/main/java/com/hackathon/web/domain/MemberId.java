package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@Getter
@Setter
public class MemberId implements Serializable {


    private Long memberID;

    @Column(insertable=false, updatable=false)
    private Long teamID;

}
