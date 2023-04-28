package qc.ca.bdeb.sim202;

public class ItemPanier {
    private int codeProduit;
    private double quantite;
    private String unite;

    public ItemPanier(int codeProduit, double quantite, String unite) {
        this.codeProduit = codeProduit;
        this.quantite = quantite;
        this.unite = unite;
    }
}
