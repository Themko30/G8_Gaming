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
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;

@WebServlet(name = "CarrelloServlet", value = "/cart/*")
public class CarrelloServlet extends HttpServlet {

    CarrelloService carrelloService = new CarrelloServiceImpl();
    OrdineService ordineService = new OrdineServiceImpl();
    Validator validator = new ValidatorImpl();


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getPathInfo();
        RequestDispatcher dispatcher;
        HttpSession session = request.getSession();
        Carrello carrello;
        int codiceProdotto;

        switch (path) {
            case "/Add":

                codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));
                int quantitaProdotto = Integer.parseInt(request.getParameter("quantita"));

                carrello = (Carrello) session.getAttribute("carrello");

                synchronized (session) {

                    carrello = carrelloService.aggiungiProdotto(carrello, codiceProdotto, quantitaProdotto);

                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);

                }

                dispatcher = request.getRequestDispatcher("PRODOTTO AGGIUNTO AL CARRELLO PAGE");
                dispatcher.forward(request, response);
                break;

            case "/Modifica":
                codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));
                int quantita = Integer.parseInt(request.getParameter("quantita"));

                synchronized (session) {
                    carrello = (Carrello) session.getAttribute("carrello");
                    carrello = carrelloService.updateQuantitaCarrelloSession(carrello, codiceProdotto, quantita);
                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);
                }

                dispatcher = request.getRequestDispatcher("CART DISPLAY PAGE"); //Il carrello è presente nella sessione utente ed è accessibile solo se il cliente è loggato!
                dispatcher.forward(request, response);
                break;

            case "/Rimuovi":
                session = request.getSession();
                codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));

                synchronized (session) {

                    carrello = (Carrello) session.getAttribute("carrello");
                    carrello = carrelloService.rimuoviProdottoCarrelloSession(carrello, codiceProdotto);

                    session.removeAttribute("carrello");
                    session.setAttribute("carrello", carrello);
                }

                dispatcher = request.getRequestDispatcher("CART DISPLAY PAGE"); //Il carrello è presente nella sessione utente ed è accessibile solo se il cliente è loggato!
                dispatcher.forward(request, response);
                break;

            case "/Checkout":

                synchronized (session) {
                    carrello = (Carrello) session.getAttribute("carrello");

                    String indirizzo = request.getParameter("indirizzo");
                    Integer CAP = Integer.parseInt(request.getParameter("CAP"));
                    String paese = request.getParameter("paese");
                    String metodoPagamento = request.getParameter("metodoPagamento");

                    try {
                        validator.validateIndirizzo(indirizzo, CAP, paese);
                        Ordine ordine = ordineService.createOrdine(carrello, indirizzo, CAP, paese, metodoPagamento);
                        LinkedHashMap<Prodotto, Integer> prodotti = ordine.getProdotti();
                        Set<Prodotto> key = prodotti.keySet();
                        for (Prodotto p : key) {
                            validator.validateQuantitaProdotto(p, prodotti.get(p));
                        }

                        ordineService.saveOrdine(ordine);

                        carrello = carrelloService.clearCarrello(carrello);

                        session.removeAttribute("carrello");
                        session.setAttribute("carrello", carrello);

                        dispatcher = request.getRequestDispatcher("ORDINE SUCCESS PAGE");
                        dispatcher.forward(request, response);


                    } catch (InvalidIndirizzoException e) {
                        e.printStackTrace();
                        dispatcher = request.getRequestDispatcher("Ordine Failed Page");
                        dispatcher.forward(request, response);

                    } catch (InvalidProductQuantityException ex) {
                        ex.printStackTrace();
                        request.setAttribute("prodotto", ex.getProdotto());
                        dispatcher = request.getRequestDispatcher("Ordine Prodotto Failed Page");
                        dispatcher.forward(request, response);
                    }


                }

                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String path = request.getPathInfo();
        RequestDispatcher dispatcher;

        switch (path) {
            case "/":
                dispatcher = request.getRequestDispatcher("CART DISPLAY PAGE"); //Il carrello è presente nella sessione utente ed è accessibile solo se il cliente è loggato!
                dispatcher.forward(request, response);
                break;
        }

    }
}
