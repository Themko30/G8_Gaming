package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;
import main.java.Storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Set;

public class CarrelloDAO {

    public int doUpdateCarrello(Carrello carrello){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1, carrello.getTotale());
            ps.setInt(2, carrello.getNumeroArticoli());
            ps.setInt(3, carrello.getUtente().getId());
            int x= ps.executeUpdate();

            ps= con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setInt(1,carrello.getUtente().getId());

            x+=ps.executeUpdate();

            LinkedHashMap<Prodotto, Integer> articoli = carrello.getProdotti();
            Set<Prodotto> keys = articoli.keySet();

            for(Prodotto key: keys){
                ps= con.prepareStatement("INSERT INTO ArticoloSelezionato(utente, articolo, quantita) VALUES(?,?,?)");
                ps.setInt(1,carrello.getUtente().getId());
                ps.setInt(2, key.getCodice());
                ps.setInt(3,articoli.get(key));
                x+= ps.executeUpdate();
            }

            return x>0? 1:0;


        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doCreateCarrello(Utente u){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO Carrello(utente, totale, numeroArticoli) VALUES(?,?,?)");
            ps.setInt(1, u.getId());
            ps.setDouble(2,0);
            ps.setInt(3, 0);

            int x=ps.executeUpdate();
            return x>0? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doClearCarrello(Carrello carrello){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("UPDATE Carrello SET totale=?, numeroArticoli=? WHERE utente=?");

            ps.setDouble(1,0);
            ps.setInt(2, 0);
            ps.setInt(3, carrello.getUtente().getId());
            int x= ps.executeUpdate();

            ps= con.prepareStatement("DELETE FROM ArticoloSelezionato WHERE utente=?");
            ps.setInt(1,carrello.getUtente().getId());

            x+=ps.executeUpdate();

            return x>0? 1:0;


        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Carrello doRetrieveCarrelloByUtente(Utente u) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Carrello c, ArticoloSelezionato as, Prodotto p WHERE c.utente=as.utente AND as.articolo=p.codice AND c.utente=?");

            ps.setInt(1, u.getId());
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

                    prodotti.put(prodotto, rs.getInt("as.quantita"));
                } while (rs.next());
            }

            carrello.setProdotti(prodotti);
            return carrello;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
