package model;


import medal.MEDAL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Individuo implements Cloneable, Comparable<Individuo>{
  private List<Item> cromosomas;
  private Integer fH;
  private Integer fL;
  private Integer fC;
  private Integer fitness;
  private Integer id;
  List<Integer> positions;

  // Constructor
  public Individuo(){
    cromosomas = new LinkedList<>();
    this.fH = null;
    this.fL = null;
    this.fC = null;
    this.id = ++MEDAL.contInd;
    this.positions = new LinkedList<>();
  }

  // intern Method
  public void fitnessTotal(){
    fitness = 0;
    if (fH != null){
      fitness += fH;
    }// fin if
    if (fL != null){
      fitness += fL;
    }// fin if
    if (fC != null){
      fitness += fC;
    }// fin if
  }

  // Override Method
  /**
   * Creates and returns a copy of this object.  The precise meaning
   * of "copy" may depend on the class of the object. The general
   * intent is that, for any object {@code x}, the expression:
   * <blockquote>
   * <pre>
   * x.clone() != x</pre></blockquote>
   * will be true, and that the expression:
   * <blockquote>
   * <pre>
   * x.clone().getClass() == x.getClass()</pre></blockquote>
   * will be {@code true}, but these are not absolute requirements.
   * While it is typically the case that:
   * <blockquote>
   * <pre>
   * x.clone().equals(x)</pre></blockquote>
   * will be {@code true}, this is not an absolute requirement.
   * <p>
   * By convention, the returned object should be obtained by calling
   * {@code super.clone}.  If a class and all of its superclasses (except
   * {@code Object}) obey this convention, it will be the case that
   * {@code x.clone().getClass() == x.getClass()}.
   * <p>
   * By convention, the object returned by this method should be independent
   * of this object (which is being cloned).  To achieve this independence,
   * it may be necessary to modify one or more fields of the object returned
   * by {@code super.clone} before returning it.  Typically, this means
   * copying any mutable objects that comprise the internal "deep structure"
   * of the object being cloned and replacing the references to these
   * objects with references to the copies.  If a class contains only
   * primitive fields or references to immutable objects, then it is usually
   * the case that no fields in the object returned by {@code super.clone}
   * need to be modified.
   * <p>
   * The method {@code clone} for class {@code Object} performs a
   * specific cloning operation. First, if the class of this object does
   * not implement the interface {@code Cloneable}, then a
   * {@code CloneNotSupportedException} is thrown. Note that all arrays
   * are considered to implement the interface {@code Cloneable} and that
   * the return type of the {@code clone} method of an array type {@code T[]}
   * is {@code T[]} where T is any reference or primitive type.
   * Otherwise, this method creates a new instance of the class of this
   * object and initializes all its fields with exactly the contents of
   * the corresponding fields of this object, as if by assignment; the
   * contents of the fields are not themselves cloned. Thus, this method
   * performs a "shallow copy" of this object, not a "deep copy" operation.
   * <p>
   * The class {@code Object} does not itself implement the interface
   * {@code Cloneable}, so calling the {@code clone} method on an object
   * whose class is {@code Object} will result in throwing an
   * exception at run time.
   *
   * @return a clone of this instance.
   * @throws CloneNotSupportedException if the object's class does not
   *                                    support the {@code Cloneable} interface. Subclasses
   *                                    that override the {@code clone} method can also
   *                                    throw this exception to indicate that an instance cannot
   *                                    be cloned.
   * @see Cloneable
   */
  @Override
  public Individuo clone() {
    Individuo obj = null;

    try {
      obj = (Individuo) super.clone();
      obj.setfH(this.getfH());
      obj.setfC(this.getfC());
      obj.setfL(this.getfL());
      obj.setFitness(this.getFitness());

      List<Item> temp = new ArrayList<>();
      for (int i = 0; i < this.getCromosomas().size(); i++) {
        temp.add(this.getCromosomas().get(i).clone());
      }// For
      obj.setCromosomas(temp);
    } catch (CloneNotSupportedException ex){

    }// fin try

    return obj;
  }

  @Override
  public String toString() {
    return "Individuo{" + ", fitness=" + fitness + ", fH=" + fH + ", fL=" + fL + ", fC=" + fC + " cromosomas=" + cromosomas + '}';
  }

  @Override
  public int compareTo(Individuo o) {
    return Double.compare(this.getFitness(), o.getFitness());
  }

  // Gettes and Settes
  public synchronized List<Item> getCromosomas() {
    return cromosomas;
  }

  public synchronized void setCromosomas(List<Item> cromosomas) {
    this.cromosomas = cromosomas;
  }

  public synchronized Integer getfH() {
    return fH;
  }

  public synchronized void setfH(Integer fH) {
    this.fH = fH;
  }

  public synchronized Integer getfL() {
    return fL;
  }

  public synchronized void setfL(Integer fL) {
    this.fL = fL;
  }

  public synchronized Integer getfC() {
    return fC;
  }

  public synchronized void setfC(Integer fC) {
    this.fC = fC;
  }

  public synchronized Integer getFitness() {
    return fitness;
  }

  public synchronized void setFitness(Integer fitness) {
    this.fitness = fitness;
  }

  public synchronized Integer getId() {
    return id;
  }

  public synchronized void setId(Integer id) {
    this.id = id;
  }

  public synchronized List<Integer> getPositions(){ return this.positions; }

  public synchronized void setPositions(List<Integer> positions){ this.positions = positions; }
}//--
