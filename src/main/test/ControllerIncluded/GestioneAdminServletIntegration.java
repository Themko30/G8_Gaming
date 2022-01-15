package ControllerIncluded;

import main.java.Gestione_Admin.Servlet.AdminServlet;
import main.java.Storage.Dao.*;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prenotazione;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

public class GestioneAdminServletIntegration {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    AdminServlet adminServlet = new AdminServlet();
    Utente admin = new Utente();

    @Test
    public void testDoPostSetAdmin() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn("/Users/SetAdmin");
        when(request.getParameter("username")).thenReturn("emilio");
        when(request.getSession()).thenReturn(session);

        admin.setAdmin(true);
        when(session.getAttribute("utente")).thenReturn(admin);

        UtenteDAO utenteDAO = new UtenteDAOImpl();

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        adminServlet.doPost(request, response);
        assertTrue(utenteDAO.doRetrieveUtenteByUsername("emilio").isAdmin());
    }

    @Test
    public void testDoPostManageOrder() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn("/Orders/ManageOrder");
        when(request.getParameter("numero")).thenReturn("2");
        when(request.getParameter("stato")).thenReturn("Consegnato");

        when(request.getSession()).thenReturn(session);

        admin.setAdmin(true);
        when(session.getAttribute("utente")).thenReturn(admin);

        OrdineDAO ordineDAO = new OrdineDAOImpl();

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        adminServlet.doPost(request, response);
        ArrayList<Ordine> ordini = ordineDAO.doRetrieveAllOrdini(0, 100);
        Ordine o = null;
        for(Ordine x: ordini){
            if(x.getNumero() == 2)
                o = x;
        }
        assertTrue(o.getStato().equals("Consegnato"));
    }

    @Test
    public void testDoPostManageBooking() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn("/Booking/ManageBooking");
        when(request.getParameter("numeroPrenotazione")).thenReturn("3");

        when(request.getSession()).thenReturn(session);

        admin.setAdmin(true);
        when(session.getAttribute("utente")).thenReturn(admin);

        PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();

        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        adminServlet.doPost(request, response);
        ArrayList<Prenotazione> prenotazioni = prenotazioneDAO.doRetrievePrenotazione(0, 100);
        Prenotazione p = null;
        for(Prenotazione x: prenotazioni){
            if(x.getNumeroPrenotazione() == 3)
                p = x;
        }
        assertTrue(p.getAccettata() == 1);
    }


}
