package main.java.Storage.Dao;

import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;

public interface CarrelloDAO {

    /**
     * Metodo per aggiornare un carrello nel DB.
     * @param carrello il bean del carrello.
     * @return un booleano per controllare la riuscita.
     */
    boolean doUpdateCarrello(Carrello carrello);

    /**
     * Metodo per creare un carrello di un utente nel DB.
     * @param u il bean dell`utente riempito.
     * @return un booleano per controllare la riuscita.
     */
    boolean doCreateCarrello(Utente u);

    /**
     * Metodo per svuotare il carrello di un utente.
     * @param carrello il bean del carrello riempito.
     * @return un booleano per controllare la riuscita.
     */
    boolean doClearCarrello(Carrello carrello);

    /**
     * Metodo per recuperare il carrello dell`utente dal DB.
     * @param u il bean dell`utente riempito.
     * @return il bean del carrello riempito.
     */
    Carrello doRetrieveCarrelloByUtente(Utente u);
}
