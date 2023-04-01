package modelo;

import java.util.Date;

public class Persona {

    private int id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private Date fecha_cumpleanios;
    private String email;

    public Persona() {
        id = 0;
        nombre = "";
        apellido_materno = "";
        apellido_paterno = "";
        fecha_cumpleanios = null;
        email = "";

    }

    public Persona(int id) {
        this.id = id;
    }
        
    public Persona(int id, String nombre, String apellido_paterno, String apellido_materno, Date fecha_cumpleanios, String email){
        this.id = id;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_cumpleanios = fecha_cumpleanios;
        this.email = email;
    }
    
    public Persona(String nombre, String apellido_paterno, String apellido_materno, Date fecha_cumpleanios, String email) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_cumpleanios = fecha_cumpleanios;
        this.email = email;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public Date getFecha_cumpleanios() {
        return fecha_cumpleanios;
    }

    public void setFecha_cumpleanios(Date fecha_cumpleanios) {
        this.fecha_cumpleanios = fecha_cumpleanios;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
