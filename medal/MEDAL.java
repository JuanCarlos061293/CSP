package medal;

import controller.SaveFile;
import fitness.FitnessC;
import fitness.FitnessH;
import fitness.FitnessL;
import medal.distribution.Distribution;
import medal.muestreo.Muestreo;
import medal.population.Population;
import medal.selection.Selection;
import model.Individuo;
import model.Instancia;

import java.text.SimpleDateFormat;
import java.util.*;

public class MEDAL {

    public static int contInd; //indice de los individuos en la poblacion
    private Population population;
    private Selection selection;
    private Distribution distribution;
    private Muestreo muestreo;
    Integer popSize;
    Integer generaciones;

    public MEDAL(MEDALFactory factory, String populationMethod, String selectionMethod,
                 String distributionMethod, String muestreoMethod){
        this.population = factory.createPopulation(populationMethod);
        this.selection = factory.chooiceSelection(selectionMethod);
        this.distribution = factory.choiceDistribution(distributionMethod);
        this.muestreo = factory.choiceMuestreo(muestreoMethod);
        this.popSize = 100;
        this.generaciones = 10;
        contInd = 0;
    }// fin Constructor

    public void runLex(Instancia instancia){
        Individuo best;
        getHora("de Inicio");
        //Crear Poblacion
        population.crearPopulation(popSize, instancia.getVehiclesSec(), 123L);
        //Calcular Fitness por objetivo
        for(Character obj : instancia.getObjectives())
            calcularFitness(obj, population.getPopulation(), instancia);
        //Calcular Fitness total
        for(Individuo ind : population.getPopulation())
            ind.fitnessTotal();
        //Imprimir poblacion antes de ordenar
        SaveFile.escribirPoblacion(population.getPopulation(), "Poblacion No Ordenada");
        //Ordenar poblacion (depende del objetivo)
        ordenarPoblacion(population.getPopulation(), instancia.getObjectives().get(0));
        //Seleccion de los N/2 mejores individuos
        selection.selection(population.getPopulation(), (popSize/2));
        best = population.getPopulation().getFirst().clone();
        //Imprimir poblacion ordenada
        SaveFile.escribirPoblacion(population.getPopulation(), "Poblacion Ordenada");
        distribution.createFreqMatrix(best.getPositions().size());
        distribution.calculaFreqMatrix(population.getPopulation());
        System.out.println("LISTO!!");

        getHora("de Fin");
    }// fin runLex
    private void calcularFitness(Character obj, LinkedList<Individuo> poblacion, Instancia instancia) {
        switch (obj) {
            case 'H':
                for (Individuo individuo : poblacion) {
                    FitnessH fitnessH = new FitnessH(instancia.getRatiosHPRC(), individuo.getCromosomas(),
                            instancia.getVehiclesSec(), instancia.getVehiclesNoSec(), instancia.getClases());
                    fitnessH.run();
                    individuo.setfH(fitnessH.getfH());
                }// fin for
                break;
            case 'L':
                for (Individuo individuo : poblacion) {
                    FitnessL fitnessL = new FitnessL(instancia.getRatiosLPRC(), individuo.getCromosomas(),
                            instancia.getVehiclesSec(), instancia.getVehiclesNoSec(), instancia.getClases());
                    fitnessL.run();
                    individuo.setfL(fitnessL.getfL());
                }// fin for
                break;
            default:
                for (Individuo individuo : poblacion) {
                    FitnessC fitnessC = new FitnessC(individuo.getCromosomas(), instancia.getVehiclesSec(),
                            instancia.getVehiclesNoSec(), instancia.getPaintLimit(), instancia.getClases());
                    fitnessC.run();
                    individuo.setfC(fitnessC.getfC());
                }// fin for
        }// fin sw
    }

    private void ordenarPoblacion(List<Individuo> pop, Character obj){
        switch (obj){
            case 'H':
                Collections.sort(pop, new Comparator<Individuo>() {
                    @Override
                    public int compare(Individuo i1, Individuo i2) {
                        return Double.compare(i1.getfH(), i2.getfH());
                    }// compare
                });//Collections.sort
                break;
            case 'C':
                Collections.sort(pop, new Comparator<Individuo>() {
                    @Override
                    public int compare(Individuo i1, Individuo i2) {
                        return Double.compare(i1.getfC(), i2.getfC());
                    }// compare
                });//Collections.sort
                break;
            case 'L':
                Collections.sort(pop, new Comparator<Individuo>() {
                    @Override
                    public int compare(Individuo i1, Individuo i2) {
                        return Double.compare(i1.getfL(), i2.getfL());
                    }// compare
                });//Collections.sort
                break;
            case 'T':
                Collections.sort(pop, new Comparator<Individuo>() {
                    @Override
                    public int compare(Individuo i1, Individuo i2) {
                        return Double.compare(i1.getFitness(), i2.getFitness());
                    }// compare
                });//Collections.sort
        }//switch
    }// ordenarPoblacion

    private void getHora(String clave){
        System.out.println("Hora y Fecha " + clave + ": " + new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
    }// fin getHora

}// -->
