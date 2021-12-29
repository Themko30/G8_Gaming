package main.java.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;

import java.io.IOException;
import java.util.Collection;

public interface Validator {
    void validateQuantitaProdotto(Prodotto prodotto, int quantita) throws InvalidProductQuantityException;

    void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException;

    String validatePath(String path);

    void validateProdotto(Prodotto prodotto) throws InvalidProductException, IOException;

    void validateUtente(Utente utente) throws InvalidUserException, InvalidIndirizzoException;
    void validateImage(String copertina, Collection<Part> image) throws IOException, InvalidProductException;
    }

