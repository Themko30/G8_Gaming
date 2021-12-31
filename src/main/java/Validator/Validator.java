package main.java.Validator;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.Part;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public interface Validator {

    void validateQuantitaProdotto(Prodotto prodotto, int quantita) throws InvalidProductQuantityException;

    void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException;

    String validatePath(String path);

    void validateProdotto(Prodotto prodotto) throws InvalidProductException, IOException;

    void validateUtente(Utente utente) throws InvalidUserException, InvalidIndirizzoException;

    void validateImage(String copertina, Collection<Part> image) throws IOException, InvalidProductException;
}

