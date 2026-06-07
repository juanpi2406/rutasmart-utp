package rutasmart_api.usuario;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoAndPasswordHash(
        String correo,
        String passwordHash
);
}