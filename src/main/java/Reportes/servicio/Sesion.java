package Reportes.servicio;

import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class Sesion {

    private static HttpSession httpSession;
    private static Boolean sesionActiva;

    public static void iniciarSesion(FacesContext fc) {
        httpSession = (HttpSession) fc.getExternalContext().getSession(false);
    }

    public static void setDatosSesion(String nomObjeto, Object objeto) {
        try {
            if (httpSession.getId() != null && !httpSession.getId().isEmpty()) {
                sesionActiva = true;
                httpSession.setAttribute(nomObjeto, objeto);
                httpSession.setAttribute("sesionActiva", sesionActiva);
            } else {
                sesionActiva = false;
                httpSession.setAttribute("sesionActiva", sesionActiva);
                throw new Exception("Error en el inicio de sesiÃ³n");
            }
        } catch (Exception e) {
            httpSession.invalidate();
            e.printStackTrace();
        }
    }

    public static void cerrarSesion() {
        Enumeration<String> atributos = null;

        try {
            if (httpSession != null && httpSession.getId() != null && !httpSession.getId().isEmpty()) {
                sesionActiva = false;

                atributos = httpSession.getAttributeNames();

                while (atributos.hasMoreElements()) {
                    String atributo = atributos.nextElement();

                    httpSession.removeAttribute(atributo);
                }

                httpSession.invalidate();
            } else {
                throw new Exception("Error en el cierre de sesiÃ³n");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getDatosSesion(String dato) {
        if (httpSession != null && httpSession.getId() != null && !httpSession.getId().isEmpty()) {
            httpSession.setAttribute("sesionActiva", sesionActiva);

            return httpSession.getAttribute(dato);
        } else {
            return null;
        }
    }

    public static Boolean getEstadoSesion() {
        if (httpSession != null
                && httpSession.getId() != null
                && !httpSession.getId().isEmpty()
                && httpSession.getAttribute("sesionActiva") != null) {
            return Boolean.parseBoolean(httpSession.getAttribute("sesionActiva").toString());
        } else {
            return false;
        }
    }
}
