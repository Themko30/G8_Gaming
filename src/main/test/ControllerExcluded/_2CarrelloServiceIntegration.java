package ControllerExcluded;

import main.java.Autenticazione.Service.UtenteService;
import main.java.Autenticazione.Service.UtenteServiceImpl;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import org.junit.Test;

import java.time.LocalDate;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class _2CarrelloServiceIntegration {
    CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
    CarrelloDAOImpl carrelloDAO = new CarrelloDAOImpl();

    @Test
    public void testClearCarrello() {
        Carrello carrello = new Carrello();
        Utente u = new Utente();
        u.setUsername("acaro");
        carrello.setUtente(u);

        Carrello afterTest = carrelloService.clearCarrello(carrello);

        assertTrue(afterTest.getProdotti().size() == 0);
        assertTrue(afterTest.getNumeroArticoli()==0);
        assertTrue(afterTest.getTotale()==0.0);

        Carrello afterTestDB = carrelloDAO.doRetrieveCarrelloByUtente(u);

        assertTrue(afterTestDB.getProdotti().size() == 0);
        assertTrue(afterTestDB.getNumeroArticoli() == 0);
        assertTrue(afterTestDB.getTotale() == 0.0);
    }

    @Test
    public void testUpdateCarrello() {
        Carrello carrello = new Carrello();
        Prodotto prodotto = new Prodotto();
        prodotto.setCodice(1);
        prodotto.setPrezzo(79.99);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 2);
        carrello.setProdotti(prodotti);
        carrello.setTotale(prodotto.getPrezzo() * 2);
        carrello.setNumeroArticoli(2);
        Utente u = new Utente();
        u.setUsername("acaro");
        carrello.setUtente(u);

        boolean update = carrelloService.updateCarrello(carrello);

        assertTrue(update);

        Carrello afterTestDB = carrelloDAO.doRetrieveCarrelloByUtente(u);

        assertTrue(afterTestDB.getProdotti().size() == 1);
        assertTrue(afterTestDB.getNumeroArticoli() == 2);
        assertTrue(afterTestDB.getTotale() == 159.97);
    }

    @Test
    public void testCreateCarrelloAlreadyCreated() {
        Utente u = new Utente();
        u.setUsername("acaro");

        assertThrows(RuntimeException.class, () -> carrelloService.creaCarrello(u));

    }

    @Test
    public void testCreateCarrello() throws Exception {

        UtenteService utenteService = new UtenteServiceImpl();
        Utente utente = utenteService.createUtente("biopani", "biopami@gmail.com", "xiopani123", "Xiopani", "xiopani", "altro", LocalDate.of(2000, 2, 2), "via Donna, 16", 80053, "Xiopanation");
        UtenteDAO utenteDAO= new UtenteDAOImpl();
        utenteDAO.doSaveUtente(utente);


        boolean saved = carrelloService.creaCarrello(utente);

        assertTrue(saved);

        Carrello afterTestDB = carrelloDAO.doRetrieveCarrelloByUtente(utente);

        assertTrue(afterTestDB.getProdotti().size() == 0);
        assertTrue(afterTestDB.getNumeroArticoli() == 0);
        assertTrue(afterTestDB.getTotale() == 0.0);

    }

    @Test
    public void testRecuperaCarrello() throws Exception {

        Utente u = new Utente();
        u.setUsername("acaro");


        Carrello carrelloDB = carrelloService.recuperaCarrello(u);

        assertTrue(carrelloDB.getProdotti().size() == 1);
        assertTrue(carrelloDB.getNumeroArticoli() == 2);
        assertTrue(carrelloDB.getTotale() == 159.97);

    }


}
