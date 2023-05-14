package qc.ca.bdeb.sim202;

import java.util.ArrayList;
import java.util.Stack;

public class Colis {

    private final String idTransaction;

    private final Stack<Sachet> pileSachet;
    private final ArrayList<Pot> listePot;
    private final long dateTransaction;

    private final String idClient;

    Colis(String idTransaction,String idClient, Stack<Sachet> pileSachet, ArrayList<Pot> listePot, long dateTransaction){
        this.idTransaction=idTransaction;
        this.listePot=listePot;
        this.pileSachet=pileSachet;
        this.idClient=idClient;
        this.dateTransaction = dateTransaction;
    }




    public void emballer(){
        /*for(ItemPanier i:BaseDonnee.getHashMapPanier().get(idTransaction).getItems()){
            if(BaseDonnee.getHashMapProduit().get(i.getCodeProduit()).)

        }



         */




    }
    public String getIdTransaction() {
        return idTransaction;
    }

    public Stack<Sachet> getPileSachet() {
        return pileSachet;
    }

    public ArrayList<Pot> getListePot() {
        return listePot;
    }

    public long getDateTransaction() {
        return dateTransaction;
    }
}
