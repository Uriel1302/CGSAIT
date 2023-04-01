package Reportes.controlador;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import Reportes.servicio.Sesion;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String name;
    private String pass;
    private boolean isLogged = false;

    public LoginBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String action() throws IOException {
        Connection conn;
        Statement pst;
        ResultSet rs;
        int nivel = 0;
        boolean verified = false;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String sql = "SELECT nivel FROM login WHERE usuario='" + name + "' AND contra='" + pass + "'";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, "postgres", "root");
            pst = conn.createStatement();
            rs = pst.executeQuery(sql);
            while (rs.next()) {
                nivel = rs.getInt(1);
                if (nivel == 1) {
                    verified = true;
                }
            }
            conn.close();
        } catch (Exception e) {
        }
        if (verified) {
            this.name = null;
            this.pass = null;
            Sesion.iniciarSesion(FacesContext.getCurrentInstance());
            Sesion.setDatosSesion("nomUsuario", this.name);

            return "/admin/index.xhtml?faces-redirect=true";
        } else {
            this.name = null;
            this.pass = null;
            isLogged = false;
            LoginBean bean = new LoginBean();
            bean.isLogged = false;

            return "fail.xhtml?faces-redirect=true";

        }

    }

    public String cerrarSesion() {
        try {
            Sesion.iniciarSesion(FacesContext.getCurrentInstance());
            Sesion.cerrarSesion();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml?faces-redirect=true");
            return "../login.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
