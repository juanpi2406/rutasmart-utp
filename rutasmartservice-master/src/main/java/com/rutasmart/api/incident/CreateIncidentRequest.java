package com.rutasmart.api.incident;

import jakarta.validation.constraints.NotBlank;

public record CreateIncidentRequest(
        @NotBlank String type,
        @NotBlank String description,
        String photoName
) {
}
