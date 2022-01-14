package main.java.Catalogo.Service;

import java.util.ArrayList;
import main.java.Storage.Entity.Prodotto;

public interface ProdottoService {

    /**
     * Metodo per creare un prodotto.
     * @param categoria La categoria di appartenenza del videogioco
     * @param nome Il nome del videogioco
     * @param piattaforma La piattaforma del videogioco
     * @param prezzo Il prezzo del videogioco
     * @param scontoAttivo Lo sconto (0 - 0.99) del videogioco
     * @param quantita La quantità presente in magazzino
     * @param descrizione La descrizione del videogioco
     * @param copertina L'immagine del videogioco
     * @return Il prodotto creato
     */
    Prodotto creaProdotto(String categoria, String nome,
                          String piattaforma, double prezzo,
                          double scontoAttivo, int quantita,
                          String descrizione, String copertina);

    /**
     * Metodo per ottenere tutti i prodotti di una categoria.
     * @param categoria La categoria di cui ottenere i prodotti
     * @return La lista dei prodotti
     */
    ArrayList<Prodotto> prodottiCategoria(String categoria);

    /**
     * Metodo per ottenere un prodotto dato il suo codice.
     * @param codice Il codice univoco del prodotto
     * @return Il prodotto recuperato
     */
    Prodotto prodottoCodice(int codice);

    /**
     * Metodo per ottenere la quantità di un prodotto.
     * @param prodotto Il prodotto da cui recuperare la quantità
     * @return La quantità del prodotto
     */
    int quantitaProdotto(Prodotto prodotto);

    /**
     *
     * @param prodotto Il prodotto da valutare
     * @param valutazione La valutazione dell'utente
     * @return True se il salvataggio ha successo, False altrimenti
     */
    boolean updateValutazione(Prodotto prodotto, int valutazione);

    /**
     * Metodo utilizzato durante la ricerca da parte
     * degli utenti per ottenere i prodotti con un
     * nome simile a quello indicato come input.
     * @param nome Il nome cercato
     * @param limit La quantità massima di prodotti da recuperare
     * @return La lista dei prodotti
     */
    ArrayList<Prodotto> prodottiNomeLike(String nome, int limit);

    /**
     * Metodo per recuperare tutti i prodotti presenti nel database.
     * @return La lista dei prodotti
     */
    ArrayList<Prodotto> allProdotti();

    /**
     * Metodo per ottenere tutti i prodotti di una piattaforma.
     * @param piattaforma La piattaforma da cui ottenere i prodotti
     * @return La lista dei prodotti
     */
    ArrayList<Prodotto> prodottiPiattaforma(String piattaforma);

    /**
     * Metodo per salvare un prodotto nel database.
     * @param prodotto Il prodotto da salvare
     * @return True se il salvataggio ha successo, False altrimenti
     */
    boolean saveProdotto(Prodotto prodotto);

    /**
     * Metodo per aggiornare un prodotto nel database.
     * @param prodotto Il prodotto da aggiornare
     * @return True se l'aggiornamento ha successo, False altrimenti
     */
    String updateProdotto(Prodotto prodotto);

    /**
     * Metodo per ottenere il numero di tutti i
     * prodotti presenti nel database.
     * @return Il numero dei prodotti
     */
    int counterProdotti();

    /**
     * Metodo per ottenere i prodotti con la valutazione più alta.
     * @return La lista dei prodotti
     */
    ArrayList<Prodotto> mostVoted();
}
