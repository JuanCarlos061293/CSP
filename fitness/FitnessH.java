package fitness;

import model.GrupoClase;
import model.Item;
import model.Ratio;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class FitnessH extends Thread {
  private Integer fH;
  private List<Ratio> ratios;
  private List<Item> individuo;
  private List<Vehicle> vehiclesSec;
  private List<Vehicle> vehiclesNoSec;
  private List<GrupoClase> clases;

  /**
   * Allocates a new {@code Thread} object. This constructor has the same
   * effect as {@linkplain Thread(ThreadGroup, Runnable, String) Thread}
   * {@code (group, null, name)}.
   *
   * @param group the thread group. If {@code null} and there is a security
   *              manager, the group is determined by {@linkplain
   *              SecurityManager#getThreadGroup SecurityManager.getThreadGroup()}.
   *              If there is not a security manager or {@code
   *              SecurityManager.getThreadGroup()} returns {@code null}, the group
   *              is set to the current thread's thread group.
   * @param name  the name of the new thread
   * @throws SecurityException if the current thread cannot create a thread in the specified
   *                           thread group
   */
  public FitnessH(ThreadGroup group, String name, List<Ratio> ratios, List<Item> individuo,
                  List<Vehicle> vehiclesSec, List<Vehicle> vehiclesNoSec, List<GrupoClase> clases) {
    super(group, name);
    this.fH = null;
    this.ratios = ratios;
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
  }

  /**
   * Allocates a new {@code Thread} object. This constructor has the same
   * effect as {@linkplain Thread(ThreadGroup, Runnable, String) Thread}
   * {@code (null, null, name)}.
   *
   * @param name the name of the new thread
   */
  public FitnessH(String name, List<Ratio> ratios, List<Item> individuo,
                  List<Vehicle> vehiclesSec, List<Vehicle> vehiclesNoSec, List<GrupoClase> clases) {
    super(name);
    this.fH = null;
    this.ratios = ratios;
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
  }

  /**
   * Allocates a new {@code Thread} object. This constructor has the same
   * effect as {@linkplain Thread(ThreadGroup, Runnable, String) Thread}
   * {@code (null, null, gname)}, where {@code gname} is a newly generated
   * name. Automatically generated names are of the form
   * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
   */
  public FitnessH(List<Ratio> ratios, List<Item> individuo, List<Vehicle> vehiclesSec,
                  List<Vehicle> vehiclesNoSec, List<GrupoClase> clases) {
    this.ratios = ratios;
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
  }

  /**
   * If this thread was constructed using a separate
   * {@code Runnable} run object, then that
   * {@code Runnable} object's {@code run} method is called;
   * otherwise, this method does nothing and returns.
   * <p>
   * Subclasses of {@code Thread} should override this method.
   *
   * @see #start()
   * @see #stop()
   * @see Thread(ThreadGroup, Runnable, String)
   */
  @Override
  public void run() {
    ThreadGroup group = new ThreadGroup(("H"));
    SlidingWindow[] hilos = new SlidingWindow[ratios.size()];
    int total = 0;

    for (int i = 0; i < ratios.size(); i++) {
      hilos[i] = new SlidingWindow(group, String.valueOf(i), ratios.get(i).getNumTareas(), ratios.get(i).getVentana(), generarVectorOpcion(i, ratios.get(i).getVentana()));
      hilos[i].start();
    }// fin for

    while (group.activeCount() > 0) {
    }// fin wh

    for (int i = 0; i < hilos.length; i++) {
      total += hilos[i].getOverload();
    }// fin for

    hilos = null;

    fH = total;
  }

  private List<Byte> generarVectorOpcion(int opcion, int ventana) {
    ArrayList<Byte> lista = new ArrayList<>();

    // Añade a la lista, los últimos P-1 valores de la opción indicada, apartir de la lista de vehiculos (D-1)
    for (int i = ventana - 1; i > 0; i--) {
      lista.add(vehiclesNoSec.get(vehiclesNoSec.size() - i).getHigh().get(opcion));
    }// fin for

    // Añade a la lista, los valores de la opción indicada, apartir de la lista de vehiculos (D)
    for (Item item : individuo) {
      lista.add(vehiclesSec.get(clases.get(item.getClase()).getSeqRank().get(0) - 1).getHigh().get(opcion));
    }// fin for

    return lista;
  }

  public Integer getfH() {
    return fH;
  }

}//-->
