package qc.ca.bdeb.sim202;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            BaseDonnee.loadClients("fichiers/clients.dat");
            BaseDonnee.loadProduits("fichiers/produits.dat");
            BaseDonnee.loadPaniers("fichiers/paniers.bin", clients, produits);


            // Traitez les données, effectuez des validations supplémentaires, calculez les factures, etc.


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

