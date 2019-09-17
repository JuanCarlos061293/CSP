package bumda.population;

import model.Individuo;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * AbstractProduct(AbstractFactory Pattern). Declares an interface for a type of product object.
 *
 * Product (Factory Method Pattern). Defines the interface of objects the factory method creates.
 */
public interface Population {
  void crearPoblacion(int tamPob, List<Vehicle> vehiculos, Long seed);
  LinkedList<Individuo> getPopulation();
  void setPopulation(LinkedList<Individuo> population);
}//-->
