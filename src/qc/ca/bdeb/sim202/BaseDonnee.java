package qc.ca.bdeb.sim202;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



//TRY CATCH
//VERIFICATION PANIER
public class BaseDonnee {
    public static HashMap<String,Client> hashMapClient=new HashMap<>();
    public static HashMap<Integer,Produit> hashMapProduit=new HashMap<>();

    public static HashMap<String,Panier> hashMapPanier=new HashMap<>();

    public static HashMap<Integer,ItemPanier> hashMapItemPanier=new HashMap<>();
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

    public static boolean validerPanier(Panier panier) {
        System.out.println(panier.getDate());
        System.out.println(panier.getItems());
        System.out.println(panier.getIdClient());
        System.out.println(panier.getIdTransaction());
        // Vérifie les conditions d'invalidité pour les paniers
        if(panier.getNombreProduits()>0) {
            return false;
        }
        return true; // Retourne true si toutes les validations passent
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
        }
    }

    public static void loadPaniers(String fileName) throws IOException, ClassNotFoundException {
        try (DataInputStream lecteur = new DataInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    String idTransaction= lecteur.readUTF();
                    String idClient=lecteur.readUTF();
                    long date=lecteur.readLong();
                    int nombreProduits= lecteur.readInt();


                    for(int i=0;i<nombreProduits;i++){
                        int idItemTransaction=lecteur.readInt();
                        ItemPanier itemPanier=new ItemPanier(idItemTransaction,lecteur.readDouble(),lecteur.readUTF());
                        hashMapItemPanier.put(idItemTransaction,itemPanier);
                    }

                    Panier panier=new Panier(idTransaction, idClient, date, nombreProduits, hashMapItemPanier);
                    hashMapPanier.put(idTransaction,panier);
                    if(validerPanier(panier)){
                        hashMapPanier.put(idTransaction,panier);
                    }



                } catch (EOFException e) {
                    break; //fin du fichier
                }
            }

        }
    }
}
