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

  private Validator validator;
  private UtenteService utenteService;
  private OrdineService ordineService;
  private ProdottoService prodottoService;
  private CarrelloService carrelloService;


  /**
   * @throws ServletException
   */

  @Override
  public void init() throws ServletException {
    super.init();
  }

  /**
   * @param req
   * @param resp
   * @throws ServletException
   * @throws IOException
   */

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
        HttpSession session1 = req.getSession(false);
        Utente ordineUtente = (Utente) session1.getAttribute("utente");
        ordineService = new OrdineServiceImpl();
        ArrayList<Ordine> ordini = ordineService.retrieveOrders(ordineUtente);
        req.setAttribute("ordini", ordini);
        req.getRequestDispatcher("/WEB-INF/views/user/ordini.jsp").forward(req, resp);
        break;
      case "/orderView":
        int codiceOrdine = Integer.parseInt(req.getParameter("codice"));
        ordineService = new OrdineServiceImpl();
        Ordine ordine = ordineService.retrieveOrder(codiceOrdine);
        req.setAttribute("ordine", ordine);
        req.getRequestDispatcher("/WEB-INF/views/user/ordine.jsp").forward(req, resp);
        break;
      case "/logout":
        HttpSession session = req.getSession(false);
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        carrelloService = new CarrelloServiceImpl();
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

    String username, email, password, nome, cognome, sesso, indirizzo, paese;
    LocalDate dataDiNascita;
    int codiceOrdine, codiceProdotto, cap;

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
        utenteService = new UtenteServiceImpl();
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
        utenteService = new UtenteServiceImpl();
        Utente utente = utenteService.login(tmpUtente.getUsername(), tmpUtente.getPassword());
        if (utente == null) {
          req.setAttribute("errate", 1);
          req.setAttribute("us", req.getParameter("username"));
          req.setAttribute("pw", req.getParameter("password"));
          req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
        } else {
          carrelloService = new CarrelloServiceImpl();
          Carrello carrello = carrelloService.recuperaCarrello(utente);
          HttpSession session2 = req.getSession(false);
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
        prodottoService = new ProdottoServiceImpl();
        prodottoService.updateValutazione(prodottoService.prodottoCodice(codiceProdotto), valutazione);
        ordineService.setProdottoValutato(codiceOrdine, codiceProdotto);
        req.getRequestDispatcher("DA FARE").forward(req, resp);
        break;
    }
  }
}
