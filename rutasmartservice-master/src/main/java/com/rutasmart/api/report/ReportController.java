package com.rutasmart.api.report;

import com.rutasmart.api.incident.IncidentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final IncidentRepository incidents;

    public ReportController(IncidentRepository incidents) {
        this.incidents = incidents;
    }

    @GetMapping("/summary")
    public ReportSummary summary() {
        long incidentCount = incidents.count();
        return new ReportSummary(
                128,
                incidentCount,
                86,
                List.of(
                        new ActivityItem("Lun", 46), new ActivityItem("Mar", 68), new ActivityItem("Mié", 54),
                        new ActivityItem("Jue", 82), new ActivityItem("Vie", 74), new ActivityItem("Sáb", 38)
                ),
                List.of(
                        new ReportRow("Retraso", "Ruta Norte", "Hoy", "Pendiente"),
                        new ReportRow("Paradero", "Ruta Sur", "Ayer", "Resuelto"),
                        new ReportRow("Tráfico", "Ruta Este", "29/05", "Resuelto"),
                        new ReportRow("Bus lleno", "Ruta Norte", "28/05", "En revisión")
                )
        );
    }
}
