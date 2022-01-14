package main.java.Storage.Dao;

import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;

import java.util.ArrayList;

public interface OrdineDAO {

    /**
     * Metodo che recupera tutti gli ordini di utente del DB.
     * @param u il bean dell`utente riempito.
     * @return la lista di ordini dell`utente.
     */
    ArrayList<Ordine> doRetrieveOrdiniByUtente(Utente u);

    /**
     * Metodo che recupera i prodotti acquistati
     * di uno specifico ordine.
     * @param codiceOrdine il codice dell`ordine
     * @return il bean dell`ordine riempito.
     */
    Ordine doRetrieveProdottiAcquistati(int codiceOrdine);

    /**
     * Metodo che recupera tutti gli ordini dal DB.
     * @param offset la compensazione d`inizio della lista dal DB
     * @param limit il limite finale della lista del DB.
     * @return la lista di ordini dal DB
     */
    ArrayList<Ordine> doRetrieveAllOrdini(int offset, int limit);

    /**
     * Metodo che aggiorna lo stato di un ordine.
     * @param numero il numero dell`ordine.
     * @param stato lo stato dell`ordine.
     * @return un booleano per controllare la riuscita.
     */
    boolean doUpdateStatoOrdine(int numero, String stato);

    /**
     * Metodo per salvare l`ordine nel DB.
     * @param o il bean dell`ordine riempito.
     * @return un booleano per controllare la riuscita.
     */
    boolean doSaveOrdine(Ordine o);

    /**
     * Metodo per settare la valutazione di un prodotto.
     * @param codiceOrdine il codice dell`ordine.
     * @param codiceProdotto il codice del prodotto.
     * @return un booleano per controllare la riuscita.
     */
    boolean doSetProdottoValutato(int codiceOrdine, int codiceProdotto);

    /**
     * Metodo per recuperare il numero di ordini dal DB.
     * @return il numero di ordini totali.
     */
    int doRetrieveCounterOrdini();
}
