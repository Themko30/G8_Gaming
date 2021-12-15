package main.java.Validator;

import main.java.Carrello.Carrello;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;

import java.util.LinkedHashMap;
import java.util.Set;

public class ValidatorOrdine {
    public boolean checkProdotto(Prodotto p, int quantita){

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        if(prodottoDAO.doRetrieveQuantitaProdottoByCodice(p.getCodice()) < quantita){
            return false;
        }

        return true;
    }

}
