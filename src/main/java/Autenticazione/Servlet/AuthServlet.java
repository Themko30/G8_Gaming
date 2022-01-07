package main.java.Autenticazione.Servlet;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;


@WebServlet(name = "AuthServlet", value = "/account/*")

public class AuthServlet extends HttpServlet {

  /**
   * Creazione di Validator.
   */
  private Validator validator = new ValidatorImpl();
  /**
   * Creazione di UtenteService.
   */
  private UtenteService utenteService = new UtenteServiceImpl();
  /**
   * Creazione di OrdineService.
   */
  private OrdineService ordineService = new OrdineServiceImpl();
  /**
   * Creazione di ProdottoService.
   */
  private ProdottoService prodottoService = new ProdottoServiceImpl();
  /**
   * Creazione di CarrelloService.
   */
  private CarrelloService carrelloService = new CarrelloServiceImpl();

  /**
   * Set di Validator ai fini di testing.
   *
   * @param validator
   */
  public void setValidator(Validator validator) {
    this.validator = validator;
  }

  /**
   * Set di UtenteService ai fini di testing.
   *
   * @param utenteService
   */
  public void setUtenteService(UtenteService utenteService) {
    this.utenteService = utenteService;
  }

  /**
   * Set di OrdineService ai fini di testing.
   *
   * @param ordineService
   */
  public void setOrdineService(OrdineService ordineService) {
    this.ordineService = ordineService;
  }

  /**
   * Set di ProdottoService ai fini di testing.
   *
   * @param prodottoService
   */
  public void setProdottoService(ProdottoService prodottoService) {
    this.prodottoService = prodottoService;
  }

  /**
   * Set di CarrelloService ai fini di testing.
   *
   * @param carrelloService
   */
  public void setCarrelloService(CarrelloService carrelloService) {
    this.carrelloService = carrelloService;
  }

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
    path = validator.validatePath(path);

    switch (path) {
      case "/login":
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        break;
      case "/profile":
        req.getRequestDispatcher("/WEB-INF/views/user/profilo.jsp").forward(req, resp);
        break;
      case "/update":
        req.getRequestDispatcher("/WEB-INF/views/user/modifica_profilo.jsp").forward(req, resp);
        break;
      case "/ordersPage":
        HttpSession session1 = req.getSession();
        Utente ordineUtente = (Utente) session1.getAttribute("utente");
        ArrayList<Ordine> ordini = ordineService.retrieveOrders(ordineUtente);
        req.setAttribute("ordini", ordini);
        req.getRequestDispatcher("/WEB-INF/views/user/ordini.jsp").forward(req, resp);
        break;
      case "/orderView":
        int codiceOrdine = Integer.parseInt(req.getParameter("codice"));
        Ordine ordine = ordineService.retrieveOrder(codiceOrdine);
        req.setAttribute("ordine", ordine);
        req.getRequestDispatcher("/WEB-INF/views/user/ordine.jsp").forward(req, resp);
        break;
      case "/logout":
        HttpSession session = req.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        carrelloService.updateCarrello(carrello);
        session.removeAttribute("utente");
        session.removeAttribute("carrello");
        session.invalidate();
        resp.sendRedirect("http://localhost:8080/G8_Gaming_war_exploded/");
        break;
    }

  }

  /**
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String path = req.getPathInfo();
    validator = new ValidatorImpl();
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
    int codiceOrdine;
    int codiceProdotto;
    int cap;
    switch (path) {
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
        Utente updateUtente = utenteService.createUtente(username, email, password, nome, cognome, sesso, dataDiNascita, indirizzo, cap, paese);
        if (utenteService.updateUtente(updateUtente)) {
          // SET ALERT
          /*TODO inserire il nuovo utente in sessione*/
          req.getRequestDispatcher("/WEB-INF/views/user/profilo.jsp").forward(req, resp);
        } else {
          throw new ServletException("Errore di aggiornamento...");
        }
        break;
      case "/login":
        Utente tmpUtente = new Utente();
        tmpUtente.setUsername(req.getParameter("username"));
        tmpUtente.setPassword(req.getParameter("password"));
        Utente utente = utenteService.login(tmpUtente.getUsername(), tmpUtente.getPassword());
        if (utente == null) {
          req.setAttribute("errate", 1);
          req.setAttribute("us", req.getParameter("username"));
          req.setAttribute("pw", req.getParameter("password"));
          req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        } else {
          Carrello carrello = carrelloService.recuperaCarrello(utente);
          HttpSession session2 = req.getSession();
          session2.setAttribute("utente", utente);
          session2.setAttribute("carrello", carrello);
          if (utente.isAdmin()) {
            resp.sendRedirect("/G8_Gaming_war_exploded/admin/");
          } else {
            resp.sendRedirect("/G8_Gaming_war_exploded/");
          }
        }

        break;
      case "/updateValutazione":
        codiceProdotto = Integer.parseInt(req.getParameter("codiceProdotto"));
        int valutazione = Integer.parseInt(req.getParameter("valutazione"));
        codiceOrdine = Integer.parseInt(req.getParameter("codiceOrdine"));
        req.setAttribute("codiceOrdine", codiceOrdine);
        prodottoService.updateValutazione(prodottoService.prodottoCodice(codiceProdotto), valutazione);
        ordineService.setProdottoValutato(codiceOrdine, codiceProdotto);
        resp.sendRedirect("/G8_Gaming_war_exploded/" + "Prodotto/Visualizza?prodotto=" + codiceProdotto);
        break;
    }
  }
}
