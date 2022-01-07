package main.java.Storage.Entity;

import java.util.LinkedHashMap;


public class Carrello {

    private double totale;
    private int numeroArticoli;

    private Utente utente;
    private LinkedHashMap<Prodotto, Integer> prodotti;

    public Carrello() {
        prodotti = new LinkedHashMap<>();
    }

    public double getTotale() {
        return Math.floor(totale * 100) / 100;
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
