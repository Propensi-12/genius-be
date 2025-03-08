package com.propensi.genius.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "end_user")
@SQLDelete(sql = "UPDATE end_user SET is_deleted = true WHERE id_user=?")
@Where(clause = "is_deleted = false")
public class EndUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user", nullable = false, unique = true)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String name;

    private String nomorHp;
    private String alamat;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "is_first_login", nullable = false)
    private boolean isFirstLogin = true;
}

