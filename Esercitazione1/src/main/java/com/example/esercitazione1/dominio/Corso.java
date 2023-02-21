package com.example.esercitazione1.dominio;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "corso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name ="nomeCorso")
    private String nome;
    @Column(name ="descrizione")
    private String descrizione;

}