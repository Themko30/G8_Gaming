package main.java.Catalogo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ProdottoServlet", value = "/Prodotto")
public class ProdottoServlet extends HttpServlet{
    ProdottoService prodottoService = new ProdottoServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        RequestDispatcher dispatcher;

        switch (path){
            case "/Categoria":
                String categoria = request.getParameter("categoria");

                request.setAttribute("prodotti", prodottoService.prodottiCategoria(categoria));

                dispatcher = request.getRequestDispatcher("CATEGORIA DISPLAY PAGE");
                dispatcher.forward(request, response);
                break;

            case "/Piattaforma":
                String piattaforma = request.getParameter("piattaforma");

                request.setAttribute("prodotti", prodottoService.prodottiPiattaforma(piattaforma));

                dispatcher = request.getRequestDispatcher("PRODOTTI DISPLAY PAGE");
                dispatcher.forward(request, response);
                break;

            case "/Ricerca":
                String nomeProdotto = request.getParameter("nome");

                request.setAttribute("prodotti", prodottoService.prodottiNomeLike(nomeProdotto));

                dispatcher = request.getRequestDispatcher("PRODOTTI DISPLAY PAGE");
                dispatcher.forward(request, response);
                break;

            case "/Visualizza":
                int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));

                Prodotto p = prodottoService.prodottoCodice(codiceProdotto);

                request.setAttribute("prodotto", p);
                /*TODO prodotti consigliati*/

                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/videogioco.jsp");
                dispatcher.forward(request, response);
        }
    }
}