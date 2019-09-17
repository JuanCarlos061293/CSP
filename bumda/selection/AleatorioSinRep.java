package bumda.selection;

import model.Individuo;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AleatorioSinRep implements Selection {

  @Override
  public List<Individuo> seleccionar(List<Individuo> population) {
    List<Individuo> lista = new LinkedList<>();

    for (int i = 0; i < 3; i++) {
      int item = new Random().nextInt(population.size());
      lista.add(population.get(item));
      population.remove(item);
    }// fin for

    return lista;
  }

  @Override
  public List<Individuo> seleccion(LinkedList<Individuo> population, int nSelect) {
    return null;
  }

}//-->
