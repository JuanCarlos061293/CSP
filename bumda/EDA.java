package bumda;

import bumda.distribution.Distribution;
import bumda.muestreo.Muestreo;
import bumda.population.Population;
import bumda.reparador.Reparador;
import bumda.selection.Selection;
import controller.PrincipalC;
import controller.SaveFile;
import fitness.FitnessC;
import fitness.FitnessH;
import fitness.FitnessL;
import model.Individuo;
import model.Instancia;

import java.text.SimpleDateFormat;
import java.util.*;

public class EDA {
  private Population population;
  private Selection selection;
  private Muestreo muestreo;
  private Distribution distribution;
  private Reparador reparador;
  private Integer popSize;
  private Integer generaciones;
  private List<Individuo> mejores;

  // Constructor
  public EDA(EDAFactory factory, String population_Method, String selection_Method,
             String muestreo_Method, String distribution_Method, String reparator_Method) {
    this.population = factory.createPopulation(population_Method);
    this.selection = factory.choiceSelection(selection_Method);
    this.muestreo = factory.choiceMuestreo(muestreo_Method);
    this.distribution = factory.choiceDistibution(distribution_Method);
    this.reparador = factory.choiceReparator(reparator_Method);
    //this.popSize = 100;
    //this.generaciones = 1000;
  }
  // runLex para varias ejecuciones
  public void runLex(Instancia instancia, int ejecuciones, int iteraciones){}

  // runLex para una sola ejecución
  public void runLex(Instancia instancia, int nSelec){
      //getHora("de Inicio");
      mejores = new LinkedList<>();
      int gen = 0; // contador para las generaciones
      // Build Initial Infinite Population Distribution
      population.crearPoblacion(this.getPopSize(), instancia.getVehiclesSec(), 123L );
    //population.crearPoblacion(this.getPopSize(), instancia.getVehiclesSec(), null );

      Individuo best = null;
      // AssessFitness(P)
      for (Character obj : instancia.getObjectives()) {
          calcularFitness(obj, population.getPopulation(), instancia);
      }// for obj
      for(Individuo ind : population.getPopulation()){
          ind.fitnessTotal();
      }// for ind
      // guardar la poblacion inicial en un archivo
      SaveFile.escribirPoblacion(population.getPopulation(), "Poblacion Inicial BUMDA");
      do{
          gen++;

          // a sample of individuals generated from D (seleccion)
          ordenarPoblacion(population.getPopulation(), instancia.getObjectives().get(0));
          selection.seleccion(population.getPopulation(), nSelec);
          //SaveFile.escribirPoblacion(population.getPopulation(), "Poblacion Seleccionada y ordenada");

          // UpdateDistribution(D, P)
          ///*
          distribution.calculaMedia(population.getPopulation(), instancia.getObjectives().get(0));
          distribution.calculaSigma(population.getPopulation(), instancia.getObjectives().get(0));
          //*/
          /*
          distribution.calculaMediaClase(population.getPopulation(), instancia.getObjectives().get(0));
          distribution.calculaSigmaClase(population.getPopulation(), distribution.getMediaClase(), instancia.getObjectives().get(0));
          distribution.calculaMediaColor(population.getPopulation(), instancia.getObjectives().get(0));
          distribution.calculaSigmaColor(population.getPopulation(), distribution.getMediaColor(), instancia.getObjectives().get(0));
          //*/

          // generar nuevos individuos

          //muestreo.muestreoSinR(population.getPopulation(),distribution.getMediaClase(), distribution.getSigmaClase(),
          //        distribution.getMediaColor(), distribution.getSigmaColor(), instancia);

          muestreo.muestreoSimple(population.getPopulation(), distribution.getMedia(), distribution.getSigma(), instancia.getVehiclesSec(), getPopSize());

          //muestreo.muestreoR(population.getPopulation(), distribution.getMediaClase(), distribution.getSigmaClase(),
          //        distribution.getMediaColor(), distribution.getSigmaColor(), instancia, reparador);

          for(Individuo i : population.getPopulation()){
              for (Character obj : instancia.getObjectives())
                  calcularFitness(obj, i, instancia);
              i.fitnessTotal();
          }// for i
          // ordenar poblacion dependiendo del objetivo prioritario
          ordenarPoblacion(population.getPopulation(), instancia.getObjectives().get(0));
          // Guardar el mejor por generacion
          best = population.getPopulation().getFirst();
          mejores.add(best);
          System.out.println("Generación: "+gen);
      }while (gen < generaciones);
      SaveFile.escribirPoblacion(population.getPopulation(), "Poblacion Final");
      ordenarPoblacion(mejores, instancia.getObjectives().get(0));
      SaveFile.escribirPoblacion((LinkedList<Individuo>) mejores, "Mejores individuos");
    /*
    System.out.println("Mejor Secuencia Encontrada");
    System.out.println(mejores.get(0).getCromosomas().toString());
    System.out.println("Con fitness:");
    System.out.println("FH: " +mejores.get(0).getfH());
    System.out.println("FC: " +mejores.get(0).getfC());
    System.out.println("FL: " +mejores.get(0).getfL());
    */
      //getHora("de Fin");
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

  private void calcularFitness(Character obj, Individuo individuo, Instancia instancia) {
    switch (obj) {
      case 'H':
        FitnessH fitnessH = new FitnessH(instancia.getRatiosHPRC(), individuo.getCromosomas(),
                instancia.getVehiclesSec(), instancia.getVehiclesNoSec(), instancia.getClases());
        fitnessH.run();
        individuo.setfH(fitnessH.getfH());
        break;
      case 'L':
        FitnessL fitnessL = new FitnessL(instancia.getRatiosLPRC(), individuo.getCromosomas(),
                instancia.getVehiclesSec(), instancia.getVehiclesNoSec(), instancia.getClases());
        fitnessL.run();
        individuo.setfL(fitnessL.getfL());
        break;
      default:
        FitnessC fitnessC = new FitnessC(individuo.getCromosomas(), instancia.getVehiclesSec(),
                instancia.getVehiclesNoSec(), instancia.getPaintLimit(), instancia.getClases());
        fitnessC.run();
        individuo.setfC(fitnessC.getfC());
    }// fin sw
  }

  // Getter and Setter
  public Integer getPopSize() { return popSize; }

  public void setPopSize(Integer popSize) { this.popSize = popSize; }

  public Integer getGeneraciones() { return generaciones; }

  public void setGeneraciones(Integer generaciones) { this.generaciones = generaciones; }

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
      default:
        Collections.sort(pop, new Comparator<Individuo>() {
          @Override
          public int compare(Individuo i1, Individuo i2) {
            return Double.compare(i1.getFitness(), i2.getFitness());
          }// compare
        });//Collections.sort
    }//switch
  }// ordenarPoblacion

  public Individuo getBest(){ return mejores.get(0); }

  public String getHora(String clave){
      return ("Hora y Fecha " + clave + ": " + new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
    }// fin getHora

}//-->
