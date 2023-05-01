package qc.ca.bdeb.sim202;

public class UniteChangement {
    protected double quantite;
    protected String unite;

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
