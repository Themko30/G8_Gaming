package main.java.Prenotazione;

import java.util.ArrayList;

public interface PrenotazioneService {
    ArrayList<Prenotazione> allPrenotazioni();
    Prenotazione retrievePrenotazione(int codice);
    void acceptPrenotazione(int numero);
}
