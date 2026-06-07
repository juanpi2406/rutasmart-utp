package rutasmart_api.paradero;

import jakarta.persistence.*;

@Entity
@Table(name = "paraderos")
public class Paradero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paradero")
    private Long idParadero;

    @Column(name = "id_ruta")
    private Long idRuta;

    private String nombre;

    private Double latitud;

    private Double longitud;

    @Column(name = "orden_ruta")
    private Integer ordenRuta;

    private Boolean estado;

    // GETTERS Y SETTERS

    public Long getIdParadero() {
        return idParadero;
    }

    public void setIdParadero(Long idParadero) {
        this.idParadero = idParadero;
    }

    public Long getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Long idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Integer getOrdenRuta() {
        return ordenRuta;
    }

    public void setOrdenRuta(Integer ordenRuta) {
        this.ordenRuta = ordenRuta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
