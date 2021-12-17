package main.java.Validator;

import javax.servlet.http.HttpServletRequest;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;

import java.util.regex.Pattern;

public class ValidatorImpl implements Validator{


    public void validateQuantitaProdotto(Prodotto prodotto, int quantita) throws InvalidProductQuantityException{
        ProdottoService prodottoService = new ProdottoServiceImpl();
        int quantitaProdottoDatabase = prodottoService.quantitaProdotto(prodotto);
        if(quantitaProdottoDatabase<quantita){
            throw new InvalidProductQuantityException("Quantità eccessiva", prodotto);
        }
    }

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException {
        Pattern pattern = Pattern.compile("[A-Za-z]+[ ][A-Za-z]+[,][ ]?[0-9A-Za-z]+");
        if(!(pattern.matcher(indirizzo).matches())){
            throw new InvalidIndirizzoException();
        }

        String cap1 = ""+cap;
        String init = "00010";
        String end = "97100";
        if(cap1.compareTo(init) < 0 || cap1.compareTo(end) > 0 || cap1.length() !=5)
            throw new InvalidIndirizzoException();

        pattern = Pattern.compile("[A-Za-z ]+");
        if(!(pattern.matcher(paese).matches())){
            throw new InvalidIndirizzoException();
        }
    }

    public String validatePath(String path) {
        if (path == null){
            path = "/";
        }
        return path;
    }
}
