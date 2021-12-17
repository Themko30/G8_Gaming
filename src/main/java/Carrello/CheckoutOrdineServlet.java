package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;
import main.java.Validator.InvalidIndirizzoException;
import main.java.Validator.InvalidProductQuantityException;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/Checkout")
public class CheckoutOrdineServlet extends HttpServlet {
    private OrdineBuilder ordineBuilder;
    private Validator validator;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        HttpSession session = request.getSession();

        synchronized (session){
            Carrello carrello = (Carrello) session.getAttribute("carrello");

            String indirizzo = request.getParameter("indirizzo");
            Integer CAP = Integer.parseInt(request.getParameter("CAP"));
            String paese = request.getParameter("paese");
            String metodoPagamento = request.getParameter("metodoPagamento");

            validator = new ValidatorImpl();

            try {
                validator.validateIndirizzo(indirizzo, CAP, paese);
            } catch (InvalidIndirizzoException e) {
                e.printStackTrace();
                RequestDispatcher dispatcher = request.getRequestDispatcher("Ordine Failed Page");
                dispatcher.forward(request, response);
            }
            String indirizzoSpedizione = indirizzo + ", " + CAP +", " + paese;


            OrdineDAO ordineDAO = new OrdineDAO();
            ordineBuilder = new OrdineBuilderImpl();
            Ordine ordine = ordineBuilder.utente(carrello.getUtente())
                    .totale(carrello.getTotale())
                    .numeroArticoli(carrello.getNumeroArticoli())
                    .indirizzoSpedizione(indirizzoSpedizione)
                    .metodoPagamento(metodoPagamento)
                    .data(LocalDate.now())
                    .prodotti(carrello.getProdotti())
                    .build();


            try {
                ordineDAO.doSaveOrdine(ordine, validator);
            } catch (InvalidProductQuantityException e) {
                Prodotto invalidProdotto = e.getProdotto();
                request.setAttribute("prodotto", invalidProdotto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Ordine Failed Page");
                dispatcher.forward(request, response);
            }

            Utente utente = carrello.getUtente();
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            carrelloDAO.doClearCarrello(carrello);

            carrello = new Carrello();
            carrello.setUtente(utente);

            session.removeAttribute("carrello");
            session.setAttribute("carrello", carrello);

            RequestDispatcher dispatcher = request.getRequestDispatcher("ORDINE SUCCESS PAGE");
            dispatcher.forward(request, response);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }
}
