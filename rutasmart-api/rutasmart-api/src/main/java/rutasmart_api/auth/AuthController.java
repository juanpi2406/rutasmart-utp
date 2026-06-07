package rutasmart_api.auth;

import org.springframework.web.bind.annotation.*;
import rutasmart_api.usuario.Usuario;
import rutasmart_api.usuario.UsuarioRepository;
import rutasmart_api.alumno.Alumno;
import rutasmart_api.alumno.AlumnoRepository;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final AlumnoRepository alumnoRepository;

public AuthController(
        UsuarioRepository usuarioRepository,
        AlumnoRepository alumnoRepository
) {
    this.usuarioRepository = usuarioRepository;
    this.alumnoRepository = alumnoRepository;
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

        Long idAlumno = alumnoRepository
                .findByIdUsuario(u.getIdUsuario())
                .map(Alumno::getIdAlumno)
                .orElse(null);

        return new LoginResponse(
                u.getIdUsuario(),
                idAlumno,
                u.getNombres(),
                u.getRol(),
                "Login exitoso"
        );
        }

        return new LoginResponse(
                null,
                null,
                "",
                "",
                "Credenciales incorrectas"
        );
    }
}