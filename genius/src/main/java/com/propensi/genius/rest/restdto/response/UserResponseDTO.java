package com.propensi.genius.rest.restdto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserResponseDTO {
    private UUID id;
    private String email;
    private String role;
    private String displayName;
    private String name;
    private String nomorHp;
    private String alamat;
    private boolean isFirstLogin;
}
