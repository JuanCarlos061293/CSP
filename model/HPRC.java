package model;

import java.util.ArrayList;

public class HPRC {
    /**
     * Almacena la configuración de opciones de alta prioridad dada para un vehiculo dado en un arreglo dinámico
     */
    private ArrayList<Byte> opciones;

    /* constructor */
    public HPRC() {
        opciones = new ArrayList<>();
    }

    /* Gettes and Settes */
    public ArrayList<Byte> getOpciones() {
        return opciones;
    }
}//-->
