package main.java.Carrello;

import java.util.ArrayList;
import main.java.Autenticazione.Utente;
import main.java.Validator.Validator;

public interface OrdineService {
    Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento);
    boolean saveOrdine(Ordine ordine);
    ArrayList<Ordine> retrieveOrders(Utente utente);
    Ordine retrieveOrder(int codiceOrdine);
    void setProdottoValutato(int codiceOrdine, int codiceProdotto);
}
