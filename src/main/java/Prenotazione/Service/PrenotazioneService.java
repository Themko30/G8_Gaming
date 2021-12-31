package main.java.Prenotazione.Service;

import java.util.ArrayList;
import main.java.Storage.Entity.Prenotazione;

public interface PrenotazioneService {

    ArrayList<Prenotazione> allPrenotazioni();

    Prenotazione retrievePrenotazione(int codice);

    boolean acceptPrenotazione(int numero);

    boolean savePrenotazione(Prenotazione prenotazione);

    boolean updatePrenotazione(Prenotazione prenotazione);

    boolean deletePrenotazione(int codice);

    int counterPrenotazioni();
}
