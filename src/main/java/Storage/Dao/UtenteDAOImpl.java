package main.java.Storage.Dao;

import main.java.Storage.ConPool;
import main.java.Storage.Entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteDAOImpl implements UtenteDAO {

  /**
   * Metodo per recuperare il numero
   * di tutti gli utenti nel DB.
   * @return il numero * di tutti gli utenti nel DB.
   */
  public int doRetrieveCounterUtenti() {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT COUNT(*) FROM Utente");

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
  /**
   * Metodo per salvare un utente nel database.
   * @param utente il bean dell`utente da inserire.
   * @return un booleano per controllare la riuscita.
   * @throws Exception una eccezione generale
   * che potrebbe lanciare durante l`esecuzione sul DB.
   */
  public boolean doSaveUtente(Utente utente) throws Exception {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "INSERT INTO Utente(username, email, passwordhash, nome,"
                      + " cognome, sesso, dataDiNascita, "
                      + " adminFlag, indirizzo, cap, paese) "
                      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

      ps.setString(1, utente.getUsername());
      ps.setString(2, utente.getEmail());
      ps.setString(3, utente.getPassword());
      ps.setString(4, utente.getNome());
      ps.setString(5, utente.getCognome());
      ps.setString(6, utente.getSesso());
      ps.setObject(7, java.sql.Date.valueOf(utente.getDataDiNascita()));
      ps.setBoolean(8, utente.isAdmin());
      ps.setString(9, utente.getIndirizzo());
      ps.setInt(10, utente.getCap());
      ps.setString(11, utente.getPaese());

      int x = ps.executeUpdate();

      return x == 1;

    } catch (SQLException e) {
      throw new Exception(e);
    }

  }
  /**
   * Metodo per recuperare tutti gli utenti dal DB.
   * @param limit il limite finale della lista del DB.
   * @param offset la compensazione d`inizio della lista dal DB.
   * @return la lista di tutti gli utenti nel DB.
   */
  public ArrayList<Utente> doRetrieveAllUtente(int limit, int offset) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Utente LIMIT ?,?");
      ps.setInt(1, limit);
      ps.setInt(2, offset);

      ResultSet rs = ps.executeQuery();
      ArrayList<Utente> utenti = new ArrayList<>();

      while (rs.next()) {
        Utente utente = new Utente();
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("passwordhash"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setSesso(rs.getString("sesso"));
        utente.setDataDiNascita(rs.getObject("dataDiNascita", LocalDate.class));
        utente.setAdmin(rs.getBoolean("adminFlag"));
        utente.setIndirizzo(rs.getString("indirizzo"));
        utente.setCap(rs.getInt("cap"));
        utente.setPaese(rs.getString("paese"));
        utenti.add(utente);
      }
      return utenti;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  /**
   * Metodo di servizio e testing che recupera
   * un utente dal DB con solo il suo username.
   * @param username username dell`utente
   * @return il bean dell`utente riempito.
   */
  public Utente doRetrieveUtenteByUsername(String username) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Utente WHERE username=?");
      ps.setString(1, username);

      ResultSet rs = ps.executeQuery();
      Utente utente = new Utente();

      if (rs.next()) {
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("passwordhash"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setSesso(rs.getString("sesso"));
        utente.setDataDiNascita(rs.getObject("dataDiNascita",
                LocalDate.class));
        utente.setAdmin(rs.getBoolean("adminFlag"));
        utente.setIndirizzo(rs.getString("indirizzo"));
        utente.setCap(rs.getInt("cap"));
        utente.setPaese(rs.getString("paese"));

      }
      return utente;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  /**
   * Metodo per trovare un utente nel DB con solo username e password.
   * @param username username dell`utente.
   * @param password password dell`utente
   * @return il bean dell`utente riempito.
   */
  public Utente doRetrieveUtenteByUsernameAndPassword(
          String username, String password) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Utente WHERE username=? AND passwordhash=?");
      ps.setString(1, username);
      ps.setString(2, password);

      ResultSet rs = ps.executeQuery();
      Utente utente = null;

      if (rs.next()) {
        utente = new Utente();
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("passwordhash"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setSesso(rs.getString("sesso"));
        utente.setDataDiNascita(rs.getObject("dataDiNascita", LocalDate.class));
        utente.setAdmin(rs.getBoolean("adminFlag"));
        utente.setIndirizzo(rs.getString("indirizzo"));
        utente.setCap(rs.getInt("cap"));
        utente.setPaese(rs.getString("paese"));

      }
      return utente;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  /**
   * Metodo che controlla se un utente esiste
   * utilizzando il suo username.
   * @param username username dell`utente.
   * @return un booleano per controllare la riuscita.
   */
  public boolean doCheckUsername(String username) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Utente WHERE username=?");
      ps.setString(1, username);

      ResultSet rs = ps.executeQuery();
      boolean x = rs.next();
      rs.close();
      return x;

    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  /**
   * Metodo che aggiorna un utente nel DB.
   * @param utente il bean dell`utente da aggiornare.
   * @return un booleano per controllare la riuscita.
   */
  public boolean doUpdateUtente(Utente utente) {

    try (Connection con = ConPool.getConnection()) {
      PreparedStatement ps = con.prepareStatement(
              "UPDATE Utente SET email=?, passwordhash=?, nome=?,"
                      + " cognome=?, sesso=?, dataDiNascita=?,"
                      + " adminFlag=?, indirizzo=?, cap=?, "
                      + "paese=?  WHERE username=?");

      ps.setString(1, utente.getEmail());
      ps.setString(2, utente.getPassword());
      ps.setString(3, utente.getNome());
      ps.setString(4, utente.getCognome());
      ps.setString(5, utente.getSesso());
      ps.setObject(6, java.sql.Date.valueOf(utente.getDataDiNascita()));
      ps.setBoolean(7, utente.isAdmin());
      ps.setString(8, utente.getIndirizzo());
      ps.setInt(9, utente.getCap());
      ps.setString(10, utente.getPaese());
      ps.setString(11, utente.getUsername());

      int x = ps.executeUpdate();

      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  /**
   * metodo per settare ad admin un utente.
   * @param username username dell`utente
   * @return un booleano per controllare la riuscita.
   */
  public boolean doSetAdmin(String username) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement(
              "UPDATE Utente SET adminFlag=1 WHERE username=?");
      ps.setString(1, username);

      int x = ps.executeUpdate();
      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
