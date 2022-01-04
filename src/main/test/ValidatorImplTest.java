import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

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


}
