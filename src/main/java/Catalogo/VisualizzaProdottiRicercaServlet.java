package main.java.Catalogo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VisualizzaProdottiRicercaServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String nomeProdotto = request.getParameter("nome");

        ProdottoService prodottoService = new ProdottoServiceImpl();

        request.setAttribute("prodotti", prodottoService.prodottiNomeLike(nomeProdotto));

        RequestDispatcher dispatcher = request.getRequestDispatcher("PRODOTTI DISPLAY PAGE");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }
}
