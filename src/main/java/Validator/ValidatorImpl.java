package main.java.Validator;

import javax.servlet.http.HttpServletRequest;
import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;

import java.util.regex.Pattern;

public class ValidatorImpl implements Validator{


    public boolean validateQuantitaProdotto(Prodotto prodotto, int quantita){
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        return prodottoDAO.doRetrieveQuantitaProdottoByCodice(prodotto.getCodice()) >= quantita;
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

    @Override
    public String getPath(HttpServletRequest req) {
        return req.getPathInfo() != null ? req.getPathInfo() : "/";
    }
}
