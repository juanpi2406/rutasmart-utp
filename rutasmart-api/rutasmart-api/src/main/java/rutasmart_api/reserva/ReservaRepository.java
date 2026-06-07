package rutasmart_api.reserva;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    boolean existsByIdAlumnoAndIdViaje(
        Long idAlumno,
        Long idViaje
);

long countByIdViajeAndEstado(
        Long idViaje,
        String estado
);
}

