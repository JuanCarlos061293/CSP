package bumda.reparador;

import model.GrupoClase;
import model.GrupoColor;
import model.Individuo;

import java.util.List;

/**
 * AbstractProduct(AbstractFactory Pattern). Declares an interface for a type of product object.
 *
 * Product (Factory Method Pattern). Defines the interface of objects the factory method creates.
 */
public interface Reparador {
  Individuo repararIndividuo(Individuo individuo, List<GrupoClase> clases);
  Individuo repararIndividuoC(Individuo individuo, List<GrupoColor> colores);
}//-->
