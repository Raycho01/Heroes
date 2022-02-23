package com.example.servingwebcontent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 3)
    private String name;

    @Column
    private String type;

    @Column
    private int level;

    @Column
    private Long userId;

//    enum Type {
//        WARRIOR,
//        MAGE,
//        ARCHER
//    }
}
