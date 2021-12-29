package main.java.Prenotazione;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import main.java.Validator.InvalidProductException;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

@WebServlet(name = "PrenotazioneServlet", value = "/prenotazione/*")
@MultipartConfig

public class PrenotazioneServlet extends HttpServlet {

  private final PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();
  private PrenotazioneService prenotazioneService;
  private Validator validator;

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
    path = validator.validatePath(path);

    switch (path) {
      case "/":
        RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
      case "/save":
        Prenotazione savePrenotazione = new Prenotazione();
        savePrenotazione.setEmailRichiedente(req.getParameter("email"));
        savePrenotazione.setCategoria(req.getParameter("categoria"));

        Part filePart = req.getPart("copertina");
        String copertina = filePart.getSubmittedFileName();

        savePrenotazione.setCopertina(copertina);

        savePrenotazione.setDescrizione(req.getParameter("descrizione"));
        prenotazioneService = new PrenotazioneServiceImpl();

        try{
          validator.validateImage(copertina, req.getParts());
          for(Part part: req.getParts()){
            if(part.getContentType() != null)
              part.write("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\prenotazioni\\"+copertina);
          }
        }catch (InvalidProductException e) {
          req.getRequestDispatcher("ERRORE PRENOTAZIONE").forward(req, resp);
        }
        if (prenotazioneService.savePrenotazione(savePrenotazione)) {
          resp.setStatus(HttpServletResponse.SC_CREATED);
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
      case "/update":
        Prenotazione updatePrenotazione = new Prenotazione();
        updatePrenotazione.setEmailRichiedente(req.getParameter("email"));
        updatePrenotazione.setCategoria(req.getParameter("categoria"));
        updatePrenotazione.setDescrizione(req.getParameter("descrizione"));
        prenotazioneService = new PrenotazioneServiceImpl();
        if (prenotazioneService.updatePrenotazione(updatePrenotazione)) {
          // SET ALERT
          /*TODO*/
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/delete":
        prenotazioneService = new PrenotazioneServiceImpl();
        if (prenotazioneService.deletePrenotazione(Integer.valueOf(req.getParameter("codice")))) {
          // SET ALERT
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        } else {
          throw new ServletException("Errore di eliminazione...");
        }
    }

  }
}
