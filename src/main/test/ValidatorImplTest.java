import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Exceptions.InvalidUserException;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

public class ValidatorImplTest {

  ValidatorImpl validator;

  @Test
  public void quantitaProdottoMinore() throws InvalidProductQuantityException {
    int TESTQUANTITASBAGLIATO = 50;
    validator = new ValidatorImpl();
    ProdottoServiceImpl prodottoServiceImpl = Mockito.mock(ProdottoServiceImpl.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodottoServiceImpl.quantitaProdotto(prodotto)).thenReturn(7);
    validator.setProdottoService(prodottoServiceImpl);
    assertThrows(InvalidProductQuantityException.class, () -> validator.validateQuantitaProdotto(prodotto, TESTQUANTITASBAGLIATO));
  }

  @Test
  public void quantitaProdottoOkay() throws InvalidProductQuantityException {
    int TESTQUANTITAGIUSTO = 5;
    validator = new ValidatorImpl();
    ProdottoServiceImpl prodottoServiceImpl = Mockito.mock(ProdottoServiceImpl.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodottoServiceImpl.quantitaProdotto(prodotto)).thenReturn(7);
    validator.setProdottoService(prodottoServiceImpl);
    validator.validateQuantitaProdotto(prodotto, TESTQUANTITAGIUSTO);
  }

  @Test
  public void indirizzoPatternSbagliato() {
    String indirizzoPatternSbagliato = "via Donna Orcama";
    int capGiusto = 80053;
    String paeseGiusto = "Italia";
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternSbagliato, capGiusto, paeseGiusto));
  }

  @Test
  public void capErrore() {
    String indirizzoPatternGiusto = "via Mario, 54";
    String paeseGiusto = "Italia";
    int capCorto = 00003;
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternGiusto, capCorto, paeseGiusto));
  }

  @Test
  public void paeseErrore() {
    String indirizzoPatternGiusto = "via Mario, 54";
    int capGiusto = 80053;
    String paeseErrore = "Italia 1";
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternGiusto, capGiusto, paeseErrore));
  }

  @Test
  public void validateIndirizzoGiusto() throws InvalidIndirizzoException {
    String indirizzoPatternGiusto = "via Mario, 54";
    int capGiusto = 80053;
    String paeseGiusto = "Italia";
    validator = new ValidatorImpl();
    validator.validateIndirizzo(indirizzoPatternGiusto, capGiusto, paeseGiusto);
  }

  @Test
  public void pathNull() {
    String path = null;
    validator = new ValidatorImpl();
    assertEquals("/", validator.validatePath(path));
  }

  @Test
  public void validateImagePath() throws IOException, InvalidProductException {
    validator = new ValidatorImpl();
    Collection<Part> parts = new ArrayList<>();
    Part part = Mockito.mock(Part.class);
    parts.add(part);
    when(part.getContentType()).thenReturn("Java");
    doNothing().when(part).write(isA(String.class));
    BufferedImage image = Mockito.mock(BufferedImage.class);
    File file = Mockito.mock(File.class);
    ImageIO io = null;
    when(io.read(file)).thenReturn(image);
    when(image.getWidth()).thenReturn(1500);
    when(image.getHeight()).thenReturn(1900);
    assertThrows(InvalidProductException.class, () -> validator.validateImage("ciao", parts));
  }


  @Test
  public void prodottoNomeLengthMinore() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("ci");
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoDescrizioneLengthMinore() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("desc");
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoInvalidCopertina() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.bit");
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoQuantitaMinore() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(-1);
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoPrezzoMinore() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(10);
    when(prodotto.getPrezzo()).thenReturn(0.0);
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));

  }

  @Test
  public void prodottoScontoAttivoMaggiore() {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(10);
    when(prodotto.getPrezzo()).thenReturn(10.25);
    when(prodotto.getScontoAttivo()).thenReturn(1.25);
    assertThrows(InvalidProductException.class, () -> validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoOkay() throws IOException, InvalidProductException {
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(10);
    when(prodotto.getPrezzo()).thenReturn(10.25);
    when(prodotto.getScontoAttivo()).thenReturn(0.25);
    validator.validateProdotto(prodotto);
  }

  @Test
  public void utenteUsernameInvalid() {
    validator = new ValidatorImpl();
    Utente utente = Mockito.mock(Utente.class);
    when(utente.getUsername()).thenReturn("@#");
    assertThrows(InvalidUserException.class, () -> validator.validateUtente(utente));
  }

  @Test
  public void utenteUsernameTaken() {
    validator = new ValidatorImpl();
    Utente utente = Mockito.mock(Utente.class);
    UtenteServiceImpl utenteService = Mockito.mock(UtenteServiceImpl.class);
    when(utente.getUsername()).thenReturn("marco");
    when(utenteService.checkUtente(utente.getUsername())).thenReturn(false);
    validator.setUtenteService(utenteService);
    assertThrows(Exception.class, () -> validator.validateUtente(utente));
  }
}
