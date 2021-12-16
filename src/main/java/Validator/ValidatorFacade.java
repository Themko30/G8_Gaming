package main.java.Validator;

import main.java.Carrello.Carrello;
import main.java.Catalogo.Prodotto;

public class ValidatorFacade {
    private ValidatorOrdine validatorOrdine = new ValidatorOrdine();
    private ValidatorIndirizzo validatorIndirizzo = new ValidatorIndirizzo();

    public boolean validateQuantitaProdotto(Prodotto prodotto, int quantita){
        return validatorOrdine.checkProdotto(prodotto, quantita);
    }

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException {
        validatorIndirizzo.checkIndirizzo(indirizzo, cap, paese);
    }
}
