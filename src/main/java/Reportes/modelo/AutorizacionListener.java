package Reportes.modelo;

import java.io.IOException;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import Reportes.servicio.Sesion;

public class AutorizacionListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {

        try {
            String paginaActual = event.getFacesContext().getViewRoot().getViewId();
            Sesion.iniciarSesion(event.getFacesContext());

            if ((paginaActual.contains("/admin/Datos.xhtml") || paginaActual.contains("/admin/admin.xhtml") || paginaActual.contains("/admin/index.xhtml")) && Sesion.getEstadoSesion() == false) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml?faces-redirect=true");
                NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
                nh.handleNavigation(event.getFacesContext(), null, "index");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
