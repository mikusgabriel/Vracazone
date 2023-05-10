package qc.ca.bdeb.sim202;

public enum TypePot {
    MINUSCULE,
    PETIT,
    GRAND,
    EXAGERE;

    public String getNomPot(){
        switch (this){
            case MINUSCULE -> {
                return "minuscule pot";
            }
            case PETIT -> {
                return "petit pot";
            }
            case GRAND -> {
                return "grand pot";
            }
            case EXAGERE -> {
                return "pot exagéré";
            }


        }
        return null;
    }


}
