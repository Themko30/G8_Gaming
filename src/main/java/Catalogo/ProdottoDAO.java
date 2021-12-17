package main.java.Catalogo;

import main.java.Storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDAO {

    public int doUpdateProdotto(Prodotto prodotto){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Prodotto SET categoria=?, nome=?, piattaforma=?, prezzo=?, scontoAttivo=?, quantita=(quantita+?), descrizione=? WHERE codice=?");
            ps.setString(1, prodotto.getCategoria());
            ps.setString(2, prodotto.getNome());
            ps.setString(3, prodotto.getPiattaforma());
            ps.setDouble(4, prodotto.getPrezzo());
            ps.setDouble(5, prodotto.getScontoAttivo());
            ps.setInt(6, prodotto.getQuantita());
            ps.setString(7, prodotto.getDescrizione());
            ps.setInt(8, prodotto.getCodice());
            int x=ps.executeUpdate();
            return x>0? 1:0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doUpdateQuantita(Prodotto prodotto, int quantita){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Prodotto SET quantita=(quantita-?) WHERE codice=?");
            ps.setInt(1,quantita);
            ps.setInt(2, prodotto.getCodice());
            int x=ps.executeUpdate();
            return x>0? 1:0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doSaveProdotto(Prodotto prodotto){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("INSERT INTO Prodotto(categoria, nome, piattaforma, prezzo, scontoAttivo, quantita, descrizione, copertina) VALUES(?,?,?,?,?,?,?,?)");

            ps.setString(1, prodotto.getCategoria());
            ps.setString(2, prodotto.getNome());
            ps.setString(3, prodotto.getPiattaforma());
            ps.setDouble(4, prodotto.getPrezzo());
            ps.setDouble(5, prodotto.getScontoAttivo());
            ps.setInt(6, prodotto.getQuantita());
            ps.setString(7, prodotto.getDescrizione());
            ps.setString(8, prodotto.getCopertina());

            int x= ps.executeUpdate();

            return x>0? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveProdotti(int limit, int offset){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto LIMIT ?,?");
            ps.setInt(1, limit);
            ps.setInt(2, offset);

            ResultSet rs= ps.executeQuery();
            ArrayList<Prodotto> prodotti= new ArrayList<>();

            while (rs.next()){
                Prodotto p= new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                prodotti.add(p);
            }
            return prodotti;


        } catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Prodotto> doRetrieveProdottiByNomeLike(String nome, int offset, int limit){

        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE nome LIKE ? LIMIT ?,?");
            ps.setString(1, "%"+nome+"%");
            ps.setInt(2, offset);
            ps.setInt(3, limit);

            ResultSet rs= ps.executeQuery();
            ArrayList<Prodotto> prodotti= new ArrayList<>();
            while (rs.next()){
                Prodotto p= new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                prodotti.add(p);
            }
            return prodotti;


        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveProdottoByCodice(int codice){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto WHERE codice=?");
            ps.setInt(1,codice);

            ResultSet rs= ps.executeQuery();
            Prodotto p= new Prodotto();

            if(rs.next()){
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));

            }
            return p;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Prodotto> doRetrieveProdottiByCategoria(String categoria, int limit, int offset){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prdotto WHERE categoria=? LIMIT ?,?");
            ps.setString(1, categoria);
            ps.setInt(2, limit);
            ps.setInt(3, offset);

            ResultSet rs= ps.executeQuery();
            ArrayList<Prodotto> prodotti= new ArrayList<>();

            while (rs.next()){
                Prodotto p= new Prodotto();
                p.setCodice(rs.getInt("codice"));
                p.setPrezzo(rs.getDouble("prezzo"));
                p.setScontoAttivo(rs.getDouble("scontoAttivo"));
                p.setQuantita(rs.getInt("quantita"));
                p.setCopertina(rs.getString("copertina"));
                p.setNome(rs.getString("nome"));
                p.setPiattaforma(rs.getString("piattaforma"));
                p.setCategoria(rs.getString("categoria"));
                prodotti.add(p);
            }
            return prodotti;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

    public int doRetrieveQuantitaProdottoByCodice(int codice){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps = con.prepareStatement("SELECT quantita FROM Prodotto WHERE codice=?");
            ps.setInt(1,codice);

            ResultSet rs= ps.executeQuery();
            int quantita = 0;

            if(rs.next()){
                quantita = rs.getInt("quantita");
            }
            return quantita;

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }



}