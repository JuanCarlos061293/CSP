package bumda.distribution;

import model.Individuo;

import java.util.List;

public interface Distribution {
    //void calculaEstadisticos(List<Individuo> population, Character obj);
    void calculaMedia(List<Individuo> population, Character obj);
    void calculaSigma(List<Individuo> population, Character obj);

    int[] getMedia();
    void setMedia(int[] media);
    int[] getSigma();
    void setSigma(int[] sigma);

    void calculaMediaClase(List<Individuo> population, Character obj);
    void calculaSigmaClase(List<Individuo> population, int[] media, Character obj);
    void calculaMediaColor(List<Individuo> population, Character obj);
    void calculaSigmaColor(List<Individuo> population, int[] media, Character obj);

    int[] getMediaColor();
    void setMediaColor(int[] mediaColor);
    int[] getMediaClase();
    void setMediaClase(int[] mediaClase);
    int[] getSigmaColor();
    void setSigmaColor(int[] sigmaColor);
    int[] getSigmaClase();
    void setSigmaClase(int[] sigmaClase);

}//-->
