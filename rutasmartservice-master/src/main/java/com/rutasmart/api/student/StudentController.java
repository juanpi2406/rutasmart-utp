package com.rutasmart.api.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentProfileRepository profiles;

    public StudentController(StudentProfileRepository profiles) {
        this.profiles = profiles;
    }

    @GetMapping("/profile")
    public StudentProfile profile(@RequestParam(required = false) String email) {
        if (email != null && !email.isBlank()) {
            return profiles.findByEmailIgnoreCase(email.trim())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No existe un perfil para ese correo"));
        }
        return profiles.findAll().stream().findFirst().orElseGet(this::createDefaultProfile);
    }

    @PutMapping("/profile")
    public StudentProfile updateProfile(@RequestParam(required = false) String email, @RequestBody StudentProfile payload) {
        StudentProfile profile = profile(email != null ? email : payload.getEmail());
        profile.setName(payload.getName());
        profile.setEmail(payload.getEmail());
        profile.setCode(payload.getCode());
        profile.setPhone(payload.getPhone());
        profile.setAssignedRoute(payload.getAssignedRoute());
        return profiles.save(profile);
    }

    @GetMapping("/route-summary")
    public RouteSummary routeSummary() {
        return new RouteSummary(
                "Ruta Norte",
                "Paradero Av Pachacutec",
                "07:15 AM",
                "En 5 minutos",
                "En camino",
                "A 1.2 km de ti",
                "15 minutos",
                "Llegada a UTP",
                new String[]{"07:15 AM", "07:35 AM", "07:55 AM"}
        );
    }

    private StudentProfile createDefaultProfile() {
        StudentProfile profile = new StudentProfile();
        profile.setName("Juan Pablo Castillo");
        profile.setEmail("Juanpi_2406@hotmail.com");
        profile.setCode("U23252915");
        profile.setPhone("944475928");
        profile.setAssignedRoute("Sur de lima");
        return profiles.save(profile);
    }
}
