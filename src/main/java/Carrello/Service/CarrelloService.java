package main.java.Carrello.Service;

import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;

public interface CarrelloService {

    /**
     * Metodo per aggiungere un prodotto al carrello.
     * @param carrello L'oggetto carrello dell'utente
     * @param codiceProdotto Il codice del prodotto da aggiungere
     * @param quantita La quantità del prodotto da aggiungere
     * @return Il carrello aggiornato
     */
    Carrello aggiungiProdotto(Carrello carrello,
                              int codiceProdotto, int quantita);

    /**
     * Metodo per aggiornale la quantità di un
     * determinato prodotto nel carrello.
     * @param carrello L'oggetto carrello dell'utente
     * @param codiceProdotto Il codice del prodotto da aggiornare
     * @param quantita La nuova quantità del prodotto
     * @return Il carrello aggiornato
     */
    Carrello updateQuantitaCarrelloSession(Carrello carrello,
                                           int codiceProdotto, int quantita);

    /**
     * Metodo per rimuovere un prodotto al carrello.
     * @param carrello L'oggetto carrello dell'utente
     * @param codiceProdotto Il codice del prodotto da rimuovere
     * @return Il carrello aggiornato
     */
    Carrello rimuoviProdottoCarrelloSession(Carrello carrello,
                                            int codiceProdotto);

    /**
     * Metodo per svuotare un carrello.
     * @param carrello L'oggetto carrello dell'utente
     * @return Il carrello vuoto
     */
    Carrello clearCarrello(Carrello carrello);

    /**
     * Metodo per salvare nel database un carrello.
     * @param carrello Il carrello da salvare
     * @return True se il salvataggio ha successo, altrimenti False
     */
    boolean updateCarrello(Carrello carrello);

    /**
     * Metodo per creare il carrello di un nuovo utente nel database.
     * @param utente L'oggetto utente di cui creare il carrello
     * @return True se la creazione ha successo, False altrimenti
     */
    boolean creaCarrello(Utente utente);

    /**
     * Metodo per recuperare dal database il carrello di un determinato utente.
     * @param utente L'utente dal quale recuperare il carrello
     * @return Il carrello dell'utente
     */
    Carrello recuperaCarrello(Utente utente);
}
