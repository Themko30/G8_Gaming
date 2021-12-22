package main.java.Prenotazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.java.Catalogo.Prodotto;
import main.java.Storage.ConPool;

public class PrenotazioneDAO {

  public boolean doSavePrenotazione(Prenotazione prenotazione){
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("INSERT INTO Prenotazione(categoria, descrizione, emailRichiedente, copertina) VALUES(?,?,?,?)");

      ps.setString(1, prenotazione.getCategoria());
      ps.setString(2, prenotazione.getDescrizione());
      ps.setString(3, prenotazione.getEmailRichiedente());
      ps.setString(4, prenotazione.getCopertina());

      int x= ps.executeUpdate();

      return x == 1;

    }
    catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public ArrayList<Prenotazione> doRetrievePrenotazione(int limit, int offset){
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("SELECT * FROM Prenotazione LIMIT ?,?");
      ps.setInt(1, limit);
      ps.setInt(2, offset);

      ResultSet rs= ps.executeQuery();
      ArrayList<Prenotazione> prenotazioni= new ArrayList<>();

      while (rs.next()){
        Prenotazione p = new Prenotazione();
        p.setNumeroPrenotazione(rs.getInt("numeroPrenotazione"));
        p.setCategoria(rs.getString("categoria"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setEmailRichiedente(rs.getString("emailRichiedente"));
        p.setCopertina(rs.getString("copertina"));
        p.setAccettata(rs.getInt("accettata"));
        prenotazioni.add(p);
      }
      return prenotazioni;


    } catch(SQLException e){
      throw new RuntimeException(e);
    }

  }

  public Prenotazione doRetrievePrenotazioneByCodice(int numeroPrenotazione){
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("SELECT * FROM Prenotazione WHERE numeroPrenotazione=?");
      ps.setInt(1,numeroPrenotazione);

      ResultSet rs= ps.executeQuery();
      Prenotazione p= new Prenotazione();

      if(rs.next()){
        p.setNumeroPrenotazione(rs.getInt("numeroPrenotazione"));
        p.setCategoria(rs.getString("categoria"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setEmailRichiedente(rs.getString("emailRichiedente"));
        p.setCopertina(rs.getString("copertina"));
        p.setAccettata(rs.getInt("accettata"));

      }
      return p;

    } catch(SQLException e){
      throw new RuntimeException(e);
    }
  }

  public boolean doUpdatePrenotazione(Prenotazione prenotazione){

    try (Connection con = ConPool.getConnection()) {
      PreparedStatement ps = con.prepareStatement("UPDATE Prenotazione SET categoria=?, descrizione=?, emailRichiedente=?, copertina=?, accettata=? WHERE numeroPrenotazione=?");


      ps.setString(1, prenotazione.getCategoria());
      ps.setString(2, prenotazione.getDescrizione());
      ps.setString(3, prenotazione.getEmailRichiedente());
      ps.setString(4, prenotazione.getCopertina());
      ps.setInt(5, prenotazione.getAccettata());
      ps.setInt(6, prenotazione.getNumeroPrenotazione());

      int x=ps.executeUpdate();

      return x == 1;

    } catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public boolean doAcceptPrenotazione(int numeroPrenotazione){

    try (Connection con = ConPool.getConnection()) {
      PreparedStatement ps = con.prepareStatement("UPDATE Prenotazione SET accettata=1 WHERE numeroPrenotazione=?");

      ps.setInt(1, numeroPrenotazione);

      int x=ps.executeUpdate();

      return x == 1;

    } catch (SQLException e){
      throw new RuntimeException(e);
    }
  }

  public boolean doDeletePrenotazione(int numeroPrenotazione) {
    try (Connection con = ConPool.getConnection()) {

      PreparedStatement ps = con.prepareStatement("DELETE FROM Prenotazione WHERE numeroPrenotazione=?");
      ps.setInt(1, numeroPrenotazione);

      int x = ps.executeUpdate();
      return x == 1;

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
