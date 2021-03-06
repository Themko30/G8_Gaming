package main.java.Gestione_Admin.Servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Prenotazione.Service.PrenotazioneService;
import main.java.Prenotazione.Service.PrenotazioneServiceImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Service.Validator;
import main.java.Validator.Service.ValidatorImpl;

@MultipartConfig
@WebServlet(name = "Admin", value = "/admin/*")
public class AdminServlet extends HttpServlet {

    /**
     * Il Validator della classe.
     */
    private Validator validator = new ValidatorImpl();

    /**
     * L'UtenteService della classe.
     */
    private UtenteService utenteService = new UtenteServiceImpl();

    /**
     * L'OrdineService della classe.
     */
    private OrdineService ordineService = new OrdineServiceImpl();

    /**
     * Il ProdottoService della classe.
     */
    private ProdottoService prodottoService = new ProdottoServiceImpl();

    /**
     * Il PrenotazioneService della classe.
     */
    private PrenotazioneService prenotazioneService =
            new PrenotazioneServiceImpl();

    /**
     * Il RequestDispatcher della classe.
     */
    private RequestDispatcher dispatcher;

    /**
     * Metodo per impostare il Validator della classe.
     * @param validator Il Validator da impostare
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Metodo per impostare l'UtenteService della classe.
     * @param utenteService L'UtenteService da impostare
     */
    public void setUtenteService(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    /**
     * Metodo per impostare l'OrdineService della classe.
     * @param ordineService L'OrdineService da impostare
     */
    public void setOrdineService(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * Metodo per impostare il ProdottoService della classe.
     * @param prodottoService Il ProdottoService da impostare
     */
    public void setProdottoService(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    /**
     * Metodo per impostare il PrenotazioneService della classe.
     * @param prenotazioneService Il PrenotazioneService da impostare
     */
    public void setPrenotazioneService(
            PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        HashMap<String, Integer> statistics = new HashMap<>();
        statistics.put("Utenti",
                utenteService.counterUtente());
        statistics.put("Ordini",
                ordineService.counterOrdini());
        statistics.put("Prodotti",
                prodottoService.counterProdotti());
        statistics.put("Prenotazioni",
                prenotazioneService.counterPrenotazioni());
        synchronized (ctx) {
            ctx.setAttribute("statistics", statistics);

            ArrayList<Prodotto> home = new ArrayList<>();
            home.add(prodottoService.prodottoCodice(30));
            home.add(prodottoService.prodottoCodice(23));
            home.add(prodottoService.prodottoCodice(16));
            home.add(prodottoService.prodottoCodice(14));
            home.add(prodottoService.prodottoCodice(12));
            home.add(prodottoService.prodottoCodice(11));
            home.add(prodottoService.prodottoCodice(20));
            home.add(prodottoService.prodottoCodice(22));
            home.add(prodottoService.prodottoCodice(7));
            home.add(prodottoService.prodottoCodice(3));

            ctx.setAttribute("home", home);
            ctx.setAttribute("mostVoted",
                    prodottoService.mostVoted());
        }
    }

    @Override
    public void doGet(HttpServletRequest req,
                      HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        if (u == null) {
            resp.sendError(401);
            return;
        }
        if (!u.isAdmin()) {
            resp.sendError(403);
            return;
        }

        String path = req.getPathInfo();
        validator = new ValidatorImpl();
        path = validator.validatePath(path);
        switch (path) {
            case "/":
            case "/Statistics":
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/index.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Products":
                req.setAttribute("prodotti",
                        prodottoService.allProdotti());
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/prodotti.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Orders":
                req.setAttribute("ordini",
                        ordineService.allOrders());
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/ordini.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Users":
                req.setAttribute("utenti",
                        utenteService.allUtenti());
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/utenti.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Statistics/Update":
                ServletContext ctx = getServletContext();
                HashMap<String, Integer> statistics = new HashMap<>();
                statistics.put("Utenti", utenteService.counterUtente());
                statistics.put("Ordini", ordineService.counterOrdini());
                statistics.put("Prodotti", prodottoService.counterProdotti());
                statistics.put("Prenotazioni",
                        prenotazioneService.counterPrenotazioni());

                ArrayList<Prodotto> home = new ArrayList<>();
                home.add(prodottoService.prodottoCodice(1));
                home.add(prodottoService.prodottoCodice(3));
                home.add(prodottoService.prodottoCodice(4));
                home.add(prodottoService.prodottoCodice(5));
                synchronized (ctx) {
                    ctx.setAttribute("statistics", statistics);
                    ctx.setAttribute("home", home);
                }
                req.setAttribute("aggiornato", 1);
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/index.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Booking":
                req.setAttribute("prenotazioni",
                        prenotazioneService.allPrenotazioni());
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/prenotazioni.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/ShowHomePage":
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/prodotti_home.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Products/AddProduct":
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/aggiungi_prodotto.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Products/ModifyProduct":
                int codiceProdotto = Integer.parseInt(
                        req.getParameter("codice"));
                req.setAttribute("prodotto",
                        prodottoService.prodottoCodice(codiceProdotto));
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/modifica_prodotto.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Products/MostVotedUpdate":
                ServletContext context = getServletContext();
                synchronized (context) {
                    context.removeAttribute("mostVoted");
                    context.setAttribute("mostVoted",
                            prodottoService.mostVoted());
                }
                req.setAttribute("aggiornato", 1);
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/index.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Orders/ManageOrder":
                int codiceOrdine = Integer.parseInt(req.getParameter("numero"));
                req.setAttribute("ordine",
                        ordineService.retrieveOrder(codiceOrdine));
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/ordine.jsp");
                dispatcher.forward(req, resp);
                break;
            case "/Booking/ManageBooking":
                int codicePrenotazione = Integer.parseInt(
                        req.getParameter("numeroPrenotazione"));
                req.setAttribute("prenotazione",
                        prenotazioneService
                                .retrievePrenotazione(codicePrenotazione));
                dispatcher = req.getRequestDispatcher(
                        "/WEB-INF/views/admin/prenotazione.jsp");
                dispatcher.forward(req, resp);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        Utente u = (Utente) session.getAttribute("utente");
        if (u == null) {
            resp.sendError(401);
            return;
        } else if (!u.isAdmin()) {
            resp.sendError(403);
            return;
        }
        String path = req.getPathInfo();
        path = validator.validatePath(path);
        int codiceProdotto;
        switch (path) {
            case "/Products/AddProductHomePage":
                ArrayList<Prodotto> homeAdd =
                        (ArrayList<Prodotto>) getServletContext()
                                .getAttribute("home");
                codiceProdotto = Integer.parseInt(
                        req.getParameter("codiceProdotto"));

                synchronized (homeAdd) {
                    homeAdd.add(prodottoService.prodottoCodice(codiceProdotto));
                    getServletContext().removeAttribute("home");
                    getServletContext().setAttribute("home", homeAdd);
                }
                resp.sendRedirect(
                        "/G8_Gaming_war_exploded/admin/ShowHomePage");
                break;
            case "/SetHomePage":
                ArrayList<Prodotto> home =
                        (ArrayList<Prodotto>) getServletContext()
                                .getAttribute("home");
                codiceProdotto =
                        Integer.parseInt(req.getParameter("codiceProdotto"));
                synchronized (home) {
                    for (Prodotto p : home) {
                        if (p.getCodice() == codiceProdotto) {
                            home.remove(p);
                            break;
                        }
                    }
                    getServletContext().removeAttribute("home");
                    getServletContext().setAttribute("home", home);
                }
                resp.sendRedirect("/G8_Gaming_war_exploded/admin/ShowHomePage");
                break;
            case "/Products/AddProduct":
                String categoria = req.getParameter("categoria");
                String nome = req.getParameter("nome");
                String piattaforma = req.getParameter("piattaforma");
                double prezzo =
                        Double.parseDouble(req.getParameter("prezzo"));
                double scontoAttivo =
                        Double.parseDouble(req.getParameter("scontoAttivo"));
                int quantita =
                        Integer.parseInt(req.getParameter("quantita"));
                String descrizione = req.getParameter("descrizione");

                Part filePart = req.getPart("copertina");
                String copertina =
                        filePart.getSubmittedFileName();

                Prodotto prodotto =
                        prodottoService.creaProdotto(categoria, nome,
                                piattaforma, prezzo, scontoAttivo, quantita,
                                descrizione, copertina);
                try {
                    validator.validateProdotto(prodotto);
                    validator.validateImage(copertina, req.getParts());
                    prodottoService.saveProdotto(prodotto);
                    resp.sendRedirect(
                            "/G8_Gaming_war_exploded/admin/Products");
                } catch (InvalidProductException e) {
                    dispatcher = req.getRequestDispatcher(
                            "/WEB-INF/views/errors/prodotto.jsp");
                    dispatcher.forward(req, resp);
                }
                break;
            case "/Products/ModifyProduct":
                codiceProdotto =
                        Integer.parseInt(req.getParameter("codice"));
                String categoriaM = req.getParameter("categoria");
                String nomeM = req.getParameter("nome");
                String piattaformaM =
                        req.getParameter("piattaforma");
                double prezzoM =
                        Double.parseDouble(req.getParameter("prezzo"));
                double scontoAttivoM =
                        Double.parseDouble(req.getParameter("scontoAttivo"));
                int quantitaM =
                        Integer.parseInt(req.getParameter("quantita"));
                String descrizioneM =
                        req.getParameter("descrizione");

                Part filePartM = req.getPart("copertina");
                String copertinaM =
                        filePartM.getSubmittedFileName();

                Prodotto prodottoM =
                        prodottoService
                                .creaProdotto(categoriaM, nomeM, piattaformaM,
                                prezzoM, scontoAttivoM, quantitaM,
                                descrizioneM, copertinaM);
                prodottoM.setCodice(codiceProdotto);
                try {
                    validator.validateProdotto(prodottoM);
                    validator.validateImage(copertinaM, req.getParts());
                    String oldCopertina =
                            prodottoService.updateProdotto(prodottoM);

                    File oldCopertinaFile =
                            new File("C:\\Program Files\\"
                                    + "Apache Software Foundation\\"
                                    + "Tomcat 9.0\\"
                                    + "webapps\\G8_Gaming_war_exploded\\"
                                    + "images\\"
                                    + oldCopertina);
                    oldCopertinaFile.delete();

                    resp.sendRedirect(
                            "/G8_Gaming_war_exploded/admin/Products");
                } catch (InvalidProductException e) {
                    dispatcher =
                            req.getRequestDispatcher(
                                    "/WEB-INF/views/errors/prodotto.jsp");
                    dispatcher.forward(req, resp);
                }
                break;
            case "/Users/SetAdmin":
                String username = req.getParameter("username");
                utenteService.setAdmin(username);
                resp.sendRedirect(
                        "/G8_Gaming_war_exploded/admin/Users");
                break;
            case "/Orders/ManageOrder":
                int codiceOrdine =
                        Integer.parseInt(req.getParameter("numero"));
                String stato = req.getParameter("stato");

                ordineService.updateStato(codiceOrdine, stato);
                resp.sendRedirect("/G8_Gaming_war_exploded/admin/Orders");
                break;
            case "/Booking/ManageBooking":
                int codicePrenotazione =
                        Integer.parseInt(req.getParameter(
                                "numeroPrenotazione"));
                prenotazioneService.acceptPrenotazione(codicePrenotazione);
                resp.sendRedirect("/G8_Gaming_war_exploded/admin/Booking");
                break;
        }
    }
}
