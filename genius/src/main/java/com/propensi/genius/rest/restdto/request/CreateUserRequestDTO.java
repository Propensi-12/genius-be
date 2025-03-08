package com.propensi.genius.rest.restdto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUserRequestDTO {

    @NotBlank(message = "Nama harus diisi")
    private String name;

    @NotBlank(message = "Email harus diisi")
    @Email(message = "Format email tidak valid")
    private String email;

    @NotBlank(message = "Role harus diisi")
    private String role; // Bisa "ADMIN", "CSR", "BD", dll.

    private String displayName;
    private String nomorHp;
    private String alamat;
}
