import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Autenticazione.Servlet.AuthServlet;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;


public class AuthServletTest {

  HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

  @Test
  public void testDoGetLogin() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/login");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetProfile() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/profile");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetUpdate() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/update");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetOrdersPage() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/ordersPage");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    OrdineService ordineService = Mockito.mock(OrdineServiceImpl.class);
    HttpSession session = Mockito.mock(HttpSession.class);
    authServlet.setOrdineService(ordineService);
    when(httpServletRequest.getSession()).thenReturn(session);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetOrderView() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/orderView");
    when(httpServletRequest.getParameter("codice")).thenReturn("1");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    OrdineService ordineService = Mockito.mock(OrdineServiceImpl.class);
    Ordine ordine = Mockito.mock(Ordine.class);
    authServlet.setOrdineService(ordineService);
    when(ordineService.retrieveOrder(1)).thenReturn(ordine);
    doNothing().when(httpServletRequest).setAttribute("anyString", ordine);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetLogout() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/logout");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    CarrelloService carrelloService = Mockito.mock(CarrelloServiceImpl.class);
    AuthServlet authServlet = new AuthServlet();
    HttpSession session = Mockito.mock(HttpSession.class);
    authServlet.setCarrelloService(carrelloService);
    when(httpServletRequest.getSession()).thenReturn(session);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoPostUpdateGiusto() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/update");
    AuthServlet authServlet = new AuthServlet();
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    when(httpServletRequest.getParameter("username")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("email")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("password")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("nome")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("cognome")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("sesso")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("data")).thenReturn("2021-04-01");
    LocalDate data = LocalDate.now();
    when(httpServletRequest.getParameter("indirizzo")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("cap")).thenReturn("80053");
    when(httpServletRequest.getParameter("paese")).thenReturn("Xiopani");
    UtenteService utenteService = Mockito.mock(UtenteServiceImpl.class);
    authServlet.setUtenteService(utenteService);
    Utente utente = Mockito.mock(Utente.class);
    when(utenteService.createUtente("xiopani", "xiopani", "xiopani", "xiopani", "xiopani", "xiopani", data, "xiopani", 80053, "xiopani")).thenReturn(utente);
    when(utenteService.updateUtente(any())).thenReturn(true);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoPostUpdateSbagliato() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/update");
    AuthServlet authServlet = new AuthServlet();
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    when(httpServletRequest.getParameter("username")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("email")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("password")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("nome")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("cognome")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("sesso")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("data")).thenReturn("2021-04-01");
    LocalDate data = LocalDate.now();
    when(httpServletRequest.getParameter("indirizzo")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("cap")).thenReturn("80053");
    when(httpServletRequest.getParameter("paese")).thenReturn("Xiopani");
    UtenteService utenteService = Mockito.mock(UtenteServiceImpl.class);
    authServlet.setUtenteService(utenteService);
    Utente utente = Mockito.mock(Utente.class);
    when(utenteService.createUtente("xiopani", "xiopani", "xiopani", "xiopani", "xiopani", "xiopani", data, "xiopani", 80053, "xiopani")).thenReturn(utente);
    when(utenteService.updateUtente(any())).thenReturn(false);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    assertThrows(ServletException.class, () -> authServlet.doPost(httpServletRequest, httpServletResponse));
  }

  @Test
  public void testDoPostLogin() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/login");
    AuthServlet authServlet = new AuthServlet();
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    UtenteService utenteService = Mockito.mock(UtenteServiceImpl.class);
    Utente utente = Mockito.mock(Utente.class);
    authServlet.setUtenteService(utenteService);
    when(httpServletRequest.getParameter("username")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("password")).thenReturn("Xiopani");
    when(utenteService.login(any(), any())).thenReturn(utente);
    CarrelloServiceImpl carrelloService = Mockito.mock(CarrelloServiceImpl.class);
    authServlet.setCarrelloService(carrelloService);
    HttpSession session = Mockito.mock(HttpSession.class);
    when(httpServletRequest.getSession()).thenReturn(session);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoPostLoginUtenteNullo() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/login");
    AuthServlet authServlet = new AuthServlet();
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    UtenteService utenteService = Mockito.mock(UtenteServiceImpl.class);
    authServlet.setUtenteService(utenteService);
    Utente utente = null;
    when(httpServletRequest.getParameter("username")).thenReturn("Xiopani");
    when(httpServletRequest.getParameter("password")).thenReturn("Xiopani");
    CarrelloServiceImpl carrelloService = Mockito.mock(CarrelloServiceImpl.class);
    authServlet.setCarrelloService(carrelloService);
    HttpSession session = Mockito.mock(HttpSession.class);
    when(httpServletRequest.getSession()).thenReturn(session);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoPostUpdateValutazione() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/updateValutazione");
    AuthServlet authServlet = new AuthServlet();
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    ProdottoService prodotti = Mockito.mock(ProdottoServiceImpl.class);
    OrdineService ordineService = Mockito.mock(OrdineServiceImpl.class);
    authServlet.setOrdineService(ordineService);
    authServlet.setProdottoService(prodotti);
    when(httpServletRequest.getParameter("codiceProdotto")).thenReturn("1");
    when(httpServletRequest.getParameter("valutazione")).thenReturn("5");
    when(httpServletRequest.getParameter("codiceOrdine")).thenReturn("1");
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doPost(httpServletRequest, httpServletResponse);
  }

}




