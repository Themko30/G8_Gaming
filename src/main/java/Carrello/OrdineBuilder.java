package main.java.Carrello;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public interface OrdineBuilder {

    public OrdineBuilderImpl totale(double totale);

    public OrdineBuilderImpl numeroArticoli(int numeroArticoli);

    public OrdineBuilderImpl indirizzoSpedizione(String indirizzoSpedizione);

    public OrdineBuilderImpl metodoPagamento(String metodoPagamento);

    public OrdineBuilderImpl data(LocalDate data);

    public OrdineBuilderImpl stato(String stato);

    public OrdineBuilderImpl prodotti(LinkedHashMap<Prodotto, Integer> prodotti);

    public OrdineBuilderImpl utente(Utente utente);

    public Ordine build();

    public double getTotale();

    public int getNumeroArticoli();

    public String getIndirizzoSpedizione();

    public String getMetodoPagamento();

    public LocalDate getData();

    public String getStato();

    public LinkedHashMap<Prodotto, Integer> getProdotti();

    public Utente getUtente();
}
