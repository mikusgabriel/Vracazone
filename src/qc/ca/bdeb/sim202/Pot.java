package qc.ca.bdeb.sim202;

public class Pot {
    private int nombrePot;
    private double capaciteMaximale;
    private int codeProduit;
    private TypePot type;
    private double quantite=0;

    private boolean isFerme=false;

    private boolean isMoitiePlein=false;


    Pot(double capaciteMaximale,int codeProduit,TypePot type){
        this.type=type;
        this.nombrePot=valeurNombrePot;
        valeurNombrePot++;
        this.capaciteMaximale=capaciteMaximale;
        this.codeProduit=codeProduit;

    }

    public void verserSolide(double quantite){
        this.quantite+=quantite;
        System.out.println("Verser "+quantite+" "+BaseDonnee.getHashMapProduit().get(codeProduit).getUnite()+" de "+ BaseDonnee.getHashMapProduit().get(codeProduit).getDescription()+" dans "+type.getNomPot() +" "+capaciteMaximale+" kg de capacite "+" (pot "+nombrePot+")");

    }


    private static int valeurNombrePot=1;
}
