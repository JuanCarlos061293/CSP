package bumda.muestreo;

import bumda.reparador.Reparador;
import model.Individuo;
import model.Instancia;
import model.Item;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class muestreoUMDA implements Muestreo {
    @Override
    public void muestreoSimple(LinkedList<Individuo> population, int[] media, int[] sigma, List<Vehicle> autosSec, Integer
                                maxPop) {

    }

    @Override
    public void muestreoR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor,
                          int[] sigmaColor, Instancia instancia, Reparador reparador) {
        int clase, color;
        int popSize = population.size() * 2;
        Individuo ind = new Individuo();
        population.clear();
        for (int i = 0; i < popSize; i++) {
            for (int j = 0; j < mediaClase.length; j++) {
                clase = (new Random().nextInt() * mediaClase[j]) + sigmaClase[j];
                color = (new Random().nextInt() * mediaColor[j]) + sigmaColor[j];
                ind.getCromosomas().add(new Item(clase, color));
            }// for j
            population.add(ind);
        }// for i
    }// fin creaNuevosInd

    @Override
    public void muestreoSinR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor, int[] sigmaColor, Instancia instancia) {

    }
}// -->
