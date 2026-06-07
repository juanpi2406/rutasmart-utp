package rutasmart_api.abordaje;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "abordajes")
public class Abordaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abordaje")
    private Long idAbordaje;

    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "id_paradero")
    private Long idParadero;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    public Long getIdAbordaje() {
        return idAbordaje;
    }

    public void setIdAbordaje(Long idAbordaje) {
        this.idAbordaje = idAbordaje;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Long getIdParadero() {
        return idParadero;
    }

    public void setIdParadero(Long idParadero) {
        this.idParadero = idParadero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}