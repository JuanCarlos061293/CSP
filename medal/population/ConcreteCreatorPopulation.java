package medal.population;

public class ConcreteCreatorPopulation {
    public static Population getInstance(String option){
        switch (option){
            case "aleatorio":
                return new Aleatorio();
            default:
                return null;
        }// switch
    }// fin getInstance
}// -->
