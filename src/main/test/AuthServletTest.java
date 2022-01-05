import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Servlet.AuthServlet;
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
  public void testDoGetordersPage() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/ordersPage");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    AuthServlet authServlet = new AuthServlet();
    HttpSession session1 = Mockito.mock(HttpSession.class);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    authServlet.doGet(httpServletRequest, httpServletResponse);
  }

}
