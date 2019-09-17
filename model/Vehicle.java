package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    /**
     * Objeto que almacena los datos sobre la fecha de producción, se utiliza la seqRank Fecha.
     */
    private Fecha date;
    /**
     * Variable que almacena la sequencia del auto.
     */
    private Integer seqRank;
    /**
     * Variable que almacena el identificador del vehiculo
     */
    private String ident;
    /**
     * Variable que almacena el Colores utilizado para pintar el vehiculo
     */
    private Integer color;
    /**
     * Objeto HPRC que almacena la configuración de opciones de alta prioridad para este vehiculo
     */
    private List<Byte> high;
    /**
     * Objeto LPRC que almacena la configuración de opciones de baja prioridad para este vehiculo
     */
    private List<Byte> low;

    private List<Integer> distH;

    private List<Integer> distL;

    private Integer clase;

    /* Constructor */
    public Vehicle() {
        this.clase = null;
    }

    public Vehicle(Integer seqRank) {
        this.seqRank = seqRank;
        this.clase = null;
        this.color = null;
    }

    public Vehicle(Integer seqRank, Integer clase, Integer color) {
        this.seqRank = seqRank;
        this.clase = clase;
        this.color = color;
    }

    public Vehicle(Fecha date, int seqRank, String ident, int color, List<Byte> high, List<Byte> low) {
        this.date = date;
        this.seqRank = seqRank;
        this.ident = ident;
        this.color = color;
        this.high = high;
        this.low = low;
        this.distH = new ArrayList<>();
        this.distL = new ArrayList<>();
        this.clase = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        if (this.seqRank != null ) {
            return seqRank.equals(vehicle.seqRank);
        } else {
            return (clase.equals(vehicle.clase) && color.equals(vehicle.color));
        }// fin if
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqRank);
    }

    @Override
    public int compareTo(Vehicle o) {
        return Integer.compare(this.seqRank, o.getSeqRank());
    }

    /* Gettes and Settes */
    public Fecha getDate() {
        return date;
    }

    public Integer getSeqRank() {
        return seqRank;
    }

    public String getIdent() {
        return ident;
    }

    public Integer getColor() {
        return color;
    }

    public List<Byte> getHigh() {
        return high;
    }

    public List<Byte> getLow() {
        return low;
    }

    public List<Integer> getDistH() {
        return distH;
    }

    public List<Integer> getDistL() {
        return distL;
    }

    public Integer getClase() {
        return clase;
    }

    public void setColor(Integer color) { this.color = color; }

    public void setClase(Integer clase) {
        this.clase = clase;
    }

    public void setSeqRank(Integer seqRank) { this.seqRank = seqRank; }

}//-->
