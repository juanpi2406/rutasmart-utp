package rutasmart_api.reserva;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaRepository repository;

    public ReservaController(ReservaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Reserva> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Reserva guardar(@RequestBody Reserva reserva) {
        return repository.save(reserva);
    }
}