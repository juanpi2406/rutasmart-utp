package rutasmart_api.ubicacion;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class UbicacionController {

    private final UbicacionRepository repository;

    public UbicacionController(
            UbicacionRepository repository
    ) {

        this.repository = repository;
    }

    @GetMapping("/{idViaje}")
    public UbicacionBus obtener(
            @PathVariable Long idViaje
    ) {

        return repository
                .findTopByIdViajeOrderByFechaHoraDesc(
                        idViaje
                )
                .orElseThrow();

    }

}