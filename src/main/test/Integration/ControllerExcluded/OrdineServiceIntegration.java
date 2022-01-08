package Integration.ControllerExcluded;

import main.java.Carrello.Service.OrdineBuilderImpl;
import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Dao.OrdineDAO;
import main.java.Storage.Dao.OrdineDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class OrdineServiceIntegration {

    OrdineService ordineService = new OrdineServiceImpl();

    @Test
    public void testSaveOrdineFailNoProducts() {
        Utente u = new Utente();
        u.setUsername("Xiopani");
        Carrello carrello = new CarrelloDAOImpl().doRetrieveCarrelloByUtente(u);

        Ordine o = ordineService.createOrdine(carrello, "via Donna, 16", 80053, "Xiopanation", "Visa");
        assertTrue(ordineService.saveOrdine(o) == false);
    }

    @Test
    public void testSaveOrdineFailNoUser() {
        Utente u = new Utente();
        u.setUsername("tizio");
        Carrello carrello = new Carrello();
        carrello.setUtente(u);
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<Prodotto, Integer>();
        Prodotto p = new Prodotto();
        p.setCodice(1);
        prodotti.put(p, 1);
        carrello.setProdotti(prodotti);
        Ordine o = ordineService.createOrdine(carrello, "via Donna, 16", 80053, "Xiopanation", "Visa");
        assertThrows(RuntimeException.class, ()-> ordineService.saveOrdine(o));
    }

    @Test
    public void testSaveOrdineOK() {
        Utente u = new Utente();
        u.setUsername("acaro");
        Carrello carrello = new CarrelloDAOImpl().doRetrieveCarrelloByUtente(u);
        Ordine o = ordineService.createOrdine(carrello, "via Donna, 16", 80053, "Xiopanation", "Visa");

        assertTrue(ordineService.saveOrdine(o));

        OrdineDAO ordineDAO = new OrdineDAOImpl();
        ArrayList<Ordine> ordiniAfterTest = ordineDAO.doRetrieveOrdiniByUtente(u);
        assertTrue(ordiniAfterTest.size() == 10);
    }

    @Test
    public void testRetrieveOrdersNoUser(){
        Utente u = new Utente();
        u.setUsername("tizio");

        assertTrue(ordineService.retrieveOrders(u).size() == 0);

    }

    @Test
    public void testRetrieveOrdersOK(){
        Utente u = new Utente();
        u.setUsername("acaro");

        assertTrue(ordineService.retrieveOrders(u).size() == 10);

    }

    @Test
    public void testRetrieveOrderFail(){
        assertThrows(RuntimeException.class, ()->ordineService.retrieveOrder(40));
    }

    @Test
    public void testRetrieveOrderOK(){
        Ordine ordine = ordineService.retrieveOrder(9);
        assertTrue(ordine.getNumero() == 9);
        assertTrue(ordine.getNumeroArticoli() == 1);
        assertTrue(ordine.getTotale() == 19.97);
    }



}
