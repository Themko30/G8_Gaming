package ControllerIncluded;

import main.java.Catalogo.Servlet.ProdottoServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.when;

public class ProdottoServletIntegration {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    ProdottoServlet prodottoServlet = new ProdottoServlet();

    @Test
    public void testDoGetRicercaAjax() throws IOException, ServletException {
        when(request.getPathInfo()).thenReturn("/Ricerca/api");
        when(request.getHeader("X-Requested-With")).thenReturn("XMLHttpRequest");
        when(request.getParameter("nome")).thenReturn("Demon's");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);

        prodottoServlet.doGet(request, response);
        assertTrue(sw.toString().contains("Demon's Souls"));

    }

}
