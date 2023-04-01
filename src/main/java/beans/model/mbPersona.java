package beans.model;

import EJB.ejbPersona;
import domain.PersonaDao;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Persona;

@Named(value = "personaOA")
@RequestScoped

public class mbPersona {
    @EJB
    private ejbPersona ejbPersona;
    Persona cumpleaniero;
    
    private Date today = new Date();
    private long oneDay = 24 * 60 * 60 * 1000;
    private Date minDate = new Date( today.getTime() - 36135 );
    private Date maxDate = new Date( today.getTime());
    

    @PostConstruct
    public void inicialize() {
       mostrarTodo();
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }
    
    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
    
    private List<Persona> personas;
    
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
    
    public void eliminarPersona(int id){
        ejbPersona.eliminarPersona(id);
        mostrarTodo();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se eliminó correctamente"));
    }
    
    public void mostrarUno(int id){
        Persona persona = ejbPersona.mostrarUno(id);
        System.out.println("" + persona.getApellido_materno());
        System.out.println("" + persona.getEmail());
        mostrarTodo();
    }
    
    public mbPersona(){
        cumpleaniero = new Persona();
    }
    
    public Persona getCumpleaniero(){
        return this.cumpleaniero;
    }
    
     public void setCumpleaniero(Persona cumpleaniero) {
        this.cumpleaniero = cumpleaniero;
    }
    
    public void insertar(){
        if(ejbPersona.insertarPersona(cumpleaniero) != 0){
            cumpleaniero = new Persona();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se inserto correctamente"));
        }
        mostrarTodo();
    }
    
    public void mostrarTodo(){
        PersonaDao persona = new PersonaDao();
        personas = persona.mostrarTodo();
    }
    
    public void modificar(Persona p){
        
        ejbPersona.modificarPersona(p);
        mostrarTodo();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Se modificó correctamente"));
    }
    
    public void cargarData(Persona p){
        this.cumpleaniero = p;
    }
    
}
