package main.java.Carrello.Service;

import java.util.LinkedHashMap;
import java.util.Set;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public class CarrelloServiceImpl implements CarrelloService {

    private CarrelloDAOImpl carrelloDAO = new CarrelloDAOImpl();

    @Override
    public Carrello updateQuantitaCarrelloSession(Carrello carrello, int codiceProdotto, int quantita) {
        LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
        Set<Prodotto> prodotti = prodottiMap.keySet();
        for (Prodotto p : prodotti) {
            if (p.getCodice() == codiceProdotto) {
                carrello.setNumeroArticoli(carrello.getNumeroArticoli() - prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale() - p.getPrezzo()*p.getScontoAttivo()*prodottiMap.get(p));
                prodottiMap.replace(p, quantita);
                carrello.setNumeroArticoli(carrello.getNumeroArticoli() + prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale() + p.getPrezzo()*p.getScontoAttivo()*prodottiMap.get(p));

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
                carrello.setNumeroArticoli(carrello.getNumeroArticoli() - prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale() - p.getPrezzo()*p.getScontoAttivo()*prodottiMap.get(p));
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
    public boolean creaCarrello(Utente utente) {
        return carrelloDAO.doCreateCarrello(utente);
    }

    @Override
    public Carrello recuperaCarrello(Utente utente) {
        return carrelloDAO.doRetrieveCarrelloByUtente(utente);
    }

    @Override
    public Carrello aggiungiProdotto(Carrello carrello, int codiceProdotto, int quantita) {
        if(quantita>0){
            LinkedHashMap<Prodotto, Integer> prodottiCarrelloMap = carrello.getProdotti();
            Set<Prodotto> prodottiCarrello = prodottiCarrelloMap.keySet();
            boolean added = false;
            for (Prodotto p : prodottiCarrello) {
                if (p.getCodice() == codiceProdotto) {
                    prodottiCarrelloMap.replace(p, prodottiCarrelloMap.get(p) + quantita);
                    carrello.setNumeroArticoli(carrello.getNumeroArticoli() + quantita);
                    carrello.setTotale(carrello.getTotale() + p.getPrezzo() * p.getScontoAttivo() * quantita);
                    added = true;
                    break;
                }
            }
            if (!added) {
                ProdottoService prodottoService = new ProdottoServiceImpl();
                Prodotto p = prodottoService.prodottoCodice(codiceProdotto);
                prodottiCarrelloMap.put(p, quantita);
                carrello.setNumeroArticoli(carrello.getNumeroArticoli() + quantita);
                carrello.setTotale(carrello.getTotale() + p.getPrezzo() * p.getScontoAttivo() * quantita);
            }

            carrello.setProdotti(prodottiCarrelloMap);
        }
        return carrello;
    }
}
