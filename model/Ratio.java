package model;

public class Ratio {
    private short numTareas;
    private short ventana;

    /* Constructor */
    public Ratio() {
    }

    public Ratio(short numTareas, short ventana) {
        this.numTareas = numTareas;
        this.ventana = ventana;
    }

    /* Gettes and Settes */
    public short getNumTareas() {
        return numTareas;
    }

    public short getVentana() {
        return ventana;
    }
}//-->
