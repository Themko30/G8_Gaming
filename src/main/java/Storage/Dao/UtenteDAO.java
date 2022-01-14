package main.java.Storage.Dao;

import main.java.Storage.Entity.Utente;

import java.util.ArrayList;

public interface UtenteDAO {

  /**
   * Metodo per recuperare il numero
   * di tutti gli utenti nel DB.
   * @return il numero * di tutti gli utenti nel DB.
   */
  int doRetrieveCounterUtenti();

    /**
     * Metodo per salvare un utente nel database.
     * @param utente il bean dell`utente da inserire.
     * @return un booleano per controllare la riuscita.
     * @throws Exception una eccezione generale
     * che potrebbe lanciare durante l`esecuzione sul DB.
     */
    boolean doSaveUtente(Utente utente) throws Exception;

    /**
     * Metodo per recuperare tutti gli utenti dal DB.
     * @param limit il limite finale della lista del DB.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @return la lista di tutti gli utenti nel DB.
     */
    ArrayList<Utente> doRetrieveAllUtente(int limit, int offset);

    /**
     * Metodo per trovare un utente nel DB con solo username e password.
     * @param username username dell`utente.
     * @param password password dell`utente
     * @return il bean dell`utente riempito.
     */
    Utente doRetrieveUtenteByUsernameAndPassword(
            String username, String password);

    /**
     * Metodo che controlla se un utente esiste
     * utilizzando il suo username.
     * @param username username dell`utente.
     * @return un booleano per controllare la riuscita.
     */
    boolean doCheckUsername(String username);

    /**
     * Metodo che aggiorna un utente nel DB.
     * @param utente il bean dell`utente da aggiornare.
     * @return un booleano per controllare la riuscita.
     */
    boolean doUpdateUtente(Utente utente);

    /**
     * metodo per settare ad admin un utente.
     * @param username username dell`utente
     * @return un booleano per controllare la riuscita.
     */
    boolean doSetAdmin(String username);

    /**
     * Metodo di servizio e testing che recupera
     * un utente dal DB con solo il suo username.
     * @param username username dell`utente
     * @return il bean dell`utente riempito.
     */
    Utente doRetrieveUtenteByUsername(String username);
}
