package main.java.Autenticazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import main.java.Prenotazione.Prenotazione;
import main.java.Storage.ConPool;

public class UtenteDAO {

  public boolean doSaveUtente(Utente utente) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("INSERT INTO Utente(username, email, password, nome, cognome, sesso, dataDiNascita, adminFlag) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

      ps.setString(1, utente.getUsername());
      ps.setString(2, utente.getEmail());
      ps.setString(3, utente.getPassword());
      ps.setString(4, utente.getNome());
      ps.setString(5, utente.getCognome());
      ps.setString(6, utente.getSesso());
      ps.setObject(7, java.sql.Date.valueOf(utente.getDataDiNascita()));
      ps.setBoolean(8, utente.isAdmin());

      int x= ps.executeUpdate();

      return x==1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public ArrayList<Utente> doRetrieveAllUtente(int limit, int offset){
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente LIMIT ?,?");
      ps.setInt(1, limit);
      ps.setInt(2, offset);

      ResultSet rs= ps.executeQuery();
      ArrayList<Utente> utenti= new ArrayList<>();

      while (rs.next()){
        Utente utente= new Utente();
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("password"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setSesso(rs.getString("sesso"));
        utente.setDataDiNascita(rs.getObject("dataDiNascita", LocalDate.class));
        utente.setAdmin(rs.getBoolean("admin"));
        utenti.add(utente);

      }
      return utenti;


    } catch(SQLException e){
      throw new RuntimeException(e);
    }

  }

  public Utente doRetrieveUtenteByUsername(String username){
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("SELECT * FROM Prenotazione WHERE username=?");
      ps.setString(1,username);

      ResultSet rs= ps.executeQuery();
      Utente utente= new Utente();

      if(rs.next()){
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("password"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setSesso(rs.getString("sesso"));
        utente.setDataDiNascita(rs.getObject("dataDiNascita", LocalDate.class));
        utente.setAdmin(rs.getBoolean("admin"));

      }
      return utente;

    } catch(SQLException e){
      throw new RuntimeException(e);
  }
    }

    public boolean doUpdateUtente(Utente utente){

      try (Connection con = ConPool.getConnection()) {
        PreparedStatement ps = con.prepareStatement("UPDATE Utente SET username=?, email=?, password=?, nome=?, cognome=?, sesso=?, dataDiNascita=?, admin=?  WHERE codice=?");

        ps.setString(1, utente.getUsername());
        ps.setString(2, utente.getEmail());
        ps.setString(3, utente.getPassword());
        ps.setString(4, utente.getNome());
        ps.setString(5, utente.getCognome());
        ps.setString(6, utente.getSesso());
        ps.setObject(7, java.sql.Date.valueOf(utente.getDataDiNascita()));
        ps.setBoolean(8, utente.isAdmin());

        int x=ps.executeUpdate();

        return x == 1;

      } catch (SQLException e){
        throw new RuntimeException(e);
      }
    }

  public boolean doDeleteUtente(String username) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("DELETE FROM Utente WHERE username=?");
      ps.setString(1, username);

      int x = ps.executeUpdate();
      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}