package qc.ca.bdeb.sim202;

public class ItemPanier {
    private int codeProduit;
    private double quantite;
    private String unite;


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

    public void uniteChangement(){

        switch (unite) {
            case "ml" -> {
                quantite /= 1000;
                unite = "L";
            }
            case "cl" -> {
                quantite /= 10;
                unite = "L";
            }
            case "g" -> {
                quantite /= 1000;
                unite = "kg";
            }
            case "mg" -> {
                quantite /= 1000000;
                unite = "kg";
            }
        }

    }


}
