package main.java.Storage.Dao;

import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Utente;

import java.util.ArrayList;

public interface OrdineDAO {
    ArrayList<Ordine> doRetrieveOrdiniByUtente(Utente u);
    Ordine doRetrieveProdottiAcquistati(int codiceOrdine);
    ArrayList<Ordine> doRetrieveAllOrdini(int offset, int limit);
    boolean doUpdateStatoOrdine(int numero, String stato);
    boolean doSaveOrdine(Ordine o);
    boolean doSetProdottoValutato(int codiceOrdine, int codiceProdotto);
    int doRetrieveCounterOrdini();
}
