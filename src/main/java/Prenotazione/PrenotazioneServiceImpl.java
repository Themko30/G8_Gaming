package main.java.Prenotazione;

import java.util.ArrayList;

public class PrenotazioneServiceImpl implements PrenotazioneService{
    private final PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO();


    @Override
    public ArrayList<Prenotazione> allPrenotazioni() {
        return prenotazioneDAO.doRetrievePrenotazione(0, 100);
    }

    @Override
    public Prenotazione retrievePrenotazione(int codice) {
        return prenotazioneDAO.doRetrievePrenotazioneByCodice(codice);
    }

    @Override
    public void acceptPrenotazione(int numero) {
        prenotazioneDAO.doAcceptPrenotazione(numero);
    }




}
