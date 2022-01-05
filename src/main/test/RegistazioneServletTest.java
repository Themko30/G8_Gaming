import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Catalogo.Servlet.ProdottoServlet;
import main.java.Registrazione.Servlet.RegistrazioneServlet;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RegistazioneServletTest {
    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    RegistrazioneServlet registrazioneServlet = new RegistrazioneServlet();
    UtenteServiceImpl utenteService = Mockito.mock(UtenteServiceImpl.class);
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
        when(request.getPathInfo()).thenReturn("/");
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(validator.validatePath(anyString())).thenReturn("/");
        registrazioneServlet.setValidator(validator);
        registrazioneServlet.doGet(request, response);
    }

}
