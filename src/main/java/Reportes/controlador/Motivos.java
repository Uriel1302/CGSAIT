package Reportes.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;

public class Motivos {

    private List<String> dias;
    private String mes;
    private String motivo;
    private String detalle;

    public Motivos() {
        this.motivo = "";
        this.dias = null;
        this.mes = "";
        this.detalle = "";
    }

    public Motivos(List<String> dias, String mes, String motivo) {
        this.dias = dias;
        this.mes = mes;
        this.motivo = motivo;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    

    public void clear() {
        this.mes = null;
        this.dias = null;
        this.motivo = null;
        this.detalle = null;
    }
}
