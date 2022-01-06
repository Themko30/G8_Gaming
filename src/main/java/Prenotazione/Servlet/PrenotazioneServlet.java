package main.java.Prenotazione.Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import main.java.Prenotazione.Service.PrenotazioneService;
import main.java.Prenotazione.Service.PrenotazioneServiceImpl;
import main.java.Storage.Entity.Prenotazione;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;

@WebServlet(name = "PrenotazioneServlet", value = "/prenotazione/*")
@MultipartConfig

public class PrenotazioneServlet extends HttpServlet {

  PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();
  private Validator validator = new ValidatorImpl();

  public void setPrenotazioneService(PrenotazioneService prenotazioneService) {
    this.prenotazioneService = prenotazioneService;
  }

  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String path = req.getPathInfo();
    path = validator.validatePath(path);

    switch (path) {
      case "/":
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/prenota.jsp");
        dispatcher.forward(req, resp);
        break;
    }
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String path = req.getPathInfo();
    path = validator.validatePath(path);

    switch (path) {
      case "/save":
        Prenotazione savePrenotazione = new Prenotazione();
        savePrenotazione.setEmailRichiedente(req.getParameter("email"));
        savePrenotazione.setCategoria(req.getParameter("categoria"));

        Part filePart = req.getPart("copertina");
        String copertina = filePart.getSubmittedFileName();

        savePrenotazione.setCopertina(copertina);

        savePrenotazione.setDescrizione(req.getParameter("descrizione"));
        savePrenotazione.setNomeProdotto(req.getParameter("nomeProdotto"));

        try {
          validator.validateImage(copertina, req.getParts());
        } catch (InvalidProductException e) {
          /*TODO*/
          req.getRequestDispatcher("ERRORE PRENOTAZIONE").forward(req, resp);
        }
        if (prenotazioneService.savePrenotazione(savePrenotazione)) {
          resp.setStatus(HttpServletResponse.SC_CREATED);
          req.getRequestDispatcher("/WEB-INF/views/user/prenotazione_effettuata.jsp").forward(req, resp);
        } else {
          throw new ServletException("Errore di inserimento...");
        }
        break;
    }

  }
}
