package main.java.Prenotazione;

public class Prenotazione {
  private int numeroPrenotazione, accettata;
  private String categoria, descrizione, emailRichiedente, copertina;

  public int getNumeroPrenotazione() {
    return numeroPrenotazione;
  }

  public void setNumeroPrenotazione(int numeroPrenotazione) {
    this.numeroPrenotazione = numeroPrenotazione;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public String getEmailRichiedente() {
    return emailRichiedente;
  }

  public void setEmailRichiedente(String emailRichiedente) {
    this.emailRichiedente = emailRichiedente;
  }

  public String getCopertina() {
    return copertina;
  }

  public void setCopertina(String copertina) {
    this.copertina = copertina;
  }

  public int getAccettata() {
    return accettata;
  }

  public void setAccettata(int accettata) {
    this.accettata = accettata;
  }
}
