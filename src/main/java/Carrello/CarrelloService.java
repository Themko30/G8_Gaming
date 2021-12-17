package main.java.Carrello;

public interface CarrelloService {
    public Carrello updateQuantitaCarrelloSession(Carrello carrello, int codiceProdotto, int quantita);
    public Carrello rimuoviProdottoCarrelloSession(Carrello carrello, int codiceProdotto);
    public Carrello clearCarrello(Carrello carrello);
}
