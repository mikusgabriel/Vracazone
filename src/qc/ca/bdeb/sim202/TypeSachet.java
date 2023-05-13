package qc.ca.bdeb.sim202;

public enum TypeSachet {
    LILLIPUTIEN,
    MINUSCULE,
    PETIT,
    MOYEN,
    GRAND,
    TRES_GRAND,
    GARGANTUESQUE;

    public String getInfoSachet(boolean isString){
        switch (this){

            case LILLIPUTIEN -> {
                if(isString){
                    return " sachet liliputien de 30 g ";
                }else{
                    return "0.03";
                }

            }
            case MINUSCULE -> {
                if(isString){
                    return " sachet minuscule de 100 g ";
                }else{
                    return "0.1";
                }

            }
            case PETIT -> {
                if(isString){
                    return " petit sachet de 500 g ";

                }else{
                    return "0.5";
                }

            }
            case MOYEN -> {
                if(isString){
                    return " sachet moyen de 2 kg ";
                }else{
                    return "2";
                }

            }
            case GRAND -> {
                if(isString){
                    return " grand sachet de 20 kg ";
                }else{
                    return "20";
                }

            }
            case TRES_GRAND -> {
                if(isString){
                    return " tres grand sachet de 100 kg ";
                }else{
                    return "100";
                }

            }
            case GARGANTUESQUE -> {
                if(isString){
                    return " sachet gargantuesque de 500 kg ";
                }else{
                    return "500";
                }

            }


        }
        return " ";
    }
}
