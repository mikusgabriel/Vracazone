package qc.ca.bdeb.sim202;

public enum TypeSachet {
    LILLIPUTIEN,
    MINUSCULE,
    PETIT,
    MOYEN,
    GRAND,
    TRES_GRAND,
    GARGANTUESQUE;

    public String getInfoSachet(int number){
        switch (this){

            case LILLIPUTIEN -> {
                if(number==0){
                    return " sachet liliputien de 30 g ";
                }else if(number==1){
                    return "0.03";
                }else if(number==2){
                    return "0.02";
                }

            }
            case MINUSCULE -> {
                if(number==0){
                    return " sachet minuscule de 100 g ";
                }else if(number==1){
                    return "0.1";
                }else if(number==2){
                    return "0.15";
                }

            }
            case PETIT -> {
                if(number==0){
                    return " petit sachet de 500 g ";

                }else if(number==1){
                    return "0.5";
                }else if(number==2){
                    return "0.25";
                }

            }
            case MOYEN -> {
                if(number==0){
                    return " sachet moyen de 2 kg ";
                }else if(number==1){
                    return "2";
                }else if(number==2){
                    return "0.75";
                }

            }
            case GRAND -> {
                if(number==0){
                    return " grand sachet de 20 kg ";
                }else if(number==1){
                    return "20";
                }else if(number==2){
                    return "1.25";
                }

            }
            case TRES_GRAND -> {
                if(number==0){
                    return " tres grand sachet de 100 kg ";
                }else if(number==1){
                    return "100";
                }else if(number==2){
                    return "5.5";
                }

            }
            case GARGANTUESQUE -> {
                if(number==0){
                    return " sachet gargantuesque de 500 kg ";
                }else if(number==1){
                    return "500";
                }else if(number==2){
                    return "15.75";
                }

            }


        }
        return " ";
    }
}
