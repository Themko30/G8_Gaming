package main.java.Validator.Exceptions;

import main.java.Storage.Entity.Prodotto;

public class InvalidProductQuantityException extends Exception {

    private final Prodotto prodotto;

    public InvalidProductQuantityException(String message, Prodotto p) {
        super(message);
        this.prodotto = p;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }
}
