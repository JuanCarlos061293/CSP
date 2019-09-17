package bumda.reparador;

public class ConcreteCreatorReparador {
  // Métodos
  public static Reparador getInstance(String opcion){
    switch (opcion){
      case "reparador":
        return new RepClases();
      default:
        return null;
    }// fin sw
  }
}//-->
