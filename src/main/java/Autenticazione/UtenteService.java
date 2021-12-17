package main.java.Autenticazione;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import main.java.Autenticazione.Utente;
import main.java.Prenotazione.Prenotazione;

public interface UtenteService {

  public Utente setUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita);
}
