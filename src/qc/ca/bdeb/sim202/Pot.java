package qc.ca.bdeb.sim202;

public class Pot {
    private final int nombrePot;
    private double capaciteMaximale;
    private final int codeProduit;
    private TypePot type;
    private double quantite=0;

    private boolean isFerme=false;



    Pot(int codeProduit){
        this.nombrePot=valeurNombrePot;
        valeurNombrePot++;
        this.codeProduit=codeProduit;
    }

    public double verserLiquide(double quantite){
        if(!isFerme){
            if(quantite>=Double.parseDouble(TypePot.EXAGERE.getInfoPot(1))/2){
                type=TypePot.EXAGERE;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypePot.GRAND.getInfoPot(1))/2){
                type=TypePot.GRAND;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypePot.PETIT.getInfoPot(1))/2){
                type=TypePot.PETIT;
                quantite=calculator(type,quantite);
            }else{
                type=TypePot.MINUSCULE;
                quantite=calculator(type,quantite);

            }


            System.out.println("Verser "+this.quantite+" "+BaseDonnee.getHashMapProduit().get(codeProduit).getUnite()+" de "+
                    BaseDonnee.getHashMapProduit().get(codeProduit).getDescription()+" dans"+type.getInfoPot(0)+"de capacite "+" (pot "+nombrePot+")");

        }else{
            System.out.println("Le pot est déja vissé");
        }
        return quantite;


    }

    private double calculator(TypePot type, double quantite){
        capaciteMaximale= Double.parseDouble(type.getInfoPot(1));
        if(quantite>capaciteMaximale){
            quantite-=capaciteMaximale;
            this.quantite=capaciteMaximale;
        }else{
            this.quantite=Math.round(quantite * 100.0) / 100.0;
            quantite=0;
        }
        return quantite;
    }

    public void visser(){
        System.out.println("Visser couvercle pot "+nombrePot);
        isFerme=true;
    }

    public double getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public TypePot getType() {
        return type;
    }

    public double getQuantite() {
        return quantite;
    }

    public int getNombrePot() {
        return nombrePot;
    }

    private static int valeurNombrePot=1;

    public static int getValeurNombrePot() {
        return valeurNombrePot;
    }
}
