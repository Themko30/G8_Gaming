package ControllerIncluded;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.Prenotazione.Servlet.PrenotazioneServlet;
import org.junit.Test;
import org.mockito.Mockito;

public class PrenotazioneServletIntegration {

  PrenotazioneServlet prenotazioneServlet = new PrenotazioneServlet();
  HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
  HttpSession session = Mockito.mock(HttpSession.class);
  RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);

  @Test
  public void testProfile() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    prenotazioneServlet.doGet(request, response);
  }

}
