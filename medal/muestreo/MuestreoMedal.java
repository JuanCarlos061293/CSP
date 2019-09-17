package medal.muestreo;

import model.Individuo;
import java.util.LinkedList;

public class MuestreoMedal implements Muestreo {
    @Override
    public void createNewInd(LinkedList<Individuo> population, int[][] freqMatrix) {

    } // fin createNewInd
    
    protected int discrete_roulette(int[] frequencies){
        double total = 0;
        for (int i = 0; i < frequencies.length; i++)
            total+=frequencies[i];

        double random = Math.random();
        double acumulator = 0;
        for (int i = 0; i < frequencies.length; i++)
        {
            if( random >= acumulator && random <=  acumulator + frequencies[i] / total)
                return (i+1);
            acumulator += frequencies[i] / total;
        }
        //Nunca debería ser alcanzada esta línea. En caso de que sucediera las frecuencias están en ceros y hay un error.
        return -1;
    }// fin discrete_roulette
    
}// -->
