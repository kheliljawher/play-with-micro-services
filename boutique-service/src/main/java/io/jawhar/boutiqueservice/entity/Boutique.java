package io.jawhar.boutiqueservice.entity;

import io.jawhar.boutiqueservice.controller.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "boutique")
public class Boutique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;



    public Boutique(Long id, String name) {
        this.id = id;
        this.name = name;

    }
}
