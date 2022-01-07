package main.java.Carrello.Service;

import java.util.LinkedHashMap;
import java.util.Set;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Dao.CarrelloDAO;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public class CarrelloServiceImpl implements CarrelloService {

    private CarrelloDAO carrelloDAO = new CarrelloDAOImpl();
    private ProdottoService prodottoService = new ProdottoServiceImpl();

    public void setProdottoService(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    public void setCarrelloDAO(CarrelloDAO carrelloDAO) {
        this.carrelloDAO = carrelloDAO;
    }

    @Override
    public Carrello updateQuantitaCarrelloSession(Carrello carrello,
                                                  int codiceProdotto,
                                                  int quantita) {
        if (quantita < 1) {
            return rimuoviProdottoCarrelloSession(carrello, codiceProdotto);
        }
        LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
        Set<Prodotto> prodotti = prodottiMap.keySet();
        for (Prodotto p : prodotti) {
            if (p.getCodice() == codiceProdotto) {
                if (quantita > p.getQuantita()) {
                    return carrello;
                }
                double prezzoScontato =
                        Math.floor((p.getPrezzo()
                                - p.getPrezzo()
                                * p.getScontoAttivo()) * 100) / 100;
                carrello.setNumeroArticoli(carrello.getNumeroArticoli()
                        - prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale()
                        - (prezzoScontato) * prodottiMap.get(p));
                prodottiMap.replace(p, quantita);
                carrello.setNumeroArticoli(carrello.getNumeroArticoli()
                        + prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale()
                        + (prezzoScontato) * prodottiMap.get(p));

                break;
            }
        }
        carrello.setProdotti(prodottiMap);
        return carrello;
    }

    @Override
    public Carrello rimuoviProdottoCarrelloSession(Carrello carrello,
                                                   int codiceProdotto) {

        LinkedHashMap<Prodotto, Integer> prodottiMap = carrello.getProdotti();
        Set<Prodotto> prodotti = prodottiMap.keySet();
        for (Prodotto p : prodotti) {
            if (p.getCodice() == codiceProdotto) {
                double prezzoScontato =
                        Math.floor((p.getPrezzo() - p.getPrezzo()
                                * p.getScontoAttivo()) * 100) / 100;
                carrello.setNumeroArticoli(carrello.getNumeroArticoli()
                        - prodottiMap.get(p));
                carrello.setTotale(carrello.getTotale()
                        - (prezzoScontato) * prodottiMap.get(p));
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
    public Carrello aggiungiProdotto(Carrello carrello,
                                     int codiceProdotto,
                                     int quantita) {
        if (quantita > 0) {
            LinkedHashMap<Prodotto, Integer> prodottiCarrelloMap =
                    carrello.getProdotti();
            Set<Prodotto> prodottiCarrello =
                    prodottiCarrelloMap.keySet();
            boolean added = false;
            for (Prodotto p : prodottiCarrello) {
                if (p.getCodice() == codiceProdotto) {
                    double prezzoScontato =
                            Math.floor((p.getPrezzo() - p.getPrezzo()
                                    * p.getScontoAttivo()) * 100) / 100;
                    prodottiCarrelloMap.replace(p,
                            prodottiCarrelloMap.get(p) + quantita);
                    carrello.setNumeroArticoli(carrello.getNumeroArticoli()
                            + quantita);
                    carrello.setTotale(carrello.getTotale()
                            + (prezzoScontato) * quantita);
                    added = true;
                    break;
                }
            }
            if (!added) {
                Prodotto p =
                        prodottoService.prodottoCodice(codiceProdotto);
                double prezzoScontato =
                        Math.floor((p.getPrezzo() - p.getPrezzo()
                                * p.getScontoAttivo()) * 100) / 100;
                prodottiCarrelloMap.put(p, quantita);
                carrello.setNumeroArticoli(carrello.getNumeroArticoli()
                        + quantita);
                carrello.setTotale(carrello.getTotale()
                        + (prezzoScontato) * quantita);
            }

            carrello.setProdotti(prodottiCarrelloMap);
        }
        return carrello;
    }
}
