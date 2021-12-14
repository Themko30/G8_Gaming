package main.java.Carrello;

import main.java.Autenticazione.Utente;
import main.java.Catalogo.Prodotto;
import main.java.Storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OrdineDAO {

    public ArrayList<Ordine> doRetrieveOrdiniByUtente(Utente u){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine WHERE utente=?");
            ps.setInt(1, u.getId());
            ResultSet rs= ps.executeQuery();
            ArrayList<Ordine> ordini= new ArrayList<>();
            Ordine o;
            while(rs.next()){
                o= new Ordine();
                o.setUtente(u);
                o.setNumero(rs.getInt(2));
                o.setTotale(rs.getDouble(3));
                o.setNumeroElementi(rs.getInt(4));
                o.setIndirizzoSpedizione(rs.getString(5));
                o.setData(rs.getObject(6, LocalDate.class));
                o.setStato(rs.getString(7));
                ordini.add(o);
            }

            return ordini;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveProdottiAcquistati(int codiceOrdine){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine o, ArticoloAcquistato a, Prodotto p  WHERE o.codice=? AND o.codice=a.ordine AND a.prodotto=p.codice");
            ps.setInt(1, codiceOrdine);
            ResultSet rs= ps.executeQuery();
            LinkedHashMap<Prodotto, Integer> prodotti= new LinkedHashMap<>();
            Ordine o = new Ordine();

            boolean firstElement = true;

            while(rs.next()){
                if(firstElement){
                    o.setNumero(rs.getInt("o.numero"));
                    o.setTotale(rs.getDouble("o.totale"));
                    o.setNumeroElementi(rs.getInt("o.numeroElementi"));
                    o.setIndirizzoSpedizione(rs.getString("o.indirizzoSpedizione"));
                    o.setData(rs.getObject("o.data", LocalDate.class));
                    o.setStato(rs.getString("o.stato"));

                    firstElement = false;
                }
                Prodotto p = new Prodotto();

                p.setNome(rs.getString("p.nome"));
                p.setCategoria(rs.getString("p.categoria"));
                p.setDescrizione(rs.getString("p.descrizione"));
                p.setCopertina(rs.getString("p.copertina"));

                prodotti.put(p, rs.getInt("a.quantitaAcquistata"));
            }

            o.setProdotti(prodotti);
            return o;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Ordine> doRetrieveAllOrdini(int offset, int limit){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Ordine LIMIT ?,?");
            ps.setInt(1, offset);
            ps.setInt(2, limit);
            ResultSet rs= ps.executeQuery();
            ArrayList<Ordine> ordini= new ArrayList<>();
            Ordine o;
            while(rs.next()){
                o= new Ordine();
                Utente u= new Utente();
                u.setId(rs.getInt(1));
                o.setUtente(u);
                o.setNumero(rs.getInt(2));
                o.setTotale(rs.getDouble(3));
                o.setNumeroElementi(rs.getInt(4));
                o.setIndirizzoSpedizione(rs.getString(5));
                o.setData(rs.getObject(6, LocalDate.class));
                o.setStato(rs.getString(7));
                ordini.add(o);
            }

            return ordini;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public int doUpdateStatoOrdine(Ordine o){
        try (Connection con = ConPool.getConnection()) {

            PreparedStatement ps =
                    con.prepareStatement("UPDATE Ordine SET stato=? WHERE numero=?");
            ps.setString(1, o.getStato());
            ps.setInt(2, o.getNumero());
            int rows= ps.executeUpdate();

            return rows>0 ? 1:0;

        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }




}
