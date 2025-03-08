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
@Table(name="instansi")
@SQLDelete(sql = "UPDATE instansi SET deleted_at = NOW() WHERE id=?")
@SQLRestriction("deleted_at IS NULL")

public class Instansi {
    @Id
    @Column(name = "id_instansi")
    private String idInstansi;

    @Column(name = "nama_instansi")
    private String namaInstansi;

    @Column(name = "nama_narahubung")
    private String namaNarahubung;

    @Column(name = "nomor_kontak")
    private Long nomorKontak;

    @Column(name = "alamat_instansi")
    private String alamatInstansi;

    @Column(name = "jenis_instansi")
    private String jenisInstansi;

    @Column(name = "deleted_at")
    private Date deletedAt;



}
