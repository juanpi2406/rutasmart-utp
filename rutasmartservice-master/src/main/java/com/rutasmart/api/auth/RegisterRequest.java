package com.rutasmart.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @Email(message = "Debe ingresar un correo válido")
        @NotBlank(message = "El correo es obligatorio")
        String email,

        @NotBlank(message = "El código es obligatorio")
        @Pattern(regexp = "U[0-9]{8}", message = "El código debe tener formato U########")
        String code,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "[0-9]{9}", message = "El teléfono debe tener 9 dígitos")
        String phone,

        @NotBlank(message = "La ruta asignada es obligatoria")
        String assignedRoute,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password
) {
}
