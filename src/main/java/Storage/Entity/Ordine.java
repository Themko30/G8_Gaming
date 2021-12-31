package main.java.Storage.Entity;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import main.java.Carrello.OrdineBuilderImpl;

public class Ordine {

    private int numero;
    private double totale;
    private int numeroArticoli;
    private String indirizzoSpedizione;
    private String metodoPagamento;
    private LocalDate data;
    private String stato;

    private LinkedHashMap<Prodotto, Integer> prodotti;

    private Utente utente;

    public Ordine() {
        prodotti = new LinkedHashMap<>();
    }

    public Ordine(OrdineBuilderImpl builder) {
        this.totale = builder.getTotale();
        this.numero = builder.getNumeroArticoli();
        this.indirizzoSpedizione = builder.getIndirizzoSpedizione();
        this.metodoPagamento = builder.getMetodoPagamento();
        this.data = builder.getData();
        this.stato = builder.getStato();
        this.prodotti = builder.getProdotti();
        this.utente = builder.getUtente();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    public void setNumeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
    }

    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public LinkedHashMap<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    public void setProdotti(LinkedHashMap<Prodotto, Integer> prodotti) {
        this.prodotti = prodotti;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente u) {
        utente = u;
    }
}
