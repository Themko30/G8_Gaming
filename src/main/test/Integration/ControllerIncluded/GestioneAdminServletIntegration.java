package Integration.ControllerIncluded;

import main.java.Catalogo.Servlet.ProdottoServlet;
import main.java.Gestione_Admin.Servlet.AdminServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

public class GestioneAdminServletIntegration {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    AdminServlet adminServlet = new AdminServlet();

    @Test
    public void testDoPostAddProduct() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn("/Products/AddProduct");
        when(request.getParameter("categoria")).thenReturn("RPG");
        when(request.getParameter("nome")).thenReturn("Nuovo RPG");
        when(request.getParameter("piattaforma")).thenReturn("PlayStation 5");
        when(request.getParameter("prezzo")).thenReturn("79.99");
        when(request.getParameter("scontoAttivo")).thenReturn("0.0");
        when(request.getParameter("quantita")).thenReturn("10");
        when(request.getParameter("descrizione")).thenReturn("Questo dovrebbe essere il nuovo gioco inserito");
        when(request.getParameter("categoria")).thenReturn("RPG");
        Part part = Mockito.mock(Part.class);
        when(request.getPart("copertina")).thenReturn(part);
        when(part.getSubmittedFileName()).thenReturn("nuova_copertina.jpg");






        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }
}
