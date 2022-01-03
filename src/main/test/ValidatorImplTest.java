import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Service.ValidatorImpl;
import org.junit.Test;
import org.mockito.Mockito;

public class ValidatorImplTest {

  private final int TESTQUANTITAGIUSTO = 5;
  private final int TESTQUANTITASBAGLIATO = 50;

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

  @Test(expected = java.lang.Exception.class)
  public void quantitaProdottoOkay() throws InvalidProductQuantityException {
    ProdottoService prodottoService = Mockito.mock(ProdottoService.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    when(prodottoService.quantitaProdotto(prodotto)).thenReturn(7);
    int quantitaProdottoDatabase = prodottoService.quantitaProdotto(prodotto);
    if (quantitaProdottoDatabase < TESTQUANTITASBAGLIATO) {
      throw new InvalidProductQuantityException("Quantità eccessiva", prodotto);
    }
  }
}
