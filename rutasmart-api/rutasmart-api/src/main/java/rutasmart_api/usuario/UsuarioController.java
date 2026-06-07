package rutasmart_api.usuario;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import rutasmart_api.alumno.Alumno;
import rutasmart_api.alumno.AlumnoRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final AlumnoRepository alumnoRepository;

    public UsuarioController(
            UsuarioRepository usuarioRepository,
            AlumnoRepository alumnoRepository
    ) {

        this.usuarioRepository = usuarioRepository;
        this.alumnoRepository = alumnoRepository;

    }
    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
    @PostMapping
public Usuario crearUsuario(
        @RequestBody CrearUsuarioRequest request
) {

    Usuario usuario = new Usuario();

    usuario.setCodigo(
            request.getCodigo()
    );

    usuario.setNombres(
            request.getNombres()
    );

    usuario.setApellidos(
            request.getApellidos()
    );

    usuario.setCorreo(
            request.getCorreo()
    );

    usuario.setPasswordHash(
            request.getPassword()
    );

    usuario.setTelefono(
            request.getTelefono()
    );

    usuario.setRol(
            request.getRol()
    );

    usuario.setEstado(true);

    usuario =
            usuarioRepository.save(
                    usuario
            );

    if("ALUMNO".equals(
            request.getRol()
    )){

        Alumno alumno =
                new Alumno();

        alumno.setIdUsuario(
                usuario.getIdUsuario()
        );

        alumno.setFacultad(
                request.getFacultad()
        );

        alumno.setEscuela(
                request.getEscuela()
        );

        alumno.setCiclo(
                request.getCiclo()
        );

        alumnoRepository.save(
                alumno
        );

    }

    return usuario;

}
}