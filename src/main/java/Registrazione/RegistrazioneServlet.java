package main.java.Registrazione;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.java.Autenticazione.Utente;
import main.java.Autenticazione.UtenteDAO;
import main.java.Prenotazione.Prenotazione;

@WebServlet(name = "RegistrazioneServlet", value = "/registrazione/*")
public class RegistrazioneServlet extends HttpServlet {

  private final UtenteDAO utenteDAO = new UtenteDAO();

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doGet(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    super.doPost(req, resp);

    String path = req.getPathInfo();

    switch (path) {
      case "/":
        RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
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
        saveUtente.setDataDiNascita(req.getParameter("data"));
        saveUtente.setAdmin(false);
        if (utenteDAO.doSaveUtente(saveUtente)){
          resp.setStatus(HttpServletResponse.SC_CREATED);
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
      case "/update":
        Utente updateUtente = new Utente();
        updateUtente.setUsername(req.getParameter("username"));
        updateUtente.setEmail(req.getParameter("email"));
        updateUtente.setPassword(req.getParameter("password"));
        updateUtente.setNome(req.getParameter("nome"));
        updateUtente.setCognome(req.getParameter("cognome"));
        updateUtente.setSesso(req.getParameter("sesso"));
        updateUtente.setDataDiNascita(req.getParameter("data"));
        updateUtente.setAdmin(false);
        if (utenteDAO.doUpdateUtente(updateUtente)) {
          // SET ALERT
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/delete":
        if (utenteDAO.doDeleteUtente(req.getParameter("username"))) {
          // SET ALERT
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        }
        else {
          throw new ServletException("Errore di eliminazione...");
        }
        }
    }
  }

