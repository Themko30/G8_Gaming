package main.java.Catalogo.Service;

import java.util.ArrayList;
import main.java.Storage.Entity.Prodotto;

public interface ProdottoService {

    Prodotto creaProdotto(String categoria, String nome, String piattaforma, double prezzo, double scontoAttivo, int quantita, String descrizione, String copertina);

    ArrayList<Prodotto> prodottiCategoria(String categoria);

    Prodotto prodottoCodice(int codice);

    int quantitaProdotto(Prodotto prodotto);

    boolean updateValutazione(Prodotto prodotto, int valutazione);

    ArrayList<Prodotto> prodottiNomeLike(String nome);

    ArrayList<Prodotto> allProdotti();

    ArrayList<Prodotto> prodottiPiattaforma(String piattaforma);

    boolean saveProdotto(Prodotto prodotto);

    String updateProdotto(Prodotto prodotto);

    int counterProdotti();

    ArrayList<Prodotto> mostVoted();
}
