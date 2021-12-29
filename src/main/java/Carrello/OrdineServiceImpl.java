package main.java.Carrello;

import java.util.ArrayList;
import main.java.Autenticazione.Utente;

import java.time.LocalDate;

public class OrdineServiceImpl implements OrdineService{

    private final OrdineBuilder ordineBuilder = new OrdineBuilderImpl();
    private final OrdineDAO ordineDAO = new OrdineDAO();
    @Override
    public Ordine createOrdine(Carrello carrello, String indirizzo, Integer CAP, String paese, String metodoPagamento) {

        String indirizzoSpedizione = indirizzo + ", " + CAP +", " + paese;

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
    public void setProdottoValutato(int codiceOrdine, int codiceProdotto) {
        ordineDAO.doSetProdottoValutato(codiceOrdine, codiceProdotto);
    }

    @Override
    public ArrayList<Ordine> allOrders() {
        return ordineDAO.doRetrieveAllOrdini(0, 100);
    }

    @Override
    public void updateStato(int numeroOrdine, String stato) {
        ordineDAO.doUpdateStatoOrdine(numeroOrdine, stato);
    }

    @Override
    public int counterOrdini() {
        return ordineDAO.doRetrieveCounterOrdini();
    }
}
