package ControllerExcluded;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Entity.Utente;
import org.junit.Test;

public class _4UtenteServiceIntegration {

  UtenteDAOImpl UtenteDAOImpl = new UtenteDAOImpl();
  UtenteService utenteService = new UtenteServiceImpl();
  UtenteDAO utenteDao = new UtenteDAOImpl();

  @Test
  public void testSaveUtente() throws Exception {
    Utente utente = utenteService.createUtente("eiopani5", "eiopami5@gmail.com", "xiopani123", "Xiopani", "xiopani", "altro", LocalDate.of(2000, 2, 2), "via Donna, 16", 80053, "Xiopanation");
    boolean result = utenteService.saveUtente(utente);
    assertTrue(result);

    Utente utenteAfterSave = utenteDao.doRetrieveUtenteByUsername("eiopani5");
    assertTrue(utenteAfterSave.getUsername().equals("eiopani5"));
  }

  @Test
  public void testUpdateUtente() {
    Utente utente = utenteDao.doRetrieveUtenteByUsername("espositogennaro");
    utente.setSesso("Altro");
    boolean result = utenteService.updateUtente(utente);
    assertTrue(result);

    Utente utenteAfterModify = utenteDao.doRetrieveUtenteByUsername("espositogennaro");
    assertTrue(utenteAfterModify.getUsername().equals("espositogennaro"));
    assertTrue(utenteAfterModify.getSesso().equals("Altro"));
  }

  @Test
  public void testCheckUsername() {
    boolean result = utenteService.checkUtente("acaro");
    assertTrue(result);
  }

  @Test
  public void testDoPostSetAdmin() {
    Utente utente = utenteDao.doRetrieveUtenteByUsername("acafro");
    assertFalse(utente.isAdmin());
    utenteService.setAdmin("acafro");

    Utente utentePostMethod = utenteDao.doRetrieveUtenteByUsername("acafro");
    assertTrue(utentePostMethod.isAdmin());
  }

  @Test
  public void testAllUtenti() {
    ArrayList<Utente> allUtenti = utenteService.allUtenti();
    assertTrue(allUtenti.size() == 9);
  }

  @Test
  public void testCounterUtente() {
    int counter = utenteService.counterUtente();
    assertTrue(counter == 10);
  }

  @Test
  public void testLogin() {
    String password = "antonino";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      SecureRandom ss = new SecureRandom();
      byte[] hashedPwd = digest.digest(password.getBytes(StandardCharsets.UTF_8));
      StringBuilder builder = new StringBuilder();
      for (byte bit : hashedPwd) {
        builder.append(String.format("%02x", bit));
      }
      password= builder.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }

    Utente utente = utenteService.login("acaro", password);

  }
}
