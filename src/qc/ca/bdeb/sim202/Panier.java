package qc.ca.bdeb.sim202;

import java.util.ArrayList;

public class Panier {
    private String id_transaction;
    private String id_client;
    private long date;
    private int nombre_produits;
    private ArrayList<ItemPanier> items;




    public String getId_transaction() {
        return id_transaction;
    }

    public String getId_client() {
        return id_client;
    }

    public long getDate() {
        return date;
    }

    public int getNombre_produits() {
        return nombre_produits;
    }

    public ArrayList<ItemPanier> getItems() {
        return items;
    }



}
