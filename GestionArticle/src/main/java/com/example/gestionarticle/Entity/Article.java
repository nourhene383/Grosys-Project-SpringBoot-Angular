package com.example.gestionarticle.Entity;

import lombok.*;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idArticle;
    private String designation;
    private int nombreDePieceParLot;
    private double prixUnitaire;
    private String codeABarre;

    @OneToMany(mappedBy = "article")
    private List<Stock> stocks;
}
