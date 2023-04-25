package qc.ca.bdeb.sim202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BaseDonnee {

    public static ArrayList<Client> loadClients(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Panier>) <Client> clients = (ArrayList<Panier>) <Client>) ois.readObject();
            clients.removeIf(client -> !validerClient(client, clients));
            return clients;
        }
    }

    public static ArrayList<Panier>) <Produit> loadProduits(String fileName) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Panier>) <Produit> produits = (ArrayList<Panier>) <Produit>) ois.readObject();
            produits.removeIf(produit -> !validerProduit(produit, produits));
            return produits;
        }
    }

    public static ArrayList<Panier> loadPaniers(String fileName, ArrayList<qc.ca.bdeb.sim202.Panier>) <Client> clients, ArrayList<Panier>) <Produit> produits) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            ArrayList<Panier>) <Panier> paniers = (ArrayList<Panier>) <Panier>) ois.readObject();
            paniers.removeIf(panier -> !validerPanier(panier, clients, produits, paniers));
            return paniers;
        }
    }


}
