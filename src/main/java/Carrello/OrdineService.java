package main.java.Carrello;

import main.java.Validator.Validator;

public interface OrdineService {
    Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento);
    boolean saveOrdine(Ordine ordine);
}
