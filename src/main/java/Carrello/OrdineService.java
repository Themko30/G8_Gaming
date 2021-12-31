package main.java.Carrello;

import java.util.ArrayList;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;

public interface OrdineService {

    Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento);

    boolean saveOrdine(Ordine ordine);

    ArrayList<Ordine> retrieveOrders(Utente utente);

    Ordine retrieveOrder(int codiceOrdine);

    void setProdottoValutato(int codiceOrdine, int codiceProdotto);

    ArrayList<Ordine> allOrders();

    void updateStato(int numeroOrdine, String stato);

    int counterOrdini();
}
