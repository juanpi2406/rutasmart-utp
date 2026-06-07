package rutasmart_api.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgramacionRepository
        extends JpaRepository<Programacion, Long> {

    @Query(value = """
            SELECT
                r.nombre,
                p.hora_salida,
                p.hora_llegada_estimada,
                p.dias_operacion,
                p.estado
            FROM programacion_viajes p
            INNER JOIN rutas r
            ON p.id_ruta = r.id_ruta
            """,
            nativeQuery = true)
    List<Object[]> obtenerProgramacionesConRuta();

}