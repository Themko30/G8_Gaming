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

  /**
   * Creazione di Validator.
   */
  private Validator validator = new ValidatorImpl();
  /**
   * Creazione di UtenteService.
   */
  private UtenteService utenteService = new UtenteServiceImpl();
  /**
   * Creazione di CarrelloService.
   */
  private CarrelloService carrelloService = new CarrelloServiceImpl();

  /**
   * Set del validator per fini di testing.
   * @param validator il validator che controlla i dati.
   */
  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  /**
   * Set del utenteService per fini di testing.
   * @param utenteService il service di utente.
   */
  public void setUtenteService(UtenteService utenteService) {
    this.utenteService = utenteService;
  }

  /**
   * Set del carrelloService per fini di testing.
   * @param carrelloService il service di carrello
   */
  public void setCarrelloService(CarrelloService carrelloService) {
    this.carrelloService = carrelloService;
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String path = req.getPathInfo();
    path = validator.validatePath(path);

    HttpSession session = req.getSession();
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente != null) {
      resp.sendRedirect(
              "http://localhost:8080/G8_Gaming_war_exploded/account/profile");
      return;
    }

    switch (path) {
      case "/":
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/"
                        + "views/user/registration.jsp");
        dispatcher.forward(req, resp);
        break;
      default:
        resp.sendError(404);
        break;
    }
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    String path = req.getPathInfo();
    path = validator.validatePath(path);

    String username;
    String email;
    String password;
    String nome;
    String cognome;
    String sesso;
    String indirizzo;
    String paese;
    LocalDate dataDiNascita;
    int cap;

    switch (path) {
      case "/save":
        username = req.getParameter("username");
        email = req.getParameter("email").toLowerCase();
        password = req.getParameter("password");
        nome = req.getParameter("nome");
        cognome = req.getParameter("cognome");
        sesso = req.getParameter("sesso");
        dataDiNascita = LocalDate.parse(req.getParameter("data"));
        indirizzo = req.getParameter("indirizzo");
        cap = Integer.parseInt(req.getParameter("cap"));
        paese = req.getParameter("paese");
        Utente saveUtente =
                utenteService.createUtente(username,
                        email, password, nome, cognome,
                        sesso, dataDiNascita, indirizzo,
                        cap, paese);
        try {
          validator.validateUtente(saveUtente);
          utenteService.saveUtente(saveUtente);
          resp.setStatus(HttpServletResponse.SC_CREATED);
          HttpSession session = req.getSession();
          session.setAttribute("utente", saveUtente);
          Carrello carrello =
                  carrelloService.recuperaCarrello(saveUtente);
          session.setAttribute("carrello", carrello);
          resp.sendRedirect("/G8_Gaming_war_exploded/");
        } catch (InvalidIndirizzoException e) {
          throw new ServletException("Invalid indirizzo...");
        } catch (InvalidUserException ex) {
          throw new ServletException("Invalid user...");
        } catch (Exception exe) {
          req.setAttribute("errore", 1);
          req.setAttribute("un", username);
          req.setAttribute("pa", password);
          req.setAttribute("pa", paese);
          req.setAttribute("no", nome);
          req.setAttribute("em", email);
          req.setAttribute("co", cognome);
          req.setAttribute("in", indirizzo);
          req.setAttribute("ca", cap);
          req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp")
                  .forward(req, resp);
        }
        break;
      default:
        resp.sendError(404);
        break;
    }
  }
}

