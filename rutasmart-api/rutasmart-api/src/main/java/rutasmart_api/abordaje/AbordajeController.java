package rutasmart_api.abordaje;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abordajes")
@CrossOrigin(origins = "*")
public class AbordajeController {

    private final AbordajeRepository repository;

    public AbordajeController(AbordajeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Abordaje> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Abordaje guardar(@RequestBody Abordaje abordaje) {
        return repository.save(abordaje);
    }
}