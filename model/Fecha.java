package model;

public class Fecha {
    /**
     * Variable que almacena el año en el que se tiene que producir un vehiculo dado
     */
    private int anio;
    /**
     * Variable que almacena el mes(supuestamente por que para la instancia que me das no hay un mes 38)
     * en el que se tiene que producir un vehiculo dado
     */
    private byte mes;
    /**
     * Variable que almacena el día en el que se tiene que producir un vehiculo dado
     */
    private byte dia;

    /* Constructor */
    public Fecha() {
    }

    public Fecha(int anio, byte mes, byte dia) {
        this.anio = anio;
        this.mes = mes;
        this.dia = dia;
    }

    @Override
    public String toString() {
        return String.valueOf(anio) + " " + String.valueOf(mes) + " " + String.valueOf(dia);
    }

    /* Gettes and Settes */
    public int getAnio() {
        return anio;
    }

    public byte getMes() {
        return mes;
    }

    public byte getDia() {
        return dia;
    }
}//-->
