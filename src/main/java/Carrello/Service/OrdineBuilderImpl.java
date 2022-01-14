package main.java.Carrello.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public class OrdineBuilderImpl implements OrdineBuilder {

    /**
     * Il prezzo totale dell'ordine.
     */
    private double totale;

    /**
     * Il numero di articoli dell'ordine.
     */
    private int numeroArticoli;

    /**
     * L'indirizzo di spedizione dell'ordine.
     */
    private String indirizzoSpedizione;

    /**
     * Metodo di pagamento dell'ordine.
     */
    private String metodoPagamento;

    /**
     * La data dell'ordine.
     */
    private LocalDate data;

    /**
     * Lo stato dell'ordine.
     */
    private String stato;

    /**
     * L'HashMap contenente prodotti e relativa quantitò dell'ordine.
     */
    private LinkedHashMap<Prodotto, Integer> prodotti;

    /**
     * L'utente che ha effettuato l'ordine.
     */
    private Utente utente;

    /**
     * L'ordine completo.
     */
    private Ordine ordine;

    /**
     * Il costruttore vuoto della classe
     */
    public OrdineBuilderImpl() {

    }

    public OrdineBuilderImpl totale(double totale) {
        this.totale = totale;
        return this;
    }

    public OrdineBuilderImpl numeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
        return this;
    }

    public OrdineBuilderImpl indirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
        return this;
    }

    public OrdineBuilderImpl metodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
        return this;
    }

    public OrdineBuilderImpl data(LocalDate data) {
        this.data = data;
        return this;
    }

    public OrdineBuilderImpl stato(String stato) {
        this.stato = stato;
        return this;
    }

    public OrdineBuilderImpl prodotti(LinkedHashMap<Prodotto,
            Integer> prodotti) {
        this.prodotti = prodotti;
        return this;
    }

    public OrdineBuilderImpl utente(Utente utente) {
        this.utente = utente;
        return this;
    }

    public Ordine build() {
        ordine = new Ordine(this);
        return ordine;
    }

    public double getTotale() {
        return totale;
    }

    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public String getStato() {
        return stato;
    }

    public LinkedHashMap<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    public Utente getUtente() {
        return utente;
    }
}
