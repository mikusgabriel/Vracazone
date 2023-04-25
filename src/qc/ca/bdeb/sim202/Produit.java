package qc.ca.bdeb.sim202;

public class Produit {
    private String description;
    private boolean alimentaire;
    private boolean solide;
    private double prix_coutant;
    private double prix_unitaire;
    private String unite;
    private double quantite_max;
    private int code;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAlimentaire() {
        return alimentaire;
    }

    public boolean isSolide() {
        return solide;
    }

    public double getPrix_coutant() {
        return prix_coutant;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public String getUnite() {
        return unite;
    }

    public double getQuantite_max() {
        return quantite_max;
    }


}
