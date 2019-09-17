package bumda.muestreo;

import bumda.reparador.Reparador;
import model.Individuo;
import model.Instancia;
import model.Item;
import model.Vehicle;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomDataGenerator;

import java.util.*;

public class muestreoBUMDA implements Muestreo {

    /**
     * Muestreo que utiliza el cromosoma de posiciones
     */
    @Override
    public void muestreoSimple(LinkedList<Individuo> population, int[] media, int[] sigma, List<Vehicle> autosSec, Integer maxPop) {
        int seqRank;
        Vehicle car;
        List<Integer> index = new LinkedList<>();
        while (population.size() < maxPop) {
            iniciaSeqRank(index, autosSec.size());
            Individuo ind = new Individuo();
            for (int j = 0; j < autosSec.size(); j++) {
                //seqRank = (int)(new RandomDataGenerator().nextGaussian(media[j], sigma[j]));
                seqRank = (int)(new NormalDistribution(media[j], sigma[j]).sample());
                int max = Collections.max(index);
                car = buscarAuto(ajusteSeqRank(seqRank, max), autosSec, index, ind.getPositions());
                if (car == null){
                    j--;
                    if (j < 0)
                        j = 0;
                }else {
                    ind.getPositions().add(car.getSeqRank());
                    ind.getCromosomas().add(new Item(car.getClase(), car.getColor()));
                }
            }// for j
            //System.out.println("Creó un nuevo individuo");
            population.add(ind);
        }// while
    }// fin muestreo

    /**
     * Muestreo que utiliza los cromosomas de clase y color
     * con reparador cromosomico
     */
    @Override
    public void muestreoR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor,
                          int[] sigmaColor, Instancia instancia, Reparador reparador) {
        int clase, color;
        Individuo ind;
        int popSize = population.size();
        while (population.size() <= (popSize * 2)) {
            ind = new Individuo();
            for (int j = 0; j < mediaClase.length; j++) {
                color = (int)Math.round((new Random().nextDouble() * mediaColor[j]) + sigmaColor[j]);
                clase = (int)Math.round((new Random().nextDouble() * mediaClase[j]) + sigmaClase[j]);
                ind.getCromosomas().add(new Item(clase, color));
            }// for j
            reparador.repararIndividuo(ind, instancia.getClases());
            reparador.repararIndividuoC(ind, instancia.getColores());
            population.add(ind);
        }// while
    }// fin creaNuevosInd

    /**
     * Muestreo que utiliza los cromosomas de clase y color
     * sin el reparador cromosomico
     */
    @Override
    public void muestreoSinR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor, int[] sigmaColor, Instancia instancia) {
        int clase, color;
        Individuo ind;
        Vehicle car;
        List<Vehicle> autosSec = new LinkedList<>(instancia.getVehiclesSec());
        int popSize = population.size();

        while (population.size() < (popSize * 2)) {
            ind = new Individuo();
            for (int j = 0; j < mediaClase.length; j++) {
                clase = (int) new NormalDistribution(mediaClase[j], sigmaClase[j]).sample();
                color = (int) new NormalDistribution(mediaColor[j], sigmaColor[j]).sample();
                //clase = (int) new RandomDataGenerator().nextGaussian(mediaClase[j], sigmaClase[j]);
                //color = (int) new RandomDataGenerator().nextGaussian(mediaColor[j], sigmaColor[j]);
                //clase = (int) Math.round((new Random().nextInt(instancia.getClases().size()) * mediaClase[j]) + sigmaClase[j]);
                //color = (int) Math.round((new Random().nextInt(instancia.getColores().size()) * mediaColor[j]) + sigmaColor[j]);
                //Buscar en VehiclesSec el auto que tenga la clase y el color ya ajustados
                car = buscarAuto(ajusteClase(clase, instancia.getClases().size() - 1), ajusteColor(color, instancia.getColores().size()), autosSec);
                // Verifica que los valores sean correctos
                //ind.getCromosomas().add(new Item(ajusteClase(clase, instancia.getClases().size() - 1), ajusteColor(color, instancia.getColores().size())));
                if (car != null) {
                    ind.getPositions().add(car.getSeqRank() - 1);
                    ind.getCromosomas().add(new Item(car.getClase(), car.getColor()));
                } else{
                    j--;
                    if (j < 0)
                        j = 0;
                }// else
            }// for j
            System.out.println("Se creó un individuo");
            population.add(ind);
        }// while
    }// fin muestreoSinR sin reparador

    private int ajusteClase(int clase, int maxClase){
        // el valor minimo para la clase siempre será 0
        if (clase < 0) {
            clase = maxClase - (clase % maxClase);
            if (clase > maxClase)
                clase = 0; //Si el valor obtenido sobrepasa el limite, se asigna a 0
        }else if (clase > maxClase)
            clase = (clase % maxClase);

        return clase;
    }// fin ajusteClase

    private int ajusteColor(int color,  int maxColor){
        // el valor minimo para el color siempre será 1
        if (color < 1) {
            color = maxColor - (color % maxColor);
            if (color > maxColor)
                color = 1; //Si el valor obtenido sobrepasa el limite, se asigna a 1
        }else if (color > maxColor)
            color = 1 + (color % maxColor);
        return color;
    }// fin ajusteClase

    private Integer ajusteSeqRank(Integer seqRank, Integer maxSeqRank){
        if (seqRank < 1) {
            seqRank = maxSeqRank - (seqRank % maxSeqRank);
            if (seqRank > maxSeqRank)
                seqRank = 1;
        }else if (seqRank > maxSeqRank){
            seqRank = (seqRank % maxSeqRank);
            if ( seqRank == 0)
                seqRank = maxSeqRank;
        }
        return seqRank;
    }// fin ajusteSeqRank

    /**
     * Función buscarAuto con los valores de clase y color del auto
     */
    private Vehicle buscarAuto(Integer clase, Integer color, List<Vehicle> autosSec){
        Vehicle car = new Vehicle();
        Vehicle temp;
        car.setClase(clase);
        car.setColor(color);
        int times = Collections.frequency(autosSec, car);
        if (times > 0){
             temp = autosSec.get(autosSec.lastIndexOf(car));
             autosSec.remove(temp);
             return temp;
        }else
            return null;
    }// fin buscarAuto

    /**
     * Función buscarAuto con el indice de la secuencia
     */
    private Vehicle buscarAuto(Integer seqRank, List<Vehicle> autosSec, List<Integer> index, List<Integer> positions) {
        Vehicle temp;
        Vehicle car = new Vehicle();
        car.setSeqRank(seqRank);
        if (index.size() == 1){
            temp = autosSec.get(index.get(0) - 1);
            return temp;
        }else if (index.contains(seqRank) && !positions.contains(seqRank)){
            temp = autosSec.get(seqRank - 1);
            index.remove(index.indexOf(seqRank));
            return temp;
        }else
            return null;
    }// fin buscarAuto

    private void iniciaSeqRank(List<Integer> index, Integer max){
        for (int i = 1; i <= max; i++){
            index.add(i);
        }// for i
    }// fin iniciaseqRank


}//-->
