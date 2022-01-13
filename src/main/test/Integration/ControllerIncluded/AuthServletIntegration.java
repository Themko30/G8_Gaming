package Integration.ControllerIncluded;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Servlet.AuthServlet;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

public class AuthServletIntegration {

  AuthServlet authServlet = new AuthServlet();
  HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  HttpSession session = Mockito.mock(HttpSession.class);
  RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

  @Test
  public void testProfile() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/profile");
    when(request.getSession()).thenReturn(session);
    Utente u = new Utente();
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(request, response);
  }

  @Test
  public void testUpdate() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/update");
    when(request.getSession()).thenReturn(session);
    Utente u = new Utente();
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(request, response);
  }

  @Test
  public void testOrdersPage() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/ordersPage");
    when(request.getSession()).thenReturn(session);
    Utente u = new Utente();
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(request, response);
  }

  @Test
  public void testOrderView() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/orderView");
    when(request.getSession()).thenReturn(session);
    when(request.getParameter("codice")).thenReturn("2");
    Utente u = new Utente();
    when(session.getAttribute("utente")).thenReturn(u);
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(request, response);
  }

  @Test
  public void testLogout() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/logout");
    when(request.getSession()).thenReturn(session);
    Utente u = new Utente();
    Carrello carrello = new Carrello();
    when(session.getAttribute("carrello")).thenReturn(carrello);
    u.setUsername("acaro");
    carrello.setUtente(u);
    doNothing().when(session).removeAttribute("utente");
    doNothing().when(session).removeAttribute("carrello");
    doNothing().when(session).invalidate();
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(request, response);
  }

  @Test
  public void testDoPostUpdate() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/update");
    when(request.getSession()).thenReturn(session);
    when(request.getParameter("username")).thenReturn("emilio");
    when(request.getParameter("email")).thenReturn("Xiopani");
    when(request.getParameter("password")).thenReturn("Xiopani");
    when(request.getParameter("nome")).thenReturn("Xiopani");
    when(request.getParameter("cognome")).thenReturn("Xiopani");
    when(request.getParameter("sesso")).thenReturn("Xiopani");
    when(request.getParameter("data")).thenReturn("2021-04-01");
    LocalDate data = LocalDate.now();
    when(request.getParameter("indirizzo")).thenReturn("Xiopani");
    when(request.getParameter("cap")).thenReturn("80053");
    when(request.getParameter("paese")).thenReturn("Xiopani");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(request, response);
  }

  @Test
  public void testDoPostLogin() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/login");
    when(request.getSession()).thenReturn(session);
    when(request.getParameter("username")).thenReturn("acaro");
    when(request.getParameter("password")).thenReturn("antonino");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(request, response);
  }

  @Test
  //TODO
  public void testDoPostUpdateValutazione() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/updateValutazione");
    when(request.getSession()).thenReturn(session);
    when(request.getParameter("codiceProdotto")).thenReturn("15");
    when(request.getParameter("password")).thenReturn("antonino");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(request, response);
  }

}
