import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Exceptions.InvalidUserException;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ValidatorImplTest {

  private final int TESTQUANTITAGIUSTO = 5;
  private final int TESTQUANTITASBAGLIATO = 50;
  private String indirizzoPatternSbagliato = "via Donna Orcama";
  private String indirizzoPatternGiusto = "via Mario, 54";
  private int capGiusto = 80053;
  private String paeseGiusto = "Italia";
  private int capCorto = 00003;
  private String paeseErrore = "Italia 1";

  ValidatorImpl validator;

  @Test
  public void quantitaProdottoMinore() throws InvalidProductQuantityException {
    validator = new ValidatorImpl();
    ProdottoServiceImpl prodottoServiceImpl = Mockito.mock(ProdottoServiceImpl.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodottoServiceImpl.quantitaProdotto(prodotto)).thenReturn(7);
    validator.setProdottoService(prodottoServiceImpl);
    assertThrows(InvalidProductQuantityException.class, () -> validator.validateQuantitaProdotto(prodotto, TESTQUANTITASBAGLIATO));
  }

  @Test
  public void quantitaProdottoOkay() throws InvalidProductQuantityException {
    validator = new ValidatorImpl();
    ProdottoServiceImpl prodottoServiceImpl = Mockito.mock(ProdottoServiceImpl.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodottoServiceImpl.quantitaProdotto(prodotto)).thenReturn(7);
    validator.setProdottoService(prodottoServiceImpl);
    validator.validateQuantitaProdotto(prodotto, TESTQUANTITAGIUSTO);
  }

  @Test
  public void indirizzoPatternSbagliato() {
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternSbagliato, capGiusto, paeseGiusto));
  }

  @Test
  public void capErrore() {
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternGiusto, capCorto, paeseGiusto));
  }

  @Test
  public void paeseErrore() {
    validator = new ValidatorImpl();
    assertThrows(InvalidIndirizzoException.class, () -> validator.validateIndirizzo(indirizzoPatternGiusto, capGiusto, paeseErrore));
  }













  @Test
  public void prodottoNomeLengthMinore(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("ci");
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoDescrizioneLengthMinore(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("desc");
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoInvalidCopertina(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.bit");
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoQuantitaMinore(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(-1);
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoPrezzoMinore(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(10);
    when(prodotto.getPrezzo()).thenReturn(0.0);
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));

  }

  @Test
  public void prodottoScontoAttivoMaggiore(){
    validator = new ValidatorImpl();
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodotto.getNome()).thenReturn("prodotto");
    when(prodotto.getDescrizione()).thenReturn("descrizione bella per il prodotto");
    when(prodotto.getCopertina()).thenReturn("image.png");
    when(prodotto.getQuantita()).thenReturn(10);
    when(prodotto.getPrezzo()).thenReturn(10.25);
    when(prodotto.getScontoAttivo()).thenReturn(1.25);
    assertThrows(InvalidProductException.class, ()->validator.validateProdotto(prodotto));
  }

  @Test
  public void prodottoOkay() throws IOException,InvalidProductException{
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
  public void utenteUsernameInvalid(){
    validator = new ValidatorImpl();
    Utente utente = Mockito.mock(Utente.class);
    when(utente.getUsername()).thenReturn("@#");
    assertThrows(InvalidUserException.class, ()->validator.validateUtente(utente));
  }

  @Test
  public void utenteUsernameTaken(){
    validator = new ValidatorImpl();
    Utente utente = Mockito.mock(Utente.class);
    UtenteServiceImpl utenteService = Mockito.mock(UtenteServiceImpl.class);
    when(utente.getUsername()).thenReturn("marco");
    when(utenteService.checkUtente(utente.getUsername())).thenReturn(false);
    validator.setUtenteService(utenteService);
    assertThrows(Exception.class, ()->validator.validateUtente(utente));
  }
}
