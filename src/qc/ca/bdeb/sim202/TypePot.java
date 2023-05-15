package qc.ca.bdeb.sim202;

public enum TypePot {
    MINUSCULE,
    PETIT,
    GRAND,
    EXAGERE;

    public String getInfoPot(int number){
        switch (this){
            case MINUSCULE -> {
                if(number==0){
                    return " minuscule pot de 100 ml ";
                }else if(number==1){
                    return "0.1";
                }else if(number==2){
                    return "0.3";
                }

            }
            case PETIT -> {
                if(number==0){
                    return " petit pot de 500 ml ";
                }else if(number==1){
                    return "0.5";
                }else if(number==2){
                    return "0.5";
                }
            }
            case GRAND -> {
                if (number==0){
                    return " grand pot de 2 L ";
                }else if(number==1){
                    return "2";
                }else if(number==2){
                    return "0.7";
                }

            }
            case EXAGERE -> {
                if(number==0){
                    return " pot exagéré de 20 L ";
                }else if(number==1){
                    return "20";
                }else if(number==2){
                    return "5.75";
                }

            }


        }
        return "";
    }


}
