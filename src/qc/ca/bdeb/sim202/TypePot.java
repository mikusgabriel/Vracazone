package qc.ca.bdeb.sim202;

public enum TypePot {
    MINUSCULE,
    PETIT,
    GRAND,
    EXAGERE;

    public String getInfoPot(boolean isString){
        switch (this){
            case MINUSCULE -> {
                if(isString){
                    return " minuscule pot de 100 ml ";
                }else{
                    return "0.1";
                }

            }
            case PETIT -> {
                if(isString){
                    return " petit pot de 500 ml ";
                }else{
                    return "0.5";
                }
            }
            case GRAND -> {
                if (isString){
                    return " grand pot de 2 L ";
                }else{
                    return "2";
                }

            }
            case EXAGERE -> {
                if(isString){
                    return " pot exagéré de 20 L ";
                }else{
                    return "20";
                }

            }


        }
        return "";
    }


}
