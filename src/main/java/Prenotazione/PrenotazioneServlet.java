package main.java.Prenotazione;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrenotazioneServlet", value = "/prenotazione/*")
@MultipartConfig

public class PrenotazioneServlet extends HttpServlet {

  private final PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();

  @Override
  public void init() throws ServletException{
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doPost(req, resp);

    String path = req.getPathInfo();

    switch (path){
      case "/":
        RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
      case "/save":
        Prenotazione savePrenotazione = new Prenotazione();
        savePrenotazione.setEmailRichiedente(req.getParameter("email"));
        savePrenotazione.setCategoria(req.getParameter("categoria"));
        /*savePrenotazione.setCopertina();*/ // DA FARE PER CONFING MULTIPART
        savePrenotazione.setDescrizione(req.getParameter("descrizione"));
        if (prenotazioneDAO.doSavePrenotazione(savePrenotazione)) {
          resp.setStatus(HttpServletResponse.SC_CREATED);
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
        case "/update":
          Prenotazione updatePrenotazione = new Prenotazione();
          updatePrenotazione.setEmailRichiedente(req.getParameter("email"));
          updatePrenotazione.setCategoria(req.getParameter("categoria"));
          /*updatePrenotazione.setCopertina();*/ // DA FARE PER CONFING MULTIPART
          updatePrenotazione.setDescrizione(req.getParameter("descrizione"));
          if (prenotazioneDAO.doUpdatePrenotazione(updatePrenotazione)) {
            // SET ALERT
            req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req,resp);
          } else {
            throw new ServletException("Errore di aggiornamento...");
          }
          break;
          case "/delete":
        if (prenotazioneDAO.doDeletePrenotazione(Integer.valueOf(req.getParameter("codice")))) {
          // SET ALERT
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
            }
        else {
          throw new ServletException("Errore di eliminazione...");
        }
    }

  }
}
