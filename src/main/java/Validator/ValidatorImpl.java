package main.java.Validator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import main.java.Autenticazione.UtenteService;
import main.java.Autenticazione.UtenteServiceImpl;
import main.java.Catalogo.ProdottoService;
import main.java.Catalogo.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public class ValidatorImpl implements Validator {

    private UtenteService utenteService;


    public void validateQuantitaProdotto(Prodotto prodotto, int quantita) throws InvalidProductQuantityException {
        ProdottoService prodottoService = new ProdottoServiceImpl();
        int quantitaProdottoDatabase = prodottoService.quantitaProdotto(prodotto);
        if (quantitaProdottoDatabase < quantita) {
            throw new InvalidProductQuantityException("Quantità eccessiva", prodotto);
        }
    }

    public void validateIndirizzo(String indirizzo, Integer cap, String paese) throws InvalidIndirizzoException {
        Pattern pattern = Pattern.compile("[A-Za-z]+[ ][A-Za-z]+[,][ ]?[0-9A-Za-z]+");
        if (!(pattern.matcher(indirizzo).matches())) {
            throw new InvalidIndirizzoException();
        }

        String cap1 = "" + cap;
        String init = "00010";
        String end = "97100";
        if (cap1.compareTo(init) < 0 || cap1.compareTo(end) > 0 || cap1.length() != 5) {
            throw new InvalidIndirizzoException();
        }

        pattern = Pattern.compile("[A-Za-z ]+");
        if (!(pattern.matcher(paese).matches())) {
            throw new InvalidIndirizzoException();
        }
    }

    public String validatePath(String path) {
        if (path == null) {
            path = "/";
        }
        return path;
    }

    @Override
    public void validateProdotto(Prodotto prodotto) throws InvalidProductException, IOException {
        if (prodotto.getNome().length() < 3 || prodotto.getNome().length() > 100) {
            throw new InvalidProductException();
        }
        if (prodotto.getDescrizione().length() < 10 || prodotto.getDescrizione().length() > 2048) {
            throw new InvalidProductException();
        }
        if (!(prodotto.getCopertina().endsWith(".png") || prodotto.getCopertina().endsWith(".jpg"))) {
            throw new InvalidProductException();
        }
        if (prodotto.getQuantita() < 1) {
            throw new InvalidProductException();
        }
        if (prodotto.getPrezzo() < 0.01) {
            throw new InvalidProductException();
        }
        if (prodotto.getScontoAttivo() < 0 || prodotto.getScontoAttivo() > 0.99) {
            throw new InvalidProductException();
        }

    }

    @Override
    public void validateImage(String copertina, Collection<Part> image) throws IOException, InvalidProductException {
        for (Part part : image) {
            if (part.getContentType() != null) {
                part.write("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\tmp\\" + copertina);
            }
        }

        File imgFile = new File("C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\G8_Gaming_war_exploded\\tmp\\" + copertina);

        BufferedImage bimg = ImageIO.read(imgFile);
        int width = bimg.getWidth();
        int height = bimg.getHeight();

        if (width > 1300 || width < 800 || height > 1600 || height < 1000) {
            //noinspection ResultOfMethodCallIgnored
            imgFile.delete();
            throw new InvalidProductException();
        }

        //noinspection ResultOfMethodCallIgnored
        imgFile.delete();
    }

    @Override
    public void validateUtente(Utente utente) throws InvalidUserException, InvalidIndirizzoException {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");

        if (!(pattern.matcher(utente.getUsername()).matches())) {
            throw new InvalidUserException();
        }

        utenteService = new UtenteServiceImpl();

        if (utenteService.checkUtente(utente.getUsername())) {
            throw new InvalidUserException();
        }

        if (utente.getPassword().length() < 6 || utente.getPassword().length() > 64) {
            throw new InvalidUserException();
        }

        pattern = Pattern.compile("[a-zA-Z ]+");

        if (!pattern.matcher(utente.getNome()).matches()) {
            throw new InvalidUserException();
        }

        if (utente.getNome().length() < 2 || utente.getNome().length() > 32) {
            throw new InvalidUserException();
        }

        if (!pattern.matcher(utente.getCognome()).matches()) {
            throw new InvalidUserException();
        }

        if (utente.getCognome().length() < 2 || utente.getCognome().length() > 32) {
            throw new InvalidUserException();
        }

        if (!(utente.getSesso().equals("M") || utente.getSesso().equals("F") || utente.getSesso().equals("Altro"))) {
            throw new InvalidUserException();
        }

        LocalDate localDateStart = LocalDate.of(1900, 1, 1);
        LocalDate localDateFinish = LocalDate.now();

        if (utente.getDataDiNascita().isBefore(localDateStart) || utente.getDataDiNascita().isAfter(localDateFinish)) {
            throw new InvalidUserException();
        }

        pattern = Pattern.compile(
          "[(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])]");

        if (!pattern.matcher(utente.getEmail()).matches()) {
            throw new InvalidUserException();
        }

        validateIndirizzo(utente.getIndirizzo(), utente.getCap(), utente.getPaese());
    }


}
