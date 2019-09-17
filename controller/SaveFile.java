package controller;

import model.Individuo;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class SaveFile {

  public static void deleteFile(String name){
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\" + name;
    File nameFile = new File(path);

    try {
      if (nameFile.exists()){
        nameFile.delete();
      }// fin if
    } catch (Exception e) {
      e.printStackTrace();
    }// fin try
  }

  public static void writeLnMsj(String name, String msj) {
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\" + name;
    File nameFile = new File(path);
    FileWriter oFileWriter = null;
    PrintWriter oPrintWriter = null;

    try{
      oFileWriter = new FileWriter (nameFile,true);
      oPrintWriter = new PrintWriter(oFileWriter);
      oPrintWriter.println(msj);
    }catch(IOException e){
      System.out.println("Tipo de Error: " + e.getClass().getSimpleName()
          + ", " + e.getMessage());
    }finally{
      try{
        if( null != oFileWriter ){
          oFileWriter.close();
        }//Fin if
      }catch (IOException e2){
        System.out.println("Tipo de Error: " + e2.getClass().getSimpleName()
            + ", " + e2.getMessage());
      }// Fin Try
    }//Fin Try
  }

  public static void writeMsj(String name, String msj) {
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\" + name;
    File nameFile = new File(path);
    FileWriter oFileWriter = null;
    PrintWriter oPrintWriter = null;

    try{
      oFileWriter = new FileWriter (nameFile,true);
      oPrintWriter = new PrintWriter(oFileWriter);
      oPrintWriter.print(msj);
    }catch(IOException e){
      System.out.println("Tipo de Error: " + e.getClass().getSimpleName()
          + ", " + e.getMessage());
    }finally{
      try{
        if( null != oFileWriter ){
          oFileWriter.close();
        }//Fin if
      }catch (IOException e2){
        System.out.println("Tipo de Error: " + e2.getClass().getSimpleName()
            + ", " + e2.getMessage());
      }// Fin Try
    }//Fin Try
  }

  public static void escribirPoblacion(LinkedList<Individuo> poblacion, String name){
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\" + name + ".txt";
    try{
      PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))));
      for (Individuo i : poblacion) {
        //wr.append("Individuo "+i.getId()+": "+ i.getCromosomas().toString()+"\n");
        wr.append("Individuo "+i.getId()+": "+ i.getPositions().toString()+"\n");
        wr.append("Con FH: "+i.getfH()+"\t");
        wr.append("Con FL: "+i.getfL()+"\t");
        wr.append("Con FC: "+i.getfC()+"\t");
        wr.append("Con Fitness Total: "+i.getFitness());
        wr.println();
      }//for
      wr.close();
    }catch(IOException e){
      System.out.println("    ERROR   " +e.getLocalizedMessage());
    }//catch
  }// fin escribirPoblacion

  public static void escribirFreqMatrix(int [][] freqMatrix, String name){
    String path = System.getProperty("user.dir") + "\\src\\main\\resources\\" + name + ".txt";
    try {
      PrintWriter wr = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))));
      for (int[] row : freqMatrix) {
        wr.println(Arrays.toString(row));
      }// for
      wr.close();
    }catch(IOException e){
      System.out.println("    ERROR   " +e.getLocalizedMessage());
    }//catch
  }// fin escribirFreqMatrix

}//-->
