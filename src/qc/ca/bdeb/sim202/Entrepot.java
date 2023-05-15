package qc.ca.bdeb.sim202;

import java.util.Queue;
import java.util.TreeMap;

public class Entrepot {

    private final Queue<Colis> colisListe;
    private final TreeMap<Integer,double[]> produitHashCout= new TreeMap<>();

    public Entrepot(Queue<Colis> colisListe) {
        this.colisListe = colisListe;
    }

    public void calculerBilan(){
        for(Colis c:colisListe){
            for (Pot p:c.getListePot()){
                if(!produitHashCout.containsKey(p.getCodeProduit())){
                    double[] doubles= {0.0,0.0,0.0,0.0};
                    produitHashCout.put(p.getCodeProduit(),doubles);
                }
                produitHashCout.get(p.getCodeProduit())[0]+=p.getQuantite();
                produitHashCout.get(p.getCodeProduit())[1]+=p.getQuantite();
                produitHashCout.get(p.getCodeProduit())[2]+=Double.parseDouble(p.getType().getInfoPot(2));

            }
            for(Sachet s:c.getPileSachet()){
                if(!produitHashCout.containsKey(s.getCodeProduit())){
                    double[] doubles= {0.0,0.0,0.0,0.0};
                    produitHashCout.put(s.getCodeProduit(),doubles);
                }
                produitHashCout.get(s.getCodeProduit())[0]+=s.getQuantite();
                produitHashCout.get(s.getCodeProduit())[1]+=s.getQuantite();
                produitHashCout.get(s.getCodeProduit())[2]+=Double.parseDouble(s.getType().getInfoSachet(2));

            }
        }
        for(Integer i:produitHashCout.keySet()){
            produitHashCout.get(i)[0]*=BaseDonnee.getHashMapProduit().get(i).getPrixUnitaire();
            produitHashCout.get(i)[1]*=BaseDonnee.getHashMapProduit().get(i).getPrixCoutant();

            produitHashCout.get(i)[3]= produitHashCout.get(i)[0]-produitHashCout.get(i)[1]-produitHashCout.get(i)[2];
        }


    }
    public void afficherBilan(){
            System.out.format("%-40s %-10s %-10s %-10s %-10s%n","PRODUITS","VENTES","COUTANT","EMBALLAGE","PROFITS");

            Double[] donneesfinales={0.0,0.0,0.0,0.0};
        for(Integer i:produitHashCout.keySet()){
            donneesfinales[0] += produitHashCout.get(i)[0];
            donneesfinales[1] += produitHashCout.get(i)[1];
            donneesfinales[2] += produitHashCout.get(i)[2];
            donneesfinales[3] += produitHashCout.get(i)[3];
            System.out.format("%-40s %-10.2f %-10.2f %-10.2f %-10.2f%n",BaseDonnee.getHashMapProduit().get(i).getDescription(),produitHashCout.get(i)[0],produitHashCout.get(i)[1],produitHashCout.get(i)[2],produitHashCout.get(i)[3]);

        }


        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.format("%-40s %-10.2f %-10.2f %-10.2f %-10.2f%n","TOTAL",donneesfinales[0],donneesfinales[1],donneesfinales[2],donneesfinales[3]);
    }

}
