package com.security.jwt.module.users.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(columnDefinition = "varchar(255)")
    private String email;

    @Column(columnDefinition = "varchar(255)")
    private String password;

    @Column
    private String name;

    @Column
    private boolean isActivated;


}
