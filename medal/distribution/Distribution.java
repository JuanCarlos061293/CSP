package medal.distribution;

import model.Individuo;

import java.util.LinkedList;

public interface Distribution {

    void createFreqMatrix(int matSize);
    void calculaFreqMatrix(LinkedList<Individuo> selected);

    int [][] getFreqMatrix();
    void setFreqMatrix(int [][] freqMatrix);
}// -->
