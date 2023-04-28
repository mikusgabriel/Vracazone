package qc.ca.bdeb.sim202;

public class Produit {
    private int code;
    private String description;
    private boolean alimentaire;
    private boolean solide;
    private double prixCoutant;
    private double prixUnitaire;
    private String unite;
    private double quantiteMax;

    public Produit(int code, String description, boolean alimentaire, boolean solide, double prixCoutant, double prixUnitaire, String unite, double quantiteMax) {
        this.code = code;
        this.description = description;
        this.alimentaire = alimentaire;
        this.solide = solide;
        this.prixCoutant = prixCoutant;
        this.prixUnitaire = prixUnitaire;
        this.unite = unite;
        this.quantiteMax = quantiteMax;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", alimentaire=" + alimentaire +
                ", solide=" + solide +
                ", prix_coutant=" + prixCoutant +
                ", prix_unitaire=" + prixUnitaire +
                ", unite='" + unite + '\'' +
                ", quantite_max=" + quantiteMax +
                '}';
    }

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

    public double getPrixCoutant() {
        return prixCoutant;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public String getUnite() {
        return unite;
    }

    public double getQuantiteMax() {
        return quantiteMax;
    }


}
