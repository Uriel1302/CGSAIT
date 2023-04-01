package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Persona;

public class PersonaDao implements Serializable {

    public static final String SQL_INSERT = "INSERT INTO cumpleanios(nombre, apellido_paterno, apellido_materno, fecha_cumpleanios, email) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT = "SELECT id, nombre, apellido_paterno, apellido_materno, fecha_cumpleanios, email FROM cumpleanios order by nombre, apellido_paterno, apellido_materno";
    private static final String SQL_DELETE = "DELETE FROM cumpleanios WHERE id=?";
    private static final String SQL_UPDATE = "UPDATE cumpleanios SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, fecha_cumpleanios = ?, email = ? WHERE id=?";
    private static final String SQL_SELECTONE = "SELECT * FROM cumpleanios WHERE id = ";

    public int insertarPersona(Persona persona) {
        Conexion conn = new Conexion();
        PreparedStatement stmt = null;

        int rows = 0;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            stmt = conn.getConnection().prepareStatement(SQL_INSERT);
            stmt.clearParameters();
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido_paterno());
            stmt.setString(3, persona.getApellido_materno());
            java.sql.Date sql = new java.sql.Date(persona.getFecha_cumpleanios().getTime());
            stmt.setDate(4, sql);
            stmt.setString(5, persona.getEmail());

            rows = stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        conn.Close();
        return rows;
    }

    public int eliminarPersona(int id) {
        Conexion conn = new Conexion();
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            stmt = conn.getConnection().prepareStatement(SQL_DELETE);
            stmt.clearParameters();
            stmt.setInt(1, id);
            rows = stmt.executeUpdate();

            if (stmt != null) {
                stmt.close();
            } else if (conn != null) {
                conn.Close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rows;
    }

    private Persona convertir(ResultSet set) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String nombre = set.getString("nombre");
        String apellido_paterno = set.getString("apellido_paterno");
        String apellido_materno = set.getString("apellido_materno");
        Date fecha_cumpleanios = set.getDate("fecha_cumpleanios");
        String email = set.getString("email");
        Persona persona = new Persona(nombre, apellido_paterno, apellido_materno, fecha_cumpleanios, email);
        persona.setId(set.getInt("id"));
        return persona;
    }

    public List<Persona> mostrarTodo() {
        Conexion conn = new Conexion();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Persona> personas = new ArrayList<>();

        try {
            stmt = conn.getConnection().prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                personas.add(convertir(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            conn.Close();
        }
        return personas;
    }

    public int modificarPersona(Persona p) {
        Conexion conn = new Conexion();
        PreparedStatement stmt = null;

        int rows = 0;

        try {
            stmt = conn.getConnection().prepareStatement(SQL_UPDATE);
            stmt.clearParameters();
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getApellido_paterno());
            stmt.setString(3, p.getApellido_materno());
            java.sql.Date sql = new java.sql.Date(p.getFecha_cumpleanios().getTime());
            stmt.setDate(4, sql);
            //stmt.setDate(4, (java.sql.Date) p.getFecha_cumpleanios());
            stmt.setString(5, p.getEmail());
            stmt.setInt(6, p.getId());
            rows = stmt.executeUpdate();

            stmt.close();

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
            ex.printStackTrace(System.out);
        }
        conn.Close();
        return rows;
    }

    public Persona mostrarUno(int id) {
        Conexion conn = new Conexion();
        Persona persona = new Persona();

        try {
            Statement stmt = conn.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(SQL_SELECTONE + id);
            while (rs.next()) {
                persona.setEmail(rs.getString("email"));
                persona.setApellido_materno(rs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }

        return persona;
    }

}
