package qc.ca.bdeb.sim202;

public class ItemPanier extends UniteChangement{
    private int codeProduit;


    public ItemPanier(int codeProduit, double quantite, String unite) {
        this.codeProduit = codeProduit;
        this.quantite = quantite;
        this.unite = unite;
        uniteChangement();
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public double getQuantite() {
        return quantite;
    }

    public String getUnite() {
        return unite;
    }


}
