package main.java.Prenotazione.Service;

import java.util.ArrayList;
import main.java.Storage.Entity.Prenotazione;

public interface PrenotazioneService {

  /**
   * Metodo per recuperare tutte le prenotazioni.
   *
   * @return una lista di prenotazioni
   */
  ArrayList<Prenotazione> allPrenotazioni();

  /**
   * Metodo per recuperare una prenotazione dal DB.
   *
   * @param codice
   * @return la prenotazione trovata
   */
  Prenotazione retrievePrenotazione(int codice);

  /**
   * Metodo per accettare una richiesta di prenotazione.
   *
   * @param numero
   * @return true se il metodo è andato a buon fine
   */
  boolean acceptPrenotazione(int numero);

  /**
   * Metodo per salvare una richiesta di prenotazione nel DB.
   *
   * @param prenotazione
   * @return true se il metodo è andato a buon fine
   */
  boolean savePrenotazione(Prenotazione prenotazione);

  /**
   * Metodo per contare le richieste di prenotazione nel DB.
   *
   * @return il numero di prenotazione nel DB.
   */
  int counterPrenotazioni();
}
