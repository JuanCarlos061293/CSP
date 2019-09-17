package medal.selection;

public class ConcreteCreatorSelection {
    public static Selection getInstance(String option){
        switch (option){
            case "truncation":
                return new Truncation();
            default:
                return null;
        }// switch
    }// fin getInstance
}// -->
