package main.java.Carrello.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public interface OrdineBuilder {

    /**
     * Metodo per impostare il prezzo totale di un ordine.
     * @param totale Il prezzo dell'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato del totale
     */
    OrdineBuilderImpl totale(double totale);

    /**
     * Metodo per impostare il numero degli
     * articoli presenti in un ordine.
     * @param numeroArticoli Il numero di articoli dell'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato del numero di articoli
     */
    OrdineBuilderImpl numeroArticoli(int numeroArticoli);

    /**
     * Metodo per impostare l'indirizzo di spedizione di un ordine.
     * @param indirizzoSpedizione L'indirizzo dell'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato dell'indirizzo
     */
    OrdineBuilderImpl indirizzoSpedizione(String indirizzoSpedizione);

    /**
     * Metodo per impostare il metodo di pagamento di un ordine.
     * @param metodoPagamento Il metodo di pagamento
     *                        dell'ordine scelto dall'utente
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato del metodo di pagamento
     */
    OrdineBuilderImpl metodoPagamento(String metodoPagamento);

    /**
     * Metodo per impostare la data di un ordine.
     * @param data La data dell'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato della data
     */
    OrdineBuilderImpl data(LocalDate data);

    /**
     * Metodo per impostare lo stato di un ordine.
     * @param stato Lo stato dell'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato dello stato
     */
    OrdineBuilderImpl stato(String stato);

    /**
     * Metodo per impostare tutti i prodotti,
     * con relative quantità, di un ordine.
     * @param prodotti Una HashMap che ha i
     *                 prodotti come chiavi e la
     *                 loro quantità come valori
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato dei prodotti
     */
    OrdineBuilderImpl prodotti(LinkedHashMap<Prodotto, Integer> prodotti);

    /**
     * Metodo per impostare l'utente che ha effettuato un ordine.
     * @param utente L'utente che ha effettuato l'ordine
     * @return Un OrdineBuilderImpl, con il
     * valore aggiornato dell'utente
     */
    OrdineBuilderImpl utente(Utente utente);

    /**
     * Metodo per completare la creazione di un ordine.
     * @return L'ordine completo
     */
    Ordine build();

    /**
     * Metodo per ottenere il prezzo totale un ordine.
     * @return Il prezzo dell'ordine
     */
    double getTotale();

    /**
     * Metodo per ottenere il numero di
     * articoli presenti in un ordine.
     * @return Il numero di articoli
     */
    int getNumeroArticoli();

    /**
     * Metodo per ottenere l'indirizzo di spedizione di un ordine.
     * @return L'indirizzo dell'ordine
     */
    String getIndirizzoSpedizione();

    /**
     * Metodo per ottenere il metodo di pagamento di un ordine.
     * @return Il metodo di pagamento dell'ordine
     */
    String getMetodoPagamento();

    /**
     * Metodo per ottenere la data di un ordine.
     * @return La data dell'ordine
     */
    LocalDate getData();

    /**
     * Metodo per ottenere lo stato di un ordine.
     * @return Lo stato dell'ordine
     */
    String getStato();

    /**
     * Metodo per ottenere tutti i prodotti,
     * con relativa quantità, di un ordine.
     * @return L'indirizzo dell'ordine
     */
    LinkedHashMap<Prodotto, Integer> getProdotti();

    /**
     * Metodo per ottenere l'utente che ha
     * effettuato un determinato ordine.
     * @return L'utente che ha effettuato l'ordine
     */
    Utente getUtente();
}
