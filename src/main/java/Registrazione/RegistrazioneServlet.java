package main.java.Registrazione;


import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.Autenticazione.Utente;
import main.java.Autenticazione.UtenteDAO;
import main.java.Autenticazione.UtenteImpl;
import main.java.Autenticazione.UtenteService;
import main.java.Prenotazione.Prenotazione;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

@WebServlet(name = "RegistrazioneServlet", value = "/registrazione/*")
public class RegistrazioneServlet extends HttpServlet {

  private Validator validator;
  private UtenteService utenteService;

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String path = req.getPathInfo();
    validator= new ValidatorImpl();
    path= validator.validatePath(path);

    switch (path) {
      case "/":
        /*TODO*/ RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp");
        dispatcher.forward(req, resp);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String path = req.getPathInfo();
    validator= new ValidatorImpl();
    path= validator.validatePath(path);

    String username, email, password, nome, cognome, sesso;
    LocalDate dataDiNascita;

    switch (path) {
      case "/":
        /*TODO*/  RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
      case "/save":
        username = req.getParameter("username");
        email =req.getParameter("email");
        password =req.getParameter("password");
        nome =req.getParameter("nome");
        cognome =req.getParameter("cognome");
        sesso =req.getParameter("sesso");
        dataDiNascita = LocalDate.parse(req.getParameter("data"));
        utenteService = new UtenteImpl();
        Utente saveUtente = utenteService.createUtente(username,email,password,nome,cognome,sesso,dataDiNascita);
        if (utenteService.saveUtente(saveUtente)){
          resp.setStatus(HttpServletResponse.SC_CREATED);
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
      case "/update":
        username = req.getParameter("username");
        email =req.getParameter("email");
        password =req.getParameter("password");
        nome =req.getParameter("nome");
        cognome =req.getParameter("cognome");
        sesso =req.getParameter("sesso");
        dataDiNascita = LocalDate.parse(req.getParameter("data"));
        utenteService = new UtenteImpl();
        Utente updateUtente = utenteService.createUtente(username,email,password,nome,cognome,sesso,dataDiNascita);
        if (utenteService.updateUtente(updateUtente)){
          // SET ALERT
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/delete":
        username = req.getParameter("username");
        utenteService = new UtenteImpl();
        if (utenteService.deleteUtente(req.getParameter("username"))) {
          // SET ALERT
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        }
        else {
          throw new ServletException("Errore di eliminazione...");
        }
        }
    }
  }

