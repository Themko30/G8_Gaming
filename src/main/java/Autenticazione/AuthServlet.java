package main.java.Autenticazione;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import main.java.Carrello.Ordine;
import main.java.Carrello.OrdineService;
import main.java.Carrello.OrdineServiceImpl;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

@WebServlet(name = "AuthServlet", value = "/account/*")

public class AuthServlet extends HttpServlet {

  private Validator validator;
  private UtenteService utenteService;
  private OrdineService ordineService;
  private ProdottoService prodottoService;

  @Override
  public void init() throws ServletException {
    super.init();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    super.doGet(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

    String path = req.getPathInfo();
    validator= new ValidatorImpl();
    path= validator.validatePath(path);

    String username, email, password, nome, cognome, sesso;
    LocalDate dataDiNascita;
    int codiceOrdine, codiceProdotto;

    switch (path) {
      case "/":
        /*TODO*/ RequestDispatcher dispatcher = req.getRequestDispatcher("DISPLAY PAGE");
        dispatcher.forward(req, resp);
        break;
      case "/update":
        username = req.getParameter("username");
        email = req.getParameter("email");
        password = req.getParameter("password");
        nome = req.getParameter("nome");
        cognome = req.getParameter("cognome");
        sesso = req.getParameter("sesso");
        dataDiNascita = LocalDate.parse(req.getParameter("data"));
        utenteService = new UtenteImpl();
        Utente updateUtente =
            utenteService.createUtente(
                username, email, password, nome, cognome, sesso, dataDiNascita);
        if (utenteService.updateUtente(updateUtente)) {
          // SET ALERT
          /*TODO*/ req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
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
        } else {
          throw new ServletException("Errore di eliminazione...");
        }
        break;
      case "/ordersPage":
        HttpSession session = req.getSession(false);
        Utente ordineUtente = (Utente) session.getAttribute("utente");
        ordineService = new OrdineServiceImpl();
        ArrayList<Ordine> ordini = ordineService.retrieveOrders(ordineUtente);
        req.setAttribute("ordini", ordini);
        req.getRequestDispatcher("VIEW PAGE DA FARE").forward(req, resp);
      case "/orderView":
        codiceOrdine = Integer.parseInt(req.getParameter("codiceOrdine"));
        ordineService = new OrdineServiceImpl();
        Ordine ordine = ordineService.retrieveOrder(codiceOrdine);
        req.setAttribute("ordine", ordine);
      case "/updateValutazione":
        codiceProdotto = Integer.parseInt(req.getParameter("codiceProdotto"));
        int valutazione = Integer.parseInt(req.getParameter("valutazione"));
        codiceOrdine = Integer.parseInt(req.getParameter("codiceOrdine"));
        req.setAttribute("codiceOrdine", codiceOrdine);
        prodottoService = new ProdottoServiceImpl();
        prodottoService.updateValutazione(
          prodottoService.prodottoCodice(codiceProdotto), valutazione);
        ordineService.setProdottoValutato(codiceOrdine, codiceProdotto);
        req.getRequestDispatcher("DA FARE").forward(req, resp);
    }
  }
}
