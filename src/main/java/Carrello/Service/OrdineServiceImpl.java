package main.java.Carrello.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import main.java.Storage.Dao.OrdineDAO;
import main.java.Storage.Dao.OrdineDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;

public class OrdineServiceImpl implements OrdineService {

    private OrdineBuilder ordineBuilder = new OrdineBuilderImpl();
    private final OrdineDAO ordineDAO = new OrdineDAOImpl();

    @Override
    public Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento) {

        String indirizzoSpedizione = indirizzo + ", " + CAP + ", " + paese;

        return ordineBuilder.utente(carrello.getUtente())
                .totale(carrello.getTotale())
                .numeroArticoli(carrello.getNumeroArticoli())
                .indirizzoSpedizione(indirizzoSpedizione)
                .metodoPagamento(metodoPagamento)
                .data(LocalDate.now())
                .prodotti(carrello.getProdotti())
                .build();
    }

    @Override
    public boolean saveOrdine(Ordine ordine) {
        return ordineDAO.doSaveOrdine(ordine);
    }

    @Override
    public ArrayList<Ordine> retrieveOrders(Utente utente) {
        return ordineDAO.doRetrieveOrdiniByUtente(utente);
    }

    @Override
    public Ordine retrieveOrder(int codiceOrdine) {
        return ordineDAO.doRetrieveProdottiAcquistati(codiceOrdine);
    }

    @Override
    public boolean setProdottoValutato(int codiceOrdine, int codiceProdotto) {
        return ordineDAO.doSetProdottoValutato(codiceOrdine, codiceProdotto);
    }

    @Override
    public ArrayList<Ordine> allOrders() {
        return ordineDAO.doRetrieveAllOrdini(0, 100);
    }

    @Override
    public boolean updateStato(int numeroOrdine, String stato) {
        return ordineDAO.doUpdateStatoOrdine(numeroOrdine, stato);
    }

    @Override
    public int counterOrdini() {
        return ordineDAO.doRetrieveCounterOrdini();
    }
}
