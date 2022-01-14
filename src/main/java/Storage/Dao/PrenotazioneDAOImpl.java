package main.java.Storage.Dao;

import main.java.Storage.ConPool;
import main.java.Storage.Entity.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrenotazioneDAOImpl implements PrenotazioneDAO {

  /**
   * Metodo per salvare una prenotazione nel DB.
   * @param prenotazione il bean della prenotazione riempito.
   * @return un booleano per controllare la riuscita.
   */
  public boolean doSavePrenotazione(Prenotazione prenotazione) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "INSERT INTO Prenotazione(categoria, descrizione,"
                      + " emailRichiedente, copertina, nomeProdotto)"
                      + "VALUES(?,?,?,?,?)");

      ps.setString(1, prenotazione.getCategoria());
      ps.setString(2, prenotazione.getDescrizione());
      ps.setString(3, prenotazione.getEmailRichiedente());
      ps.setString(4, prenotazione.getCopertina());
      ps.setString(5, prenotazione.getNomeProdotto());

      int x = ps.executeUpdate();

      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  /**
   * Metodo per recuperare una prenotazione dal DB.
   * @param limit il limite finale della lista del DB.
   * @param offset la compensazione d`inizio della lista dal DB.
   * @return ritorna la lista di tutte le prenotazioni dal DB.
   */
  public ArrayList<Prenotazione> doRetrievePrenotazione(int limit, int offset) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Prenotazione LIMIT ?,?");
      ps.setInt(1, limit);
      ps.setInt(2, offset);

      ResultSet rs = ps.executeQuery();
      ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

      while (rs.next()) {
        Prenotazione p = new Prenotazione();
        p.setNumeroPrenotazione(rs.getInt("numeroPrenotazione"));
        p.setCategoria(rs.getString("categoria"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setEmailRichiedente(rs.getString("emailRichiedente"));
        p.setCopertina(rs.getString("copertina"));
        p.setAccettata(rs.getInt("accettata"));
        p.setNomeProdotto(rs.getString("nomeProdotto"));
        prenotazioni.add(p);
      }
      return prenotazioni;


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   * Metodo che recupera una prenotazione dal DB.
   * @param numeroPrenotazione il numero della prenotazione.
   * @return il bean della prenotazione riempito.
   */
  public Prenotazione doRetrievePrenotazioneByCodice(int numeroPrenotazione) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Prenotazione WHERE numeroPrenotazione=?");
      ps.setInt(1, numeroPrenotazione);

      ResultSet rs = ps.executeQuery();
      Prenotazione p = new Prenotazione();

      if (rs.next()) {
        p.setNumeroPrenotazione(rs.getInt("numeroPrenotazione"));
        p.setCategoria(rs.getString("categoria"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setEmailRichiedente(rs.getString("emailRichiedente"));
        p.setCopertina(rs.getString("copertina"));
        p.setAccettata(rs.getInt("accettata"));
        p.setNomeProdotto(rs.getString("nomeProdotto"));

      }
      return p;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Metodo per accettare una prenotazione
   * da parte di un admin.
   * @param numeroPrenotazione il numero della prenotazione.
   * @return un booleano per controllare la riuscita.
   */
  public boolean doAcceptPrenotazione(int numeroPrenotazione) {

    try (Connection con = ConPool.getConnection()) {
      PreparedStatement ps = con.prepareStatement(
              "UPDATE Prenotazione SET accettata=1 WHERE numeroPrenotazione=?");

      ps.setInt(1, numeroPrenotazione);

      int x = ps.executeUpdate();

      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Metodo per contare tutte le prenotazioni nel DB.
   * @return il numero di prenotazioni nel DB.
   */
  public int doRetrieveCounterPrenotazioni() {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT COUNT(*) FROM Prenotazione");

      ResultSet rs = ps.executeQuery();
      int quantita = 0;

      if (rs.next()) {
        quantita = rs.getInt(1);
      }
      return quantita;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
