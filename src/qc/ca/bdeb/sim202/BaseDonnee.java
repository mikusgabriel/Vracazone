package qc.ca.bdeb.sim202;

//A REVISER LES VERIFICATIONS MAIS TERMINER


import javax.swing.plaf.IconUIResource;
import java.io.*;
import java.util.*;

public class BaseDonnee {
    private static final HashMap<String,Client> hashMapClient=new HashMap<>();
    private static final HashMap<Integer,Produit> hashMapProduit=new HashMap<>();

    private static final LinkedHashMap<String,Panier> hashMapPanier=new LinkedHashMap<>();
    private static final String DATE_ORIGINALE = "2019-10-31 00:00 UTC";

    private static final long DATE_ORIGINALE_LONG=App.getDateLong(DATE_ORIGINALE);


    public static boolean validerClient(Client client) {
        if (client.getId().length() < 3) {
            return false;
        }
        return !hashMapClient.containsKey(client.getId());
    }

    public static boolean validerProduit(Produit produit) {
        String uniteSolide= "kg";
        String uniteLiquide=  "L";
        if(produit.isSolide()){
            if (!uniteSolide.equals(produit.getUnite())) {
                return false;
            }

        }else{
            if (!uniteLiquide.equals(produit.getUnite())){
                return false;
            }
        }

        if (produit.getPrixCoutant() >= produit.getPrixUnitaire() /1.2) {
            return false;
        }
        return !hashMapProduit.containsKey(produit.getCode());
    }

    public static String validerPanier(Panier panier) {
        List<String> listeUnite= List.of(new String[]{"kg","L"});
        /*BOUCLE FOR POUR TESTER SI YA DES ITEM AVEC LE MM CODE QUEUX
        SI OUI ON ADDITIONNE LEUR QTT ENSEMBLE ET ON POP LE DEUXIEME
        FAIRE JUSQUA TEMPS QUILS EN RESTE PLUS
         */

        boolean quantiteValide=true;
        String invalidCode="";
        boolean isUniteValid=true;
        boolean exists=true;
        double invalidQuantite=0;
        for (ItemPanier i:panier.getItems()){
            if(!hashMapProduit.containsKey(i.getCodeProduit())){

                exists=false;
                invalidCode= String.valueOf(i.getCodeProduit());
                break;
            }
            if(!listeUnite.contains(i.getUnite())){
                isUniteValid=false;
                break;

            }
        }

        if(exists&&isUniteValid){
            int temp=-1;
            for (ItemPanier itemPanier : panier.getItems()) {

                temp++;
                double quantite = itemPanier.getQuantite();
                for (ItemPanier itemPanier1 : panier.getItems()) {
                    if (itemPanier!=itemPanier1 && itemPanier.getCodeProduit() == itemPanier1.getCodeProduit()) {
                        quantite += itemPanier1.getQuantite();
                        panier.getItems().get(temp).setQuantite(quantite);
                        itemPanier1.setQuantite(0.000001);

                    }
                    if (hashMapProduit.get(itemPanier.getCodeProduit()).getQuantiteMax() < quantite || itemPanier1.getQuantite() <= 0) {
                        quantiteValide = false;
                        invalidCode += ("" + itemPanier1.getCodeProduit());
                        invalidQuantite=itemPanier1.getQuantite();
                        break;
                    }

                }


            }

        }


        if(panier.getNombreProduits()<=0) {
            return (panier.getIdTransaction()+" Aucun produit commandé: "+panier.getNombreProduits());
        }
        else if(hashMapPanier.containsKey(panier.getIdTransaction())) {
            return (panier.getIdTransaction()+" Associe à un panier déja traité");
        }
        else if(panier.getIdTransaction().length()!=11|| !panier.getIdTransaction().startsWith("VAZ")){
            return panier.getIdTransaction()+" Identifiant de transaction invalide "+panier.getIdTransaction();
        }
        else if(!hashMapClient.containsKey(panier.getIdClient())){
            return panier.getIdTransaction()+" Associé à un client inexistant "+ panier.getIdClient();
        }
        else if(panier.getIdClient().length()!=10|| !panier.getIdClient().startsWith("CL")){
            return panier.getIdTransaction()+" Identifiant de client invalide " + panier.getIdClient();
        }
        else if(!exists){
            return panier.getIdTransaction()+" Produit invalide (produit "+invalidCode+" )";
        }
        else if(!isUniteValid){
            return panier.getIdTransaction()+" Unité de poids du produit invalide";
        }
        else if (!quantiteValide) {
            return panier.getIdTransaction()+" Quantité non autorisée (produit "+invalidCode+" quantite= "+invalidQuantite+" )";
        }
        else if(panier.getDate()<DATE_ORIGINALE_LONG||App.getDateActuel()<panier.getDate()){
            return "Date invalide "+ App.getDateString(hashMapPanier.get(panier.getIdTransaction()).getDate());
        }
        else{
            return null;

        }

    }



