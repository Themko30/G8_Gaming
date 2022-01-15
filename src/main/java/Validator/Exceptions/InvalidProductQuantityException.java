package main.java.Validator.Exceptions;

import main.java.Storage.Entity.Prodotto;

public class InvalidProductQuantityException extends Exception {

    /**
     * Variabile prodotto usata privata per poter settare il messaggio di errore.
     */
    private final Prodotto prodotto;

    /**
     * Custom Exception per la quantità del prodotto.
     *
     * @param message il messaggio che specifica l`errore
     * @param p il bean del prodotto che ha lanciato l`eccezione.
     */
    public InvalidProductQuantityException(String message, Prodotto p) {
        super(message);
        this.prodotto = p;
    }

    /**
     * Recupera il prodotto dal DB.
     *
     * @return il prodotto da inserire
     */
    public Prodotto getProdotto() {
        return prodotto;
    }
}
