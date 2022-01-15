package ControllerIncluded;

import main.java.Carrello.Servlet.CarrelloServlet;
import main.java.Storage.Dao.OrdineDAOImpl;
import main.java.Storage.Dao.ProdottoDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

public class CarrelloServletIntegration {
    CarrelloServlet carrelloServlet = new CarrelloServlet();
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    @Test
    public void testDoPostUtenteNull() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("utente")).thenReturn(null);
        carrelloServlet.doPost(request, response);

    }

    @Test
    public void testDoPostAdd() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/Add");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Carrello c = new Carrello();
        Utente u = new Utente();
        when(session.getAttribute("carrello")).thenReturn(c);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getParameter("quantita")).thenReturn("1");

        carrelloServlet.doPost(request, response);

        assertTrue(c.getProdotti().size() == 1);
        assertTrue(c.getTotale() == 79.98);
        assertTrue(c.getNumeroArticoli() == 1);

    }

    @Test
    public void testDoPostModify() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/Modifica");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Carrello c = new Carrello();
        Prodotto p = new Prodotto();
        p.setCodice(1);
        p.setQuantita(50);
        p.setPrezzo(79.99);
        p.setScontoAttivo(0.0);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(p, 1);
        c.setProdotti(prodotti);
        c.setNumeroArticoli(1);
        c.setTotale(79.98);
        Utente u = new Utente();
        when(session.getAttribute("carrello")).thenReturn(c);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getParameter("quantita")).thenReturn("2");

        carrelloServlet.doPost(request, response);

        assertTrue(c.getProdotti().size() == 1);
        assertTrue(c.getTotale() == 159.96);
        assertTrue(c.getNumeroArticoli() == 2);

    }

    @Test
    public void testDoPostRimuovi() throws ServletException, IOException{
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/Rimuovi");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Carrello c = new Carrello();
        Prodotto p = new Prodotto();
        p.setCodice(1);
        p.setQuantita(50);
        p.setPrezzo(79.99);
        p.setScontoAttivo(0.0);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(p, 1);
        c.setProdotti(prodotti);
        c.setNumeroArticoli(1);
        c.setTotale(79.98);
        Utente u = new Utente();
        when(session.getAttribute("carrello")).thenReturn(c);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getParameter("prodotto")).thenReturn("1");

        carrelloServlet.doPost(request, response);

        assertTrue(c.getProdotti().size() == 0);
        assertTrue(c.getTotale() == 0.0);
        assertTrue(c.getNumeroArticoli() == 0);
    }

    @Test
    public void testDoPostCheckout() throws IOException, ServletException{
        when(request.getSession()).thenReturn(session);
        when(request.getPathInfo()).thenReturn("/Checkout");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        Carrello c = new Carrello();
        Prodotto p = new Prodotto();
        p.setCodice(1);
        p.setQuantita(50);
        p.setPrezzo(79.99);
        p.setScontoAttivo(0.0);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(p, 1);
        c.setProdotti(prodotti);
        c.setNumeroArticoli(1);
        c.setTotale(79.98);
        Utente u = new Utente();
        u.setUsername("acaro");
        c.setUtente(u);
        when(session.getAttribute("carrello")).thenReturn(c);
        when(session.getAttribute("utente")).thenReturn(u);
        when(request.getParameter("indirizzo")).thenReturn("Via Bene, 1");
        when(request.getParameter("CAP")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        when(request.getParameter("metodoPagamento")).thenReturn("Visa");

        carrelloServlet.doPost(request, response);


        ProdottoDAOImpl prodottoDAO = new ProdottoDAOImpl();
        int quantitaProdotto = prodottoDAO.doRetrieveQuantitaProdottoByCodice(1);
        assertTrue(quantitaProdotto == 11);

        OrdineDAOImpl ordineDAO = new OrdineDAOImpl();
        List<Ordine> ordini = ordineDAO.doRetrieveOrdiniByUtente(u);
        assertTrue(ordini.size() == 11);
    }


}
