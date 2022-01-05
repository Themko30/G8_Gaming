import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Servlet.AuthServlet;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Storage.Entity.Ordine;
import org.junit.Test;
import org.mockito.Mockito;


public class AuthServletTest {

  HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

  RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);

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
}




