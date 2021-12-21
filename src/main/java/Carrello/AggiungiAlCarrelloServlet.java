package main.java.Carrello;

import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

@WebServlet("/AggiungiAlCarrello")
public class AggiungiAlCarrelloServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));
        int quantitaProdotto = Integer.parseInt(request.getParameter("quantita"));

        Carrello carrello = (Carrello) session.getAttribute("carrello");
        CarrelloService carrelloService = new CarrelloServiceImpl();

        synchronized (session){

            carrello = carrelloService.aggiungiProdotto(carrello, codiceProdotto, quantitaProdotto);

            session.removeAttribute("carrello");
            session.setAttribute("carrello", carrello);

        }

        RequestDispatcher dispatcher= request.getRequestDispatcher("PRODOTTO AGGIUNTO AL CARRELLO PAGE");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }
}
