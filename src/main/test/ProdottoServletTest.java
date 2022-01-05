import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Catalogo.Servlet.ProdottoServlet;
import main.java.Storage.Entity.Prodotto;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class ProdottoServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    ProdottoServlet prodottoServlet = new ProdottoServlet();
    ProdottoServiceImpl prodottoService = Mockito.mock(ProdottoServiceImpl.class);
    RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

    @Test
    public void testDoGetCategoria() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Categoria");
        when(request.getParameter("categoria")).thenReturn("RPG");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(request).setAttribute(anyString(), anyCollection());
        doNothing().when(request).setAttribute(anyString(), anyString());

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }

    @Test
    public void testDoGetPiattaforma() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Piattaforma");
        when(request.getParameter("piattaforma")).thenReturn("PS5");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(request).setAttribute(anyString(), anyCollection());
        doNothing().when(request).setAttribute(anyString(), anyString());

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }

    @Test
    public void testDoGetRicerca() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Ricerca");
        when(request.getParameter("nome")).thenReturn("Gta");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(request).setAttribute(anyString(), anyCollection());
        doNothing().when(request).setAttribute(anyString(), anyString());

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }

    @Test
    public void testDoGetVisualizza() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Visualizza");
        when(request.getParameter("prodotto")).thenReturn("1");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        doNothing().when(request).setAttribute(anyString(), anyCollection());
        doNothing().when(request).setAttribute(anyString(), anyString());

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }

    @Test
    public void testDoGetRicercaApiTrue() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Ricerca/api");
        when(request.getHeader("X-Requested-With")).thenReturn("XMLHttpRequest");
        when(request.getParameter("nome")).thenReturn("Gta");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(response.getWriter()).thenReturn(Mockito.mock(PrintWriter.class));
        doNothing().when(request).setAttribute(anyString(), anyCollection());
        doNothing().when(request).setAttribute(anyString(), anyString());

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }

    @Test
    public void testDoGetRicercaApiFalse() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/Ricerca/api");
        when(request.getHeader("X-Requested-With")).thenReturn("ciao");

        prodottoServlet.setProdottoService(prodottoService);
        prodottoServlet.doGet(request, response);

    }
}
