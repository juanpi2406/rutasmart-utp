package rutasmart_api.dashboard;

import java.util.List;

public class DashboardResponse {

    private String nombre;
    private String ruta;
    private String paradero;
    private String proximoBus;
    private String estadoBus;
    private String distancia;
    private String tiempoEstimado;
    private List<String> proximasSalidas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getParadero() {
        return paradero;
    }

    public void setParadero(String paradero) {
        this.paradero = paradero;
    }

    public String getProximoBus() {
        return proximoBus;
    }

    public void setProximoBus(String proximoBus) {
        this.proximoBus = proximoBus;
    }

    public String getEstadoBus() {
        return estadoBus;
    }

    public void setEstadoBus(String estadoBus) {
        this.estadoBus = estadoBus;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public List<String> getProximasSalidas() {
        return proximasSalidas;
    }

    public void setProximasSalidas(List<String> proximasSalidas) {
        this.proximasSalidas = proximasSalidas;
    }
}