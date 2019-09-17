package model;

import java.util.ArrayList;

public class LPRC {
    /**
     * Almacena la configuración de opciones de baja prioridad dada para un vehiculo dado en un arreglo dinámico
     */
    private ArrayList<Byte> opciones;

    /* Constructor */
    public LPRC() {
        opciones = new ArrayList<>();
    }

    /* Gettes and Settes */
    public ArrayList<Byte> getOpciones() {
        return opciones;
    }
}//-->
