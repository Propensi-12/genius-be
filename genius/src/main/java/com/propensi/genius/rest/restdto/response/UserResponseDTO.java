package com.propensi.genius.rest.restdto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private String role;
    private String displayName;
    private String nomorHp;
    private String alamat;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
