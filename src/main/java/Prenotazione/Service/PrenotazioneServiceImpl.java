package main.java.Prenotazione.Service;

import java.util.ArrayList;

import main.java.Storage.Dao.PrenotazioneDAO;
import main.java.Storage.Dao.PrenotazioneDAOImpl;
import main.java.Storage.Entity.Prenotazione;

public class PrenotazioneServiceImpl implements PrenotazioneService {

    private final PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();


    @Override
    public ArrayList<Prenotazione> allPrenotazioni() {
        return prenotazioneDAO.doRetrievePrenotazione(0, 100);
    }

    @Override
    public Prenotazione retrievePrenotazione(int codice) {
        return prenotazioneDAO.doRetrievePrenotazioneByCodice(codice);
    }

    @Override
    public boolean acceptPrenotazione(int numero) {
        return prenotazioneDAO.doAcceptPrenotazione(numero);
    }

    @Override
    public boolean savePrenotazione(Prenotazione prenotazione) {
        return prenotazioneDAO.doSavePrenotazione(prenotazione);
    }

    @Override
    public boolean updatePrenotazione(Prenotazione prenotazione) {
        return prenotazioneDAO.doUpdatePrenotazione(prenotazione);
    }

    @Override
    public boolean deletePrenotazione(int codice) {
        return prenotazioneDAO.doDeletePrenotazione(codice);
    }

    @Override
    public int counterPrenotazioni() {
        return prenotazioneDAO.doRetrieveCounterPrenotazioni();
    }
}
