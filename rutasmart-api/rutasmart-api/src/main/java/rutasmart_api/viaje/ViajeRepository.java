package rutasmart_api.viaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

    List<Viaje> findByFechaViaje(LocalDate fechaViaje);



@Query(value = """
SELECT
    v.id_viaje,
    v.fecha_viaje,
    p.hora_salida,
    p.hora_llegada_estimada,
    v.estado,
    b.capacidad_asientos,

    (
        b.capacidad_asientos
        -
        COUNT(r.id_reserva)
    ) AS cupos_disponibles

FROM viajes v

JOIN programacion_viajes p
ON v.id_programacion = p.id_programacion

JOIN buses b
ON v.id_bus = b.id_bus

LEFT JOIN reservas r
ON v.id_viaje = r.id_viaje
AND r.estado = 'RESERVADO'

WHERE v.estado = 'PROGRAMADO'

GROUP BY
    v.id_viaje,
    v.fecha_viaje,
    p.hora_salida,
    p.hora_llegada_estimada,
    v.estado,
    b.capacidad_asientos

ORDER BY
    v.fecha_viaje,
    p.hora_salida
""", nativeQuery = true)
List<Object[]> obtenerDisponibilidad();
}

