package main.java.Validator;

import main.java.Carrello.Carrello;
import main.java.Catalogo.Prodotto;

public class ValidatorFacade {
    private ValidatorOrdine validatorOrdine = new ValidatorOrdine();

    public boolean validateQuantitaProdotto(Prodotto prodotto, int quantita){
        return validatorOrdine.checkProdotto(prodotto, quantita);
    }

}
