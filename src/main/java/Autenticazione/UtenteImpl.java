package main.java.Autenticazione;

import java.time.LocalDate;
import main.java.Prenotazione.Prenotazione;

public class UtenteImpl implements UtenteService {

  @Override
  public Utente setUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita) {
    Utente utente = new Utente();
    utente.setUsername(username);
    utente.setEmail(email);
    utente.setPassword(password);
    utente.setNome(nome);
    utente.setCognome(cognome);
    utente.setSesso(sesso);
    utente.setDataDiNascita(dataDiNascita);
    utente.setAdmin(false);
    return utente;
  }
}
