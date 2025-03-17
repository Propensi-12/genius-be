package com.propensi.genius.rest.restdto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
// UpdateAcaraCSRRequestDTO mewarisi field dari AddAcaraCSRRequestDTO dan menambahkan idAcaraCsr
public class UpdateAcaraCSRRequestDTO extends AddAcaraCSRRequestDTO {
    private String idAcaraCsr;
}
