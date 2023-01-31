package com.isi.pointage.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "professeur")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100)
    private String nom;
    @Column(length = 150)
    private String prenom;
    @Column(length = 150)
    private String email;
    @Column(length = 150)
    private String etat;

}
