package main.java.Catalogo;

import java.util.ArrayList;

public interface ProdottoService {
    public ArrayList<Prodotto> prodottiCategoria(String categoria);
    public Prodotto prodottoCodice(int codice);
    public int quantitaProdotto(Prodotto prodotto);
}
