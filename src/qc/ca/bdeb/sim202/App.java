package qc.ca.bdeb.sim202;



import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            BaseDonnee.loadClients("fichiers/clients.dat");
            BaseDonnee.loadProduits("fichiers/produits.dat");
            BaseDonnee.loadPaniers("fichiers/paniers.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers1.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers2.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers3.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers4.bin");

            try{
                BufferedWriter file=new BufferedWriter(new FileWriter("fichiers/facture.txt"));
                for(Panier p:BaseDonnee.getHashMapPanier().values()){
                    p.addFacturetoFile();
                }



            }catch (FileNotFoundException e){
                System.err.println("Fichier non trouve");
            }catch (Exception e){
                System.out.println("Erreur");
            }


            ArrayList<Colis> colisArrayList=new ArrayList<>();
            PrintWriter printWriter=new PrintWriter("fichiers/registre.txt");
            PrintWriter printWriter2=new PrintWriter("fichiers/livraisons.txt");
            ArrayList<Panier> arrayListPanierOrdreTemps=new ArrayList<>(BaseDonnee.getHashMapPanier().values());
            arrayListPanierOrdreTemps.sort(Comparator.comparing(Panier::getDate));
            for(Panier p:arrayListPanierOrdreTemps){
                Colis colis=new Colis(p.getIdTransaction(),p.getIdClient(), p.getDate());
                colis.emballer();
                colisArrayList.add(colis);
                colis.addToLivraison();
                colis.addToRegistre();
            }
            Queue<Colis> listeColis=new LinkedList<>(colisArrayList);
            Entrepot entrepot=new Entrepot(listeColis);
            entrepot.calculerBilan();
            entrepot.afficherBilan();









        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static long getDateLong(String string) {
        long dateLong = 0;
        Date date;
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
        formatDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = formatDate.parse(string);
            dateLong = date.getTime();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return dateLong;
    }
    public static String getDateString(long temps){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
        formatDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatDate.format(temps);

    }
    public  static long getDateActuel(){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm z");
        formatDate.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date maintenant = Date.from(Instant.now());
        return  getDateLong(formatDate.format(maintenant));
    }

}

