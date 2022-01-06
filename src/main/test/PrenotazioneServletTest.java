import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import main.java.Prenotazione.Service.PrenotazioneService;
import main.java.Prenotazione.Servlet.PrenotazioneServlet;
import main.java.Storage.Entity.Prenotazione;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Service.Validator;
import org.junit.Test;
import org.mockito.Mockito;

public class PrenotazioneServletTest {

  HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
  HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

  @Test
  public void testDoGetDefault() throws ServletException, IOException {
    when(httpServletRequest.getPathInfo()).thenReturn("/");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    PrenotazioneServlet prenotazioneServlet = new PrenotazioneServlet();
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    prenotazioneServlet.doGet(httpServletRequest, httpServletResponse);

  }

  @Test
  public void testDoPostSave() throws ServletException, IOException, InvalidProductException {
    when(httpServletRequest.getPathInfo()).thenReturn("/save");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    PrenotazioneServlet prenotazioneServlet = new PrenotazioneServlet();
    Prenotazione prenotazione = Mockito.mock(Prenotazione.class);
    Validator validator = Mockito.mock(Validator.class);
    prenotazioneServlet.setValidator(validator);
    when(validator.validatePath(any())).thenReturn("/save");
    doNothing().when(prenotazione).setEmailRichiedente(any());
    doNothing().when(prenotazione).setCategoria(any());
    Part part = Mockito.mock(Part.class);
    when(httpServletRequest.getPart(any())).thenReturn(part);
    when(part.getSubmittedFileName()).thenReturn("Paolo");
    doNothing().when(prenotazione).setCategoria(any());
    doNothing().when(prenotazione).setDescrizione(any());
    doNothing().when(prenotazione).setNomeProdotto(any());
    doNothing().when(validator).validateImage(any(), any());
    PrenotazioneService prenotazioneService = Mockito.mock(PrenotazioneService.class);
    prenotazioneServlet.setPrenotazioneService(prenotazioneService);
    when(prenotazioneService.savePrenotazione(any())).thenReturn(true);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    prenotazioneServlet.doPost(httpServletRequest, httpServletResponse);

  }

  @Test
  public void testDoPostSaveErrato() throws ServletException, IOException, InvalidProductException {
    when(httpServletRequest.getPathInfo()).thenReturn("/save");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    PrenotazioneServlet prenotazioneServlet = new PrenotazioneServlet();
    Prenotazione prenotazione = Mockito.mock(Prenotazione.class);
    Validator validator = Mockito.mock(Validator.class);
    prenotazioneServlet.setValidator(validator);
    when(validator.validatePath(any())).thenReturn("/save");
    doNothing().when(prenotazione).setEmailRichiedente(any());
    doNothing().when(prenotazione).setCategoria(any());
    Part part = Mockito.mock(Part.class);
    when(httpServletRequest.getPart(any())).thenReturn(part);
    when(part.getSubmittedFileName()).thenReturn("Paolo");
    doNothing().when(prenotazione).setCategoria(any());
    doNothing().when(prenotazione).setDescrizione(any());
    doNothing().when(prenotazione).setNomeProdotto(any());
    doNothing().when(validator).validateImage(any(), any());
    PrenotazioneService prenotazioneService = Mockito.mock(PrenotazioneService.class);
    prenotazioneServlet.setPrenotazioneService(prenotazioneService);
    when(prenotazioneService.savePrenotazione(any())).thenReturn(false);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    assertThrows(ServletException.class, () -> prenotazioneServlet.doPost(httpServletRequest, httpServletResponse));
  }

  @Test
  public void testDoPostSaveErratoValidator() throws ServletException, IOException, InvalidProductException {
    when(httpServletRequest.getPathInfo()).thenReturn("/save");
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    PrenotazioneServlet prenotazioneServlet = new PrenotazioneServlet();
    Prenotazione prenotazione = Mockito.mock(Prenotazione.class);
    Validator validator = Mockito.mock(Validator.class);
    prenotazioneServlet.setValidator(validator);
    when(validator.validatePath(any())).thenReturn("/save");
    doNothing().when(prenotazione).setEmailRichiedente(any());
    doNothing().when(prenotazione).setCategoria(any());
    Part part = Mockito.mock(Part.class);
    when(httpServletRequest.getPart(any())).thenReturn(part);
    when(part.getSubmittedFileName()).thenReturn("Paolo");
    doNothing().when(prenotazione).setCategoria(any());
    doNothing().when(prenotazione).setDescrizione(any());
    doNothing().when(prenotazione).setNomeProdotto(any());
    doThrow(new InvalidProductException()).when(validator).validateImage(anyString(), anyCollection());
    PrenotazioneService prenotazioneService = Mockito.mock(PrenotazioneService.class);
    prenotazioneServlet.setPrenotazioneService(prenotazioneService);
    when(prenotazioneService.savePrenotazione(any())).thenReturn(true);
    when(httpServletRequest.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    prenotazioneServlet.doPost(httpServletRequest, httpServletResponse);
  }

}
