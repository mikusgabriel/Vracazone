package qc.ca.bdeb.sim202;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Colis {

    private final String idTransaction;

    private  Stack<Sachet> pileSachet=new Stack<>();
    private  ArrayList<Pot> listePot=new ArrayList<>();
    private final long dateTransaction;

    private final String idClient;

    Colis(String idTransaction,String idClient, long dateTransaction){
        this.idTransaction=idTransaction;
        this.idClient=idClient;
        this.dateTransaction = dateTransaction;

    }




    public void emballer() {
        for (ItemPanier i : BaseDonnee.getHashMapPanier().get(idTransaction).getItems()) {
            double nombreRestant = i.getQuantite();
            while (nombreRestant > 0.0001){
                if (!BaseDonnee.getHashMapProduit().get(i.getCodeProduit()).isSolide()) {
                    Pot pot = new Pot(i.getCodeProduit());
                    nombreRestant = pot.verserLiquide(nombreRestant);
                    pot.visser();
                    listePot.add(pot);

                } else {
                    Sachet sachet = new Sachet(i.getCodeProduit());
                    nombreRestant = sachet.verserSolide(nombreRestant);
                    sachet.sceller();
                    pileSachet.add(sachet);

                }
            }
        }
    }
    public void addToRegistre(){
        try{
            PrintWriter file=new PrintWriter(new FileOutputStream("fichiers/registre.txt",true));
            file.println(idTransaction);

            ArrayList<Sachet> sachetQuantiteArray = new ArrayList<>(pileSachet);
            sachetQuantiteArray.sort((s1, s2) -> Double.compare(s2.getQuantite(), s1.getQuantite()));
            int[] listeSachetNombre=new int[sachetQuantiteArray.size()];
            for(int i=0;i<sachetQuantiteArray.size();i++){
                listeSachetNombre[i]=sachetQuantiteArray.get(i).getNombreSachet();
            }

            file.println("         Sachets: "+ Arrays.toString(listeSachetNombre));

            ArrayList<Pot> potQuantiteArray = new ArrayList<>(listePot);
            potQuantiteArray.sort((s1, s2) -> Double.compare(s2.getQuantite(), s1.getQuantite()));
            int[] listePotNombre=new int[potQuantiteArray.size()];
            for(int i=0;i<potQuantiteArray.size();i++){
                listePotNombre[i]=potQuantiteArray.get(i).getNombrePot();
            }

            file.println("         Pots: "+ Arrays.toString(listePotNombre));
            file.close();

        }
        catch (FileNotFoundException e){
            System.err.println("File not found");
        }catch (Exception e){
            System.out.println("Erreur");

        }
    }
    public void addToLivraison(){
        try{
            try{
                PrintWriter file=new PrintWriter(new FileOutputStream("fichiers/livraisons.txt",true));
                file.println(idTransaction+" | "+idClient+" | "+BaseDonnee.getHashMapClient().get(idClient).getNom());
                file.close();

            }
            catch (FileNotFoundException e){
                System.err.println("File not found");
            }catch (Exception e){
                System.out.println("Erreur");

            }


        } catch (Exception e){
            System.out.println("Erreur");

        }
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
