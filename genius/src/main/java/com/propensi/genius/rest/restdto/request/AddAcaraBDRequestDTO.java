package com.propensi.genius.rest.restdto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddAcaraBDRequestDTO {
    private String idAcaraBD;

    private String namaAcaraBD;

    private Date tanggalAcaraBD;

    private Long anggaranBD;

    private String status;
}
