package bumda.selection;

import model.Individuo;

import java.util.LinkedList;
import java.util.List;

public class Truncation implements Selection  {

    @Override
    public List<Individuo> seleccionar(List<Individuo> population) {
        return null;
    }

    @Override
    public List<Individuo> seleccion(LinkedList<Individuo> population, int nSelect) {
        //Collections.sort(population);
        if (population.size() >= nSelect + 1) {
            population.subList(nSelect - 1, population.size() - 1).clear();
        }

        return population;
    }// fin seleccion


}// -->
