package bumda.muestreo;

public class ConcreteCreatorMuestreo  {

  // Métodos
  public static Muestreo getInstance(String opcion){
    switch (opcion){
      case "bumda":
        return new muestreoBUMDA();
      case "umda":
        return new muestreoUMDA();
      default:
        return null;
    }// fin sw
  }

}//-->
