package main.java.Storage.Dao;

import main.java.Storage.Entity.Prodotto;

import java.util.ArrayList;

public interface ProdottoDAO {

    /**
     * Metodo per aggiornare la scheda di un prodotto nel DB.
     * @param prodotto il bean del prodotto riempito.
     * @return la stringa della vecchia copertina
     * per assicurarne l`eliminazione.
     */
    String doUpdateProdotto(Prodotto prodotto);

    /**
     * Metodo che aggiorna la quantità di un prodotto nel DB.
     * @param prodotto il bean del prodotto da aggiornare.
     * @param quantita la quantità nuova.
     * @return un booleano per controllare la riuscita.
     */
    boolean doUpdateQuantita(Prodotto prodotto, int quantita);

    /**
     * Metodo per aggiornare la media di un prodotto.
     * @param prodotto il bean del prodotto.
     * @param valutazione il valore della valutazione nuova.
     * @return un booleano per controllare la riuscita.
     */
    boolean doUpdateMedia(Prodotto prodotto, int valutazione);

    /**
     * Metodo per salvare un prodotto nel DB.
     * @param prodotto il bean del prodotto riempito.
     * @return un booleano per controllare la riuscita.
     */
    boolean doSaveProdotto(Prodotto prodotto);

    /**
     * Metodo per recuperare tutti i prodotti dal DB.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di tutte i prodotti.
     */
    ArrayList<Prodotto> doRetrieveProdotti(int offset, int limit);

  /**
   * Metodo utilizzato durante la ricerca da parte
   * degli utenti per ottenere i prodotti con un
   * nome simile a quello indicato come input.
   * @param nome Il nome del prodotto da cercare.
   * @param offset la compensazione d`inizio della lista dal DB.
   * @param limit il limite finale della lista del DB.
   * @return la lista di tutti i prodotti recuperati.
   */
  ArrayList<Prodotto> doRetrieveProdottiByNomeLike(
    String nome, int offset, int limit);

    /**
     * Metodo che recupera un prodotto dal suo codice.
     * @param codice il codice del prodotto.
     * @return il bean del prodotto riempito.
     */
    Prodotto doRetrieveProdottoByCodice(int codice);

    /**
     * Metodo per recuperare tutti i prodotti di una certa categoria.
     * @param categoria il nome della categoria.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di prodotti recuperati.
     */
    ArrayList<Prodotto> doRetrieveProdottiByCategoria(
            String categoria, int offset, int limit);

    /**
     * Metodo per recuperare tutti i prodotti di una certa piattaforma.
     * @param piattaforma il nome della piattaforma.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di prodotti recuperati.
     */
    ArrayList<Prodotto> doRetrieveProdottiByPiattaforma(
            String piattaforma, int offset, int limit);

    /**
     * Metodo per recuperare la quantità di un prodotto
     * utilizzando il suo codice.
     * @param codice il codice del prodotto.
     * @return la quantità recuperata dal DB.
     */
    int doRetrieveQuantitaProdottoByCodice(int codice);

    /**
     * Metodo per recuperare il numero di tutti i prodotti dal DB.
     * @return il numero di prodotti recuperati.
     */
    int doRetrieveCounterProdotti();

    /**
     * Metodo che recupera i prodotti maggiormente votati.
     * @return la lista di prodotti recuperati.
     */
    ArrayList<Prodotto> doRetrieveMostVoted();
}
