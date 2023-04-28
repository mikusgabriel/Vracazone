package qc.ca.bdeb.sim202;

import java.util.ArrayList;
import java.util.HashMap;

public class Panier {
    private String idTransaction;
    private String idClient;
    private long date;
    private int nombreProduits;
    private HashMap<Integer,ItemPanier> items;

    public Panier(String idTransaction, String id_client, long date, int nombreProduits, HashMap<Integer,ItemPanier> items) {
        this.idTransaction = idTransaction;
        this.idClient = id_client;
        this.date = date;
        this.nombreProduits = nombreProduits;
        this.items = items;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public String getIdClient() {
        return idClient;
    }

    public long getDate() {
        return date;
    }

    public int getNombreProduits() {
        return nombreProduits;
    }

    public HashMap<Integer, ItemPanier> getItems() {
        return items;
    }
}
