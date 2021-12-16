package main.java.Validator;


import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;


public class ValidatorOrdine {
    public boolean checkProdotto(Prodotto p, int quantita){

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        return prodottoDAO.doRetrieveQuantitaProdottoByCodice(p.getCodice()) >= quantita;
    }

}
