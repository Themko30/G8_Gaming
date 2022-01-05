import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Carrello.Servlet.CarrelloServlet;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Catalogo.Servlet.ProdottoServlet;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarrelloServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    CarrelloServlet carrelloServlet = new CarrelloServlet();
    CarrelloServiceImpl carrelloService = Mockito.mock(CarrelloServiceImpl.class);
    OrdineServiceImpl ordineService = Mockito.mock(OrdineServiceImpl.class);
    ValidatorImpl validator = Mockito.mock(ValidatorImpl.class);
    RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

    @Test
    public void testDoPostAdd() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Add");
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getParameter("quantita")).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        carrelloServlet.setCarrelloService(carrelloService);
        carrelloServlet.doPost(request, response);
    }

    @Test
    public void testDoPostModifica() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Modifica");
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getParameter("quantita")).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        carrelloServlet.setCarrelloService(carrelloService);
        carrelloServlet.doPost(request, response);
    }

    @Test
    public void testDoPostRimuovi() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Rimuovi");
        when(request.getParameter("prodotto")).thenReturn("1");
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        carrelloServlet.setCarrelloService(carrelloService);
        carrelloServlet.doPost(request, response);
    }

    @Test
    public void testDoPostCheckout() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Checkout");
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getParameter("indirizzo")).thenReturn("Via da qui, 1");
        when(request.getParameter("CAP")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        when(request.getParameter("metodoPagamento")).thenReturn("Visa");

        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        Carrello c = Mockito.mock(Carrello.class);
        when(session.getAttribute("carrello")).thenReturn(c);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        Ordine o = Mockito.mock(Ordine.class);
        when(ordineService.createOrdine(any(), anyString(), any(), anyString(), anyString())).thenReturn(o);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        Prodotto p = Mockito.mock(Prodotto.class);
        prodotti.put(p,1);
        when(o.getProdotti()).thenReturn(prodotti);
        carrelloServlet.setCarrelloService(carrelloService);
        carrelloServlet.setValidator(validator);
        carrelloServlet.setOrdineService(ordineService);
        carrelloServlet.doPost(request, response);

    }

    @Test
    public void testDoGetDefaultNull() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn(null);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        carrelloServlet.doGet(request, response);
    }

    @Test
    public void testDoGetConfirmOrder() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/confirmOrder");

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        carrelloServlet.doGet(request, response);
    }
}
