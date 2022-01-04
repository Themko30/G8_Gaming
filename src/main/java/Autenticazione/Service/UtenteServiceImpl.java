package main.java.Autenticazione.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import main.java.Carrello.Service.CarrelloService;
import main.java.Carrello.Service.CarrelloServiceImpl;
import main.java.Storage.Dao.UtenteDAOImpl;
import main.java.Storage.Dao.UtenteDAO;
import main.java.Storage.Entity.Utente;

public class UtenteServiceImpl implements UtenteService {

  private final UtenteDAO utenteDao = new UtenteDAOImpl();
  private final CarrelloService carrelloService = new CarrelloServiceImpl();

  @Override
  public Utente createUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita, String indirizzo, int cap, String paese) {
    Utente utente = new Utente();
    utente.setUsername(username);
    utente.setEmail(email);
    utente.setPassword(password);
    utente.setNome(nome);
    utente.setCognome(cognome);
    utente.setSesso(sesso);
    utente.setDataDiNascita(dataDiNascita);
    utente.setAdmin(false);
    utente.setIndirizzo(indirizzo);
    utente.setCap(cap);
    utente.setPaese(paese);
    return utente;
  }

  @Override
  public boolean saveUtente(Utente utente) throws Exception {
    boolean result = utenteDao.doSaveUtente(utente);
    carrelloService.creaCarrello(utente);
    return result;
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

  @Override
  public int counterUtente() {
    return utenteDao.doRetrieveCounterUtenti();
  }

  @Override
  public Utente login(String username, String password) {
    return utenteDao.doRetrieveUtenteByUsernameAndPassword(username, password);
  }


}
