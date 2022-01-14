package Integration.ControllerIncluded;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import main.java.Autenticazione.Servlet.AuthServlet;
import main.java.Prenotazione.Servlet.PrenotazioneServlet;
import main.java.Storage.Entity.Prenotazione;
import main.java.Storage.Entity.Utente;
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

  /*
  //TODO
  public void testDoPostSave() throws ServletException, IOException {
    when(request.getPathInfo()).thenReturn("/save");
    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    when(request.getParameter("descrizione")).thenReturn("aaaaaaaaaaa");
    when(request.getParameter("nomeProdotto")).thenReturn("Xiopani");
    doNothing().when(request).getPart(anyString());
    Part filePart = Mockito.mock(Part.class)
    doNothing().when(filePart).getSubmittedFileName();
    Prenotazione savePrenotazione = new Prenotazione();
    when(savePrenotazione.setCopertina(anyString()))
    prenotazioneServlet.doGet(request, response);
  }*/
}
