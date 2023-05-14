package qc.ca.bdeb.sim202;



import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            BaseDonnee.loadClients("fichiers/clients.dat");
            BaseDonnee.loadProduits("fichiers/produits.dat");
            //BaseDonnee.loadPaniers("fichiers/paniers.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers1.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers2.bin");
            BaseDonnee.loadPaniers("fichiers/paniers3.bin");
            //BaseDonnee.loadPaniers("fichiers/paniers4.bin");

            try{
                BufferedWriter file=new BufferedWriter(new FileWriter("fichiers/facture.txt"));
                for(Panier p:BaseDonnee.getHashMapPanier().values()){
                    p.addFacturetoFile();
                }
                BufferedReader br=new BufferedReader(new FileReader("fichiers/facture.txt"));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }


            }catch (FileNotFoundException e){
                System.err.println("Fichier non trouve");
            }catch (Exception e){
                System.out.println("Erreur");
            }


            //deliveryPackages.sort(Comparator.comparing(DeliveryPackage::getDateOfTransaction));
            ArrayList<Colis> colisArrayList=new ArrayList<>();
            for(Panier p:BaseDonnee.getHashMapPanier().values()){
                ArrayList<Pot> potArrayList=new ArrayList<>();
                Stack<Sachet> sachetPile=new Stack<>();
                for(ItemPanier i:p.getItems()){
                    double nombreRestant=i.getQuantite();
                    while(nombreRestant>0)
                        if(!BaseDonnee.getHashMapProduit().get(i.getCodeProduit()).isSolide()){
                            Pot pot=new Pot(i.getCodeProduit());
                            nombreRestant=pot.verserLiquide(nombreRestant);
                            pot.visser();
                            potArrayList.add(pot);
                        }else{
                            Sachet sachet=new Sachet(i.getCodeProduit());
                            nombreRestant=sachet.verserSolide(nombreRestant);
                            sachet.sceller();
                            System.out.println(sachet.getQuantite());
                            sachetPile.add(sachet);
                        }
                }
                potArrayList.sort(Comparator.comparing(Pot::getCapaciteMaximale).reversed());
                sachetPile.sort(Comparator.comparing(Sachet::getQuantite));
                for(Sachet s:sachetPile){
                    System.out.println(s.getQuantite());
                }
                colisArrayList.add(new Colis(p.getIdTransaction(),p.getIdClient(),sachetPile,potArrayList, p.getDate()));
            }
            colisArrayList.sort(Comparator.comparing(Colis::getDateTransaction));
//fonctionne pas//
            for (Colis c:colisArrayList){
                ArrayList<Integer> potArrayListNombre=new ArrayList<>();
                ArrayList<Integer> sachetArrayListNombre=new ArrayList<>();
                System.out.println(c.getIdTransaction());
                for (Sachet s:c.getPileSachet()){
                    sachetArrayListNombre.add(s.getNombreSachet());


                }
                for (Pot p:c.getListePot()){
                    potArrayListNombre.add(p.getNombrePot());
                }
                System.out.println();
                System.out.println("Sachets:");
                
                System.out.println(sachetArrayListNombre);
                System.out.println();
                System.out.println("Pots:");
                System.out.println(potArrayListNombre);




            }









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

