package com.example.grosysbackend.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Role  implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
}