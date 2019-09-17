package bumda.distribution;

import model.Individuo;

import java.util.List;

public class DistUMDA implements Distribution
{

    private int[] mediaColor, mediaClase, sigmaColor, sigmaClase;

    @Override
    public void calculaMedia(List<Individuo> population, Character obj) {

    }

    @Override
    public void calculaSigma(List<Individuo> population, Character obj) {

    }

    //@Override
    //public void calculaEstadisticos(List<Individuo> population, Character obj, List<Vehicle> autosSec) {}

    // Calcula los estadisticos por Clase


    public void calculaMediaClase(List<Individuo> population) {
        mediaClase = new int[population.get(0).getCromosomas().size()];
        for (int i = 0; i < mediaClase.length; i++) {
            double sum = 0;
            for (Individuo ind : population){
                sum += ind.getCromosomas().get(i).getClase();
            }// for ind
            mediaClase[i] = (int)(sum / population.size());
        }// for i
    }// fin calculaMediaClase


    public void calculaSigmaClase(List<Individuo> population, int[] media) {
        sigmaClase = new int[population.get(0).getCromosomas().size()];
        for (int i = 0; i < sigmaClase.length; i++) {
            double sum = 0;
            for (Individuo ind :population) {
                sum += Math.pow((ind.getCromosomas().get(i).getClase() - media[i]), 2);
            }// for ind
            sigmaClase[i] = (int)(Math.sqrt(sum / population.size()));
        }// for i
    }// fin calculaSigmaClase

    // Calcula los estadisticos por Color

    public void calculaMediaColor(List<Individuo> population) {
        mediaColor = new int[population.get(0).getCromosomas().size()];
        for (int i = 0; i < mediaColor.length; i++) {
            double sum = 0;
            for (Individuo ind : population){
                sum += ind.getCromosomas().get(i).getColor();
            }// for ind
            mediaColor[i] = (int)(sum / population.size());
        }// for i
    }// fin calculaMediaColor


    public void calculaSigmaColor(List<Individuo> population, int[] media) {
        sigmaColor = new int[population.get(0).getCromosomas().size()];
        for (int i = 0; i < sigmaColor.length; i++) {
            double sum = 0;
            for (Individuo ind :population) {
                sum += Math.pow((ind.getCromosomas().get(i).getColor() - media[i]), 2);
            }// for ind
            sigmaColor[i] = (int)(Math.sqrt(sum / population.size()));
        }// for i
    }// fin calculaSigmaColor

    // gettes and settes
    @Override
    public int[] getMediaColor() {
        return mediaColor;
    }
    @Override
    public void setMediaColor(int[] mediaColor) {
        this.mediaColor = mediaColor;
    }
    @Override
    public int[] getMediaClase() {
        return mediaClase;
    }
    @Override
    public void setMediaClase(int[] mediaClase) {
        this.mediaClase = mediaClase;
    }
    @Override
    public int[] getSigmaColor() {
        return sigmaColor;
    }
    @Override
    public void setSigmaColor(int[] sigmaColor) {
        this.sigmaColor = sigmaColor;
    }
    @Override
    public int[] getSigmaClase() {
        return sigmaClase;
    }
    @Override
    public void setSigmaClase(int[] sigmaClase) {
        this.sigmaClase = sigmaClase;
    }
    @Override
    public int[] getMedia() {
        return new int[0];
    }

    @Override
    public void setMedia(int[] media) {

    }

    @Override
    public int[] getSigma() {
        return new int[0];
    }

    @Override
    public void setSigma(int[] sigma) {

    }

    @Override
    public void calculaMediaClase(List<Individuo> population, Character obj) {

    }

    @Override
    public void calculaSigmaClase(List<Individuo> population, int[] media, Character obj) {

    }

    @Override
    public void calculaMediaColor(List<Individuo> population, Character obj) {

    }

    @Override
    public void calculaSigmaColor(List<Individuo> population, int[] media, Character obj) {

    }
}//-->
