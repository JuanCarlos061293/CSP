package bumda.selection;

public class ConcreteCreatorSelection {
  // Métodos
  public static Selection getInstance(String opcion){
    switch (opcion){
      case "aleatorioSinRep":
        return new AleatorioSinRep();
      case "truncation":
        return new Truncation();
      default:
        return null;
    }// fin sw
  }

}//-->
