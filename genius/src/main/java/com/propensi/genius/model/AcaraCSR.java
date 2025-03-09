package com.propensi.genius.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "acara_csr")
public class AcaraCSR {

    @Id
    @Column(name = "id_acara_csr", nullable = false, unique = true)
    private String idAcaraCsr;

    // Contoh relasi Many-to-One dengan Instansi (FK id_instansi)
    // Pastikan class Instansi dan repository-nya sudah ada
    @ManyToOne
    @JoinColumn(name = "id_instansi", referencedColumnName = "id_instansi", nullable = false)
    private Instansi instansi;

    @Column(name = "nama_acara_csr", nullable = false)
    private String namaAcaraCsr;

    @Column(name = "tanggal_acara_csr", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalAcaraCsr;

    @Column(name = "anggaran_csr")
    private Long anggaranCsr;

    @Column(name = "status")
    private String status;
}