    //Methodes qui load les donnees
    public static void loadClients(String fileName) throws IOException, ClassNotFoundException {
        try (DataInputStream lecteur = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Client client= new Client(lecteur.readUTF(),lecteur.readUTF());
                    if(validerClient(client)){
                        hashMapClient.put(client.getId(),client);
                    }

                } catch (EOFException e) {

                    break; //fin du fichier
                }
            }
        }  catch (FileNotFoundException e){
            System.err.println("Le fichier n'a pas été trouvé!");
        } catch (IOException e){
            System.out.println("Erreur");
        }


    }

    public static void loadProduits(String fileName) throws IOException, ClassNotFoundException {
        try (DataInputStream lecteur = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Produit produit=new Produit(
                            lecteur.readInt(),
                            lecteur.readUTF(),
                            lecteur.readBoolean(),
                            lecteur.readBoolean(),
                            lecteur.readDouble(),
                            lecteur.readDouble(),
                            lecteur.readUTF(),
                            lecteur.readDouble());
                    if(validerProduit(produit)){
                        hashMapProduit.put(produit.getCode(),produit);
                    }

                } catch (EOFException e) {
                    break; //fin du fichier
                }
            }
        } catch (FileNotFoundException e){
            System.err.println("Le fichier n'a pas été trouvé!");
        } catch (IOException e){
            System.out.println("Erreur");
        }
    }

    public static void loadPaniers(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<String> listeErreurs=new ArrayList<>();
        listeErreurs.add("PANIERS REJETES =====================================================================");
        listeErreurs.add("ID_TRANSACTION RAISON_DU_REJET");
        try (DataInputStream lecteur = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    ArrayList<ItemPanier> itemPaniers=new ArrayList<>();
                    String idTransaction= lecteur.readUTF();
                    String idClient=lecteur.readUTF();
                    long date=lecteur.readLong();
                    int nombreProduits= lecteur.readInt();



                    for(int i=0;i<nombreProduits;i++){
                        int idItemTransaction=lecteur.readInt();
                        double quantite=lecteur.readDouble();
                        String unite=lecteur.readUTF();
                        itemPaniers.add(new ItemPanier(idItemTransaction,quantite,unite));

                    }

                    Panier panier=new Panier(idTransaction, idClient, date, nombreProduits, itemPaniers);


                    String constatPanier=validerPanier(panier);
                    if(constatPanier==null){
                        hashMapPanier.put(idTransaction,panier);
                    }else{
                        listeErreurs.add(constatPanier);

                    }



                } catch (EOFException e) {
                    break; //fin du fichier
                }
            }
            listeErreurs.add("=====================================================================================");

        } catch (FileNotFoundException e){
            System.err.println("Le fichier n'a pas été trouvé!");
        } catch (IOException e){
            System.err.println("Erreur");
        }



        for(String s:listeErreurs){
            System.out.println(s);
        }
    }

    public static HashMap<String, Client> getHashMapClient() {
        return hashMapClient;
    }

    public static HashMap<Integer, Produit> getHashMapProduit() {
        return hashMapProduit;
    }

    public static HashMap<String, Panier> getHashMapPanier() {
        return hashMapPanier;
    }


}
