package com.security.session.module.users.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    private String email;

    @Column(columnDefinition = "varchar(255)")
    private String password;

    private String name;

    private boolean isActivated;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Authority> authorities;



}
