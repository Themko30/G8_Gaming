package main.java.Catalogo.Service;

import java.util.ArrayList;

import main.java.Storage.Dao.ProdottoDAO;
import main.java.Storage.Dao.ProdottoDAOImpl;
import main.java.Storage.Entity.Prodotto;

public class ProdottoServiceImpl implements ProdottoService {

    /**
     * Il ProdottoDAO della classe.
     */
    private final ProdottoDAO prodottoDAO = new ProdottoDAOImpl();

    @Override
    public ArrayList<Prodotto> prodottiCategoria(String categoria) {
        return prodottoDAO.doRetrieveProdottiByCategoria(categoria, 0, 100);
    }

    @Override
    public Prodotto prodottoCodice(int codice) {
        return prodottoDAO.doRetrieveProdottoByCodice(codice);
    }

    @Override
    public int quantitaProdotto(Prodotto prodotto) {
        return prodottoDAO
                .doRetrieveQuantitaProdottoByCodice(prodotto.getCodice());
    }

    @Override
    public boolean updateValutazione(Prodotto prodotto, int valutazione) {
        return prodottoDAO
                .doUpdateMedia(prodotto, valutazione);
    }

    @Override
    public ArrayList<Prodotto> prodottiNomeLike(String nome, int limit) {
        return prodottoDAO
                .doRetrieveProdottiByNomeLike(nome, 0, limit);
    }

    @Override
    public ArrayList<Prodotto> allProdotti() {

        return prodottoDAO.doRetrieveProdotti(0, 100);
    }

    @Override
    public ArrayList<Prodotto> prodottiPiattaforma(String piattaforma) {
        return prodottoDAO
                .doRetrieveProdottiByPiattaforma(piattaforma, 0, 100);
    }

    @Override
    public boolean saveProdotto(Prodotto prodotto) {
        return prodottoDAO.doSaveProdotto(prodotto);
    }

    @Override
    public String updateProdotto(Prodotto prodotto) {
        return prodottoDAO.doUpdateProdotto(prodotto);
    }

    @Override
    public Prodotto creaProdotto(String categoria, String nome,
                                 String piattaforma, double prezzo,
                                 double scontoAttivo, int quantita,
                                 String descrizione, String copertina) {

        Prodotto prodotto = new Prodotto();
        prodotto.setCategoria(categoria);
        prodotto.setNome(nome);
        prodotto.setPiattaforma(piattaforma);
        prodotto.setPrezzo(prezzo);
        prodotto.setScontoAttivo(scontoAttivo);
        prodotto.setQuantita(quantita);
        prodotto.setDescrizione(descrizione);
        prodotto.setCopertina(copertina);

        return prodotto;
    }

    @Override
    public int counterProdotti() {
        return prodottoDAO.doRetrieveCounterProdotti();
    }

    @Override
    public ArrayList<Prodotto> mostVoted() {
        return prodottoDAO.doRetrieveMostVoted();
    }
}
