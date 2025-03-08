package com.propensi.genius.rest.restdto.response;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InstansiResponseDTO {
    private String idInstansi;

    private String namaInstansi;

    private String namaNarahubung;

    private Long nomorKontak;

    private String alamatInstansi;

    private String jenisInstansi;


}
