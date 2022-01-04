package main.java.Storage.Dao;

import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Utente;

public interface CarrelloDAO {
    boolean doUpdateCarrello(Carrello carrello);
    boolean doCreateCarrello(Utente u);
    boolean doClearCarrello(Carrello carrello);
    Carrello doRetrieveCarrelloByUtente(Utente u);
}
