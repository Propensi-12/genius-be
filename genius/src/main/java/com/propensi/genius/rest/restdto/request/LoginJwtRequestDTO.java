package com.propensi.genius.rest.restdto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginJwtRequestDTO {

    @NotBlank(message = "Email harus diisi")
    @Email(message = "Format email tidak valid")
    private String email;

    @NotBlank(message = "Password harus diisi")
    private String password;
}
