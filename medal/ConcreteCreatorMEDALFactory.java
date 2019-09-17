package medal;

import medal.distribution.ConcreteCreatorDistribution;
import medal.distribution.Distribution;
import medal.muestreo.ConcreteCreatorMuestreo;
import medal.muestreo.Muestreo;
import medal.population.ConcreteCreatorPopulation;
import medal.population.Population;
import medal.selection.ConcreteCreatorSelection;
import medal.selection.Selection;

public class ConcreteCreatorMEDALFactory implements MEDALFactory {

    private static ConcreteCreatorMEDALFactory ourInstance = new ConcreteCreatorMEDALFactory();

    public static ConcreteCreatorMEDALFactory getInstance(){
        return ourInstance;
    }

    @Override
    public Population createPopulation(String population) {
        return ConcreteCreatorPopulation.getInstance(population);
    }// fin createPopulation

    @Override
    public Muestreo choiceMuestreo(String muestreo) {
        return ConcreteCreatorMuestreo.getInstance(muestreo);
    }

    @Override
    public Distribution choiceDistribution(String distribution) {
        return ConcreteCreatorDistribution.getInstance(distribution);
    }

    @Override
    public Selection chooiceSelection(String selection) {
        return ConcreteCreatorSelection.getInstance(selection);
    }
}//-->
