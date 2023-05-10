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

    public double verserSolide(double quantite){
        if(!isFerme){
            if(quantite>Double.parseDouble(TypePot.EXAGERE.getInfoPot(false))/2){
                type=TypePot.EXAGERE;
                quantite=calculator(type,quantite);
            }else if(quantite>Double.parseDouble(TypePot.GRAND.getInfoPot(false))/2){
                type=TypePot.GRAND;
                quantite=calculator(type,quantite);
            }else if(quantite>Double.parseDouble(TypePot.PETIT.getInfoPot(false))/2){
                type=TypePot.PETIT;
                quantite=calculator(type,quantite);
            }else{
                type=TypePot.MINUSCULE;
                quantite=calculator(type,quantite);

            }


            System.out.println("Verser "+this.quantite+" "+BaseDonnee.getHashMapProduit().get(codeProduit).getUnite()+" de "+
                    BaseDonnee.getHashMapProduit().get(codeProduit).getDescription()+" dans"+type.getInfoPot(true)+"de capacite "+" (pot "+nombrePot+")");

        }else{
            System.out.println("Le pot est déja vissé");
        }
        return quantite;


    }

    private double calculator(TypePot type, double quantite){
        capaciteMaximale= Double.parseDouble(type.getInfoPot(false));
        if(quantite>capaciteMaximale){
            quantite-=capaciteMaximale;
            this.quantite=capaciteMaximale;
        }else{
            this.quantite=quantite;
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

    private static int valeurNombrePot=1;
}
