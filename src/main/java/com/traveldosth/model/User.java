package com.traveldosth.model;

import com.traveldosth.model.enums.RoleEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    @ApiModelProperty(hidden = true)
    private String password;

    @Enumerated
    @Column(columnDefinition = "smallint default 2")
    private RoleEnum role;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
