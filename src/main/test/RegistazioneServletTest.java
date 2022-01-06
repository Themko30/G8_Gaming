import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Catalogo.Servlet.ProdottoServlet;
import main.java.Registrazione.Servlet.RegistrazioneServlet;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidUserException;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class RegistazioneServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    RegistrazioneServlet registrazioneServlet = new RegistrazioneServlet();
    UtenteServiceImpl utenteService = Mockito.mock(UtenteServiceImpl.class);
    Utente utente = Mockito.mock(Utente.class);
    CarrelloServiceImpl carrelloService = Mockito.mock(CarrelloServiceImpl.class);
    ValidatorImpl validator = Mockito.mock(ValidatorImpl.class);
    RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);


    @Test
    public void testDoGetDefault() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/");
        registrazioneServlet.setValidator(validator);
        registrazioneServlet.doGet(request, response);
    }

    @Test
    public void testDoPostSave() throws ServletException, IOException {
        when(request.getPathInfo()).thenReturn("/save");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/save");
        registrazioneServlet.setValidator(validator);
        registrazioneServlet.doGet(request, response);
        when(request.getParameter("username")).thenReturn("mario");
        when(request.getParameter("email")).thenReturn("mario@gmail.com");
        when(request.getParameter("password")).thenReturn("mariomario2");
        when(request.getParameter("nome")).thenReturn("mario");
        when(request.getParameter("cognome")).thenReturn("biondi");
        when(request.getParameter("sesso")).thenReturn("Maschio");
        when(request.getParameter("data")).thenReturn("2000-11-11");
        when(request.getParameter("cap")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        when(utenteService.createUtente(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(), anyString(), any(Integer.class), anyString())).thenReturn(utente);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        registrazioneServlet.setValidator(validator);
        registrazioneServlet.setUtenteService(utenteService);
        registrazioneServlet.setCarrelloService(carrelloService);
        registrazioneServlet.doPost(request, response);

    }

    @Test
    public void testDoPostSaveFalso() throws Exception {
        when(request.getPathInfo()).thenReturn("/save");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/save");
        registrazioneServlet.setValidator(validator);
        when(request.getParameter("username")).thenReturn("mario");
        when(request.getParameter("email")).thenReturn("mario@gmail.com");
        when(request.getParameter("password")).thenReturn("mariomario2");
        when(request.getParameter("nome")).thenReturn("mario");
        when(request.getParameter("cognome")).thenReturn("biondi");
        when(request.getParameter("sesso")).thenReturn("Maschio");
        when(request.getParameter("data")).thenReturn("2000-11-11");
        when(request.getParameter("cap")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        doThrow(new InvalidIndirizzoException()).when(validator).validateUtente(any());
        when(utenteService.createUtente(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(), anyString(), any(Integer.class), anyString())).thenReturn(utente);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        registrazioneServlet.setValidator(validator);
        registrazioneServlet.setUtenteService(utenteService);
        registrazioneServlet.setCarrelloService(carrelloService);

        assertThrows(ServletException.class, ()->registrazioneServlet.doPost(request, response));


    }

    @Test
    public void testDoPostSaveFalso2() throws Exception {
        when(request.getPathInfo()).thenReturn("/save");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/save");
        registrazioneServlet.setValidator(validator);
        when(request.getParameter("username")).thenReturn("mario");
        when(request.getParameter("email")).thenReturn("mario@gmail.com");
        when(request.getParameter("password")).thenReturn("mariomario2");
        when(request.getParameter("nome")).thenReturn("mario");
        when(request.getParameter("cognome")).thenReturn("biondi");
        when(request.getParameter("sesso")).thenReturn("Maschio");
        when(request.getParameter("data")).thenReturn("2000-11-11");
        when(request.getParameter("cap")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        doThrow(new InvalidUserException()).when(validator).validateUtente(any());
        when(utenteService.createUtente(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(), anyString(), any(Integer.class), anyString())).thenReturn(utente);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        registrazioneServlet.setValidator(validator);
        registrazioneServlet.setUtenteService(utenteService);
        registrazioneServlet.setCarrelloService(carrelloService);

        assertThrows(ServletException.class, ()->registrazioneServlet.doPost(request, response));


    }

    @Test
    public void testDoPostSaveFalso3() throws Exception{
        when(request.getPathInfo()).thenReturn("/save");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/save");
        registrazioneServlet.setValidator(validator);
        when(request.getParameter("username")).thenReturn("mario");
        when(request.getParameter("email")).thenReturn("mario@gmail.com");
        when(request.getParameter("password")).thenReturn("mariomario2");
        when(request.getParameter("nome")).thenReturn("mario");
        when(request.getParameter("cognome")).thenReturn("biondi");
        when(request.getParameter("sesso")).thenReturn("Maschio");
        when(request.getParameter("data")).thenReturn("2000-11-11");
        when(request.getParameter("cap")).thenReturn("80053");
        when(request.getParameter("paese")).thenReturn("Italia");
        doThrow(new Exception()).when(validator).validateUtente(any());
        when(utenteService.createUtente(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(), anyString(), any(Integer.class), anyString())).thenReturn(utente);
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);

        registrazioneServlet.setValidator(validator);
        registrazioneServlet.setUtenteService(utenteService);
        registrazioneServlet.setCarrelloService(carrelloService);

        registrazioneServlet.doPost(request, response);


    }

}
