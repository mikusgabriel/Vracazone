package qc.ca.bdeb.sim202;


import java.util.ArrayList;


public class Panier {
    private String idTransaction;
    private String idClient;
    private long date;
    private int nombreProduits;
    private ArrayList<ItemPanier> items;

    public Panier(String idTransaction, String id_client, long date, int nombreProduits, ArrayList<ItemPanier> items) {
        this.idTransaction = idTransaction;
        this.idClient = id_client;
        this.date = date;
        this.nombreProduits = nombreProduits;
        this.items = items;
    }
    public void getFacture(){
        for(ItemPanier i:items){
           BaseDonnee.getHashMapProduit().get(i.getCodeProduit());


        }
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

    public ArrayList<ItemPanier> getItems() {
        return items;
    }

}
