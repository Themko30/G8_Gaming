package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class OrdineBuilder {
    private double totale;
    private int numeroArticoli;
    private String indirizzoSpedizione;
    private String metodoPagamento;
    private LocalDate data;
    private String stato;
    private LinkedHashMap<Prodotto, Integer> prodotti;
    private Utente utente;

    public OrdineBuilder(){    }

    public OrdineBuilder totale(double totale){
        this.totale = totale;
        return this;
    }

    public OrdineBuilder numeroArticoli(int numeroArticoli){
        this.numeroArticoli=numeroArticoli;
        return this;
    }

    public OrdineBuilder indirizzoSpedizione(String indirizzoSpedizione){
        this.indirizzoSpedizione = indirizzoSpedizione;
        return this;
    }

    public OrdineBuilder metodoPagamento(String metodoPagamento){
        this.metodoPagamento = metodoPagamento;
        return this;
    }

    public OrdineBuilder data(LocalDate data){
        this.data = data;
        return this;
    }

    public OrdineBuilder stato(String stato){
        this.stato = stato;
        return this;
    }

    public OrdineBuilder prodotti(LinkedHashMap<Prodotto, Integer> prodotti){
        this.prodotti = prodotti;
        return this;
    }

    public OrdineBuilder utente(Utente utente){
        this.utente = utente;
        return this;
    }

    public Ordine build(){
        return new Ordine(this);
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
