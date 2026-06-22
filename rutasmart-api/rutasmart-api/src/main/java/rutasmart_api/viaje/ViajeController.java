package rutasmart_api.viaje;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/viajes")
@CrossOrigin("*")
public class ViajeController {

    private final ViajeRepository repository;

    public ViajeController(ViajeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Viaje> listar() {
        return repository.findAll();
    }

    @GetMapping("/hoy")
    public List<Viaje> listarHoy() {
        return repository.findByFechaViaje(LocalDate.now());
    }




@GetMapping("/disponibles")
public List<ViajeDisponibleDTO> disponibles() {

    List<Object[]> datos =
            repository.obtenerDisponibilidad();

    List<ViajeDisponibleDTO> resultado =
            new ArrayList<>();

    for(Object[] fila : datos){
        System.out.println(
    "Columnas devueltas: "
    + fila.length
);
        resultado.add(
            
            new ViajeDisponibleDTO(

                ((Number) fila[0]).longValue(),

                fila[1].toString(),

                fila[2].toString(),

                fila[3].toString(),

                fila[4].toString(),

                ((Number) fila[5]).intValue(),
                ((Number) fila[6]).intValue()
            )
        );
    }

    return resultado;
}

}