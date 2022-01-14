package main.java.Storage.Dao;

import main.java.Storage.ConPool;
import main.java.Storage.Entity.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAOImpl implements ProdottoDAO {

    /**
     * Metodo per aggiornare la scheda di un prodotto nel DB.
     * @param prodotto il bean del prodotto riempito.
     * @return la stringa della vecchia copertina
     * per assicurarne l`eliminazione.
     */
    public String doUpdateProdotto(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT copertina FROM Prodotto WHERE codice=?");
            ps.setInt(1, prodotto.getCodice());

            ResultSet rs = ps.executeQuery();

            String oldCopertina = null;
            if (rs.next()) {
                oldCopertina = rs.getString("copertina");
            }

            ps = con.prepareStatement(
                    "UPDATE Prodotto SET categoria=?, nome=?, "
                            + "piattaforma=?, prezzo=?, scontoAttivo=?, "
                            + "quantita=(quantita+?), descrizione=?, "
                            + "copertina=? WHERE codice=?");
            ps.setString(1, prodotto.getCategoria());
            ps.setString(2, prodotto.getNome());
            ps.setString(3, prodotto.getPiattaforma());
            ps.setDouble(4, prodotto.getPrezzo());
            ps.setDouble(5, prodotto.getScontoAttivo());
            ps.setInt(6, prodotto.getQuantita());
            ps.setString(7, prodotto.getDescrizione());
            ps.setString(8, prodotto.getCopertina());
            ps.setInt(9, prodotto.getCodice());
            int x = ps.executeUpdate();

            return oldCopertina;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che aggiorna la quantità di un prodotto nel DB.
     * @param prodotto il bean del prodotto da aggiornare.
     * @param quantita la quantità nuova.
     * @return un booleano per controllare la riuscita.
     */
    public boolean doUpdateQuantita(Prodotto prodotto, int quantita) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Prodotto SET quantita=(quantita-?) WHERE codice=?");
            ps.setInt(1, quantita);
            ps.setInt(2, prodotto.getCodice());
            int x = ps.executeUpdate();
            return x > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo per aggiornare la media di un prodotto.
     * @param prodotto il bean del prodotto.
     * @param valutazione il valore della valutazione nuova.
     * @return un booleano per controllare la riuscita.
     */
    public boolean doUpdateMedia(Prodotto prodotto, int valutazione) {
        try (Connection con = ConPool.getConnection()) {
            if (valutazione > 0 && valutazione < 6) {

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE Prodotto SET numeroVoti=(numeroVoti+1), "
                                + "totaleVoti = (totaleVoti + ?) WHERE codice=?");
                ps.setInt(1, valutazione);
                ps.setInt(2, prodotto.getCodice());

                int x = ps.executeUpdate();
                ps = con.prepareStatement(
                        "UPDATE Prodotto SET media=(totaleVoti/numeroVoti)"
                                + "WHERE codice=?");
                ps.setInt(1, prodotto.getCodice());
                x = ps.executeUpdate();

                return x > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo per salvare un prodotto nel DB.
     * @param prodotto il bean del prodotto riempito.
     * @return un booleano per controllare la riuscita.
     */
    public boolean doSaveProdotto(Prodotto prodotto) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Prodotto(categoria, nome, piattaforma, "
                            + "prezzo, scontoAttivo, quantita, descrizione, "
                            + "copertina) VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, prodotto.getCategoria());
            ps.setString(2, prodotto.getNome());
            ps.setString(3, prodotto.getPiattaforma());
            ps.setDouble(4, prodotto.getPrezzo());
            ps.setDouble(5, prodotto.getScontoAttivo());
            ps.setInt(6, prodotto.getQuantita());
            ps.setString(7, prodotto.getDescrizione());
            ps.setString(8, prodotto.getCopertina());

            int x = ps.executeUpdate();

            return x > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo per recuperare tutti i prodotti dal DB.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di tutte i prodotti.
     */
    public ArrayList<Prodotto> doRetrieveProdotti(int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Prodotto LIMIT ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
                prodotti.add(p);
            }
            return prodotti;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Metodo utilizzato durante la ricerca da parte
     * degli utenti per ottenere i prodotti con un
     * nome simile a quello indicato come input.
     * @param nome Il nome del prodotto da cercare.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di tutti i prodotti recuperati.
     */
    public ArrayList<Prodotto> doRetrieveProdottiByNomeLike(
            String nome, int offset, int limit) {

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Prodotto WHERE nome LIKE ? LIMIT ?,?");
            ps.setString(1, "%" + nome.trim()
                    .replace(" ", "%") + "%");
            ps.setInt(2, offset);
            ps.setInt(3, limit);

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();
            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
                prodotti.add(p);
            }
            return prodotti;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo che recupera un prodotto dal suo codice.
     * @param codice il codice del prodotto.
     * @return il bean del prodotto riempito.
     */
    public Prodotto doRetrieveProdottoByCodice(int codice) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Prodotto WHERE codice=?");
            ps.setInt(1, codice);

            ResultSet rs = ps.executeQuery();
            Prodotto p = null;

            if (rs.next()) {
                p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
            }
            return p;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo per recuperare tutti i prodotti di una certa categoria.
     * @param categoria il nome della categoria.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di prodotti recuperati.
     */
    public ArrayList<Prodotto> doRetrieveProdottiByCategoria(
      String categoria, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
              "SELECT * FROM Prodotto WHERE categoria=? LIMIT ?,?");
            ps.setString(1, categoria);
            ps.setInt(2, offset);
            ps.setInt(3, limit);

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
                prodotti.add(p);
            }
            return prodotti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Metodo per recuperare tutti i prodotti di una certa piattaforma.
     * @param piattaforma il nome della piattaforma.
     * @param offset la compensazione d`inizio della lista dal DB.
     * @param limit il limite finale della lista del DB.
     * @return la lista di prodotti recuperati.
     */
    public ArrayList<Prodotto> doRetrieveProdottiByPiattaforma(
      String piattaforma, int offset, int limit) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Prodotto WHERE piattaforma=? LIMIT ?,?");
            ps.setString(1, piattaforma);
            ps.setInt(2, offset);
            ps.setInt(3, limit);

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> prodotti = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
                prodotti.add(p);
            }
            return prodotti;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Metodo per recuperare la quantità di un prodotto
     * utilizzando il suo codice.
     * @param codice il codice del prodotto.
     * @return la quantità recuperata dal DB.
     */
    public int doRetrieveQuantitaProdottoByCodice(int codice) {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT quantita FROM Prodotto WHERE codice=?");
            ps.setInt(1, codice);

            ResultSet rs = ps.executeQuery();
            int quantita = 0;

            if (rs.next()) {
                quantita = rs.getInt("quantita");
            } else {
                return -1;
            }
            return quantita;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Metodo per recuperare il numero di tutti i prodotti dal DB.
     * @return il numero di prodotti recuperati.
     */
    public int doRetrieveCounterProdotti() {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT COUNT(*) FROM Prodotto");

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
     * Metodo che recupera i prodotti maggiormente votati.
     * @return la lista di prodotti recuperati.
     */
    public ArrayList<Prodotto> doRetrieveMostVoted() {
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Prodotto ORDER BY media DESC LIMIT 5");

            ResultSet rs = ps.executeQuery();
            ArrayList<Prodotto> mostVoted = new ArrayList<>();

            while (rs.next()) {
                Prodotto p = new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setDescrizione(rs.getString("descrizione"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                p.setNumeroVoti(rs.getInt("numeroVoti"));
                p.setTotaleVoti(rs.getInt("totaleVoti"));
                p.setMedia(rs.getDouble("media"));
                mostVoted.add(p);
            }
            return mostVoted;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
