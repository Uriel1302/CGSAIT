package Reportes.controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "Persona")
@RequestScoped
public class Persona {

    private int codigo;
    private String nombre;
    private String fecha_actual;

    private String dias;
    private String mes;
    private String detalle;
    private String motivo;
    private String status;

    public Persona() {
        this.nombre = "";
        this.fecha_actual = "";
        this.codigo = 0;
        this.dias = "";
        this.mes = "";
        this.motivo = "";

    }

    public Persona(int codigo, String nombre, String fecha_actual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha_actual = fecha_actual;
    }

    public Persona(int codigo, String nombre, String fecha_actual, String dias, String mes, String motivo, String detalle, String status) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fecha_actual = fecha_actual;
        this.dias = dias;
        this.mes = mes;
        this.motivo = motivo;
        this.detalle = detalle;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

    }

    public void guardarCheckbox(Persona p) throws ClassNotFoundException {
        domain.Conexion conn = new domain.Conexion();

        try {
            PreparedStatement stmt = conn.getConnection().prepareStatement("UPDATE personas SET status = 'Revisado' WHERE codigo=?");
            stmt.setInt(1, p.getCodigo());
            stmt.executeUpdate();
            //System.out.println("Guardó.......");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se cambió el estado correctamente"));
            stmt.close();
            conn.Close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.codigo = 0;
        this.nombre = "";

    }

}
