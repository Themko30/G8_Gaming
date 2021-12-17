package main.java.Carrello;

import main.java.Validator.Validator;

public interface OrdineService {
    public Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento);
    public boolean saveOrdine(Ordine ordine);
}
