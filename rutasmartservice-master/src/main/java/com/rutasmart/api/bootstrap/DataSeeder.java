package com.rutasmart.api.bootstrap;

import com.rutasmart.api.incident.IncidentEntity;
import com.rutasmart.api.incident.IncidentRepository;
import com.rutasmart.api.notification.NotificationEntity;
import com.rutasmart.api.notification.NotificationRepository;
import com.rutasmart.api.student.StudentProfile;
import com.rutasmart.api.student.StudentProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(StudentProfileRepository profiles, IncidentRepository incidents, NotificationRepository notifications) {
        return args -> {
            if (profiles.count() == 0) {
                StudentProfile profile = new StudentProfile();
                profile.setName("Juan Pablo Castillo");
                profile.setEmail("Juanpi_2406@hotmail.com");
                profile.setCode("U23252915");
                profile.setPhone("944475928");
                profile.setAssignedRoute("Sur de lima");
                profiles.save(profile);
            }

            if (incidents.count() == 0) {
                incidents.save(incident("Problema con el Bus", "07:05 am", "En revisión"));
                incidents.save(incident("Paradero cerrado", "06:50 am", "Resuelto"));
                incidents.save(incident("Trafico pesado", "06:00 am", "Resuelto"));
            }

            if (notifications.count() == 0) {
                notifications.save(notification("Bus próximo", "La unidad llegará al paradero en 5 minutos.", "Hoy 07:10 am", false));
                notifications.save(notification("Ruta actualizada", "La Ruta Norte mantiene su horario regular.", "Hoy 06:45 am", false));
                notifications.save(notification("Incidencia resuelta", "El paradero cerrado ya fue habilitado.", "Ayer", true));
            }
        };
    }

    private IncidentEntity incident(String type, String time, String status) {
        IncidentEntity incident = new IncidentEntity();
        incident.setType(type);
        incident.setDescription(type);
        incident.setRouteName("Ruta Norte");
        incident.setTime(time);
        incident.setStatus(status);
        return incident;
    }

    private NotificationEntity notification(String title, String message, String time, boolean read) {
        NotificationEntity notification = new NotificationEntity();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTime(time);
        notification.setRead(read);
        return notification;
    }
}
