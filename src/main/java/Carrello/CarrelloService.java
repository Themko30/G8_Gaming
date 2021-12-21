package main.java.Carrello;

public interface CarrelloService {
    Carrello aggiungiProdotto(Carrello carrello, int codiceProdotto, int quantita);
    public Carrello updateQuantitaCarrelloSession(Carrello carrello, int codiceProdotto, int quantita);
    public Carrello rimuoviProdottoCarrelloSession(Carrello carrello, int codiceProdotto);
    public Carrello clearCarrello(Carrello carrello);
}
