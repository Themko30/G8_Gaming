package ControllerIncluded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Registrazione.Servlet.RegistrazioneServlet;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

public class RegistrazioneServletIntegration {

  RegistrazioneServlet registrazioneServlet = new RegistrazioneServlet();
  HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  HttpSession session = Mockito.mock(HttpSession.class);
  RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
  UtenteDAO utenteDAO = new UtenteDAOImpl();

  @Test
  public void testProfile() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/");
    when(request.getSession()).thenReturn(session);
    Utente u = new Utente();
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    registrazioneServlet.doGet(request, response);
  }

  @Test
  public void testDoPostSave() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/save");
    when(request.getSession()).thenReturn(session);
    Utente u = null;
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getParameter("username")).thenReturn("acaro2");
    when(request.getParameter("email")).thenReturn("Xiopani4@gmail.com");
    when(request.getParameter("password")).thenReturn("Xiopani");
    when(request.getParameter("nome")).thenReturn("Xiopani");
    when(request.getParameter("cognome")).thenReturn("Xiopani");
    when(request.getParameter("sesso")).thenReturn("Altro");
    when(request.getParameter("data")).thenReturn("2021-04-01");
    when(request.getParameter("indirizzo")).thenReturn("Xiopani, 15");
    when(request.getParameter("cap")).thenReturn("80053");
    when(request.getParameter("paese")).thenReturn("Xiopani");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    registrazioneServlet.doPost(request, response);

    Utente utenteAfterSave = utenteDAO.doRetrieveUtenteByUsername("acaro2");
    assertEquals("acaro2", utenteAfterSave.getUsername());
  }
}
