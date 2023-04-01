package Reportes.servicio;

import Reportes.controlador.BeanController;
import Reportes.controlador.Motivos;
import Reportes.controlador.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Reportes.modelo.Conexion;

public class Servicio {

    public void Insertar(Persona p, Motivos m) {
        Conexion conexion = new Conexion();
        conexion.Insertar(p, m);
    }

    public List<Persona> obtenerTodo() throws SQLException {
        Conexion conexion = new Conexion();
        return conexion.obtenerTodo();
    }

}
