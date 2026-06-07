package rutasmart_api.programacion;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programaciones")
@CrossOrigin(origins = "http://localhost:4200")
public class ProgramacionController {

    private final ProgramacionRepository repository;

    public ProgramacionController(ProgramacionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ProgramacionDTO> listar() {

        return repository
                .obtenerProgramacionesConRuta()
                .stream()
                .map(obj -> new ProgramacionDTO(

                        (String) obj[0],

                        ((java.sql.Time) obj[1]).toLocalTime(),

                        ((java.sql.Time) obj[2]).toLocalTime(),

                        (String) obj[3],

                        (Boolean) obj[4]

                ))
                .toList();
    }

    @PostMapping
    public Programacion guardar(
            @RequestBody Programacion programacion) {

        return repository.save(programacion);
    }
}