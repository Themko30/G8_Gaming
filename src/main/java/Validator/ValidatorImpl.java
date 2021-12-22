package main.java.Validator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import main.java.Catalogo.Prodotto;
import main.java.Catalogo.ProdottoDAO;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
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

    @Override
    public void validateProdotto(Prodotto prodotto, Collection<Part> image) throws InvalidProductException, IOException {
        if(prodotto.getNome().length() < 3 || prodotto.getNome().length() > 100)
            throw new InvalidProductException();
        if(prodotto.getDescrizione().length() < 10 || prodotto.getDescrizione().length() > 2048)
            throw new InvalidProductException();
        if(!(prodotto.getCopertina().endsWith(".png") || prodotto.getCopertina().endsWith(".jpg")))
            throw new InvalidProductException();
        if(prodotto.getQuantita() < 1)
            throw new InvalidProductException();
        if(prodotto.getPrezzo() < 0.01)
            throw new InvalidProductException();
        if(prodotto.getScontoAttivo() < 0 || prodotto.getScontoAttivo() > 0.99)
            throw new InvalidProductException();


        for(Part part: image){
            if(part.getContentType() != null)
                part.write("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\tmp\\"+prodotto.getCopertina());
        }


        File imgFile = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\tmp\\"+prodotto.getCopertina());

        BufferedImage bimg = ImageIO.read(imgFile);
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        if(width > 1300 || width < 800 || height > 1600 || height < 1000){
            imgFile.delete();
            throw new InvalidProductException();
        }

        imgFile.delete();


    }


}
