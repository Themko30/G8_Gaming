package main.java.Storage.Entity;

import main.java.Carrello.Service.OrdineBuilderImpl;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Ordine {

    /**
     * Il codice univoco dell'ordine.
     */
    private int numero;

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
     * Il metodo di pagamento dell'ordine.
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
     * L'HashMap dei prodotti con le relative quantità.
     */
    private LinkedHashMap<Prodotto, Integer> prodotti;

    /**
     * L'utente dell'ordine.
     */
    private Utente utente;

    /**
     * Il costruttore della classe che inizializza l'HashMap prodotti.
     */
    public Ordine() {
        prodotti = new LinkedHashMap<>();
    }

    /**
     * Il costruttore della classe che inizializza le variabili di istanza.
     * @param builder L'OrdineBuilder riempito di tutti i campi
     */
    public Ordine(OrdineBuilderImpl builder) {
        this.totale = builder.getTotale();
        this.numeroArticoli = builder.getNumeroArticoli();
        this.indirizzoSpedizione = builder.getIndirizzoSpedizione();
        this.metodoPagamento = builder.getMetodoPagamento();
        this.data = builder.getData();
        this.stato = builder.getStato();
        this.prodotti = builder.getProdotti();
        this.utente = builder.getUtente();
    }

    /**
     * Metodo Getter per ottenere il codice dell'ordine.
     * @return Il numero univoco dell'ordine
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Metodo Setter per impostare il codice dell'ordine.
     * @param numero Il numero univoco da impostare
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Metodo Getter per ottenere il prezzo totale dell'ordine.
     * @return Il totale dell'ordine
     */
    public double getTotale() {
        return totale;
    }

    /**
     * Metodo Setter per impostare il prezzo totale dell'ordine.
     * @param totale Il totale da impostare
     */
    public void setTotale(double totale) {
        this.totale = totale;
    }

    /**
     * Metodo Getter per ottenere il numero di articoli nell'ordine.
     * @return Il numero degli articoli nell'ordine
     */
    public int getNumeroArticoli() {
        return numeroArticoli;
    }

    /**
     * Metodo Setter per impostare il numero di articoli nell'ordine.
     * @param numeroArticoli Il numero degli articoli da impostare
     */
    public void setNumeroArticoli(int numeroArticoli) {
        this.numeroArticoli = numeroArticoli;
    }

    /**
     * Metodo Getter per ottenere l'indirizzo di spedizione dell'ordine.
     * @return L'indirizzo dell'ordine
     */
    public String getIndirizzoSpedizione() {
        return indirizzoSpedizione;
    }

    /**
     * Metodo Setter per impostare l'indirizzo di spedizione dell'ordine.
     * @param indirizzoSpedizione L'indirizzo da impostare
     */
    public void setIndirizzoSpedizione(String indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    /**
     * Metodo Getter per ottenere il metodo di pagamento dell'ordine.
     * @return Il metodo di pagamento dell'ordine
     */
    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    /**
     * Metodo Setter per impostare il metodo di pagamento dell'ordine.
     * @param metodoPagamento Il metodo di pagamento da impostare
     */
    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    /**
     * Metodo Getter per ottenere la data dell'ordine.
     * @return La data dell'ordine
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Metodo Setter per impostare la data dell'ordine.
     * @param data La data da impostare
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Metodo Getter per ottenere lo stato dell'ordine.
     * @return Lo stato dell'ordine
     */
    public String getStato() {
        return stato;
    }

    /**
     * Metodo Setter per impostare lo stato dell'ordine.
     * @param stato Lo stato da impostare
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Metodo Getter per ottenere i prodotti,
     * con relative quantità, dell'ordine.
     * @return L'HashMap dei prodotti con le quantità
     */
    public LinkedHashMap<Prodotto, Integer> getProdotti() {
        return prodotti;
    }

    /**
     * Metodo Setter per impostare i prodotti,
     * con relative quantità, dell'ordine.
     * @param prodotti L'HashMap da impostare
     */
    public void setProdotti(LinkedHashMap<Prodotto, Integer> prodotti) {
        this.prodotti = prodotti;
    }

    /**
     * Metodo Getter per ottenere l'utente che ha effettuato l'ordine.
     * @return L'utente che ha effettuato l'ordine
     */
    public Utente getUtente() {
        return utente;
    }

    /**
     * Metodo Setter per impostare l'utente dell'ordine.
     * @param u L'utente da impostare
     */
    public void setUtente(Utente u) {
        utente = u;
    }
}
