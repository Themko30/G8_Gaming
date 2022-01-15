package ControllerExcluded;

import main.java.Catalogo.Service.ProdottoService;
import main.java.Catalogo.Service.ProdottoServiceImpl;
import main.java.Storage.Entity.Prodotto;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.assertTrue;


public class _3ProdottoServiceIntegration {
    ProdottoService prodottoService = new ProdottoServiceImpl();

    @Test
    public void _1testProdottiCategoriaNoCategoria() {
        assertTrue(prodottoService.prodottiCategoria("nonEsiste").size() == 0);
    }

    @Test
    public void _2testProdottiCategoriaOk() {
        assertTrue(prodottoService.prodottiCategoria("RPG").size() == 7);
    }

    @Test
    public void _3testProdottoCodiceNoCodice() {
        assertTrue(prodottoService.prodottoCodice(100) == null);
    }

    @Test
    public void _4testProdottoCodiceOK() {
        Prodotto p = prodottoService.prodottoCodice(1);
        assertTrue(p.getCodice() == 1);
        assertTrue(p.getCategoria().equals("RPG"));
        assertTrue(p.getNome().equals("Demon's Souls"));
        assertTrue(p.getPiattaforma().equals("PlayStation 5"));
        assertTrue(p.getPrezzo() == 79.99);
        assertTrue(p.getScontoAttivo() == 0);
        assertTrue(p.getQuantita() == 12);
    }

    @Test
    public void _5testQuantitaProdottoFailNoProduct() {
        Prodotto p = new Prodotto();
        p.setCodice(40);
        int quantita = prodottoService.quantitaProdotto(p);
        assertTrue(quantita == -1);
    }

    @Test
    public void _6testQuantitaProdottoOK() {
        Prodotto p = new Prodotto();
        p.setCodice(1);
        int quantita = prodottoService.quantitaProdotto(p);
        assertTrue(quantita == 12);
    }

    @Test
    public void _7testUpdateValutazioneFailOutOfBound() {
        Prodotto p = new Prodotto();
        p.setCodice(1);
        assertTrue(prodottoService.updateValutazione(p, -1) == false);
    }

    @Test
    public void _8testUpdateValutazioneFailNoProduct() {
        Prodotto p = new Prodotto();
        p.setCodice(40);
        assertTrue(prodottoService.updateValutazione(p, 5) == false);
    }

    @Test
    public void _9testUpdateValutazioneOk() {
        Prodotto p = new Prodotto();
        p.setCodice(31);
        assertTrue(prodottoService.updateValutazione(p, 5));
        p = prodottoService.prodottoCodice(31);
        assertTrue(p.getMediaArrotondata() == 5);
        assertTrue(p.getNumeroVoti() == 1);
        assertTrue(p.getTotaleVoti() == 5);
    }

    @Test
    public void _10testProdottiNomeLikeNoProduct() {
        assertTrue(prodottoService.prodottiNomeLike("Non esiste un prodotto", 5).size() == 0);
    }

    @Test
    public void _11testProdottiNomeLikeOk() {
        assertTrue(prodottoService.prodottiNomeLike("Demon's", 5).size() == 1);
    }

    @Test
    public void _12testAllProdotti(){
        int quantitaProdotto = prodottoService.allProdotti().size();
        assertTrue(quantitaProdotto == 35);
    }

    @Test
    public void _13testProdottiPiattaformaFailNoPiattaforma() {
        assertTrue(prodottoService.prodottiPiattaforma("Non esiste").size() == 0);
    }

    @Test
    public void _14testProdottiPiattaformaOk() {
        assertTrue(prodottoService.prodottiPiattaforma("PlayStation 5").size() == 7);
    }

    @Test
    public void _15testSaveProdotto() {
        Prodotto p = prodottoService.creaProdotto("Strategia", "Age of Empires II", "PC", 19.99, 0, 10, "Gioco bellissimo v2!", "age_of_empires_3.jpg");
        assertTrue(prodottoService.saveProdotto(p));

        assertTrue(prodottoService.prodottoCodice(36).getNome().equals("Age of Empires II"));
    }

    @Test
    public void _16testUpdateProdotto() {
        Prodotto p = prodottoService.prodottoCodice(36);
        p.setPrezzo(9.99);
        assertTrue(prodottoService.updateProdotto(p).equals("age_of_empires_3.jpg"));
        assertTrue(prodottoService.prodottoCodice(36).getPrezzo() == 9.99) ;
    }

    @Test
    public void _17testCounterProdotti() {
        int quantitaProdotti = prodottoService.counterProdotti();
        assertTrue(quantitaProdotti == 35);
    }

    @Test

    public void _18testMostVoted() {
        ArrayList<Prodotto> mostVoted = prodottoService.mostVoted();

        assertTrue(mostVoted.size() == 5);
        assertTrue(mostVoted.get(0).getCodice() == 31);
        assertTrue(mostVoted.get(1).getCodice() == 1);
        assertTrue(mostVoted.get(2).getCodice() == 10);
        assertTrue(mostVoted.get(3).getCodice() == 5);
        assertTrue(mostVoted.get(4).getCodice() == 3);
    }







}
