package rutasmart_api.ruta;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin("*")
public class RutaController {

    private final RutaRepository repository;

    public RutaController(RutaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ruta> listar() {
        return repository.findAll();
    }
}