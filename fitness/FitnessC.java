package fitness;

import model.GrupoClase;
import model.Item;
import model.Vehicle;

import java.util.List;

public class FitnessC extends Thread{
  private List<Item> individuo;
  private List<Vehicle> vehiclesSec;
  private List<Vehicle> vehiclesNoSec;
  private List<GrupoClase> clases;
  private int paintLimit;
  private Integer fC;

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
  public FitnessC(ThreadGroup group, String name, List<Item> individuo,
                  List<Vehicle> vehiclesSec, List<Vehicle> vehiclesNoSec, int paintLimit, List<GrupoClase> clases) {
    super(group, name);
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
    this.paintLimit = paintLimit;
    fC = null;
  }

  /**
   * Allocates a new {@code Thread} object. This constructor has the same
   * effect as {@linkplain Thread(ThreadGroup, Runnable, String) Thread}
   * {@code (null, null, name)}.
   *
   * @param name the name of the new thread
   */
  public FitnessC(String name, List<Item> individuo, List<Vehicle> vehiclesSec,
                  List<Vehicle> vehiclesNoSec, int paintLimit, List<GrupoClase> clases) {
    super(name);
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
    this.paintLimit = paintLimit;
    fC = null;
  }

  /**
   * Allocates a new {@code Thread} object. This constructor has the same
   * effect as {@linkplain Thread(ThreadGroup, Runnable, String) Thread}
   * {@code (null, null, gname)}, where {@code gname} is a newly generated
   * name. Automatically generated names are of the form
   * {@code "Thread-"+}<i>n</i>, where <i>n</i> is an integer.
   */
  public FitnessC(List<Item> individuo, List<Vehicle> vehiclesSec,
                  List<Vehicle> vehiclesNoSec, int paintLimit, List<GrupoClase> clases) {
    this.individuo = individuo;
    this.vehiclesSec = vehiclesSec;
    this.vehiclesNoSec = vehiclesNoSec;
    this.clases = clases;
    this.paintLimit = paintLimit;
    fC = null;
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
    super.run();
    int sum = 0;
    byte count = 1;
    int color = vehiclesNoSec.get(vehiclesNoSec.size() - 1).getColor();

    for (Item item : individuo) {
      if (item.getColor() != color) {
        ++sum;

        count = 1;
        color = item.getColor();
      } else {
        ++count;
        if (count > paintLimit) {
          ++sum;
          count = 1;
        }// fin if
      }// fin if
    }// fin for

    fC = sum;
  }

  private Integer obtenerColor(Integer item, List<Integer> index){
    int a = vehiclesSec.get(clases.get(item).getSeqRank().get(index.get(item)) - 1).getColor();
    index.set(item,(index.get(item) + 1));
    return a;
  }

  public Integer getfC() {
    return fC;
  }
}//-->
