package bumda.selection;

import model.Individuo;

import java.util.LinkedList;
import java.util.List;

/**
 * AbstractProduct(AbstractFactory Pattern). Declares an interface for a type of product object.
 *
 * Product (Factory Method Pattern). Defines the interface of objects the factory method creates.
 */
public interface Selection {
  List<Individuo> seleccionar(List<Individuo> population);
  List<Individuo> seleccion(LinkedList<Individuo> population, int nSelect);
}//-->
