package main.java.Autenticazione;

import java.time.LocalDate;
import java.util.ArrayList;

import main.java.Prenotazione.Prenotazione;

public class UtenteServiceImpl implements UtenteService {

  private final UtenteDAO utenteDao = new UtenteDAO();

  @Override
  public Utente createUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita) {
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

  @Override
  public boolean saveUtente(Utente utente) {
   return utenteDao.doSaveUtente(utente);
  }

  @Override
  public boolean updateUtente(Utente utente) {
    return utenteDao.doUpdateUtente(utente);
  }

  @Override
  public boolean deleteUtente(String username) {
    return utenteDao.doDeleteUtente(username);
  }

  @Override
  public boolean checkUtente(String username) {
    return utenteDao.doCheckUsername(username);
  }

  @Override
  public boolean setAdmin(String username) {
    return utenteDao.doSetAdmin(username);
  }

  @Override
  public ArrayList<Utente> allUtenti() {
    return utenteDao.doRetrieveAllUtente(0, 100);
  }


}
