package rutasmart_api.reserva;

public class ReservaRequest {

    private Long idAlumno;
    private Long idViaje;
    private Long idParadero;



    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Long getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Long idViaje) {
        this.idViaje = idViaje;
    }

    public Long getIdParadero() {
    return idParadero;
}

public void setIdParadero(Long idParadero) {
    this.idParadero = idParadero;
}
}