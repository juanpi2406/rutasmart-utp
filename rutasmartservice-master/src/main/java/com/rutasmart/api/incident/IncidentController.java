package com.rutasmart.api.incident;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IncidentRepository incidents;

    public IncidentController(IncidentRepository incidents) {
        this.incidents = incidents;
    }

    @GetMapping
    public List<IncidentEntity> list() {
        return incidents.findAllByOrderByIdDesc();
    }

    @PostMapping
    public IncidentEntity create(@Valid @RequestBody CreateIncidentRequest request) {
        IncidentEntity incident = new IncidentEntity();
        incident.setType(request.type());
        incident.setDescription(request.description());
        incident.setPhotoName(request.photoName());
        incident.setRouteName("Ruta Norte");
        incident.setTime(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
        incident.setStatus("En revisión");
        return incidents.save(incident);
    }
}
