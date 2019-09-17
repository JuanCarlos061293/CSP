package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GrupoColor {
  String name;
  List<Integer> seqRank;
  HashMap<Integer, Integer> clases; // llave - clase, valor - cantidad

  public GrupoColor(String name) {
    this.name = name;
    this.seqRank = new LinkedList<>();
    this.clases = new HashMap<>();
  }

  @Override
  public String toString() {
    return "GrupoColor{" + "name='" + name + '\'' + ", seqRank=" + seqRank + ", clases=" + clases + '}';
  }

  public List<Integer> getSeqRank() {
    return seqRank;
  }

  public HashMap<Integer, Integer> getClases() {
    return clases;
  }

}//-->
