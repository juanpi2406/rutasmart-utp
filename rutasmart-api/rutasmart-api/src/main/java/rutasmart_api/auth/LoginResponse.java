package rutasmart_api.auth;

public class LoginResponse {

    private Long idUsuario;
    private Long idAlumno;
    private String nombre;
    private String rol;
    private String mensaje;

    public LoginResponse(
            Long idUsuario,
            Long idAlumno,
            String nombre,
            String rol,
            String mensaje
    ) {
        this.idUsuario = idUsuario;
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.rol = rol;
        this.mensaje = mensaje;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Long getIdAlumno() {
        return idAlumno;
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