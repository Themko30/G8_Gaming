package main.java.Catalogo;

import java.util.ArrayList;

public class ProdottoServiceImpl implements ProdottoService{

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();

    @Override
    public ArrayList<Prodotto> prodottiCategoria(String categoria) {
        return prodottoDAO.doRetrieveProdottiByCategoria(categoria, 0, 100);
    }

    @Override
    public Prodotto prodottoCodice(int codice){
        return prodottoDAO.doRetrieveProdottoByCodice(codice);
    }

    @Override
    public int quantitaProdotto(Prodotto prodotto) {
        return prodottoDAO.doRetrieveQuantitaProdottoByCodice(prodotto.getCodice());
    }

    @Override
    public int updateValutazione(Prodotto prodotto, int valutazione) {
        return prodottoDAO.doUpdateMedia(prodotto, valutazione);
    }

    @Override
    public ArrayList<Prodotto> prodottiNomeLike(String nome) {
        return prodottoDAO.doRetrieveProdottiByNomeLike(nome, 100, 0);
    }

    @Override
    public ArrayList<Prodotto> allProdotti() {
        return prodottoDAO.doRetrieveProdotti(100, 0);
    }
}
