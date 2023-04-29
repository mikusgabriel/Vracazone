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
            /*for (String s:BaseDonnee.getHashMapClient().keySet())
                  {
                      System.out.println(s+" : "+ BaseDonnee.getHashMapClient().get(s).getNom());
            }
            System.out.println(BaseDonnee.getHashMapClient().get("CL00000001").getNom());
            System.out.println(BaseDonnee.getHashMapProduit().get(1).toString());



             */

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

