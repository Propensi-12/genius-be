package com.propensi.genius.rest.restdto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcaraCSRResponseDTO {
    private String idAcaraCsr;
    
    private String idInstansi;
    
    private String namaAcaraCsr;
    private Date tanggalAcaraCsr;
    private Long anggaranCsr;
    private String status;
}
