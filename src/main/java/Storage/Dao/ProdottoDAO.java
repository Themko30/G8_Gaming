package main.java.Storage.Dao;

import main.java.Storage.Entity.Prodotto;

import java.util.ArrayList;

public interface ProdottoDAO {
    String doUpdateProdotto(Prodotto prodotto);
    boolean doUpdateQuantita(Prodotto prodotto, int quantita);
    boolean doUpdateMedia(Prodotto prodotto, int valutazione);
    boolean doSaveProdotto(Prodotto prodotto);
    ArrayList<Prodotto> doRetrieveProdotti(int offset, int limit);
    ArrayList<Prodotto> doRetrieveProdottiByNomeLike(String nome, int offset, int limit);
    Prodotto doRetrieveProdottoByCodice(int codice);
    ArrayList<Prodotto> doRetrieveProdottiByCategoria(String categoria, int offset, int limit);
    ArrayList<Prodotto> doRetrieveProdottiByPiattaforma(String piattaforma, int offset, int limit);
    int doRetrieveQuantitaProdottoByCodice(int codice);
    int doRetrieveCounterProdotti();
    ArrayList<Prodotto> doRetrieveMostVoted();
}
