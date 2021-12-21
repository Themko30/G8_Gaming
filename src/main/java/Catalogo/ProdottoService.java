package main.java.Catalogo;

import java.util.ArrayList;

public interface ProdottoService {
    ArrayList<Prodotto> prodottiCategoria(String categoria);
    Prodotto prodottoCodice(int codice);
    int quantitaProdotto(Prodotto prodotto);
    int updateValutazione(Prodotto prodotto, int valutazione);
    ArrayList<Prodotto> prodottiNomeLike(String nome);
    ArrayList<Prodotto> allProdotti();
}
