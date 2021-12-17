package main.java.Validator;

import main.java.Catalogo.Prodotto;

public interface Validator {
    public boolean validateQuantitaProdotto(Prodotto prodotto, int quantita);

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException;
}
