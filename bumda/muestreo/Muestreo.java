package bumda.muestreo;

import bumda.reparador.Reparador;
import model.Individuo;
import model.Instancia;
import model.Vehicle;

import java.util.LinkedList;
import java.util.List;

public interface Muestreo {
    void muestreoR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor, int[] sigmaColor, Instancia instancia, Reparador reparador);
    void muestreoSinR(LinkedList<Individuo> population, int[] mediaClase, int[] sigmaClase, int[] mediaColor, int[] sigmaColor, Instancia instancia);

    void muestreoSimple(LinkedList<Individuo> population, int[] media, int [] sigma, List<Vehicle> autosSec, Integer maxPop);

}//-->
