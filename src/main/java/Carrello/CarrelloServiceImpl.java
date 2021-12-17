package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;

import java.util.LinkedHashMap;
import java.util.Set;

public class CarrelloServiceImpl implements CarrelloService{

    private CarrelloDAO carrelloDAO = new CarrelloDAO();
    @Override
    public Carrello updateQuantitaCarrelloSession(Carrello carrello, int codiceProdotto, int quantita) {
        LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
        Set<Prodotto> prodotti = prodottiMap.keySet();
        for (Prodotto p : prodotti) {
            if (p.getCodice() == codiceProdotto) {
                prodottiMap.replace(p, quantita);
                break;
            }
        }
        carrello.setProdotti(prodottiMap);
        return carrello;
    }

    @Override
    public Carrello rimuoviProdottoCarrelloSession(Carrello carrello, int codiceProdotto) {

        LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
        Set<Prodotto> prodotti = prodottiMap.keySet();
        for(Prodotto p: prodotti){
            if(p.getCodice()==codiceProdotto){
                prodottiMap.remove(p);
                break;
            }
        }
        carrello.setProdotti(prodottiMap);
        return carrello;
    }

    @Override
    public Carrello clearCarrello(Carrello carrello) {

        Utente utente = carrello.getUtente();

        carrelloDAO.doClearCarrello(carrello);

        carrello = new Carrello();
        carrello.setUtente(utente);
        return carrello;
    }


}
