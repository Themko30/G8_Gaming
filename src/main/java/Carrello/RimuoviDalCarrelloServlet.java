package main.java.Carrello;

import main.java.Catalogo.Prodotto;

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

@WebServlet("/RimuoviDalCarrello")
public class RimuoviDalCarrelloServlet extends HttpServlet {

    private CarrelloService carrelloService;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));

        carrelloService = new CarrelloServiceImpl();

        synchronized (session){

            Carrello carrello = (Carrello) session.getAttribute("carrello");
            carrello = carrelloService.rimuoviProdottoCarrelloSession(carrello,codiceProdotto);

            session.removeAttribute("carrello");
            session.setAttribute("carrello", carrello);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("CART DISPLAY PAGE"); //Il carrello è presente nella sessione utente ed è accessibile solo se il cliente è loggato!
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doPost(request, response);
    }
}
