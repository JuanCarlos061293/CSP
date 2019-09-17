package medal.selection;

import model.Individuo;

import java.util.Collections;
import java.util.LinkedList;

public class Truncation implements Selection {
    @Override
    public LinkedList<Individuo> selection(LinkedList<Individuo> population, Integer nSelect) {
        Collections.sort(population);
        for (int i = 0; i < nSelect; i++) {
            population.removeLast();
        }
        return population;
    }// selection
}// -->
