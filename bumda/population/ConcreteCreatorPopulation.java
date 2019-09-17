package bumda.population;

public class ConcreteCreatorPopulation {
  // Métodos
  public static Population getInstance(String opcion){
    switch (opcion){
      case "aleatorio":
        return new Aleatorio();
      case "basadoEnCambio":
        return null;
      default:
        return null;
    }// fin sw
  }
}//-->
