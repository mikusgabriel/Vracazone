package qc.ca.bdeb.sim202;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



//TRY CATCH
//VERIFICATION PANIER
public class BaseDonnee {
    private static HashMap<String,Client> hashMapClient=new HashMap<>();
    private static HashMap<Integer,Produit> hashMapProduit=new HashMap<>();

    private static HashMap<String,Panier> hashMapPanier=new HashMap<>();

    public static boolean validerClient(Client client) {
        if (client.getId().length() < 3) {
            return false;
        }
        return !hashMapClient.containsKey(client.getId());
    }

    public static boolean validerProduit(Produit produit) {
        List<String> listeUniteSolide= List.of(new String[]{"mg", "g", "kg"});
        List<String> listeUniteLiquide= List.of(new String[]{"ml", "cl", "L"});
        if(produit.isSolide()){
            if (!listeUniteSolide.contains(produit.getUnite())) {
                return false;
            }

        }else{
            if (!listeUniteLiquide.contains(produit.getUnite())){
                return false;
            }
        }

        if (produit.getPrixCoutant() >= produit.getPrixUnitaire() * 0.8) {
            return false;
        }
        return !hashMapProduit.containsKey(produit.getCode());
    }

    public static String validerPanier(Panier panier) {
        System.out.println(panier.getDate());
        System.out.println(panier.getItems());
        System.out.println(panier.getIdClient());
        System.out.println(panier.getIdTransaction());
        List<String> listeUnite= List.of(new String[]{"mg", "g", "kg","ml","cl","L"});
        // Vérifie les conditions d'invalidité pour les paniers
        boolean isAlone=true;
        boolean isUniteValid=true;
        boolean isQuantite=true;
        int invalidCode=0;
        for (ItemPanier i:panier.getItems()){
            if(!hashMapProduit.containsKey(i.getCodeProduit())){
                isAlone=false;
                invalidCode=i.getCodeProduit();
            }
            if(!listeUnite.contains(i.getUnite())){
                isUniteValid=false;

            }if(hashMapProduit.get(i.getCodeProduit()).getQuantiteMax()<i.getQuantite()){
                isQuantite=false;
                invalidCode=i.getCodeProduit();
            }
        }
        if(panier.getNombreProduits()<=0) {
            return (panier.getIdTransaction()+"Aucun produit commandé: "+panier.getNombreProduits());
        }
        else if(hashMapPanier.containsKey(panier.getIdTransaction())) {
            return ("Associe à un panier déja traité");

        }else if(panier.getIdTransaction().length()!=11|| panier.getIdTransaction().substring(0,3).equals("VAZ")){
            return "Identifiant de transaction invalide "+panier.getIdTransaction();
        }else if(!hashMapClient.containsKey(panier.getIdClient())){
            return "Associé à un client inexsistant "+ panier.getIdClient();

        }else if(panier.getIdTransaction().length()!=10|| panier.getIdTransaction().substring(0,2).equals("CL")){
            return "Identifiant de client invalide" + panier.getIdClient();
        }
        /*
        else if(){
            DATE
            "Date et heure invalide"
            }
         */

        else if(!isAlone){
            return "Produit invalide"+invalidCode;
        }
        else if(!isUniteValid){
            return "Unité de poids du produit invalide";
        } else if (!isQuantite) {
            return "Quantité non autorisée (produit "+invalidCode+" )";
        }else{
            return "";

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
        ArrayList<ItemPanier> itemPaniers=new ArrayList<>()
;        try (DataInputStream lecteur = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    String idTransaction= lecteur.readUTF();
                    String idClient=lecteur.readUTF();
                    long date=lecteur.readLong();
                    int nombreProduits= lecteur.readInt();


                    for(int i=0;i<nombreProduits;i++){
                        int idItemTransaction=lecteur.readInt();
                        ItemPanier itemPanier=new ItemPanier(idItemTransaction,lecteur.readDouble(),lecteur.readUTF());
                        itemPaniers.add(itemPanier);
                    }

                    Panier panier=new Panier(idTransaction, idClient, date, nombreProduits, itemPaniers);
                    hashMapPanier.put(idTransaction,panier);

                    String constatPanier=validerPanier(panier);
                    if(constatPanier==null){
                        hashMapPanier.put(idTransaction,panier);
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
