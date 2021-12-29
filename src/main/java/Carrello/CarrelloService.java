package main.java.Carrello;

import main.java.Autenticazione.Utente;

public interface CarrelloService {

    Carrello aggiungiProdotto(Carrello carrello, int codiceProdotto, int quantita);

    public Carrello updateQuantitaCarrelloSession(Carrello carrello, int codiceProdotto, int quantita);

    public Carrello rimuoviProdottoCarrelloSession(Carrello carrello, int codiceProdotto);

    public Carrello clearCarrello(Carrello carrello);

    public boolean updateCarrello(Carrello carrello);

    void creaCarrello(Utente utente);
}
