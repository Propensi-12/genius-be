package com.propensi.genius.rest.restdto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddAcaraCSRRequestDTO {

    private String idInstansi;
    
    private String namaAcaraCsr;
    
    private Date tanggalAcaraCsr;
    
    private Long anggaranCsr;
    
    private String status;
}
