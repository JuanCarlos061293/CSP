package medal.population;

import model.Individuo;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;

public interface Population {

    void crearPopulation(int popSize, List<Vehicle> vehiculos, Long seed);
    LinkedList<Individuo> getPopulation();
    void setPopulation(LinkedList<Individuo> population);
}// -->
