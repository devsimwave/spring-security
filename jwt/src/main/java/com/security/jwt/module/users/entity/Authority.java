package com.security.jwt.module.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@NoArgsConstructor
public class Authority {

    @Id
    @Column(length = 50)
    private String authorityName;

    @ManyToOne
    @JsonIgnore
    private Users users;
}
