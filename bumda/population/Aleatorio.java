package bumda.population;

import model.Individuo;
import model.Item;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Aleatorio implements Population {
  private LinkedList<Individuo> population;

  public Aleatorio() { this.population = new LinkedList<>();  }

  @Override
  public void crearPoblacion(int tamPob, List<Vehicle> vehiculos, Long seed) {
    List<Integer> indices;
    Individuo ind;
    population = new LinkedList<>();
    for (int i = 0; i < tamPob; i++) {
      indices = getIndices(vehiculos.size(), seed + i);
      ind = new Individuo();
      for (Integer in : indices){
        ind.getCromosomas().add(new Item(vehiculos.get(in - 1).getClase(), vehiculos.get(in - 1).getColor()));
      }// for ind
      ind.setPositions(indices);
      this.population.add(ind);
    }// for i
  }// fin crearPoblacion

  @Override
  public LinkedList<Individuo> getPopulation() {
    return population;
  }

  @Override
  public void setPopulation(LinkedList<Individuo> population) {
    this.population = population;
  }

  private List<Integer> getIndices(int size, Long seed){
    List<Integer> indices = new LinkedList<>();
    Random rnd = (seed == null) ? new Random() : new Random(seed);
    for (int i = 0; i < size; i++) {
      int posible = rnd.nextInt(size);
      if(indices.contains(posible+1))
        i--;
      else
        indices.add(posible+1);
    }// for
    return indices;
  }// fin getIndices

}//-->
