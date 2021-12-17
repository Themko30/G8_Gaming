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
import main.java.Prenotazione.Prenotazione;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

@WebServlet(name = "RegistrazioneServlet", value = "/registrazione/*")
public class RegistrazioneServlet extends HttpServlet {

  private Validator validator;

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
        /*TODO*/ RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String path = req.getPathInfo();
    if(path == null)
      path = "/";

    switch (path) {
      case "/":
        /*TODO*/  RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
      case "/save":
        Utente saveUtente = new Utente();
        saveUtente.setUsername(req.getParameter("username"));
        saveUtente.setEmail(req.getParameter("email"));
        saveUtente.setPassword(req.getParameter("password"));
        saveUtente.setNome(req.getParameter("nome"));
        saveUtente.setCognome(req.getParameter("cognome"));
        saveUtente.setSesso(req.getParameter("sesso"));
        saveUtente.setDataDiNascita(LocalDate.parse(req.getParameter("data")));
        saveUtente.setAdmin(false);
        if (utenteDAO.doSaveUtente(saveUtente)){
          resp.setStatus(HttpServletResponse.SC_CREATED);
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
      case "/update":
        String username = req.getParameter("username");
        String email =req.getParameter("email");
        String password =req.getParameter("password");
        String nome =req.getParameter("nome");
        String cognome =req.getParameter("cognome");
        String sesso =req.getParameter("sesso");
        LocalDate dataDiNascita = LocalDate.parse(req.getParameter("data"));
                if (utenteDAO.doUpdateUtente(updateUtente)) {
          // SET ALERT
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/delete":
        if (utenteDAO.doDeleteUtente(req.getParameter("username"))) {
          // SET ALERT
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        }
        else {
          throw new ServletException("Errore di eliminazione...");
        }
        }
    }
  }

