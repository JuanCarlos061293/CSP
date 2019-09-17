package bumda;

import bumda.distribution.ConcreteCreatorDistribution;
import bumda.distribution.Distribution;
import bumda.muestreo.ConcreteCreatorMuestreo;
import bumda.muestreo.Muestreo;
import bumda.population.ConcreteCreatorPopulation;
import bumda.population.Population;
import bumda.reparador.ConcreteCreatorReparador;
import bumda.reparador.Reparador;
import bumda.selection.ConcreteCreatorSelection;
import bumda.selection.Selection;


public class ConcreteCreatorEDAFactory implements EDAFactory {
  private static ConcreteCreatorEDAFactory ourInstance = new ConcreteCreatorEDAFactory();

  // Constructor Method
  private ConcreteCreatorEDAFactory() {
  }

  // Static Method
  public static ConcreteCreatorEDAFactory getInstance() {
    return ourInstance;
  }

  // Override Method
  @Override
  public Population createPopulation(String population) {
    return ConcreteCreatorPopulation.getInstance(population);
  }

  @Override
  public Muestreo choiceMuestreo(String muestreo) {
    return ConcreteCreatorMuestreo.getInstance(muestreo);
  }

  @Override
  public Distribution choiceDistibution(String distribution) { return ConcreteCreatorDistribution.getInstance(distribution); }

  @Override
  public Selection choiceSelection(String selection) {
    return ConcreteCreatorSelection.getInstance(selection);
  }

  @Override
  public Reparador choiceReparator(String reparador) { return ConcreteCreatorReparador.getInstance(reparador); }

}//-->