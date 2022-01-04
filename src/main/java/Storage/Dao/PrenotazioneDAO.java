package main.java.Storage.Dao;

import main.java.Storage.Entity.Prenotazione;

import java.util.ArrayList;

public interface PrenotazioneDAO {
    boolean doSavePrenotazione(Prenotazione prenotazione);
    ArrayList<Prenotazione> doRetrievePrenotazione(int limit, int offset);
    Prenotazione doRetrievePrenotazioneByCodice(int numeroPrenotazione);
    boolean doUpdatePrenotazione(Prenotazione prenotazione);
    boolean doAcceptPrenotazione(int numeroPrenotazione);
    boolean doDeletePrenotazione(int numeroPrenotazione);
    int doRetrieveCounterPrenotazioni();
}
