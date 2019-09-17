package bumda.reparador;

import model.GrupoClase;
import model.GrupoColor;
import model.Individuo;
import model.Item;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RepClases implements Reparador{

  @Override
  public Individuo repararIndividuo(Individuo individuo, List<GrupoClase> clase) {
    for (int i = 0; i < individuo.getCromosomas().size(); i++) {
      if ( (individuo.getCromosomas().get(i).getClase() < 0) || (individuo.getCromosomas().get(i).getClase() >= clase.size()) ) {
        individuo.getCromosomas().get(i).setClase(substituirClase(clase, individuo.getCromosomas(), individuo.getCromosomas().get(i).getClase()));
      } else {
        if ( Collections.frequency(individuo.getCromosomas(), new Item(individuo.getCromosomas().get(i).getClase(), null)) > clase.get(individuo.getCromosomas().get(i).getClase()).getSeqRank().size() ) {
          individuo.getCromosomas().get(i).setClase(substituirClase(clase, individuo.getCromosomas(), individuo.getCromosomas().get(i).getClase()));
        }// fin if
      }// fin if
      repararColor(i, individuo.getCromosomas(), clase.get(individuo.getCromosomas().get(i).getClase()), individuo.getCromosomas().get(i).getClase(), individuo.getCromosomas().get(i).getColor());
    }// fin for
    return individuo.clone();
  }

  private Integer substituirClase(List<GrupoClase> clases, List<Item> cromosomas, Integer claseASustituir){
   for (int i = 0; i < clases.size(); i++) {

     //System.out.println("Clase: " + i + ", Frecuencia: "+ Collections.frequency(cromosomas, new Item(i, null)) + ", Permitida: " + clases.get(i).getSeqRank().size());

     if ( Collections.frequency(cromosomas, new Item(i, null)) < clases.get(i).getSeqRank().size()){
       //System.out.println("Clase retornada: " + i);
        return i;
      }// fin if
    }// fin for

    return claseASustituir;
  }

  private void repararColor(Integer indice, List<Item> cromosomas, GrupoClase grupoClase, Integer clase, Integer color){
    if (!grupoClase.getColores().containsKey(color)){
      cromosomas.get(indice).setColor(substituirColor(grupoClase, cromosomas.subList(0, indice), color, clase));
    } else {
    if (Collections.frequency(cromosomas.subList(0, indice), new Item(clase, color)) >= grupoClase.getColores().get(color)) {
        cromosomas.get(indice).setColor(substituirColor(grupoClase, cromosomas.subList(0, indice), color, clase));
      }// fin if
    }// fin if
  }

  private Integer substituirColor(GrupoClase grupoClase, List<Item> cromosomas, Integer colorASustituir, Integer clase){
    Integer colorEncontrado = null;

    for (Integer color: grupoClase.getColores().keySet()) {

      //System.out.println("Clase: " + clase + ", Color: "+ color + ", Frecuencia: " + Collections.frequency(cromosomas, new Item(clase, color)) + ", Permitida: " + grupoClase.getColores().get(color));

      if (Collections.frequency(cromosomas, new Item(clase, color)) < grupoClase.getColores().get(color)){
        colorEncontrado = color;
        break;
      }// fin if
    }// fin for

    return colorEncontrado;
  }

  @Override
  public Individuo repararIndividuoC(Individuo individuo, List<GrupoColor> colores) {
    //System.out.println(individuo.toString());

    for (int i = 0; i < individuo.getCromosomas().size(); i++) {
      if ((individuo.getCromosomas().get(i).getColor() < 1) || (individuo.getCromosomas().get(i).getColor() > colores.size())) {
        individuo.getCromosomas().get(i).setColor(substituirColorC(colores, individuo.getCromosomas(), individuo.getCromosomas().get(i).getColor()));
      } else{

        //System.out.println("Cromosoma: " + i + ", Color: " + individuo.getCromosomas().get(i).getColor() + ", Frecuencia: " + Collections.frequency(individuo.getCromosomas(), new Item(null,individuo.getCromosomas().get(i).getColor())) + ", Permitida: " + sumarColores(colores.get(individuo.getCromosomas().get(i).getColor() - 1).getClases().values()));

        if (Collections.frequency(individuo.getCromosomas(), new Item(null,individuo.getCromosomas().get(i).getColor())) > sumarColores(colores.get(individuo.getCromosomas().get(i).getColor() - 1).getClases().values())) {
          individuo.getCromosomas().get(i).setColor(substituirColorC(colores, individuo.getCromosomas(), individuo.getCromosomas().get(i).getColor()));
        }// fin if
      }// fin if

      repararClase(i, individuo.getCromosomas(), colores.get(individuo.getCromosomas().get(i).getColor() - 1), individuo.getCromosomas().get(i).getColor(), individuo.getCromosomas().get(i).getClase());
    }//fin for
    return individuo.clone();
  }

  private Integer substituirColorC(List<GrupoColor> color, List<Item> cromosomas, Integer colorASustituir){

    for (int i = 1; i <= color.size(); i++) {
      if ( Collections.frequency(cromosomas, new Item(null,i)) < sumarColores(color.get(i - 1).getClases().values())){
        return i;
      }// fin if
    }// fin for

    return colorASustituir;
  }

  private void repararClase(Integer indice, List<Item> cromosomas, GrupoColor grupoColor, Integer color, Integer clase){
    //System.out.println("Cromosoma: " + indice + ", Color: " + color + ", Clase: "+ clase);

    if (!grupoColor.getClases().containsKey(clase)){
      cromosomas.get(indice).setClase(substituirClaseC(grupoColor, cromosomas.subList(0, indice), clase, color));
    } else {

      //System.out.println("Cromosoma: " + indice + ", Color: " + color + ", Clase: "+ clase + ", Frecuencia: " + Collections.frequency(cromosomas.subList(0, indice), new Item(clase, color)) + ", Permitida: " + grupoColor.getClases().get(clase));

      if (Collections.frequency(cromosomas.subList(0, indice), new Item(clase, color)) >= grupoColor.getClases().get(clase)) {
        cromosomas.get(indice).setClase(substituirClaseC(grupoColor, cromosomas.subList(0, indice), clase, color));
      }// fin if
    }// fin if
  }

  private Integer substituirClaseC(GrupoColor grupoColor, List<Item> cromosomas, Integer claseASustituir, Integer color){
    Integer claseEncontrada = null;

    for (Integer clase: grupoColor.getClases().keySet()) {

      //System.out.println("Color: " + color + ", Clase: "+ clase + ", Frecuencia: " + Collections.frequency(cromosomas, new Item(clase, color)) + ", Permitida: " + grupoColor.getClases().get(clase));

        if (Collections.frequency(cromosomas, new Item(clase, color)) < grupoColor.getClases().get(clase)){
          claseEncontrada = clase;
          break;
        }// fin if
    }// fin for

    return claseEncontrada;
  }

  private Integer sumarColores(Collection<Integer> valores){
    int sumador = 0;

    for (Integer valor : valores) {
      sumador += valor;
    }// fin for

    return sumador;
  }

}//->
