package com.rutasmart.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ForgotPasswordRequest(
        @Email(message = "Debe ingresar un correo válido")
        @NotBlank(message = "El correo es obligatorio")
        String email
) {
}
