package main.java.Validator;

import main.java.Catalogo.Prodotto;

public class InvalidProductQuantityException extends Throwable {
    private Prodotto prodotto;
    public InvalidProductQuantityException(String message, Prodotto p) {
        super(message);
        this.prodotto = p;
    }

    public Prodotto getProdotto(){
        return prodotto;
    }
}
