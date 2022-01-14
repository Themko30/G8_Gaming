package main.java.Storage.Entity;

import org.json.JSONObject;

public class Prodotto {

    /**
     * Il codice univoco del prodotto.
     */
    private int codice;

    /**
     * La categoria del prodotto.
     */
    private String categoria;

    /**
     * Il nome del prodotto.
     */
    private String nome;

    /**
     * La piattaforma del prodotto.
     */
    private String piattaforma;

    /**
     * Il prezzo del prodotto.
     */
    private double prezzo;

    /**
     * Lo sconto del prodotto.
     */
    private double scontoAttivo;

    /**
     * La quantità del prodotto.
     */
    private int quantita;

    /**
     * La descrizione del prodotto.
     */
    private String descrizione;

    /**
     * La copertina del prodotto.
     */
    private String copertina;

    /**
     * Il numero totale delle valutazioni del prodotto.
     */
    private int numeroVoti;

    /**
     * La somma di tutte le valutazioni del prodotto.
     */
    private int totaleVoti;

    /**
     * La valutazione media, ottenuta dal numero di voti
     * totali e dalla somma delle valutazioni, del prodotto.
     */
    private double media;

    /**
     * La media senza parte decimale del prodotto.
     */
    private int mediaArrotondata;

    /**
     * Il flag che indica se il prodotto è stato valutato.
     */
    private int valutato;

    /**
     * Il costruttore vuoto della classe.
     */
    public Prodotto() { }

    /**
     * Metodo Getter per ottenere il codice del prodotto.
     * @return Il codice univoco del prodotto
     */
    public int getCodice() {
        return codice;
    }

    /**
     * Metodo Setter per impostare il codice del prodotto.
     * @param codice Il codice univoco da impostare
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }

    /**
     * Metodo Getter per ottenere la categoria del prodotto.
     * @return La categoria del prodotto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Metodo Setter per impostare la categoria del prodotto.
     * @param categoria La categoria da impostare
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo Getter per ottenere il nome del prodotto.
     * @return Il nome del prodotto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo Setter per impostare il nome del prodotto.
     * @param nome Il nome da impostare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo Getter per ottenere la piattaforma del prodotto.
     * @return La piattaforma del prodotto
     */
    public String getPiattaforma() {
        return piattaforma;
    }

    /**
     * Metodo Setter per impostare la piattaforma del prodotto.
     * @param piattaforma La piattaforma da impostare
     */
    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    /**
     * Metodo Getter per ottenere il prezzo del prodotto.
     * @return Il prezzo del prodotto
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**
     * Metodo Setter per impostare il prezzo del prodotto.
     * @param prezzo Il prezzo da impostare
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Metodo Getter per ottenere lo sconto del prodotto.
     * @return Lo sconto (0 - 0.99) del prodotto
     */
    public double getScontoAttivo() {
        return scontoAttivo;
    }

    /**
     * Metodo Setter per impostare lo sconto del prodotto.
     * @param scontoAttivo Lo sconto da impostare
     */
    public void setScontoAttivo(double scontoAttivo) {
        this.scontoAttivo = scontoAttivo;
    }

    /**
     * Metodo Getter per ottenere la quantità del prodotto.
     * @return La quantità del prodotto
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * Metodo Setter per impostare la quantità del prodotto.
     * @param quantita La quantità da impostare
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * Metodo Getter per ottenere la descrizione del prodotto.
     * @return La descrizione del prodotto
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Metodo Setter per impostare la descrizione del prodotto.
     * @param descrizione La descrizione da impostare
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Metodo Getter per ottenere l'immagine del prodotto.
     * @return L'immagine del prodotto
     */
    public String getCopertina() {
        return copertina;
    }

    /**
     * Metodo Setter per impostare l'immagine del prodotto.
     * @param copertina L'immagine da impostare
     */
    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    /**
     * Metodo Getter per ottenere il numero totale di voti del prodotto.
     * @return Il numero di voti del prodotto
     */
    public int getNumeroVoti() {
        return numeroVoti;
    }

    /**
     * Metodo Setter per impostare il numero totale di voti del prodotto.
     * @param numeroVoti Il numero da impostare
     */
    public void setNumeroVoti(int numeroVoti) {
        this.numeroVoti = numeroVoti;
    }

    /**
     * Metodo Getter per ottenere la somma delle valutazioni del prodotto.
     * @return La somma delle valutazioni del prodotto
     */
    public int getTotaleVoti() {
        return totaleVoti;
    }

    /**
     * Metodo Setter per impostare la somma delle valutazioni del prodotto.
     * @param totaleVoti La somma da impostare
     */
    public void setTotaleVoti(int totaleVoti) {
        this.totaleVoti = totaleVoti;
    }

    /**
     * Metodo Getter per ottenere la media delle valutazioni del prodotto.
     * @return La media delle valutazioni del prodotto
     */
    public double getMedia() {
        return media;
    }

    /**
     * Metodo Setter per impostare la media delle valutazioni del prodotto.
     * @param media La media da impostare
     */
    public void setMedia(double media) {
        this.media = media;
    }

    /**
     * Metodo Getter per ottenere il flag valutato del prodotto.
     * @return 1 se il prodotto è stato valutato, 0 altrimenti
     */
    public int getValutato() {
        return valutato;
    }

    /**
     * Metodo Setter per impostare il flag valutato del prodotto.
     * @param valutato 1 se il prodotto è stato valutato, 0 altrimenti
     */
    public void setValutato(int valutato) {
        this.valutato = valutato;
    }

    /**
     * Metodo Getter per ottenere la media
     * arrotondata delle valutazioni del prodotto.
     * @return La media senza valore decimale delle valutazioni del prodotto
     */
    public int getMediaArrotondata() {
        return (int) Math.round(media);
    }

    /**
     * Metodo Setter per impostare la media
     * arrotondata delle valutazioni del prodotto.
     * @param mediaArrotondata La media senza valore decimale da impostare
     */
    public void setMediaArrotondata(int mediaArrotondata) {
        this.mediaArrotondata = mediaArrotondata;
    }

    /**
     * Metodo per ottenere un oggetto json contenente il nome
     * e il codice univoco del prodotto. Utilizzato per
     * riempire i risultati nella barra di ricerca.
     * @return L'Oggetto JSON contenente nome e codice del prodotto
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        object.put("codice", codice);
        object.put("nome", nome);
        return object;
    }

    /**
     * Metodo Getter per ottenere il prezzo scontato del prodotto.
     * @return Il prezzo scontato del prodotto
     */
    public double getPrezzoScontato() {
        return Math.floor((prezzo - prezzo * scontoAttivo) * 100) / 100;
    }
}
