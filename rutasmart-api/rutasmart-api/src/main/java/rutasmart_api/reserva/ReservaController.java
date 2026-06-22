package rutasmart_api.reserva;

import org.springframework.web.bind.annotation.*;
import rutasmart_api.abordaje.Abordaje;
import rutasmart_api.abordaje.AbordajeRepository;

import java.util.List;
import rutasmart_api.viaje.*;
import rutasmart_api.bus.*;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {

    private final ReservaRepository repository;
    private final ViajeRepository viajeRepository;
private final BusRepository busRepository;
private final AbordajeRepository abordajeRepository;

    public ReservaController(
            ReservaRepository repository,
            ViajeRepository viajeRepository,
            BusRepository busRepository,
            AbordajeRepository abordajeRepository
            
    ) {
        this.repository = repository;
        this.viajeRepository = viajeRepository;
        this.busRepository = busRepository;
        this.abordajeRepository = abordajeRepository;
    }

    @GetMapping
    public List<Reserva> listar() {
        return repository.findAll();
    }

    @PostMapping
    public ReservaResponse reservar(
            @RequestBody ReservaRequest request
    ) {

        if(repository.existsByIdAlumnoAndIdViaje(
                request.getIdAlumno(),
                request.getIdViaje()
        )){

            return new ReservaResponse(
                    false,
                    "Ya tienes una reserva para este viaje."
            );
        }

        Viaje viaje = viajeRepository
                .findById(request.getIdViaje())
                .orElse(null);

        if(viaje == null){

            return new ReservaResponse(
                    false,
                    "Viaje no encontrado."
            );
        }

        if(!"PROGRAMADO".equals(viaje.getEstado())){

            return new ReservaResponse(
                    false,
                    "El viaje no está disponible."
            );
        }

        Bus bus = busRepository
                .findById(viaje.getIdBus())
                .orElse(null);

        if(bus == null){

            return new ReservaResponse(
                    false,
                    "Bus no encontrado."
            );
        }

        long reservados =
                repository.countByIdViajeAndEstado(
                        request.getIdViaje(),
                        "RESERVADO"
                );

        if(reservados >= bus.getCapacidadAsientos()){

            return new ReservaResponse(
                    false,
                    "No hay cupos disponibles."
            );
        }

        Reserva reserva = new Reserva();

        reserva.setIdAlumno(
        request.getIdAlumno()
        );

        reserva.setIdViaje(
        request.getIdViaje()
        );

        reserva.setIdParadero(
        request.getIdParadero()
        );

        reserva.setEstado("RESERVADO");

        reserva = repository.save(reserva);

        Abordaje abordaje = new Abordaje();

        abordaje.setIdReserva(
                reserva.getIdReserva()
        );

        abordaje.setIdParadero(
                request.getIdParadero()
        );

        abordajeRepository.save(abordaje);

        return new ReservaResponse(
                true,
                "Reserva realizada correctamente."
        );
    }
}