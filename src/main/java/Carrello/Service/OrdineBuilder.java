package main.java.Carrello.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public interface OrdineBuilder {

    OrdineBuilderImpl totale(double totale);

    OrdineBuilderImpl numeroArticoli(int numeroArticoli);

    OrdineBuilderImpl indirizzoSpedizione(String indirizzoSpedizione);

    OrdineBuilderImpl metodoPagamento(String metodoPagamento);

    OrdineBuilderImpl data(LocalDate data);

    OrdineBuilderImpl stato(String stato);

    OrdineBuilderImpl prodotti(LinkedHashMap<Prodotto, Integer> prodotti);

    OrdineBuilderImpl utente(Utente utente);

    Ordine build();

    double getTotale();

    int getNumeroArticoli();

    String getIndirizzoSpedizione();

    String getMetodoPagamento();

    LocalDate getData();

    String getStato();

    LinkedHashMap<Prodotto, Integer> getProdotti();

    Utente getUtente();
}
