package com.hackathon.web.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
@Table(name = "judge", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
public class Judge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long judgeid;

    @NotBlank
    @NotEmpty(message = "This field cannot be empty.")
    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 255)
    @Column(unique = true)
    private String username;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 255)
    private String password;

    @Email
    @NotBlank
    @Size(min = 2, max = 255)
    private String mail;

    @NotBlank
    @Size(min = 2, max = 255)
    @NotNull
    @NotEmpty
    private String lastName;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 255)
    private String profession;

    @NotBlank
    @NotEmpty
    @Size(min = 2, max = 255)
    private String country;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Mark> marks;


    @OneToMany(mappedBy="judge",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Judgehackathon> judgehackathons;

}
