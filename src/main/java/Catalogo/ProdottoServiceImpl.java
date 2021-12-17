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


}
