package qc.ca.bdeb.sim202;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            BaseDonnee.loadClients("fichiers/clients.dat");
            BaseDonnee.loadProduits("fichiers/produits.dat");
            BaseDonnee.loadPaniers("fichiers/paniers.bin");
            BaseDonnee.loadPaniers("fichiers/paniers1.bin");
            BaseDonnee.loadPaniers("fichiers/paniers2.bin");
            BaseDonnee.loadPaniers("fichiers/paniers3.bin");
            BaseDonnee.loadPaniers("fichiers/paniers4.bin");
            for (String s:BaseDonnee.hashMapClient.keySet())
                  {
                      System.out.println(s+" : "+ BaseDonnee.hashMapClient.get(s).getNom());
            }
            System.out.println(BaseDonnee.hashMapClient.get("CL00000001").getNom());
            System.out.println(BaseDonnee.hashMapProduit.get(1).toString());


            // Traitez les données, effectuez des validations supplémentaires, calculez les factures, etc.
            System.out.println(BaseDonnee.hashMapPanier.get("VAZ00000009").getIdClient());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

