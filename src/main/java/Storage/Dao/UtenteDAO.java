package main.java.Storage.Dao;

import main.java.Storage.Entity.Utente;

import java.util.ArrayList;

public interface UtenteDAO {
    int doRetrieveCounterUtenti();
    boolean doSaveUtente(Utente utente) throws Exception;
    ArrayList<Utente> doRetrieveAllUtente(int limit, int offset);
    Utente doRetrieveUtenteByUsernameAndPassword(
            String username, String password);
    boolean doCheckUsername(String username);
    boolean doUpdateUtente(Utente utente);
    boolean doDeleteUtente(String username);
    boolean doSetAdmin(String username);
    Utente doRetrieveUtenteByUsername(String username);
}
