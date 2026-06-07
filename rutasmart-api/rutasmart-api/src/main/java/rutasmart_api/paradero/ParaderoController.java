package rutasmart_api.paradero;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paraderos")
@CrossOrigin("*")
public class ParaderoController {

    private final ParaderoRepository repository;

    public ParaderoController(ParaderoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Paradero> listar() {
        return repository.findAll();
    }

    @GetMapping("/ruta/{idRuta}")
    public List<Paradero> listarPorRuta(@PathVariable Long idRuta) {
        return repository.findByIdRutaOrderByOrdenRuta(idRuta);
    }
}