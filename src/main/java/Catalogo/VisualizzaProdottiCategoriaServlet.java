package main.java.Catalogo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/VisualizzaCategoria")
public class VisualizzaProdottiCategoriaServlet extends HttpServlet {
    private ProdottoService prodottoService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String categoria = request.getParameter("categoria");

        prodottoService = new ProdottoServiceImpl();

        request.setAttribute("prodotti", prodottoService.prodottiCategoria(categoria));

        RequestDispatcher dispatcher = request.getRequestDispatcher("CATEGORIA DISPLAY PAGE");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
