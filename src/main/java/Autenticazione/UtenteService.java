package main.java.Autenticazione;

import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import main.java.Autenticazione.Utente;
import main.java.Prenotazione.Prenotazione;

public interface UtenteService {

  Utente createUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita);
  boolean saveUtente(Utente utente);
  boolean updateUtente(Utente utente);
  boolean deleteUtente(String username);
  boolean checkUtente(String username);
  boolean setAdmin(String username);
  ArrayList<Utente> allUtenti();

}
