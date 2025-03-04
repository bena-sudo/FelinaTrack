package com.pfc.felinatrack_back.security.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

import com.pfc.felinatrack_back.model.db.UserDb;

@Data
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserDb.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserDb user;

    private LocalDateTime expiryDate;

}
