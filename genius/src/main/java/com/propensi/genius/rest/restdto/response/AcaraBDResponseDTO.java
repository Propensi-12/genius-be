package com.propensi.genius.rest.restdto.response;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcaraBDResponseDTO {
    private String idAcaraBD;

    private String namaAcaraBD;

    private Date tanggalAcaraBD;

    private Long anggaranBD;

    private String status;
}
