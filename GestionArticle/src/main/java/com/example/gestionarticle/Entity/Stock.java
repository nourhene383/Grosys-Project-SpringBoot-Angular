package com.example.gestionarticle.Entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idStock;
    private int quantiteAjout;
    private LocalDateTime dateAjout;

    @ManyToOne
    private Article article;
}
