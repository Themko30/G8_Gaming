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

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
    path = validator.validatePath(path);

    String username, email, password, nome, cognome, sesso, indirizzo, paese;
    LocalDate dataDiNascita;
    int cap;

    switch (path) {
      case "/":
        /*TODO*/
        RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
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
          utenteService.saveUtente(saveUtente);
          resp.setStatus(HttpServletResponse.SC_CREATED);
          HttpSession session = req.getSession(false);
          session.setAttribute("utente", saveUtente);
          carrelloService = new CarrelloServiceImpl();
          Carrello carrello = carrelloService.recuperaCarrello(saveUtente);
          session.setAttribute("carrello", carrello);
          resp.sendRedirect("/G8_Gaming_war_exploded/");
        } catch (Exception e) {
          HttpSession session2 = req.getSession(false);
          session2.setAttribute("errore", 1);
          session2.setAttribute("us", username);
          session2.setAttribute("pw", password);
          session2.setAttribute("pa", paese);
          session2.setAttribute("no", nome);
          session2.setAttribute("em", email);
          session2.setAttribute("co", cognome);
          session2.setAttribute("in", indirizzo);
          session2.setAttribute("ca", cap);
          session2.setAttribute("errore", 1);
          req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
        }
        break;
      case "/update":
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
        Utente updateUtente = utenteService.createUtente(username, email, password, nome, cognome, sesso, dataDiNascita, indirizzo, cap, paese);
        if (utenteService.updateUtente(updateUtente)) {
          // SET ALERT
          /*TODO*/
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/delete":
        username = req.getParameter("username");
        utenteService = new UtenteServiceImpl();
        if (utenteService.deleteUtente(req.getParameter("username"))) {
          // SET ALERT
          /*TODO*/
          req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
        } else {
          throw new ServletException("Errore di eliminazione...");
        }
        break;
    }
  }
}

