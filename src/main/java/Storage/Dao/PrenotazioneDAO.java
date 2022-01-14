package main.java.Storage.Dao;

import main.java.Storage.Entity.Prenotazione;

import java.util.ArrayList;

public interface PrenotazioneDAO {

    /**
     * Metodo per salvare una prenotazione nel DB.
     * @param prenotazione il bean della prenotazione riempito.
     * @return un booleano per controllare la riuscita.
     */
    boolean doSavePrenotazione(Prenotazione prenotazione);

    /**
     * Metodo per recuperare una prenotazione dal DB.
     * @param limit il limite finale della lista del DB.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @return ritorna la lista di tutte le prenotazioni dal DB.
     */
    ArrayList<Prenotazione> doRetrievePrenotazione(int limit, int offset);

    /**
     * Metodo che recupera una prenotazione dal DB.
     * @param numeroPrenotazione il numero della prenotazione.
     * @return il bean della prenotazione riempito.
     */
    Prenotazione doRetrievePrenotazioneByCodice(int numeroPrenotazione);

    /**
     * Metodo per accettare una prenotazione
     * da parte di un admin.
     * @param numeroPrenotazione il numero della prenotazione.
     * @return un booleano per controllare la riuscita.
     */
    boolean doAcceptPrenotazione(int numeroPrenotazione);

    /**
     * Metodo per contare tutte le prenotazioni nel DB.
     * @return il numero di prenotazioni nel DB.
     */
    int doRetrieveCounterPrenotazioni();
}
