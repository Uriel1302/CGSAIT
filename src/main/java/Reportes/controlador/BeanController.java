package Reportes.controlador;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Reportes.modelo.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.PrimeFaces;
import Reportes.servicio.Servicio;

@Named("beanController")
@RequestScoped
public class BeanController {

    static List<Persona> listaPersonas = new ArrayList<Persona>();
    private Persona persona;
    private Motivos motivos;
    private Servicio c = new Servicio();

    @PostConstruct
    public void inicializa() {
        persona = new Persona();
        setMotivos(new Motivos());
    }

    public BeanController() {
        persona = new Persona();
        setMotivos(new Motivos());
    }

    public String Insertar() {
        c.Insertar(persona, motivos);
        return null;
    }

    public Motivos getMotivos() {
        return motivos;
    }

    public void setMotivos(Motivos motivos) {
        this.motivos = motivos;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void exportarPDF() throws JRException, IOException {
        Insertar();
        //String url = "jdbc:postgresql://localhost:5432/postgres";
        domain.Conexion _conn = new domain.Conexion();
       
        Map<String, Object> parametros = new HashMap<String, Object>();
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("invitado/report.jasper"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, _conn.getConnection());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attaachment; filename=Reporte.pdf");
        ServletOutputStream stream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

        stream.flush();
        stream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public List<Persona> getListaPersonas() throws SQLException {
        listaPersonas = c.obtenerTodo();
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public static void main(String[] args) {

    }

}
