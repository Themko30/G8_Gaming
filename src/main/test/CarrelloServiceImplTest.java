
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Dao.CarrelloDAOImpl;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarrelloServiceImplTest {
    CarrelloDAOImpl carrelloDAO = Mockito.mock(CarrelloDAOImpl.class);
    Prodotto prodotto = Mockito.mock(Prodotto.class);
    Carrello carrello = Mockito.mock(Carrello.class);

    @Test
    public void testUpdateQuantitaCarrelloSession(){
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        when(prodotto.getCodice()).thenReturn(1);
        when(prodotto.getPrezzo()).thenReturn(100.0);
        when(prodotto.getScontoAttivo()).thenReturn(0.25);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "prodotti", prodotti);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getProdotti()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setProdotti(any(LinkedHashMap.class));


        Carrello carrelloVero = carrelloService.updateQuantitaCarrelloSession(carrello, 1, 2);
        assertTrue(carrelloVero.getNumeroArticoli()==2);

        assertTrue(carrelloVero.getTotale()==150.0);
        assertTrue(carrelloVero.getProdotti().get(prodotto)==2);

    }

    @Test
    public void testUpdateQuantitaCarrelloSessionFalse(){
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        when(prodotto.getCodice()).thenReturn(1);
        when(prodotto.getPrezzo()).thenReturn(100.0);
        when(prodotto.getScontoAttivo()).thenReturn(0.25);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "prodotti", prodotti);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getProdotti()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setProdotti(any(LinkedHashMap.class));


        Carrello carrelloVero = carrelloService.updateQuantitaCarrelloSession(carrello, 2, 2);
        assertTrue(carrelloVero.getNumeroArticoli()==1);

        assertTrue(carrelloVero.getTotale()==75.0);
        assertTrue(carrelloVero.getProdotti().get(prodotto)==1);

    }

    @Test
    public void testRimuoviProdottoCarrelloSession(){
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        when(prodotto.getCodice()).thenReturn(1);
        when(prodotto.getPrezzo()).thenReturn(100.0);
        when(prodotto.getScontoAttivo()).thenReturn(0.25);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "prodotti", prodotti);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getProdotti()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setProdotti(any(LinkedHashMap.class));

        Carrello carrelloVero = carrelloService.rimuoviProdottoCarrelloSession(carrello, 1);

        assertTrue(carrelloVero.getNumeroArticoli()==0);
        assertTrue(carrelloVero.getTotale()==0.0);
        assertTrue(carrelloVero.getProdotti().get(prodotto)==null);

    }

    @Test
    public void testClearCarrello(){
        Utente utente = Mockito.mock(Utente.class);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "utente", utente);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getUtente()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setUtente(any(Utente.class));
        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        carrello.setProdotti(prodotti);
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();
        carrelloService.setCarrelloDAO(carrelloDAO);

        Carrello carrelloVero = carrelloService.clearCarrello(carrello);

        assertTrue(carrelloVero.getUtente()==utente);
        assertTrue(carrelloVero.getProdotti().size()==0);
        assertTrue(carrelloVero.getTotale()==0);
        assertTrue(carrelloVero.getNumeroArticoli()==0);

    }

    @Test
    public void testAggiungiProdottoNuovo(){
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();

        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        when(prodotto.getCodice()).thenReturn(1);
        when(prodotto.getPrezzo()).thenReturn(100.0);
        when(prodotto.getScontoAttivo()).thenReturn(0.25);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "prodotti", prodotti);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getProdotti()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setProdotti(any(LinkedHashMap.class));

        ProdottoServiceImpl prodottoService = Mockito.mock(ProdottoServiceImpl.class);
        carrelloService.setProdottoService(prodottoService);
        Prodotto prodotto2 = Mockito.mock(Prodotto.class);
        when(prodotto2.getCodice()).thenReturn(2);
        when(prodotto2.getPrezzo()).thenReturn(100.0);
        when(prodotto2.getScontoAttivo()).thenReturn(0.25);
        when(prodottoService.prodottoCodice(2)).thenReturn(prodotto2);

        Carrello carrelloVero = carrelloService.aggiungiProdotto(carrello, 2, 1);

        assertTrue(carrelloVero.getNumeroArticoli()==2);
        assertTrue(carrelloVero.getTotale()==150.0);
        assertTrue(carrelloVero.getProdotti().get(prodotto2)==1);

    }

    @Test
    public void testAggiungiProdottoEsistente(){
        CarrelloServiceImpl carrelloService = new CarrelloServiceImpl();

        LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
        prodotti.put(prodotto, 1);
        when(prodotto.getCodice()).thenReturn(1);
        when(prodotto.getPrezzo()).thenReturn(100.0);
        when(prodotto.getScontoAttivo()).thenReturn(0.25);
        Whitebox.setInternalState(carrello, "numeroArticoli", 1);
        Whitebox.setInternalState(carrello, "totale", 75.0);
        Whitebox.setInternalState(carrello, "prodotti", prodotti);
        when(carrello.getNumeroArticoli()).thenCallRealMethod();
        when(carrello.getTotale()).thenCallRealMethod();
        when(carrello.getProdotti()).thenCallRealMethod();
        doCallRealMethod().when(carrello).setNumeroArticoli(any(Integer.class));
        doCallRealMethod().when(carrello).setTotale(any(Double.class));
        doCallRealMethod().when(carrello).setProdotti(any(LinkedHashMap.class));

        ProdottoServiceImpl prodottoService = Mockito.mock(ProdottoServiceImpl.class);
        carrelloService.setProdottoService(prodottoService);
        when(prodottoService.prodottoCodice(1)).thenReturn(prodotto);

        Carrello carrelloVero = carrelloService.aggiungiProdotto(carrello, 1, 1);

        assertTrue(carrelloVero.getNumeroArticoli()==2);
        assertTrue(carrelloVero.getTotale()==150.0);
        assertTrue(carrelloVero.getProdotti().get(prodotto)==2);

    }

}
