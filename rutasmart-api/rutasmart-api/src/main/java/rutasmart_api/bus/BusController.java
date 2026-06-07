package rutasmart_api.bus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin("*")
public class BusController {

    private final BusRepository repository;

    public BusController(BusRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Bus> listar() {
        return repository.findAll();
    }
}