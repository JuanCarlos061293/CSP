package bumda.distribution;

public class ConcreteCreatorDistribution {
  // Métodos
  public static Distribution getInstance(String opcion){
    switch (opcion){
      case "distBumda":
        return new DistBUMDA();
      case "distUmda":
        //return new DistUMDA();
      default:
        return null;
    }// fin sw
  }
}//-->
