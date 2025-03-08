package com.propensi.genius.rest.restdto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateInstansiRequestDTO extends AddInstansiRequestDTO{

    private String idInstansi;
}
