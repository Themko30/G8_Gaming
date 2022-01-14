package main.java.Validator.Service;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.Part;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidIndirizzoException;
import main.java.Validator.Exceptions.InvalidProductException;
import main.java.Validator.Exceptions.InvalidProductQuantityException;
import main.java.Validator.Exceptions.InvalidUserException;

public interface Validator {

    /**
     * Metodo per validare la quantità di un prodotto.
     * @param prodotto Il prodotto da controllare
     * @param quantita La quantità del prodotto
     * @throws InvalidProductQuantityException Quando la quantità selezionata
     * è maggiore di quella disponibile in magazzino
     */
    void validateQuantitaProdotto(Prodotto prodotto, int quantita)
            throws InvalidProductQuantityException;

    /**
     * Metodo per validare i campi di un indirizzo.
     * @param indirizzo L'indirizzo da controllare
     * @param cap Il CAP da controllare
     * @param paese Il paese da controllare
     * @throws InvalidIndirizzoException Quando l'indirizzo non è valido
     */
    void validateIndirizzo(String indirizzo, Integer cap, String paese)
            throws InvalidIndirizzoException;

    /**
     * Metodo per validare la path in una servlet.
     * Se la path è nulla, la inizializza.
     * @param path La path da controllare
     * @return La path validata
     */
    String validatePath(String path);

    /**
     * Metodo per validare i campi inseriti in un prodotto.
     * @param prodotto Il prodotto da controllare
     * @throws InvalidProductException Quando almeno uno dei campi
     * inserti non è valido
     */
    void validateProdotto(Prodotto prodotto)
            throws InvalidProductException;

    /**
     * Metodo per validare i campi inseriti dall'utente durante
     * la registrazione o la modifica del profilo.
     * @param utente L'utente da controllare
     * @throws InvalidUserException Quando almeno uno dei campi
     * (nome, email, ecc.) inseriti non è valido
     * @throws InvalidIndirizzoException Quando l'indirizzo non è valido
     * @throws Exception Quando l'username o l'email è già presente nel database
     */
    void validateUtente(Utente utente)
            throws InvalidUserException,
            InvalidIndirizzoException,
            Exception;

    /**
     * Metodo per validare l'immagine inserita durante l'aggiunta
     * di un prodotto o una prenotazione.
     * @param copertina Il nome dell'immagine
     * @param image L'immagine da controllare
     * @throws IOException Quando c'è un errore durante la lettura dell'immagine
     * @throws InvalidProductException Quando l'immagine NON ha
     * altezza compresa tra 1000 e 1600 pixel o
     * larghezza compresa tra 800 e 1300 pixel
     */
    void validateImage(String copertina, Collection<Part> image)
            throws IOException, InvalidProductException;
}

