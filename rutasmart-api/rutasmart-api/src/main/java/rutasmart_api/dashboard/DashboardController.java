package rutasmart_api.dashboard;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

    @GetMapping("/dashboard")
    public Map<String, Object> obtenerDashboard() {

        Map<String, Object> datos = new HashMap<>();

        datos.put("nombre", "Carlos");
        datos.put("ruta", "Lima Sur");
        datos.put("paradero", "Puente Alipio");
        datos.put("proximoBus", "07:15 AM");
        datos.put("estadoBus", "En camino");
        datos.put("distancia", "1.2 km");
        datos.put("tiempoEstimado", "15 minutos");

        datos.put("proximasSalidas",
                Arrays.asList("07:15 AM", "07:35 AM", "07:55 AM"));

        return datos;
    }
}