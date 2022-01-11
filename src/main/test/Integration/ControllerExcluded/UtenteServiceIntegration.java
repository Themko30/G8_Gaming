package Integration.ControllerExcluded;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Entity.Utente;
import org.junit.Test;

public class UtenteServiceIntegration {

  UtenteDAOImpl UtenteDAOImpl = new UtenteDAOImpl();
  UtenteService utenteService = new UtenteServiceImpl();
  UtenteDAO utenteDao = new UtenteDAOImpl();

  @Test
  public void testSaveUtente() throws Exception {
    CarrelloService carrelloService = new CarrelloServiceImpl();
    Utente utente = utenteService.createUtente("eiopani4", "eiopami4@gmail.com", "xiopani123", "Xiopani", "xiopani", "altro", LocalDate.of(2000, 2, 2), "via Donna, 16", 80053, "Xiopanation");
    boolean result = utenteDao.doSaveUtente(utente);
    carrelloService.creaCarrello(utente);
    assertTrue(result);

    Utente utenteAfterSave = utenteDao.doRetrieveUtenteByUsername("eiopani4");
    assertTrue(utenteAfterSave.getUsername().equals("eiopani4"));
  }

  @Test
  public void testUpdateUtente() {
    Utente utente = utenteDao.doRetrieveUtenteByUsername("eiopani3");
    utente.setSesso("Maschio");
    boolean result = utenteDao.doUpdateUtente(utente);
    assertTrue(result);

    Utente utenteAfterModify = utenteDao.doRetrieveUtenteByUsername("eiopani3");
    assertTrue(utenteAfterModify.getUsername().equals("eiopani3"));
  }
}
