package com.propensi.genius.rest.restdto.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateProfileRequestDTO {
    private UUID id;
    private String displayName;
    private String name;
    private String nomorHp;
    private String alamat;
}
