package main.java.Autenticazione.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import main.java.Storage.Entity.Utente;

public interface UtenteService {

  /**
   * Metodo per creare il bean dell`utente.
   * @param username username utente.
   * @param email email utente.
   * @param password password utente.
   * @param nome nome utente.
   * @param cognome cognome utente.
   * @param sesso sesso utente.
   * @param dataDiNascita data di nascita utente.
   * @param indirizzo indirizzo utente.
   * @param cap codice avviamento postale utente.
   * @param paese paese utente.
   * @return ritorna il bean dell`utente riempito.
   */
  Utente createUtente(String username, String email,
                      String password, String nome,
                      String cognome, String sesso,
                      LocalDate dataDiNascita, String indirizzo,
                      int cap, String paese);

  /**
   * Metodo per salvare utente nel DB.
   * @param utente l`intero utente riempito.
   * @return un booleano per controllare la riuscita.
   * @throws Exception lancia le varie eccezioni
   * che il metodo potrebbe sollevare.
   */
  boolean saveUtente(Utente utente) throws Exception;

  /**
   * Metodo per aggiornare utente nel DB.
   * @param utente l`intero utente riempito.
   * @return un booleano per controllare la riuscita.
   */
  boolean updateUtente(Utente utente);

  /**
   * Metodo per controllare la presenza di utente nel DB.
   * @param username username utente.
   * @return un booleano per controllare la riuscita.
   */
  boolean checkUtente(String username);

  /**
   * Metodo per settare utente ad admin.
   * @param username username utente.
   * @return un booleano per controllare la riuscita.
   */
  boolean setAdmin(String username);

  /**
   * Metodo per recuperare tutti gli utenti nel DB.
   * @return la lista di utenti recuperati.
   */
  ArrayList<Utente> allUtenti();

  /**
   * Metodo che conta tutti gli utenti nel DB.
   * @return un intero corrispondente al numero di utenti.
   */
  int counterUtente();

  /**
   * Metodo per consentire all`utente di loggare
   * usando solo username e password.
   * @param username username utente.
   * @param password password utente.
   * @return l`utente recuperato dal DB.
   */
  Utente login(String username, String password);

}
