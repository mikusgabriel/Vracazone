package qc.ca.bdeb.sim202;

public class Sachet {
    private static int valeurNombreSachet=1;
    private final int nombreSachet;
    private double capaciteMaximale;
    private final int codeProduit;
    private TypeSachet type;
    private double quantite=0;

    private boolean isFerme=false;



    Sachet(int codeProduit){

        this.nombreSachet=valeurNombreSachet;
        valeurNombreSachet++;
        this.codeProduit=codeProduit;


    }

    public double verserSolide(double quantite){
        if(!isFerme){
            if(quantite>=Double.parseDouble(TypeSachet.GARGANTUESQUE.getInfoSachet(false))/2){
                type=TypeSachet.GARGANTUESQUE;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypeSachet.TRES_GRAND.getInfoSachet(false))/2){
                type=TypeSachet.TRES_GRAND;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypeSachet.GRAND.getInfoSachet(false))/2){
                type=TypeSachet.GRAND;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypeSachet.MOYEN.getInfoSachet(false))/2){
                type=TypeSachet.MOYEN;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypeSachet.PETIT.getInfoSachet(false))/2){
                type=TypeSachet.PETIT;
                quantite=calculator(type,quantite);
            }else if(quantite>=Double.parseDouble(TypeSachet.MINUSCULE.getInfoSachet(false))/2){
                type=TypeSachet.MINUSCULE;
                quantite=calculator(type,quantite);

            }else{
                type=TypeSachet.LILLIPUTIEN;
                quantite=calculator(type,quantite);

            }


            System.out.println("Verser "+this.quantite+" "+BaseDonnee.getHashMapProduit().get(codeProduit).getUnite()+" de "+
                    BaseDonnee.getHashMapProduit().get(codeProduit).getDescription()+" dans"+type.getInfoSachet(true)+"de capacite "+" (sachet "+nombreSachet+")");

        }else{
            System.out.println("Le sachet est déja scellé");
        }
        return quantite;


    }

    private double calculator(TypeSachet type, double quantite){
        capaciteMaximale= Double.parseDouble(type.getInfoSachet(false));
        if(quantite>capaciteMaximale){
            quantite-=capaciteMaximale;
            this.quantite=capaciteMaximale;
        }else{
            this.quantite=Math.round(quantite * 100.0) / 100.0;
            quantite=0;
        }
        return quantite;
    }

    public void sceller(){
        System.out.println("Sceller sachet "+nombreSachet);
        isFerme=true;
    }

    public double getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public TypeSachet getType() {
        return type;
    }

    public double getQuantite() {
        return quantite;
    }


    public int getNombreSachet() {
        return nombreSachet;
    }
}

