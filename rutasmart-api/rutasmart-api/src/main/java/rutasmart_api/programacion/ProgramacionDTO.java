package rutasmart_api.programacion;

import java.time.LocalTime;

public class ProgramacionDTO {

    private String ruta;
    private LocalTime horaSalida;
    private LocalTime horaLlegadaEstimada;
    private String diasOperacion;
    private Boolean estado;

    public ProgramacionDTO(
            String ruta,
            LocalTime horaSalida,
            LocalTime horaLlegadaEstimada,
            String diasOperacion,
            Boolean estado) {

        this.ruta = ruta;
        this.horaSalida = horaSalida;
        this.horaLlegadaEstimada = horaLlegadaEstimada;
        this.diasOperacion = diasOperacion;
        this.estado = estado;
    }

    public String getRuta() {
        return ruta;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHoraLlegadaEstimada() {
        return horaLlegadaEstimada;
    }

    public String getDiasOperacion() {
        return diasOperacion;
    }

    public Boolean getEstado() {
        return estado;
    }
}