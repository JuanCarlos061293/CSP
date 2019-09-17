package fitness;

import java.util.Collections;
import java.util.List;

public class SlidingWindow extends Thread {
    private short numerador;
    private short denominador;
    private List<Byte> lista;
    private int overload;

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
    public SlidingWindow(ThreadGroup group, String name, short numerador, short denominador, List<Byte> lista) {
        super(group, name);
        this.numerador = numerador;
        this.denominador = denominador;
        this.lista = lista;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        int frecuencia  = 0;

        for (int i = (denominador - 1); i < lista.size(); i++) {
            frecuencia = Collections.frequency(lista.subList(i + 1 - denominador, i + 1), Byte.valueOf("1"));
            overload += (frecuencia > numerador) ? (frecuencia - numerador) : 0;
        }// fin for

        for (int i = denominador - 1 ; i > (numerador + 1); i--) {
            frecuencia = Collections.frequency(lista.subList(lista.size() - i, lista.size()),Byte.valueOf("1"));
            overload += (frecuencia > numerador) ? frecuencia - numerador : 0;
        }// fin for
    }

    public int getOverload() {
        return overload;
    }
}//-->
