package medal.distribution;

import controller.SaveFile;
import model.Individuo;

import java.util.LinkedList;

public class MedalDistribution implements Distribution{
    private int [][] freqMatrix;

    @Override
    public void createFreqMatrix(int matSize) {
        System.out.println("Inicializando la Matriz de Frecuencias");
        freqMatrix = new int[matSize][matSize];
        for(int[] row : freqMatrix)
            for (int j = 0; j < row.length; j++)
                row[j] = 0;
    }// fin createFreqMatrix

    @Override
    public void calculaFreqMatrix(LinkedList<Individuo> selected) {
        for (Individuo ind : selected){
            for (int i = 0; i < freqMatrix.length; i++)
                    this.freqMatrix[i][ind.getPositions().get(i)]++;
        }// for ind
        validateFreqMatrix(selected.size());
        SaveFile.escribirFreqMatrix(getFreqMatrix(), "Matriz de Frecuencias");
    }// fin calculaFreqMatrix

    @Override
    public int[][] getFreqMatrix() { return this.freqMatrix; }// fin getFreqMatrix

    @Override
    public void setFreqMatrix(int [][] freqMatrix) { this.freqMatrix = freqMatrix; }// fin setFreqMatrix

    private void validateFreqMatrix(int N){
        for (int[] row : freqMatrix) {
            int sum = 0;
            for (int value : row)
                sum += value;
            if (sum > N)
                System.out.println("Error en el cálculo de la Matriz de Frecuencias");
                break;
        }// for row
    }// fin validateFreqMatrix

}// -->
