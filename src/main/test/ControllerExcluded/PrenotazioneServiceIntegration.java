package ControllerExcluded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import main.java.Prenotazione.Service.PrenotazioneService;
import main.java.Prenotazione.Service.PrenotazioneServiceImpl;
import main.java.Storage.Entity.Prenotazione;
import org.junit.Test;

public class PrenotazioneServiceIntegration {
  PrenotazioneService prenotazioneService = new PrenotazioneServiceImpl();

  @Test
    public void testAllPrenotazioni() {
    ArrayList<Prenotazione> allPrenotazioni = prenotazioneService.allPrenotazioni();
    assertTrue(allPrenotazioni.size() == prenotazioneService.counterPrenotazioni());
  }

  @Test
  public void testRetrivePrenotazione() {
    int codice = 1;
    Prenotazione p = prenotazioneService.retrievePrenotazione(codice);
    assertTrue(p.getNumeroPrenotazione() == codice);
  }

  @Test
  public void testAcceptPrenotazione() {
    int codice = 1;
    Prenotazione p = prenotazioneService.retrievePrenotazione(codice);
    assertTrue(p.getAccettata() == 0);

    prenotazioneService.acceptPrenotazione(codice);
    Prenotazione pPostAccettata = prenotazioneService.retrievePrenotazione(codice);
    assertTrue(pPostAccettata.getAccettata() == 1);
  }

  @Test
  public void testSavePrenotazione() {
    Prenotazione p = new Prenotazione();
    p.setCategoria("Action");
    p.setCopertina("f.jpg");
    p.setDescrizione("aaaaaaaaaa");
    p.setEmailRichiedente("xiopani@xiopanation.com");
    p.setNomeProdotto("Xiopani Duty 3");

    boolean result = prenotazioneService.savePrenotazione(p);
    assertTrue(result);

    Prenotazione pAfterInsert = prenotazioneService.retrievePrenotazione(5);
    assertEquals("Action", p.getCategoria());
  }

  @Test
  public void testCounterPrenotazioni() {
    int counter = prenotazioneService.counterPrenotazioni();
    assertTrue(counter == 5);
  }

}
