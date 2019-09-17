package medal.muestreo;

public class ConcreteCreatorMuestreo {

    public static Muestreo getInstance(String option){
        switch (option){
            case "medal":
                return new MuestreoMedal();
            default:
                return null;
        }// switch
    }// fin getInstance
}// -->
