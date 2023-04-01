package modelo;

public class Cumpleanero {
    private int id;
    private String correo;
    private String fecha;
    
    public Cumpleanero() {
        id = 0;
        fecha = "";
        correo = "";
    }
    public Cumpleanero(int id, String correo, String fecha) {
        this.id = id;
        this.fecha = fecha;
        this.correo = correo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getcorreo() {
        return correo;
    }
    public void setcorreo(String correo) {
        this.correo = correo;
    }
}
