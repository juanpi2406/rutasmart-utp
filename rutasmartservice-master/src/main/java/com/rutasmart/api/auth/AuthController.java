package com.rutasmart.api.auth;

import com.rutasmart.api.student.StudentProfile;
import com.rutasmart.api.student.StudentProfileRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuthController {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AuthMessageResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthMessageResponse(ex.getMessage()));
    }

    private static final Set<String> SOCIAL_PROVIDERS = Set.of("google", "azure");

    private final StudentProfileRepository profiles;

    @Value("${supabase.url:https://waymbenzsixdnehcgzfj.supabase.co}")
    private String supabaseUrl;

    @Value("${app.frontend-url:http://localhost:4200}")
    private String frontendUrl;

    public AuthController(StudentProfileRepository profiles) {
        this.profiles = profiles;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "app", "RutaSmart");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new LoginResponse("Login correcto", "STUDENT"));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<AuthMessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        profiles.findByEmailIgnoreCase(request.email()).ifPresent(profile -> {
            throw new IllegalArgumentException("El correo ya está registrado");
        });
        profiles.findByCodeIgnoreCase(request.code()).ifPresent(profile -> {
            throw new IllegalArgumentException("El código ya está registrado");
        });

        StudentProfile profile = new StudentProfile();
        profile.setName(request.name());
        profile.setEmail(request.email());
        profile.setCode(request.code());
        profile.setPhone(request.phone());
        profile.setAssignedRoute(request.assignedRoute());
        profiles.save(profile);

        return ResponseEntity.ok(new AuthMessageResponse("Estudiante registrado correctamente"));
    }

    @PostMapping("/auth/forgot-password")
    public ResponseEntity<AuthMessageResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(new AuthMessageResponse("Si el correo existe, enviaremos instrucciones de recuperación"));
    }

    @GetMapping("/auth/social/{provider}")
    public ResponseEntity<SocialAuthResponse> socialAuth(@PathVariable String provider) {
        String normalized = provider.toLowerCase(Locale.ROOT);
        if (normalized.equals("microsoft")) {
            normalized = "azure";
        }
        if (!SOCIAL_PROVIDERS.contains(normalized)) {
            return ResponseEntity.badRequest().body(new SocialAuthResponse(provider, "", "Proveedor no soportado"));
        }

        String url = UriComponentsBuilder
                .fromHttpUrl(supabaseUrl + "/auth/v1/authorize")
                .queryParam("provider", normalized)
                .queryParam("redirect_to", frontendUrl + "/dashboard")
                .toUriString();

        return ResponseEntity.ok(new SocialAuthResponse(provider, url, "Redirigiendo a " + provider));
    }
}
