package main.java.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;

import java.io.IOException;
import java.util.Collection;

public interface Validator {
    public void validateQuantitaProdotto(Prodotto prodotto, int quantita) throws InvalidProductQuantityException;

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException, InvalidUserException;

    public String validatePath(String path);

    void validateProdotto(Prodotto prodotto, Collection<Part> image) throws InvalidProductException, IOException;

    void validateUtente(Utente utente) throws InvalidUserException, InvalidIndirizzoException;
    }

