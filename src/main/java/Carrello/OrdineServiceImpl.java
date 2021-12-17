package main.java.Carrello;

import main.java.Validator.Validator;

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


}
