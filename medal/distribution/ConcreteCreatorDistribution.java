package medal.distribution;

public class ConcreteCreatorDistribution {
    private String distributionMethod;

    public static Distribution getInstance(String option){
        switch (option){
            case "distMedal":
                return new MedalDistribution();
            default:
                return null;
        }// switch
    }//getInstance
}// -->
