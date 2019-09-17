package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GrupoClase {
  String name;
  List<Integer> seqRank;
  HashMap<Integer, Integer> colores;

  public GrupoClase(String name) {
    this.name = name;
    this.seqRank = new LinkedList<>();
    this.colores = new HashMap<>();
  }

  @Override
  public String toString() {
    return "GrupoClase{" + "name='" + name + '\'' + ", seqRank=" + seqRank + ", clases=" + colores + '}';
  }

  public List<Integer> getSeqRank() {
    return seqRank;
  }

  public HashMap<Integer, Integer> getColores() {
    return colores;
  }
}//-->
