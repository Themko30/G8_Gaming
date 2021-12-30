package main.java.Carrello;

import java.util.LinkedHashMap;
import java.util.Set;
import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;

public class CarrelloServiceImpl implements CarrelloService {

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
        for (Prodotto p : prodotti) {
            if (p.getCodice() == codiceProdotto) {
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

    @Override
    public boolean updateCarrello(Carrello carrello) {
        return carrelloDAO.doUpdateCarrello(carrello);
    }

    @Override
    public void creaCarrello(Utente utente) {
        carrelloDAO.doCreateCarrello(utente);
    }

    @Override
    public Carrello recuperaCarrello(Utente utente) {
        return carrelloDAO.doRetrieveCarrelloByUtente(utente);
    }

    @Override
    public Carrello aggiungiProdotto(Carrello carrello, int codiceProdotto, int quantita) {
        LinkedHashMap<Prodotto, Integer> prodottiCarrelloMap = carrello.getProdotti();
        Set<Prodotto> prodottiCarrello = prodottiCarrelloMap.keySet();
        boolean added = false;
        for (Prodotto p : prodottiCarrello) {
            if (p.getCodice() == codiceProdotto) {
                prodottiCarrelloMap.replace(p, prodottiCarrelloMap.get(p) + quantita);
                added = true;
                break;
            }
        }
        if (!added) {
            ProdottoService prodottoService = new ProdottoServiceImpl();
            Prodotto p = prodottoService.prodottoCodice(codiceProdotto);
            prodottiCarrelloMap.put(p, quantita);
        }

        carrello.setProdotti(prodottiCarrelloMap);
        return carrello;
    }
}
