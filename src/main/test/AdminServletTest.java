import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Gestione_Admin.Servlet.AdminServlet;
import main.java.Prenotazione.Service.PrenotazioneService;
import main.java.Prenotazione.Service.PrenotazioneServiceImpl;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

public class AdminServletTest {

  AdminServlet adminServlet = new AdminServlet();
  HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);
  RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
  HttpSession session = Mockito.mock(HttpSession.class);
  Utente utente = Mockito.mock(Utente.class);
  UtenteServiceImpl utenteService = Mockito.mock(UtenteServiceImpl.class);
  ProdottoService prodottoService = Mockito.mock(ProdottoServiceImpl.class);
  OrdineService ordineService = Mockito.mock(OrdineServiceImpl.class);
  PrenotazioneService prenotazioneService = Mockito.mock(PrenotazioneServiceImpl.class);

  @Test
  public void testDoGetAdminNullo() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    Utente utente = null;
    when(session.getAttribute("utente")).thenReturn(utente);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetNotAdmin() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(false);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetStatistics() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Statistics");
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetProducts() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Products");
    adminServlet.setProdottoService(prodottoService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetOrders() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Orders");

    adminServlet.setOrdineService(ordineService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetUsers() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Users");
    adminServlet.setUtenteService(utenteService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  /*@Test
  public void testDoGetStatisticsUpdate() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Statistics/Update");
    adminServlet.setUtenteService(utenteService);
    adminServlet.setOrdineService(ordineService);
    adminServlet.setProdottoService(prodottoService);
    adminServlet.setPrenotazioneService(prenotazioneService);
    ServletContext context = Mockito.mock(ServletContext.class);
    HashMap<String, Integer> statistics = new HashMap<>();
    ArrayList<Prodotto> home = new ArrayList<>();

    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }*/

  @Test
  public void testDoGetBooking() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Booking");
    adminServlet.setPrenotazioneService(prenotazioneService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetShowHomePage() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/ShowHomePage");
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetShowAddProducts() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Products/AddProduct");
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetShowModifyProduct() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Products/ModifyProduct");
    when(httpServletRequest.getParameter("codice")).thenReturn("1");
    adminServlet.setProdottoService(prodottoService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetOrdersManageOrders() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Orders/ManageOrder");
    when(httpServletRequest.getParameter("numero")).thenReturn("1");
    adminServlet.setOrdineService(ordineService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

  @Test
  public void testDoGetBookingManageBooking() throws ServletException, IOException {
    when(httpServletRequest.getSession()).thenReturn(session);
    when(session.getAttribute("utente")).thenReturn(utente);
    when(utente.isAdmin()).thenReturn(true);
    when(httpServletRequest.getPathInfo()).thenReturn("/Booking/ManageBooking");
    when(httpServletRequest.getParameter("numeroPrenotazione")).thenReturn("1");
    adminServlet.setPrenotazioneService(prenotazioneService);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    adminServlet.doGet(httpServletRequest, httpServletResponse);
  }

}
