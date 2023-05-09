package qc.ca.bdeb.sim202;


import java.io.*;
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
    private double getPrixfinal(){
        return getPrixInitial()+getTaxesFinal();
    }
    private double getPrixInitial(){
        double prixInitial=0;
        for(ItemPanier i:items){
            prixInitial+=i.getQuantite()*BaseDonnee.getHashMapProduit().get(i.getCodeProduit()).getPrixUnitaire();
        }
        return prixInitial;
    }
    private double getTaxesFinal(){
        double taxesFinal=0;
        for (ItemPanier i:items){
            taxesFinal+=BaseDonnee.getHashMapProduit().get(i.getCodeProduit()).getTaxes()* i.getQuantite();
        }
        return taxesFinal;
    }

    public void addFacturetoFile(){
        System.out.println("occc");

        try{
            PrintWriter file=new PrintWriter(new FileOutputStream("fichiers/facture.txt",true));
            file.println(idTransaction+" | "+App.getDateString(date)+" |  $"+Math.round(getPrixInitial() * 100.0) / 100.0+" |  $"+Math.round(getTaxesFinal() * 100.0) / 100.0+" |  $"+Math.round(getPrixfinal() * 100.0) / 100.0);

            file.close();

        }
        catch (FileNotFoundException e){
            System.err.println("File not found");
        }catch (Exception e){
            System.out.println("Erreur");

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
