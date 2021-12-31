package main.java.Validator;

import main.java.Storage.Entity.Prodotto;

public class InvalidProductQuantityException extends Throwable {

    private final Prodotto prodotto;

    public InvalidProductQuantityException(String message, Prodotto p) {
        super(message);
        this.prodotto = p;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }
}
