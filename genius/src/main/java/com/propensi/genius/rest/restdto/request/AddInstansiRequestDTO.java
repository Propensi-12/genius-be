package com.propensi.genius.rest.restdto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddInstansiRequestDTO {

    private String namaInstansi;

    private String namaNarahubung;

    private Long nomorKontak;

    private String alamatInstansi;

    private String jenisInstansi;
}
