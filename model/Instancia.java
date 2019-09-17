package model;

import java.util.ArrayList;
import java.util.List;

public final class Instancia {
    private List<Vehicle> vehiclesNoSec;
    private List<Vehicle> vehiclesSec;
    private List<Ratio> ratiosHPRC;
    private List<Ratio> ratiosLPRC;
    private Integer paintLimit;
    private List<Character> objectives;
    private List<GrupoClase> clases;
    private List<GrupoColor> colores;

    public Instancia() {
        this.vehiclesNoSec = null;
        this.vehiclesSec = null;
        this.ratiosHPRC = null;
        this.ratiosLPRC = null;
        this.paintLimit = null;
        this.objectives = null;
        this.clases = null;
        this.colores = null;
    }

    public void createColores(){
        colores = new ArrayList<>();
    }

    /* Gettes and Settes */
    public synchronized List<Vehicle> getVehiclesNoSec() {
        return vehiclesNoSec;
    }

    public synchronized List<Vehicle> getVehiclesSec() {
        return vehiclesSec;
    }

    public synchronized List<Ratio> getRatiosHPRC() {
        return ratiosHPRC;
    }

    public synchronized List<Ratio> getRatiosLPRC() {
        return ratiosLPRC;
    }

    public synchronized Integer getPaintLimit() {
        return paintLimit;
    }

    public synchronized List<Character> getObjectives() {
        return objectives;
    }

    public synchronized List<GrupoClase> getClases() {
        return clases;
    }

    public synchronized List<GrupoColor> getColores() {
        return colores;
    }

    public void setColores(List<GrupoColor> colores) {
        this.colores = colores;
    }

    public void setClases(List<GrupoClase> clases) {
        this.clases = clases;
    }

    public void setVehiclesNoSec(List<Vehicle> vehiclesNoSec) {
        this.vehiclesNoSec = vehiclesNoSec;
    }

    public void setVehiclesSec(List<Vehicle> vehiclesSec) {
        this.vehiclesSec = vehiclesSec;
    }

    public void setRatiosHPRC(List<Ratio> ratiosHPRC) {
        this.ratiosHPRC = ratiosHPRC;
    }

    public void setRatiosLPRC(List<Ratio> ratiosLPRC) {
        this.ratiosLPRC = ratiosLPRC;
    }

    public void setPaintLimit(int paintLimit) {
        this.paintLimit = paintLimit;
    }

    public void setObjectives(List<Character> objectives) {
        this.objectives = objectives;
    }

}//-->
