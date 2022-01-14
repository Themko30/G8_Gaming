package main.java.Carrello.Servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;

@WebServlet(name = "CarrelloServlet", value = "/cart/*")
public class CarrelloServlet extends HttpServlet {

    /**
     * Il CarrelloService della classe.
     */
    CarrelloService carrelloService = new CarrelloServiceImpl();

    /**
     * L'OrdineService della classe.
     */
    OrdineService ordineService = new OrdineServiceImpl();

    /**
     * Il Validator della classe.
     */
    Validator validator = new ValidatorImpl();

    /**
     * Metodo per impostare il CarrelloService della classe.
     * @param carrelloService Il CarrelloService da impostare
     */
    public void setCarrelloService(CarrelloService carrelloService) {
        this.carrelloService = carrelloService;
    }

    /**
     * Metodo per impostare l'OrdineService della classe.
     * @param ordineService L'OrdineService da impostare
     */
    public void setOrdineService(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * Metodo per impostare il Validator della classe.
     * @param validator Il Validator da impostare
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = request.getPathInfo();
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        Carrello carrello;
        int codiceProdotto;

        Utente u = (Utente) session.getAttribute("utente");
        if (u == null) {
            response.sendError(401);
            return;
        }

        switch (path) {
            case "/Add":
                codiceProdotto =
                        Integer.parseInt(request.getParameter("prodotto"));
                int quantitaProdotto =
                        Integer.parseInt(request.getParameter("quantita"));

                carrello =
                        (Carrello) session.getAttribute("carrello");

                synchronized (session) {

                    carrello =
                            carrelloService.aggiungiProdotto(carrello,
                                    codiceProdotto, quantitaProdotto);

                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);

                }

                dispatcher =
                        request.getRequestDispatcher("/WEB-INF/"
                                + "views/user/carrello.jsp");
                dispatcher.forward(request, response);
                break;

            case "/Modifica":
                codiceProdotto =
                        Integer.parseInt(request.getParameter("prodotto"));
                int quantita =
                        Integer.parseInt(request.getParameter("quantita"));

                synchronized (session) {
                    carrello =
                            (Carrello) session.getAttribute("carrello");
                    carrello =
                            carrelloService
                                    .updateQuantitaCarrelloSession(carrello,
                                            codiceProdotto, quantita);
                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);
                }

                response.sendRedirect("/G8_Gaming_war_exploded/cart/");
                break;

            case "/Rimuovi":
                session = request.getSession();
                codiceProdotto =
                        Integer.parseInt(request.getParameter("prodotto"));

                synchronized (session) {

                    carrello =
                            (Carrello) session.getAttribute("carrello");
                    carrello =
                            carrelloService
                                    .rimuoviProdottoCarrelloSession(
                                            carrello, codiceProdotto);

                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);
                }

                dispatcher =
                        request.getRequestDispatcher("/WEB-INF/"
                                + "views/user/carrello.jsp");
                dispatcher.forward(request, response);
                break;

            case "/Checkout":

                synchronized (session) {
                    carrello = (Carrello) session.getAttribute("carrello");

                    String indirizzo =
                            request.getParameter("indirizzo");
                    Integer CAP =
                            Integer.parseInt(request.getParameter("CAP"));
                    String paese =
                            request.getParameter("paese");
                    String metodoPagamento =
                            request.getParameter("metodoPagamento");

                    try {
                        validator.validateIndirizzo(indirizzo,
                                CAP, paese);
                        Ordine ordine =
                                ordineService.createOrdine(carrello,
                                        indirizzo, CAP, paese, metodoPagamento);
                        LinkedHashMap<Prodotto, Integer> prodotti =
                                ordine.getProdotti();
                        Set<Prodotto> key = prodotti.keySet();
                        for (Prodotto p : key) {
                            validator.validateQuantitaProdotto(p,
                                    prodotti.get(p));
                        }

                        ordineService.saveOrdine(ordine);

                        carrello =
                                carrelloService.clearCarrello(carrello);

                        session.removeAttribute("carrello");
                        session.setAttribute("carrello", carrello);

                        dispatcher =
                                request.getRequestDispatcher("/WEB-INF/"
                                        + "views/user/ordine_completato.jsp");
                        dispatcher.forward(request, response);


                    } catch (InvalidIndirizzoException e) {
                        e.printStackTrace();
                        dispatcher =
                                request.getRequestDispatcher("/WEB-INF/"
                                        + "views/errors/ordine.jsp");
                        dispatcher.forward(request, response);

                    } catch (InvalidProductQuantityException ex) {
                        ex.printStackTrace();
                        request.setAttribute("prodotto", ex.getProdotto());
                        dispatcher =
                                request.getRequestDispatcher(
                                        "/WEB-INF/views/errors/quantita.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;
            default:
                response.sendError(404);
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String path = request.getPathInfo();
        path = validator.validatePath(path);

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if (utente == null) {
            response.sendRedirect(
                    "http://localhost:8080/G8_Gaming_war_exploded/account/login");
            return;
        }

        switch (path) {
            case "/":
                request.getRequestDispatcher(
                                "/WEB-INF/views/user/carrello.jsp")
                        .forward(request, response);
                break;
            case "/confirmOrder":
                request.getRequestDispatcher(
                                "/WEB-INF/views/user/conferma_ordine.jsp")
                        .forward(request, response);
                break;
            default:
                response.sendError(404);
                break;
        }
    }
}
