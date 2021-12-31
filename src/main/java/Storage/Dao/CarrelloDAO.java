package main.java.Storage.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Set;
import main.java.Storage.ConPool;
import main.java.Storage.Entity.Carrello;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

public class CarrelloDAO {

    public Boolean doUpdateCarrello(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1, carrello.getTotale());
            ps.setInt(2, carrello.getNumeroArticoli());
            ps.setString(3, carrello.getUtente().getUsername());
            int x = ps.executeUpdate();

            ps = con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setString(1, carrello.getUtente().getUsername());

            x += ps.executeUpdate();

            LinkedHashMap<Prodotto, Integer> articoli = carrello.getProdotti();
            Set<Prodotto> keys = articoli.keySet();

            for (Prodotto key : keys) {
                ps = con.prepareStatement("INSERT INTO ArticoloSelezionato(utente, prodotto, quantita) VALUES(?,?,?)");
                ps.setString(1, carrello.getUtente().getUsername());
                ps.setInt(2, key.getCodice());
                ps.setInt(3, articoli.get(key));
                x += ps.executeUpdate();
            }

            return x == 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doCreateCarrello(Utente u) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO Carrello(utente, totale, numeroArticoli) VALUES(?,?,?)");
            ps.setString(1, u.getUsername());
            ps.setDouble(2, 0);
            ps.setInt(3, 0);

            int x = ps.executeUpdate();
            return x>0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doClearCarrello(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1, 0);
            ps.setInt(2, 0);
            ps.setString(3, carrello.getUtente().getUsername());
            int x = ps.executeUpdate();

            ps = con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setString(1, carrello.getUtente().getUsername());

            x += ps.executeUpdate();

            return x > 0 ? 1 : 0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveCarrelloByUtente(Utente u) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Carrello c, ArticoloSelezionato a, Prodotto p WHERE c.utente=a.utente AND a.prodotto=p.codice AND c.utente=?");

            ps.setString(1, u.getUsername());
            ResultSet rs = ps.executeQuery();
            Carrello carrello = new Carrello();
            carrello.setUtente(u);
            carrello.setTotale(0);
            carrello.setNumeroArticoli(0);
            LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
            Prodotto prodotto;
            boolean firstElement = true;

            if (!rs.next()) {
                carrello.setUtente(u);
                carrello.setTotale(0);
                carrello.setNumeroArticoli(0);
            } else {
                do {
                    if (firstElement) {
                        carrello.setUtente(u);
                        carrello.setTotale(rs.getDouble("c.totale"));
                        carrello.setNumeroArticoli(rs.getInt("c.numeroArticoli"));
                        firstElement = false;
                    }
                    prodotto = new Prodotto();
                    prodotto.setCodice(rs.getInt("p.codice"));
                    prodotto.setNome(rs.getString("p.nome"));
                    prodotto.setCategoria(rs.getString("p.tipo"));
                    prodotto.setPrezzo(rs.getDouble("p.prezzo"));
                    prodotto.setQuantita(rs.getInt("p.quantita"));
                    prodotto.setCopertina(rs.getString("p.copertina"));
                    prodotto.setScontoAttivo(rs.getDouble("p.scontoAttivo"));

                    prodotti.put(prodotto, rs.getInt("a.quantita"));
                } while (rs.next());
            }

            carrello.setProdotti(prodotti);
            return carrello;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
