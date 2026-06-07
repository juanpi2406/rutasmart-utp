package rutasmart_api.reserva;

public class ReservaResponse {

    private boolean ok;
    private String mensaje;

    public ReservaResponse(
            boolean ok,
            String mensaje
    ) {
        this.ok = ok;
        this.mensaje = mensaje;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMensaje() {
        return mensaje;
    }
}