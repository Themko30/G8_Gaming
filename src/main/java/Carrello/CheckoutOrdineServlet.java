package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;
import main.java.Validator.InvalidIndirizzoException;
import main.java.Validator.ValidatorFacade;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/Checkout")
public class CheckoutOrdineServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        HttpSession session = request.getSession();

        synchronized (session){
            Carrello carrello = (Carrello) session.getAttribute("carrello");

            OrdineDAO ordineDAO = new OrdineDAO();
            ValidatorFacade validatorFacade = new ValidatorFacade();
            String indirizzo = request.getParameter("indirizzo");
            Integer CAP = Integer.parseInt(request.getParameter("CAP"));
            String paese = request.getParameter("paese");

            try {
                validatorFacade.validateIndirizzo(indirizzo, CAP, paese);
            } catch (InvalidIndirizzoException e) {
                e.printStackTrace();
                RequestDispatcher dispatcher = request.getRequestDispatcher("Ordine Failed Page");
                dispatcher.forward(request, response);
            }

            String metodoPagamento = request.getParameter("metodoPagamento");
            try {
                ordineDAO.doSaveOrdine(carrello, indirizzo, metodoPagamento, validatorFacade);
            } catch (InvalidProductQuantityException e) {
                Prodotto invalidProdotto = e.getProdotto();
                request.setAttribute("prodotto", invalidProdotto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Ordine Failed Page");
                dispatcher.forward(request, response);
            }
            Utente utente = (Utente) carrello.getUtente();
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
