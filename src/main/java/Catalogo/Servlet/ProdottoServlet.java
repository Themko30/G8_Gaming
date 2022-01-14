package main.java.Catalogo.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "ProdottoServlet", value = "/Prodotto/*")
public class ProdottoServlet extends HttpServlet {

  /**
   * Il ProdottoService della classe.
   */
  private ProdottoService prodottoService = new ProdottoServiceImpl();

  /**
   * Metodo per impostare il ProdottoService della classe.
   * @param prodottoService Il ProdottoService da impostare
   */
  public void setProdottoService(ProdottoService prodottoService) {
    this.prodottoService = prodottoService;
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String path = request.getPathInfo();
    RequestDispatcher dispatcher;

    switch (path) {
      case "/Categoria":
        String categoria = request.getParameter("categoria");

        request.setAttribute("prodotti",
                prodottoService.prodottiCategoria(categoria));

        request.setAttribute("categoria",
                request.getParameter("categoria"));

        dispatcher = request.getRequestDispatcher("/WEB-INF/"
                + "views/user/categoria.jsp");
        dispatcher.forward(request, response);
        break;

      case "/Piattaforma":
        String piattaforma =
                request.getParameter("piattaforma");

        request.setAttribute("prodotti",
                prodottoService.prodottiPiattaforma(piattaforma));
        request.setAttribute("piattaforma",
                request.getParameter("piattaforma"));

        dispatcher = request.getRequestDispatcher("/WEB-INF/"
                + "views/user/piattaforma.jsp");
        dispatcher.forward(request, response);
        break;

      case "/Ricerca":
        String nomeProdotto = request.getParameter("nome");

        request.setAttribute("prodotti",
                prodottoService.prodottiNomeLike(nomeProdotto, 100));
        request.setAttribute("ricerca", request.getParameter("nome"));
        dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/user/ricerca.jsp");
        dispatcher.forward(request, response);
        break;

      case "/Ricerca/api":
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
          String nome = request.getParameter("nome");
          ArrayList<Prodotto> prodotti =
                  prodottoService.prodottiNomeLike(nome, 5);
          JSONObject obj = new JSONObject();
          JSONArray arr = new JSONArray();
          prodotti.forEach(prodotto -> arr.put(prodotto.toJSON()));
          obj.put("products", arr);
          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          PrintWriter w = response.getWriter();
          w.println(obj);
          w.flush();
        } else {
          response.sendError(404);
        }
        break;

      case "/Visualizza":
        int codiceProdotto = Integer.parseInt(request.getParameter("prodotto"));

        Prodotto p = prodottoService.prodottoCodice(codiceProdotto);

        request.setAttribute("prodotto", p);
        /*TODO prodotti consigliati*/

        dispatcher = request.getRequestDispatcher(
                "/WEB-INF/views/user/videogioco.jsp");
        dispatcher.forward(request, response);
        break;
      default:
        response.sendError(404);
        break;
    }
  }
}
