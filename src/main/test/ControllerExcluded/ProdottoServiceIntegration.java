package ControllerExcluded;

import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class ProdottoServiceIntegration {
    ProdottoService prodottoService = new ProdottoServiceImpl();

    @Test
    public void testProdottiCategoriaNoCategoria() {
        assertTrue(prodottoService.prodottiCategoria("nonEsiste").size() == 0);
    }

    @Test
    public void testProdottiCategoriaOk() {
        assertTrue(prodottoService.prodottiCategoria("RPG").size() == 7);
    }

    @Test
    public void testProdottoCodiceNoCodice() {
        assertTrue(prodottoService.prodottoCodice(100) == null);
    }

    @Test
    public void testProdottoCodiceOK() {
        Prodotto p = prodottoService.prodottoCodice(1);
        assertTrue(p.getCodice() == 1);
        assertTrue(p.getCategoria().equals("RPG"));
        assertTrue(p.getNome().equals("Demon's Souls"));
        assertTrue(p.getPiattaforma().equals("PlayStation 5"));
        assertTrue(p.getPrezzo() == 79.99);
        assertTrue(p.getScontoAttivo() == 0);
        assertTrue(p.getQuantita() == 10);
    }

    @Test
    public void testQuantitaProdottoFailNoProduct() {
        Prodotto p = new Prodotto();
        p.setCodice(40);
        int quantita = prodottoService.quantitaProdotto(p);
        assertTrue(quantita == -1);
    }

    @Test
    public void testQuantitaProdottoOK() {
        Prodotto p = new Prodotto();
        p.setCodice(1);
        int quantita = prodottoService.quantitaProdotto(p);
        assertTrue(quantita == 10);
    }

    @Test
    public void testUpdateValutazioneFailOutOfBound() {
        Prodotto p = new Prodotto();
        p.setCodice(1);
        assertTrue(prodottoService.updateValutazione(p, -1) == false);
    }

    @Test
    public void testUpdateValutazioneFailNoProduct() {
        Prodotto p = new Prodotto();
        p.setCodice(40);
        assertTrue(prodottoService.updateValutazione(p, 5) == false);
    }

    @Test
    public void testUpdateValutazioneOk() {
        Prodotto p = new Prodotto();
        p.setCodice(31);
        assertTrue(prodottoService.updateValutazione(p, 5));
        p = prodottoService.prodottoCodice(31);
        assertTrue(p.getMediaArrotondata() == 5);
        assertTrue(p.getNumeroVoti() == 1);
        assertTrue(p.getTotaleVoti() == 5);
    }

    @Test
    public void testProdottiNomeLikeNoProduct() {
        assertTrue(prodottoService.prodottiNomeLike("Non esiste un prodotto", 5).size() == 0);
    }

    @Test
    public void testProdottiNomeLikeOk() {
        assertTrue(prodottoService.prodottiNomeLike("Demon's", 5).size() == 1);
    }

    @Test
    public void testAllProdotti(){
        assertTrue(prodottoService.allProdotti().size() == 34);
    }

    @Test
    public void testProdottiPiattaformaFailNoPiattaforma() {
        assertTrue(prodottoService.prodottiPiattaforma("Non esiste").size() == 0);
    }

    @Test
    public void testProdottiPiattaformaOk() {
        assertTrue(prodottoService.prodottiPiattaforma("PlayStation 5").size() == 7);
    }

    @Test
    public void testSaveProdotto() {
        Prodotto p = prodottoService.creaProdotto("Strategia", "Age of Empires II", "PC", 19.99, 0, 10, "Gioco bellissimo v2!", "age_of_empires_3.jpg");
        assertTrue(prodottoService.saveProdotto(p));

        assertTrue(prodottoService.prodottoCodice(37).getNome().equals("Age of Empires II"));
    }

    @Test
    public void testUpdateProdotto() {
        Prodotto p = prodottoService.prodottoCodice(37);
        p.setPrezzo(9.99);
        assertTrue(prodottoService.updateProdotto(p).equals("age_of_empires_3.jpg"));
        assertTrue(prodottoService.prodottoCodice(37).getPrezzo() == 9.99) ;
    }

    @Test
    public void testCounterProdotti() {
        assertTrue(prodottoService.counterProdotti() == 36);
    }

    @Test
    public void testMostVoted() {
        ArrayList<Prodotto> mostVoted = prodottoService.mostVoted();

        assertTrue(mostVoted.size() == 5);
        assertTrue(mostVoted.get(0).getCodice() == 31);
        assertTrue(mostVoted.get(1).getCodice() == 1);
        assertTrue(mostVoted.get(2).getCodice() == 10);
        assertTrue(mostVoted.get(3).getCodice() == 5);
        assertTrue(mostVoted.get(4).getCodice() == 3);
    }







}
