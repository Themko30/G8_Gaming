package Integration.ControllerExcluded;

import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class CarrelloServiceIntegration {
    CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
    CarrelloDAOImpl carrelloDAO = new CarrelloDAOImpl();
    @Test
    public void testClearCarrello(){
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
        assertTrue(afterTestDB.getNumeroArticoli()==0);
        assertTrue(afterTestDB.getTotale()==0.0);
    }
}
