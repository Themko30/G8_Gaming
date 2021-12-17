package main.java.Validator;

import javax.servlet.http.HttpServletRequest;
import main.java.Catalogo.Prodotto;

public interface Validator {
    public boolean validateQuantitaProdotto(Prodotto prodotto, int quantita);

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException;

    public String validatePath(String path) ;
    }

