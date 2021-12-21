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
        /*TODO*/ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = prodottoDAO.doRetrieveProdottoByCodice(codiceProdotto);
        synchronized (session){
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            LinkedHashMap<Prodotto, Integer> prodottiCarrelloMap= carrello.getProdotti();
            Set<Prodotto> prodottiCarrello = prodottiCarrelloMap.keySet();
            boolean added = false;
            for(Prodotto p: prodottiCarrello){
                if(p.getCodice()==prodotto.getCodice()){
                    prodottiCarrelloMap.replace(p, prodottiCarrelloMap.get(p) + quantitaProdotto);
                    added = true;
                    break;
                }
            }
            if(!added){
                prodottiCarrelloMap.put(prodotto, quantitaProdotto);
            }

            carrello.setProdotti(prodottiCarrelloMap);
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
