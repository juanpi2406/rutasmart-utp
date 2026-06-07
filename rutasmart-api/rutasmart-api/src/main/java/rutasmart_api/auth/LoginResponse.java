package rutasmart_api.auth;

public class LoginResponse {

    private String nombre;
    private String rol;
    private String mensaje;

    public LoginResponse(
            String nombre,
            String rol,
            String mensaje
    ) {
        this.nombre = nombre;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getMensaje() {
        return mensaje;
    }
}