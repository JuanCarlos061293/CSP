package controller;

import bumda.ConcreteCreatorEDAFactory;
import bumda.EDA;
import model.GrupoColor;
import model.Instancia;
import model.Vehicle;
import view.PrincipalV;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

public class PrincipalC implements ActionListener{

    private PrincipalV principal;
    private Instancia instancia;


    /**
     *
     */
    public void loadFrame() {
        principal = new PrincipalV(("Solucionador del Car Secuencing Problem con BUMDA"), (this));
        principal.setVisible(true);
        instancia = new Instancia();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File nameFile = null;
        switch (e.getActionCommand()) {
            case "vehicles":
                nameFile = principal.chooseFile("Vehicles", "txt", "TXT", principal.getTxtVehiculos());
                if (nameFile != null){
                    LoadFile.loadVehicles(nameFile, instancia);
                }// fin if
                break;
            case "ratios":
                nameFile = principal.chooseFile("Ratios", "txt", "TXT", principal.getTxtRatios());
                if (nameFile != null) {
                    LoadFile.loadRatios(nameFile, instancia);
                }// fin if
                break;
            case "paintLimit":
                nameFile = principal.chooseFile("Paint Limit", "txt", "TXT", principal.getTxtPaintLimit());
                if (nameFile != null) {
                    LoadFile.loadPaintLimit(nameFile, instancia);
                }// fin if
                break;
            case "objectives":
                nameFile = principal.chooseFile("Objectives", "txt", "TXT", principal.getTxtObjectives());
                if (nameFile != null) {
                  LoadFile.loadObjectives(nameFile, instancia);
                }// fin if
                break;
            case "Default":
                LoadFile.loadVehicles(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\vehicles.txt"), instancia);
                createGroupColores();
                LoadFile.loadRatios(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\ratios.txt"), instancia);
                LoadFile.loadPaintLimit(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\paint_batch_limit.txt"), instancia);
                LoadFile.loadObjectives(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\optimization_objectives.txt"), instancia);
                break;
            case "Ejecutar":
                deleteLogs();
                ///*
                EDA bumda = new EDA(ConcreteCreatorEDAFactory.getInstance(),"aleatorio",
                    "truncation","bumda","distBumda", "reparador");
                bumda.setGeneraciones(9000);
                bumda.setPopSize(500);
                //Imprimir hora de inicio
                principal.getTxaArea().append("BUMDA con 500 ind y 9000 gen\n");
                principal.getTxaArea().append(bumda.getHora("de inicio\n"));

                bumda.runLex(instancia, (bumda.getPopSize()/2)); // se toman N/2 individuos de la poblacion
                //Imprimir resultados y hora de fin
                principal.getTxaArea().append("Individuo: "+bumda.getBest().getId()+"\n");
                principal.getTxaArea().append(bumda.getBest().getPositions().toString()+"\n");
                principal.getTxaArea().append("FC: "+bumda.getBest().getfC()+"\n");
                principal.getTxaArea().append("FH: "+bumda.getBest().getfH()+"\n");
                principal.getTxaArea().append("FL: "+bumda.getBest().getfL()+"\n");
                principal.getTxaArea().append("Fitness Total: "+bumda.getBest().getFitness()+"\n");
                principal.getTxaArea().append(bumda.getHora("de fin"));
                //*/


                /*
                EDA umda = new EDA(ConcreteCreatorEDAFactory.getInstance(),"aleatorio",
                        "truncation","umda","distUmda");
                 */
                /*
                MEDAL medal = new MEDAL(ConcreteCreatorMEDALFactory.getInstance(), "aleatorio",
                        "truncation", "distMedal", "medal");
                medal.runLex(instancia);
                 */
                break;
            default:
        }// fin sw
    }

    private void createGroupColores(){
        instancia.createColores();
        Vehicle colorMax = Collections.max(instancia.getVehiclesSec(), new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        });
        for (int i = 1; i <= colorMax.getColor(); i++) {
            GrupoColor grupoColor = new GrupoColor("Color-" + String.valueOf(i));
            for (int j = 0; j < instancia.getClases().size() ; j++) {
                if (Collections.frequency(instancia.getVehiclesSec(), new Vehicle(null, j, i)) > 0){
                    grupoColor.getClases().put(j, Collections.frequency(instancia.getVehiclesSec(), new Vehicle(null, j, i)));
                }// fin if
            }// fin for
            instancia.getColores().add(grupoColor);
        }// fin for

    }

    private void deleteLogs(){
        SaveFile.deleteFile("H.txt");
        SaveFile.deleteFile("L.txt");
        SaveFile.deleteFile("C.txt");
        SaveFile.deleteFile("zCoordinador.txt");
    }
    /*
    private Individuo codificar(Individuo individuo){
        Individuo nuevo = new Individuo();
        nuevo.setfH(individuo.getfH());
        nuevo.setfL(individuo.getfL());
        nuevo.setfC(individuo.getfC());
        nuevo.setFitness(individuo.getFitness());
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < instancia.getClases().size(); i++) {
            index.add(0);
        }// fin for

        for (int i = 0; i < individuo.getCromosomas().size(); i++) {
            nuevo.getCromosomas().add(instancia.getClases().get(individuo.getCromosomas().get(i)).getSeqRank().get(index.get(individuo.getCromosomas().get(i))));
            index.set(individuo.getCromosomas().get(i), (index.get(individuo.getCromosomas().get(i)) + 1));
        }// fin for

        return nuevo;
    }
    */
}//-->
