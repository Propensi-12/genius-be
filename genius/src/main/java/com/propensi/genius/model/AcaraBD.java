package com.propensi.genius.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="acarabd")
@SQLDelete(sql = "UPDATE acarabd SET deleted_at = NOW() WHERE id=?")
// @SQLRestriction("deleted_at IS NULL")

public class AcaraBD {
    @Id
    @Column(name = "id_acara_bd")
    private String idAcaraBD;

    @Column(name = "nama_acara_bd")
    private String namaAcaraBD;

    @Column(name = "tanggal_acara_bd")
    private Date tanggalAcaraBD;

    @Column(name = "anggaran_bd")
    private Long anggaranBD;

    @Column(name = "status")
    private String status;
}
