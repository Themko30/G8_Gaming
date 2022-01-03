import static org.mockito.Mockito.when;

import main.java.Catalogo.Service.ProdottoService;
import main.java.Storage.Entity.Prodotto;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import org.junit.Test;
import org.mockito.Mock;

public class ValidatorImplTest {

  private final int TESTQUANTITAGIUSTO = 5;
  private final int TESTQUANTITASBAGLIATO = 50;

  @Mock
  ProdottoService prodottoService;
  Prodotto prodotto;

  @Test
  public void validateQuantitaProdotto() throws InvalidProductQuantityException {
    assert prodottoService != null;
    when(prodottoService.quantitaProdotto(prodotto)).thenReturn(7);
    int quantitaProdottoDatabase = prodottoService.quantitaProdotto(prodotto);
    if (quantitaProdottoDatabase < TESTQUANTITAGIUSTO) {
      throw new InvalidProductQuantityException("Quantità eccessiva", prodotto);
    }
  }

  @Test(expected = java.lang.Exception.class)
  public void validateQuantitaProdottoError() throws InvalidProductQuantityException {
    when(prodottoService.quantitaProdotto(prodotto)).thenReturn(7);
    int quantitaProdottoDatabase = prodottoService.quantitaProdotto(prodotto);
    if (quantitaProdottoDatabase < TESTQUANTITASBAGLIATO) {
      throw new InvalidProductQuantityException("Quantità eccessiva", prodotto);
    }
  }
}
