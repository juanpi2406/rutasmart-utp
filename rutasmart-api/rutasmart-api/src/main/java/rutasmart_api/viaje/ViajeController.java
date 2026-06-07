package rutasmart_api.viaje;

import org.springframework.web.bind.annotation.*;

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
}