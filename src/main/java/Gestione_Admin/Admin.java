package main.java.Gestione_Admin;

import main.java.Autenticazione.UtenteService;
import main.java.Autenticazione.UtenteServiceImpl;
import main.java.Carrello.OrdineService;
import main.java.Carrello.OrdineServiceImpl;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;
import main.java.Prenotazione.PrenotazioneService;
import main.java.Prenotazione.PrenotazioneServiceImpl;
import main.java.Validator.InvalidProductException;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@WebServlet(name = "Admin", value = "/admin/*")
public class Admin extends HttpServlet {

    private Validator validator;
    private UtenteService utenteService;
    private OrdineService ordineService;
    private ProdottoService prodottoService;
    private PrenotazioneService prenotazioneService;
    private RequestDispatcher dispatcher;

    @Override
    public void init() throws ServletException {
        super.init();

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getPathInfo();
        validator= new ValidatorImpl();
        path= validator.validatePath(path);


        switch (path) {
            case "/":
                dispatcher = req.getRequestDispatcher("DISPLAY ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Products":
                prodottoService = new ProdottoServiceImpl();
                req.setAttribute("prodotti", prodottoService.allProdotti());
                dispatcher = req.getRequestDispatcher("DISPLAY PRODOTTI ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Orders":
                ordineService = new OrdineServiceImpl();
                req.setAttribute("ordini", ordineService.allOrders());
                dispatcher = req.getRequestDispatcher("DISPLAY ORDERS ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Users":
                utenteService = new UtenteServiceImpl();
                req.setAttribute("utenti", utenteService.allUtenti());
                dispatcher = req.getRequestDispatcher("DISPLAY USERS ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Statistics":
                /*TODO*/HashMap<String, Integer> statistics = (HashMap<String, Integer>) getServletContext().getAttribute("statistics");
                req.setAttribute("statistics", statistics);
                dispatcher = req.getRequestDispatcher("DISPLAY STATISTICS ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Booking":
                prenotazioneService = new PrenotazioneServiceImpl();
                req.setAttribute("prenotazioni", prenotazioneService.allPrenotazioni());
                dispatcher = req.getRequestDispatcher("DISPLAY PRENOTAZIONI ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/ShowHomePage":
                /*TODO*/ArrayList<Prodotto> home = (ArrayList<Prodotto>) getServletContext().getAttribute("home");

                dispatcher = req.getRequestDispatcher("DISPLAY PRODOTTI HOME ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Products/AddProduct":
                dispatcher = req.getRequestDispatcher("DISPLAY INSERT PRODOTTO FORM ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Products/ModifyProduct":
                int codiceProdotto = Integer.parseInt(req.getParameter("prodotto"));
                req.setAttribute("prodotto", prodottoService.prodottoCodice(codiceProdotto));

                dispatcher = req.getRequestDispatcher("DISPLAY UPDATE PRODOTTO FORM ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Orders/ManageOrder":
                int codiceOrdine = Integer.parseInt(req.getParameter("ordine"));
                req.setAttribute("ordine", ordineService.retrieveOrder(codiceOrdine));

                dispatcher = req.getRequestDispatcher("DISPLAY UPDATE ORDINE FORM ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Booking/ManageBooking":
                int codicePrenotazione = Integer.parseInt(req.getParameter("prenotazione"));
                req.setAttribute("prenotazione", prenotazioneService.retrievePrenotazione(codicePrenotazione));

                dispatcher = req.getRequestDispatcher("DISPLAY UPDATE PRENOTAZIONE FORM ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        validator= new ValidatorImpl();
        path= validator.validatePath(path);
        int codiceProdotto;


        switch (path) {
            case "/Products/AddProductHomePage":
                /*TODO*/ArrayList<Prodotto> homeAdd = (ArrayList<Prodotto>) getServletContext().getAttribute("home");
                codiceProdotto = Integer.parseInt(req.getParameter("codiceProdotto"));

                synchronized(homeAdd){
                    homeAdd.add(prodottoService.prodottoCodice(codiceProdotto));
                    getServletContext().removeAttribute("home");
                    getServletContext().setAttribute("home", homeAdd);
                }

                dispatcher = req.getRequestDispatcher("DISPLAY PRODOTTI HOME ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/SetHomePage":
                /*TODO*/ArrayList<Prodotto> home = (ArrayList<Prodotto>) getServletContext().getAttribute("home");
                codiceProdotto = Integer.parseInt(req.getParameter("codiceProdotto"));
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
                dispatcher = req.getRequestDispatcher("DISPLAY PRODOTTI HOME ADMIN PAGE");
                dispatcher.forward(req, resp);
                break;
            case "/Products/AddProduct":
                String categoria = req.getParameter("categoria");
                String nome = req.getParameter("nome");
                String piattaforma = req.getParameter("piattaforma");
                double prezzo = Double.parseDouble(req.getParameter("prezzo"));
                double scontoAttivo = Double.parseDouble(req.getParameter("scontoAttivo"));
                int quantita = Integer.parseInt(req.getParameter("quantita"));
                String descrizione = req.getParameter("descrizione");

                Part filePart = req.getPart("copertina");
                String copertina = filePart.getSubmittedFileName();

                Prodotto prodotto = prodottoService.creaProdotto(categoria, nome, piattaforma, prezzo, scontoAttivo, quantita, descrizione, copertina);

                try{
                    validator.validateProdotto(prodotto);
                    validator.validateImage(copertina, req.getParts());
                    prodottoService.saveProdotto(prodotto);

                    for(Part part: req.getParts()){
                        if(part.getContentType() != null)
                            part.write("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\images\\"+copertina);
                    }

                    dispatcher = req.getRequestDispatcher("PRODUCT PAGE");
                    dispatcher.forward(req, resp);
                }
                catch (InvalidProductException e){
                    dispatcher = req.getRequestDispatcher("ERROR INSERT PRODUCT ADMIN PAGE");
                    dispatcher.forward(req, resp);
                }

                break;
            case "/Products/ModifyProduct":
                codiceProdotto = Integer.parseInt(req.getParameter("codice"));
                String categoriaM = req.getParameter("categoria");
                String nomeM = req.getParameter("nome");
                String piattaformaM = req.getParameter("piattaforma");
                double prezzoM = Double.parseDouble(req.getParameter("prezzo"));
                double scontoAttivoM = Double.parseDouble(req.getParameter("scontoAttivo"));
                int quantitaM = Integer.parseInt(req.getParameter("quantita"));
                String descrizioneM = req.getParameter("descrizione");

                Part filePartM = req.getPart("copertina");
                String copertinaM = filePartM.getSubmittedFileName();

                Prodotto prodottoM = prodottoService.creaProdotto(categoriaM, nomeM, piattaformaM, prezzoM, scontoAttivoM, quantitaM, descrizioneM, copertinaM);
                prodottoM.setCodice(codiceProdotto);


                try{
                    validator.validateProdotto(prodottoM);
                    validator.validateImage(copertinaM, req.getParts());
                    String oldCopertina = prodottoService.updateProdotto(prodottoM);

                    File oldCopertinaFile = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\images\\"+oldCopertina);
                    oldCopertinaFile.delete();


                    for(Part part: req.getParts()){
                        if(part.getContentType() != null)
                            part.write("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\images\\"+copertinaM);
                    }
                    dispatcher = req.getRequestDispatcher("PRODUCT ADMIN PAGE");
                    dispatcher.forward(req, resp);

                }
                catch (InvalidProductException e){
                    dispatcher = req.getRequestDispatcher("ERROR INSERT PRODUCT ADMIN PAGE");
                    dispatcher.forward(req, resp);
                }



                break;
            case "/Users/SetAdmin":
                String username = req.getParameter("username");
                utenteService.setAdmin(username);
                dispatcher = req.getRequestDispatcher("USERS PAGE ADMIN");
                dispatcher.forward(req, resp);
                break;
            case "/Orders/ManageOrder":
                int codiceOrdine = Integer.parseInt("ordine");
                String stato = req.getParameter("stato");

                ordineService.updateStato(codiceOrdine, stato);
                dispatcher = req.getRequestDispatcher("ORDERS PAGE ADMIN");
                dispatcher.forward(req, resp);

                break;
            case "/Booking/ManageBooking":
                int codicePrenotazione = Integer.parseInt("prenotazione");

                prenotazioneService.acceptPrenotazione(codicePrenotazione);
                dispatcher = req.getRequestDispatcher("BOOKINGS PAGE ADMIN");
                dispatcher.forward(req, resp);
                break;

        }
    }
}
