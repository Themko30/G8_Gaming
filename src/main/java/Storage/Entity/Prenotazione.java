package main.java.Storage.Entity;

public class Prenotazione {

  /**
   * Il codice univoco della prenotazione.
   */
  private int numeroPrenotazione;

  /**
   * Il valore che indica se la prenotazione sia stata accettata o meno.
   * La prenotazione è considerata accettata se questa variabile ha valore 1.
   */
  private int accettata;

  /**
   * La categoria del videogioco richiesto.
   */
  private String categoria;

  /**
   * La descrizione del videogioco richiesto.
   */
  private String descrizione;

  /**
   * L'email dell'utente che ha effettuato la prenotazione.
   */
  private String emailRichiedente;

  /**
   * L'immagine del videogioco richiesto.
   */
  private String copertina;

  /**
   * Il nome del videogioco richiesto.
   */
  private String nomeProdotto;

  /**
   * Metodo Getter per ottenere il codice della prenotazione.
   * @return Il numero univoco della prenotazione
   */
  public int getNumeroPrenotazione() {
    return numeroPrenotazione;
  }

  /**
   * Metodo Setter per impostare il codice della prenotazione.
   * @param numeroPrenotazione Il numero univoco da impostare
   */
  public void setNumeroPrenotazione(int numeroPrenotazione) {
    this.numeroPrenotazione = numeroPrenotazione;
  }

  /**
   * Metodo Getter per ottenere la categoria del prodotto richiesto.
   * @return La categoria del prodotto
   */
  public String getCategoria() {
    return categoria;
  }

  /**
   * Metodo Setter per impostare la categoria del prodotto richiesto.
   * @param categoria La categoria da impostare
   */
  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  /**
   * Metodo Getter per ottenere la descrizione del prodotto richiesto.
   * @return La descrizione del prodotto
   */
  public String getDescrizione() {
    return descrizione;
  }

  /**
   * Metodo Setter per impostare la descrizione del prodotto richiesto.
   * @param descrizione La descrizione da impostare
   */
  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  /**
   * Metodo Getter per ottenere l'email dell'utente
   * che ha effettuato la prenotazione.
   * @return L'email del richiedente
   */
  public String getEmailRichiedente() {
    return emailRichiedente;
  }

  /**
   * Metodo Setter per impostare l'email dell'utente
   * che ha effettuato la prenotazione.
   * @param emailRichiedente L'email da impostare
   */
  public void setEmailRichiedente(String emailRichiedente) {
    this.emailRichiedente = emailRichiedente;
  }

  /**
   * Metodo Getter per ottenere l'immagine del prodotto richiesto.
   * @return L'immagine del prodotto
   */
  public String getCopertina() {
    return copertina;
  }

  /**
   * Metodo Setter per impostare l'immagine del prodotto richiesto.
   * @param copertina L'immagine da impostare
   */
  public void setCopertina(String copertina) {
    this.copertina = copertina;
  }

  /**
   * Metodo Getter per ottenere lo stato della prenotazione.
   * @return 1 se la prenotazione è stata accettata, 0 altrimenti
   */
  public int getAccettata() {
    return accettata;
  }

  /**
   * Metodo Setter per impostare lo stato della prenotazione.
   * @param accettata 1 se la prenotazione è stata accettata, 0 altrimenti
   */
  public void setAccettata(int accettata) {
    this.accettata = accettata;
  }

  /**
   * Metodo Getter per ottenere il nome del prodotto richiesto.
   * @return Il nome del prodotto
   */
  public String getNomeProdotto() {
    return nomeProdotto;
  }

  /**
   * Metodo Setter per impostare il nome del prodotto richiesto.
   * @param nomeProdotto Il nome da impostare
   */
  public void setNomeProdotto(String nomeProdotto) {
    this.nomeProdotto = nomeProdotto;
  }
}
