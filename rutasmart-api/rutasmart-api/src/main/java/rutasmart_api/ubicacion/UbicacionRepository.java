package rutasmart_api.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UbicacionRepository
        extends JpaRepository<UbicacionBus, Long> {

    Optional<UbicacionBus>
    findTopByIdViajeOrderByFechaHoraDesc(
            Long idViaje
    );

}