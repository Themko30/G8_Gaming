package main.java.Storage.Dao;

import main.java.Storage.ConPool;
import main.java.Storage.Entity.Ordine;
import main.java.Storage.Entity.Prodotto;
import main.java.Storage.Entity.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class OrdineDAOImpl implements OrdineDAO {

    public ArrayList<Ordine> doRetrieveOrdiniByUtente(Utente u) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Ordine WHERE utente=?");
            ps.setString(1, u.getUsername());
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> ordini = new ArrayList<>();
            Ordine o;
            while (rs.next()) {
                o = new Ordine();
                o.setUtente(u);
                o.setNumero(rs.getInt(2));
                o.setTotale(rs.getDouble(3));
                o.setNumeroArticoli(rs.getInt(4));
                o.setIndirizzoSpedizione(rs.getString(5));
                o.setMetodoPagamento(rs.getString(6));
                o.setData(rs.getObject(7, LocalDate.class));
                o.setStato(rs.getString(8));
                ordini.add(o);
            }

            return ordini;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveProdottiAcquistati(int codiceOrdine) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Ordine o, ArticoloAcquistato a,"
                            + " Prodotto p  WHERE o.numero=?"
                            + " AND o.numero=a.ordine AND a.prodotto=p.codice");
            ps.setInt(1, codiceOrdine);
            ResultSet rs = ps.executeQuery();
            LinkedHashMap<Prodotto, Integer> prodotti = new LinkedHashMap<>();
            Ordine o = new Ordine();

            boolean firstElement = true;

            while (rs.next()) {
                if (firstElement) {
                    o.setNumero(rs.getInt("o.numero"));
                    o.setTotale(rs.getDouble("o.totale"));
                    o.setNumeroArticoli(rs.getInt("o.numeroArticoli"));
                    o.setIndirizzoSpedizione(rs.getString(
                            "o.indirizzoSpedizione"));
                    o.setData(rs.getObject("o.dataOrdine", LocalDate.class));
                    o.setStato(rs.getString("o.stato"));
                    o.setMetodoPagamento(rs.getString("o.metodoPagamento"));
                    Utente utente = new Utente();
                    utente.setUsername(rs.getString("o.utente"));
                    o.setUtente(utente);

                    firstElement = false;
                }
                Prodotto p = new Prodotto();

                p.setNome(rs.getString("p.nome"));
                p.setCategoria(rs.getString("p.categoria"));
                p.setDescrizione(rs.getString("p.descrizione"));
                p.setCopertina(rs.getString("p.copertina"));
                p.setPrezzo(rs.getDouble("p.prezzo"));
                p.setScontoAttivo(rs.getDouble("p.scontoAttivo"));
                p.setCodice(rs.getInt("p.codice"));

                p.setValutato(rs.getInt("a.valutato"));

                prodotti.put(p, rs.getInt("a.quantita"));
            }

            o.setProdotti(prodotti);
            return o;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Ordine> doRetrieveAllOrdini(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Ordine LIMIT ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            ArrayList<Ordine> ordini = new ArrayList<>();
            Ordine o;
            while (rs.next()) {
                o = new Ordine();
                Utente u = new Utente();
                u.setUsername(rs.getString(1));
                o.setUtente(u);
                o.setNumero(rs.getInt(2));
                o.setTotale(rs.getDouble(3));
                o.setNumeroArticoli(rs.getInt(4));
                o.setIndirizzoSpedizione(rs.getString(5));
                o.setMetodoPagamento(rs.getString(6));
                o.setData(rs.getObject(7, LocalDate.class));
                o.setStato(rs.getString(8));
                ordini.add(o);
            }

            return ordini;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doUpdateStatoOrdine(int numero, String stato) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Ordine SET stato=? WHERE numero=?");
            ps.setString(1, stato);
            ps.setInt(2, numero);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doSaveOrdine(Ordine o) {
        try (Connection con = ConPool.getConnection()) {

            LinkedHashMap<Prodotto, Integer> prodotti = o.getProdotti();
            if(prodotti.size() > 0) {


                Set<Prodotto> key = prodotti.keySet();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO Ordine(utente, totale, numeroArticoli,"
                                + "indirizzoSpedizione, metodoPagamento, dataOrdine,"
                                + " stato) VALUES(?,?,?,?,?,?,?)");
                ps.setString(1, o.getUtente().getUsername());
                ps.setDouble(2, o.getTotale());
                ps.setInt(3, o.getNumeroArticoli());
                ps.setString(4, o.getIndirizzoSpedizione());
                ps.setString(5, o.getMetodoPagamento());
                ps.setObject(6, java.sql.Date.valueOf(o.getData()));
                ps.setString(7, "Inviato");
                int rows = ps.executeUpdate();

                ps = con.prepareStatement(
                        "SELECT numero FROM Ordine WHERE utente=?"
                                + "ORDER BY numero DESC LIMIT 1");
                ps.setString(1, o.getUtente().getUsername());

                ResultSet resultSet = ps.executeQuery();
                int numeroOrdine = 0;

                if (resultSet.next()) {
                    numeroOrdine = resultSet.getInt("numero");
                }

                ProdottoDAO prodottoDAO = new ProdottoDAOImpl();

                for (Prodotto p : key) {
                    ps = con.prepareStatement(
                            "INSERT INTO ArticoloAcquistato(prodotto, ordine, quantita)"
                                    + "VALUES (?,?,?)");
                    ps.setInt(1, p.getCodice());
                    ps.setInt(2, numeroOrdine);
                    ps.setInt(3, prodotti.get(p));
                    ps.executeUpdate();
                    prodottoDAO.doUpdateQuantita(p, prodotti.get(p));
                }

                return rows > 0;
            }
            else {
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doSetProdottoValutato(int codiceOrdine, int codiceProdotto) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE ArticoloAcquistato SET valutato=1"
                            + " WHERE ordine=? AND prodotto=?");
            ps.setInt(1, codiceOrdine);
            ps.setInt(2, codiceProdotto);
            int x = ps.executeUpdate();
            return x > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doRetrieveCounterOrdini() {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM Ordine");

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
