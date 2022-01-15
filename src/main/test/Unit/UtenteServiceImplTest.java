package Unit;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Storage.Entity.Utente;
import org.junit.Test;

public class UtenteServiceImplTest {

  @Test
  public void testCreateUtente() {
    UtenteService utenteService = new UtenteServiceImpl();
    LocalDate data = LocalDate.parse("2022-05-05");
    Utente utente = utenteService.createUtente("Xiopani", "Xiopami@gmail.com", "xiopani123", "Xiopani", "xiopani", "altro", data, "via Donna Orcama, 16", 80053, "Xiopanation");
    assertTrue(utente.getUsername().equals("Xiopani"));
  }
}
