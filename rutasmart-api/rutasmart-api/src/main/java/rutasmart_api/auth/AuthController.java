package rutasmart_api.auth;

import org.springframework.web.bind.annotation.*;
import rutasmart_api.usuario.Usuario;
import rutasmart_api.usuario.UsuarioRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        Optional<Usuario> usuario =
                usuarioRepository.findByCorreoAndPasswordHash(
                        request.getCorreo(),
                        request.getPassword()
                );

        if (usuario.isPresent()) {

            Usuario u = usuario.get();

            return new LoginResponse(
                    u.getNombres(),
                    u.getRol(),
                    "Login exitoso"
            );
        }

        return new LoginResponse(
                "",
                "",
                "Credenciales incorrectas"
        );
    }
}