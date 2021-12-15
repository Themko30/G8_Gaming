package main.java.Carrello;

import main.java.Catalogo.Prodotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

public class ModificaQuantitaCarrelloServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        synchronized (session){
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
            Set<Prodotto> prodotti = prodottiMap.keySet();
            for(Prodotto p: prodotti){
                if(p.getCodice()==codiceProdotto){
                    prodottiMap.replace(p, quantita);
                    break;
                }
            }

            carrello.setProdotti(prodottiMap);
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
