package main.java.Autenticazione.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import main.java.Storage.Entity.Utente;

public interface UtenteService {

  Utente createUtente(String username, String email, String password, String nome, String cognome, String sesso, LocalDate dataDiNascita);

  boolean saveUtente(Utente utente);

  boolean updateUtente(Utente utente);

  boolean deleteUtente(String username);

  boolean checkUtente(String username);

  boolean setAdmin(String username);

  ArrayList<Utente> allUtenti();

  int counterUtente();

  Utente login(String username, String password);

}
