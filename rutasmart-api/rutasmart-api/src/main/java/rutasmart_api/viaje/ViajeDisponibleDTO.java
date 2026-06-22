package rutasmart_api.viaje;

public class ViajeDisponibleDTO {

    private Long idViaje;
    private String fechaViaje;
    private String horaSalida;
    private String horaLlegada;
    private String estado;

    private Integer capacidadTotal;
    private Integer cuposDisponibles;

    public ViajeDisponibleDTO(
            Long idViaje,
            String fechaViaje,
            String horaSalida,
            String horaLlegada,
            String estado,
            Integer capacidadTotal,
            Integer cuposDisponibles
    ) {
        this.idViaje = idViaje;
        this.fechaViaje = fechaViaje;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.estado = estado;
        this.capacidadTotal = capacidadTotal;
        this.cuposDisponibles = cuposDisponibles;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public Integer getCuposDisponibles() {
        return cuposDisponibles;
    }
}