package main.java.Carrello.Service;

import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;

public interface CarrelloService {

    Carrello aggiungiProdotto(Carrello carrello,
                              int codiceProdotto, int quantita);

    Carrello updateQuantitaCarrelloSession(Carrello carrello,
                                           int codiceProdotto, int quantita);

    Carrello rimuoviProdottoCarrelloSession(Carrello carrello,
                                            int codiceProdotto);

    Carrello clearCarrello(Carrello carrello);

    boolean updateCarrello(Carrello carrello);

    boolean creaCarrello(Utente utente);

    Carrello recuperaCarrello(Utente utente);
}
