package qc.ca.bdeb.sim202;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BaseDonnee {
    public static HashMap<String,Client> hashMapClient=new HashMap<>();
    public static boolean validerClient(Client client, HashMap clients) {
        if (client.getId().length() < 3) {
            return false;
        }

        for (String id : clients.keySet()) {
            if (c.getId().equals(client.getId())) {
                return false;
            }
        }

        return true;
    }

    public static boolean validerProduit(Produit produit, ArrayList<Produit> produits) {
        if (!Arrays.asList("mg", "g", "kg", "ml", "cl", "L").contains(produit.getUnite())) {
            return false;
        }

        if (produit.getPrix_coutant() >= produit.getPrix_unitaire() * 0.8) {
            return false;
        }

        for (Produit p : produits) {
            if (p.getCode() == produit.getCode()) {
                return false;
            }
        }

        return true;
    }

    public static boolean validerPanier(Panier panier, ArrayList<Client> clients, ArrayList<Produit> produits, ArrayList<Panier> paniers) {
        System.out.println(panier.getDate());
        System.out.println(panier.getItems());
        System.out.println(panier.getId_client());
        System.out.println(panier.getId_transaction());
        // Vérifie les conditions d'invalidité pour les paniers
        if(panier.getNombre_produits()>0){
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
                    validerClient(client, hashMapClient);
                    hashMapClient.put(client.getId(),client);
                } catch (EOFException e) {
                    break; //fin du fichier
                }
            }
        }


    }

    public static void loadProduits(String fileName) throws IOException, ClassNotFoundException {
        ArrayList<Produit> produits = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Produit produit = (Produit) ois.readObject();
                    produits.add(produit);
                } catch (EOFException e) {
                    break; //fin du fichier
                }
            }
        }
        produits.removeIf(produit -> !validerProduit(produit, produits));
        return produits;

    }

    public static ArrayList<Panier> loadPaniers(String fileName, ArrayList<Client> clients, ArrayList<Produit> produits) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Panier> paniers = (ArrayList<Panier>) ois.readObject();
            paniers.removeIf(panier -> !validerPanier(panier, clients, produits, paniers));
            return paniers;
        }
    }
}
