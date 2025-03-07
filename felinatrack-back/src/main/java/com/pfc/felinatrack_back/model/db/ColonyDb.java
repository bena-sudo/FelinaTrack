package com.pfc.felinatrack_back.model.db;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "colonies")
public class ColonyDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, length = 255)
    private String location;

    @Column(nullable = false, length = 255)
    private String municipality;

    @Column(length = 50)
    private String latitud;

    @Column(length = 50)
    private String longitud;

    @ManyToMany(mappedBy = "colonies")
    private Set<ResponsibleDb> responsibles;

    @OneToMany(mappedBy = "colony")
    private Set<CatDb> cats;

}