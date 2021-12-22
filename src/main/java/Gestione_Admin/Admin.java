package main.java.Gestione_Admin;

import main.java.Autenticazione.UtenteService;
import main.java.Carrello.OrdineService;
import main.java.Catalogo.ProdottoService;
import main.java.Validator.Validator;
import main.java.Validator.ValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;



@WebServlet(name = "Admin", value = "/admin/*")
public class Admin extends HttpServlet {

    private Validator validator;
    private UtenteService utenteService;
    private OrdineService ordineService;
    private ProdottoService prodottoService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        validator= new ValidatorImpl();
        path= validator.validatePath(path);

        String username, email, password, nome, cognome, sesso;
        LocalDate dataDiNascita;
        int codiceOrdine, codiceProdotto;

        switch (path) {
            case "/":
                break;
            case "/Products":
                break;
            case "/Orders":
                break;
            case "/Users":
                break;
            case "/Statistics":
                break;
            case "/Booking":
                break;
            case "/ShowHomePage":
                break;
            case "/Products/AddProduct":
                break;
            case "/Products/ModifyProduct":
                break;
            case "/Orders/ManageOrder":
                break;
            case "/Booking/ManageBooking":
                break;

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        validator= new ValidatorImpl();
        path= validator.validatePath(path);


        switch (path) {
            case "/SetHomePage":
                break;
            case "/Products/AddProduct":
                break;
            case "/Products/ModifyProduct":
                break;
            case "/Users/SetAdmin":
                break;
            case "/Orders/ManageOrder":
                break;
            case "/Booking/ManageBooking":
                break;

        }
    }
}
