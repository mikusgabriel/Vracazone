package qc.ca.bdeb.sim202;

public class Produit {
    private final int code;
    private final String description;
    private final boolean alimentaire;
    private final boolean solide;
    private final double prixCoutant;
    private final double prixUnitaire;
    private double quantiteMax;
    private String unite;


    public Produit(int code, String description, boolean alimentaire, boolean solide, double prixCoutant, double prixUnitaire, String unite, double quantiteMax) {
        this.code = code;
        this.description = description;
        this.alimentaire = alimentaire;
        this.solide = solide;
        this.prixCoutant = prixCoutant;
        this.prixUnitaire = prixUnitaire;
        this.unite = unite;
        this.quantiteMax = quantiteMax;
        uniteChangement();



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
    public double getTaxes(){
        if(!alimentaire){
            return (prixUnitaire*0.15);
        }else{
            return 0;
        }

    }
    public void uniteChangement(){

        switch (unite) {
            case "ml" -> {
                quantiteMax /= 1000;
                unite = "L";
            }
            case "cl" -> {
                quantiteMax /= 100;
                unite = "L";
            }
            case "g" -> {
                quantiteMax /= 1000;
                unite = "kg";
            }
            case "mg" -> {
                quantiteMax /= 1000000;
                unite = "kg";
            }
        }

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
