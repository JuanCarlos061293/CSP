package medal.population;

import model.Individuo;
import model.Item;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Aleatorio implements Population {
    private LinkedList<Individuo> population;
    private List<Integer> indices;
    @Override
    public void crearPopulation(int popSize, List<Vehicle> vehiculos, Long seed) {
        Individuo ind;
        population = new LinkedList<>();
        for (int i = 0; i < popSize; i++) {
            indices = getIndices(vehiculos.size(), seed + i);
            ind = new Individuo();
            for (Integer in : indices){
                ind.getCromosomas().add(new Item(vehiculos.get(in).getClase(), vehiculos.get(in).getColor()));
            }// for ind
            ind.setPositions(indices);
            this.population.add(ind);
        }// for i
    }// fin crearPopulation

    @Override
    public LinkedList<Individuo> getPopulation() { return this.population; }

    @Override
    public void setPopulation(LinkedList<Individuo> population) { this.population = population; }

    private List<Integer> getIndices(int size, Long seed){
        indices = new LinkedList<>();
        Random rnd = (seed == null) ? new Random() : new Random(seed);
        for (int i = 0; i < size; i++) {
            int posible = rnd.nextInt(size);
            if(indices.contains(posible))
                i--;
            else
                indices.add(posible);
        }// for
        return indices;
    }// fin getIndices

}// -->
