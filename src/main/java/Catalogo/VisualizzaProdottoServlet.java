package main.java.Catalogo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/VisualizzaProdotto")
public class VisualizzaProdottoServlet extends HttpServlet {
    private ProdottoService prodottoService;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));

        prodottoService = new ProdottoServiceImpl();
        Prodotto p = prodottoService.prodottoCodice(codiceProdotto);

        request.setAttribute("prodotto", p);

        RequestDispatcher dispatcher = request.getRequestDispatcher("PRODUCT DISPLAY PAGE");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        doGet(request, response);
    }

}
