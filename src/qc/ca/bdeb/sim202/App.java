package qc.ca.bdeb.sim202;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

public class App {
    public static void main(String[] args) {
        try {
            BaseDonnee.loadClients("fichiers/clients.dat");
            BaseDonnee.loadProduits("fichiers/produits.dat");
            BaseDonnee.loadPaniers("fichiers/paniers.bin");
            /*BaseDonnee.loadPaniers("fichiers/paniers1.bin");
            BaseDonnee.loadPaniers("fichiers/paniers2.bin");
            BaseDonnee.loadPaniers("fichiers/paniers3.bin");
            BaseDonnee.loadPaniers("fichiers/paniers4.bin");

             */
            /*for (String s:BaseDonnee.getHashMapClient().keySet())
                  {
                      System.out.println(s+" : "+ BaseDonnee.getHashMapClient().get(s).getNom());
            }
            System.out.println(BaseDonnee.getHashMapClient().get("CL00000001").getNom());
            System.out.println(BaseDonnee.getHashMapProduit().get(1).toString());
             */
            /*for (Panier p:BaseDonnee.getHashMapPanier().values()){
                p.addFacturetoFile();
            }

             */
            try{
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
            for ( ItemPanier i:BaseDonnee.getHashMapPanier().get("VAZ00000002").getItems()) {
                System.out.println(i.getQuantite());
                System.out.println(BaseDonnee.getHashMapProduit().get(i.getCodeProduit()));
            }

            Sachet sachet=new Sachet(BaseDonnee.getHashMapPanier().get("VAZ00000005").getItems().get(1).getCodeProduit());
            System.out.println(sachet.getType());
            System.out.println(sachet.getCapaciteMaximale());
            System.out.println(sachet.getQuantite());
            System.out.println(sachet.getCodeProduit());
            double restant=sachet.verserSolide(BaseDonnee.getHashMapPanier().get("VAZ00000005").getItems().get(1).getQuantite());
            System.out.println(  BaseDonnee.getHashMapPanier().get("VAZ00000005").getItems().get(1).getQuantite());
            System.out.println(sachet.getType());
            System.out.println(sachet.getCapaciteMaximale());
            System.out.println(sachet.getQuantite());
            System.out.println(sachet.getCodeProduit());
            System.out.println(restant);
            restant=sachet.verserSolide(restant);
            sachet.sceller();
            restant=sachet.verserSolide(restant);
            System.out.println(sachet.getType());
            System.out.println(sachet.getCapaciteMaximale());
            System.out.println(sachet.getQuantite());
            System.out.println(sachet.getCodeProduit());
            System.out.println(restant);



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

