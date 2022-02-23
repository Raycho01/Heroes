package com.example.servingwebcontent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 3)
    private String username;

    @Column
    @Size(min = 3)
    private String password;

    @Column
    @Email
    private String email;

    @Column
    @Size(min = 3)
    private String country;

}
