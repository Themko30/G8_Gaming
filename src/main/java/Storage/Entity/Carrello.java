package main.java.Storage.Entity;

import java.util.LinkedHashMap;

public class Carrello {

    /**
     * Il prezzo totale del carrello.
     */
    private double totale;

    /**
     * Il numero di articoli del carrello.
     */
    private int numeroArticoli;

    /**
     * L'utente del carrello.
     */
    private Utente utente;

    /**
     * L'HashMap dei prodotti con le relative quantità.
     */
    private LinkedHashMap<Prodotto, Integer> prodotti;

    /**
     * Il costruttore della classe che inizializza l'HashMap prodotti.
     */
    public Carrello() {
        prodotti = new LinkedHashMap<>();
    }

    /**
     * Metodo Getter per ottenere il prezzo totale del carrello.
     * @return Il totale del carrello,
     * arrotondato a due cifre decimali
     */
    public double getTotale() {
        return Math.floor(totale * 100) / 100;
    }

    /**
     * Metodo Setter per impostare il prezzo totale del carrello.
     * @param totale Il totale da impostare
     */
    public void setTotale(double totale) {
        this.totale = totale;
    }

    /**
     * Metodo Getter per ottenere il numero di articoli del carrello.
     * @return Il numero di prodotti nel carrello
     */
    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    /**
     * Metodo Setter per impostare il numero di articoli del carrello.
     * @param numeroArticoli Il numero degli articoli da impostare
     */
    public void setNumeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
    }

    /**
     * Metodo Getter per ottenere i prodotti,
     * con relative quantità, del carrello.
     * @return L'HashMap dei prodotti con le quantità
     */
    public LinkedHashMap<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    /**
     * Metodo Setter per impostare i prodotti,
     * con relative quantità, del carrello.
     * @param prodotti L'HashMap da impostare
     */
    public void setProdotti(LinkedHashMap<Prodotto, Integer> prodotti) {
        this.prodotti = prodotti;
    }

    /**
     * Metodo Getter per ottenere l'utente del carrello.
     * @return L'utente del carrello,
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Metodo Setter per impostare l'utente del carrello.
     * @param u L'utente da impostare
     */
    public void setUtente(Utente u) {
        utente = u;
    }
}
