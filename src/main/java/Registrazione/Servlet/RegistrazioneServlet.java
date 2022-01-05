package main.java.Registrazione.Servlet;


import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidUserException;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;

@WebServlet(name = "RegistrazioneServlet", value = "/registrazione/*")
public class RegistrazioneServlet extends HttpServlet {

  private Validator validator;
  private UtenteService utenteService;
  private CarrelloService carrelloService;

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
    path = validator.validatePath(path);

    switch (path) {
      case "/":
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp");
        dispatcher.forward(req, resp);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
    path = validator.validatePath(path);

    String username, email, password, nome, cognome, sesso, indirizzo, paese;
    LocalDate dataDiNascita;
    int cap;

    switch (path) {
      case "/save":
        username = req.getParameter("username");
        email = req.getParameter("email");
        password = req.getParameter("password");
        nome = req.getParameter("nome");
        cognome = req.getParameter("cognome");
        sesso = req.getParameter("sesso");
        dataDiNascita = LocalDate.parse(req.getParameter("data"));
        indirizzo = req.getParameter("indirizzo");
        cap = Integer.parseInt(req.getParameter("cap"));
        paese = req.getParameter("paese");
        utenteService = new UtenteServiceImpl();
        Utente saveUtente = utenteService.createUtente(username, email, password, nome, cognome, sesso, dataDiNascita, indirizzo, cap, paese);
        try {
          validator.validateUtente(saveUtente);
          utenteService.saveUtente(saveUtente);
          resp.setStatus(HttpServletResponse.SC_CREATED);
          HttpSession session = req.getSession(false);
          session.setAttribute("utente", saveUtente);
          carrelloService = new CarrelloServiceImpl();
          Carrello carrello = carrelloService.recuperaCarrello(saveUtente);
          session.setAttribute("carrello", carrello);
          resp.sendRedirect("/G8_Gaming_war_exploded/");
        } catch (InvalidIndirizzoException e) {
          throw new ServletException("Invalid indirizzo...");
        }
        catch (InvalidUserException ex){
          throw new ServletException("Invalid user...");
        } catch(Exception exe) {
          req.setAttribute("errore", 1);
          req.setAttribute("un", username);
          req.setAttribute("pa", password);
          req.setAttribute("pa", paese);
          req.setAttribute("no", nome);
          req.setAttribute("em", email);
          req.setAttribute("co", cognome);
          req.setAttribute("in", indirizzo);
          req.setAttribute("ca", cap);
          req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
        }
        break;
    }
  }
}

