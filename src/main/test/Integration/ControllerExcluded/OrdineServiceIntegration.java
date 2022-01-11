package Integration.ControllerExcluded;

import main.java.Carrello.Service.OrdineService;
import main.java.Carrello.Service.OrdineServiceImpl;
import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
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
import java.util.Set;

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
        assertTrue(ordiniAfterTest.size() == 13);
        ProdottoService prodottoService = new ProdottoServiceImpl();
        assertTrue(prodottoService.quantitaProdotto(prodottoService.prodottoCodice(1)) == 4);

    }

    @Test
    public void testRetrieveOrdersNoUser() {
        Utente u = new Utente();
        u.setUsername("tizio");

        assertTrue(ordineService.retrieveOrders(u).size() == 0);

    }

    @Test
    public void testRetrieveOrdersOK() {
        Utente u = new Utente();
        u.setUsername("acaro");

        assertTrue(ordineService.retrieveOrders(u).size() == 10);

    }

    @Test
    public void testRetrieveOrderFail() {
        assertThrows(RuntimeException.class, ()->ordineService.retrieveOrder(40));
    }

    @Test
    public void testRetrieveOrderOK() {
        Ordine ordine = ordineService.retrieveOrder(9);
        assertTrue(ordine.getNumero() == 9);
        assertTrue(ordine.getNumeroArticoli() == 1);
        assertTrue(ordine.getTotale() == 17.97);
    }

    @Test
    public void testSetProdottoValutatoFailNoProductInOrder() {
        assertThrows(RuntimeException.class, ()->ordineService.setProdottoValutato(9, 1));
    }

    @Test
    public void testSetProdottoValutatoFailNoOrder() {
        assertThrows(RuntimeException.class, ()->ordineService.setProdottoValutato(40, 1));
    }

    @Test
    public void testSetProdottoValutatoOK() {
        assertTrue(ordineService.setProdottoValutato(9, 14));

        Ordine o = ordineService.retrieveOrder(9);
        Set<Prodotto> prodotti = o.getProdotti().keySet();
        int valutato = 0;
        for(Prodotto p: prodotti) {
            if(p.getCodice() == 14) {
                valutato = p.getValutato();
            }
        }
        assertTrue(valutato == 1);
    }

    @Test
    public void testAllOrdersOK() {
        assertTrue(ordineService.allOrders().size() == 10);
    }

    @Test
    public void testUpdateStatoFailNoOrder() {
        assertTrue(ordineService.updateStato(40, "sbagliato!") == false);

    }

    @Test
    public void testUpdateStatoOK() {
        assertTrue(ordineService.updateStato(9, "Consegnato") == true);
        Ordine o = ordineService.retrieveOrder(9);
        assertTrue(o.getStato().equals("Consegnato"));

    }

    @Test
    public void testCounterOrdiniOK() {
        assertTrue(ordineService.counterOrdini() == 10);
    }






}
