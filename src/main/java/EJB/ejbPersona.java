package EJB;

import domain.PersonaDao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import modelo.Persona;

@LocalBean
@Stateless

public class ejbPersona {
    static List<Persona> personas = new ArrayList<Persona>();
    PersonaDao persona = new PersonaDao();
    
    public int insertarPersona(Persona persona){
        return this.persona.insertarPersona(persona);
    }
    
    public List<Persona> mostrarTodo(){
        return this.persona.mostrarTodo();
    }
   
    public int eliminarPersona(int id){
        int reg = persona.eliminarPersona(id);
        if(reg == 0){
            System.out.println("No se eliminò nada");
            return reg;
        }else{
            return reg;
        }
    }
     
    public int modificarPersona(Persona p){
        return this.persona.modificarPersona(p);
     }
    
    public Persona mostrarUno(int id){
        return persona.mostrarUno(id);
    }
}
