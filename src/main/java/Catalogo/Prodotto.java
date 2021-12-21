package main.java.Catalogo;

public class Prodotto {
    private int codice;
    private String categoria;
    private String nome;
    private String piattaforma;
    private double prezzo;
    private double scontoAttivo;
    private int quantita;
    private String descrizione;
    private String copertina;
    private long numeroVoti;
    private long totaleVoti;
    private double media;

    private int valutato;

    public Prodotto(){}

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getScontoAttivo() {
        return scontoAttivo;
    }

    public void setScontoAttivo(double scontoAttivo) {
        this.scontoAttivo = scontoAttivo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public long getNumeroVoti() {
        return numeroVoti;
    }

    public void setNumeroVoti(long numeroVoti) {
        this.numeroVoti = numeroVoti;
    }

    public long getTotaleVoti() {
        return totaleVoti;
    }

    public void setTotaleVoti(long totaleVoti) {
        this.totaleVoti = totaleVoti;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public int getValutato() {
        return valutato;
    }

    public void setValutato(int valutato) {
        this.valutato = valutato;
    }
}
