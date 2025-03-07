package com.pfc.felinatrack_back.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cats")
public class CatDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 50)
    private String gender;

    @Column(length = 50)
    private String color;

    @Column(length = 50)
    private String breed;

    @Column(columnDefinition = "TEXT")
    private String markings;

    @Column(length = 50)
    private String health;
}
