package main.java.Carrello.Service;

import java.util.ArrayList;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;

public interface OrdineService {

    /**
     * Metodo per creare un ordine.
     * @param carrello Il carrello dell'utente
     * @param indirizzo L'indirizzo di spedizione
     * @param cap Il CAP
     * @param paese Il paese
     * @param metodoPagamento Il metodo di pagamento
     * @return L'ordine creato
     */
    Ordine createOrdine(Carrello carrello,
                        String indirizzo, Integer cap,
                        String paese, String metodoPagamento);

    /**
     * Metodo per salvare un ordine nel database.
     * @param ordine L'ordine da salvare
     * @return True se il salvataggio ha successo, False altrimenti
     */
    boolean saveOrdine(Ordine ordine);

    /**
     * Metodo per recuperare tutti gli ordini di un utente.
     * @param utente L'utente dal quale recuperare gli ordini
     * @return Una lista di ordini
     */
    ArrayList<Ordine> retrieveOrders(Utente utente);

    /**
     * Metodo per recuperare un ordine dato il suo codice.
     * @param codiceOrdine Il codice univoco dell'ordine
     * @return L'ordine recuperato
     */
    Ordine retrieveOrder(int codiceOrdine);

    /**
     * Metodo per impostare un prodotto di un
     * determinato ordine come già valutato.
     * @param codiceOrdine Il codice dell'ordine
     * @param codiceProdotto Il codice del prodotto valutato
     * @return True se il salvataggio ha successo, False altrimenti
     */
    boolean setProdottoValutato(int codiceOrdine, int codiceProdotto);

    /**
     * Metodo per recuperare tutti gli ordini effettuati sul sito.
     * @return La lista degli ordini
     */
    ArrayList<Ordine> allOrders();

    /**
     * Metodo per aggiornare lo stato di un ordine.
     * @param numeroOrdine Il codice dell'ordine
     * @param stato Il nuovo stato dell'ordine
     * @return True se il salvataggio ha successo, False altrimenti
     */
    boolean updateStato(int numeroOrdine, String stato);

    /**
     * Metodo per ottenere il numero di tutti gli
     * ordini effettuati sul sito.
     * @return Il numero degli ordini
     */
    int counterOrdini();
}
