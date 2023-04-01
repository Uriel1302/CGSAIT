package Reportes.modelo;

import Reportes.controlador.BeanController;
import Reportes.controlador.Motivos;
import Reportes.controlador.Persona;
import java.sql.Array;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Conexion implements Transacciones {

    // private Connection conn;
    public void Insertar(Persona p, Motivos m) {
        //String url = "jdbc:postgresql://localhost:5432/postgres";
        domain.Conexion conn = new domain.Conexion();
        String formattedString = m.getDias().toString()
                //.replace(",", "")  //remove the commas
                .replace("[", "") //remove the right bracket
                .replace("]", "") //remove the left bracket
                .trim();
        String sql = "INSERT INTO personas(nombre,codigo) VALUES(?,?)";
        String sql2 = "INSERT INTO motivos(dias,mes,motivo,detalle) VALUES(?,?,?,?)";
        try {
            // Class.forName("org.postgresql.Driver");
            //conn = DriverManager.getConnection(url, "postgres", "root");

            PreparedStatement pstmt = conn.getConnection().prepareStatement(sql);
            PreparedStatement pstmt2 = conn.getConnection().prepareStatement(sql2);

            pstmt.setString(1, p.getNombre());
            pstmt.setInt(2, p.getCodigo());
           
            pstmt.executeUpdate();
            pstmt.close();

            pstmt2.setString(1, formattedString);
            pstmt2.setString(2, m.getMes());
            pstmt2.setString(3, m.getMotivo());
            pstmt2.setString(4, m.getDetalle());
            pstmt2.executeUpdate();
            pstmt2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.Close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Persona> obtenerTodo() throws SQLException {
        //String url = "jdbc:postgresql://localhost:5432/postgres";
        domain.Conexion conn = new domain.Conexion();

        List<Persona> listaPersonas = new ArrayList<Persona>();
        String sql = "SELECT nombre,codigo,status,fecha_actual,dias, mes,detalle, motivo FROM personas,motivos WHERE id_p=id_m";

        try {
            Statement stmt = conn.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
// el que formatea
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                Date date = parseador.parse(rs.getString("fecha_actual"));
                Persona persona = new Persona(rs.getInt("codigo"), rs.getString("nombre"), formateador.format(date), rs.getString("dias"), rs.getString("mes"), rs.getString("motivo"), rs.getString("detalle"), rs.getString("status"));
                listaPersonas.add(persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.Close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listaPersonas;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static void main(String[] args) throws SQLException {
        Conexion conexion = new Conexion();
    }

}
