package com.pfc.felinatrack_back.model.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "responsibles")
public class ResponsibleDb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 50)
    private String type; // 'association', 'volunteer', 'government'

    @Column(length = 255)
    private String contact;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserDb user;
}
