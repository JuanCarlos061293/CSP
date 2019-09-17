package bumda.distribution;

import model.Individuo;

import java.util.List;

public class DistBUMDA implements Distribution {

    private int[] mediaColor, mediaClase, sigmaColor, sigmaClase;
    private int[] media, sigma;

    public void calculaMedia(List<Individuo> population, Character obj){
        media = new int[population.get(0).getPositions().size()];
        double g;
        // Se calcula la media dependiendo del objetivo prioritario
        switch (obj){
            case 'H':
                for (int i = 0; i < media.length; i++) {
                    double num = 0, den = 0;
                    for (Individuo ind : population){
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += ind.getPositions().get(i) * g;
                        den += g;
                    }// for ind
                    media[i] = (int)(num / den);
                }// for i
            break;
            case 'C':
                for (int i = 0; i < media.length; i++) {
                    double num = 0, den = 0;
                    for (Individuo ind : population){
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += ind.getPositions().get(i) * g;
                        den += g;
                    }// for ind
                    media[i] = (int)(num / den);
                }// for i
                break;
            case 'L':
                for (int i = 0; i < media.length; i++) {
                    double num = 0, den = 0;
                    for (Individuo ind : population){
                        g = (ind.getfL() - population.get(population.size()-1).getfL()) + 1;
                        num += ind.getPositions().get(i) * g;
                        den += g;
                    }// for ind
                    media[i] = (int)(num / den);
                }// for i
                break;
            default:
                for(int i = 0; i < media.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += ind.getPositions().get(i) * g;
                        den += g;
                    }// for ind
                    media[i] = (int)(num / den);
                }// for i
                break;
        }//switch
    }// fin calculaMedia

    public void calculaSigma(List<Individuo> population, Character obj){
        sigma = new int[population.get(0).getPositions().size()];
        double g;
        // Se calcula la Desviacion Estandar dependiendo del objetivo prioritario
        switch (obj){
            case 'H':
                for (int i = 0; i < sigma.length; i++){
                    double num = 0, den = 0;
                    for (Individuo ind : population){
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += g * Math.pow((ind.getPositions().get(i) - getMedia()[i]), 2);
                        den += g;
                    }// for ind
                    sigma[i] = (int)(num / (den + 1));
                }// for i
                break;
            case 'C':
                for (int i = 0; i < sigma.length; i++){
                    double num = 0, den = 0;
                    for (Individuo ind : population){
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += g * Math.pow((ind.getPositions().get(i) - getMedia()[i]), 2);
                        den += g;
                    }// for ind
                    sigma[i] = (int)(num / (den + 1));
                }// for i
                break;
                case 'L':
                    for (int i = 0; i < sigma.length; i++){
                        double num = 0, den = 0;
                        for (Individuo ind : population){
                            g = (ind.getfL() - population.get(population.size()-1).getfL()) + 1;
                            num += g * Math.pow((ind.getPositions().get(i) - getMedia()[i]), 2);
                            den += g;
                        }// for ind
                        sigma[i] = (int)(num / (den + 1));
                    }// for i
                break;
            default:
                for (int i = 0; i < sigma.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += g * Math.pow((ind.getPositions().get(i) - getMedia()[i]), 2);
                        den += g;
                    }// for ind
                    sigma[i] = (int)(num / (den + 1));
                }// for i
                break;
        }// switch
    }// fin calculaSigma


    // Calcula los estadisticos por Clase

    @Override
    public void calculaMediaClase(List<Individuo> population, Character obj) {
        mediaClase = new int[population.get(0).getCromosomas().size()];
        double g; // g = g→(x)
        switch (obj){
            case 'H':
                for(int i = 0; i < mediaClase.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += ind.getCromosomas().get(i).getClase() * g;
                        den += g;
                    }// for ind
                    mediaClase[i] = (int)(num / den);
                }// for i
                break;
            case 'C':
                for(int i = 0; i < mediaClase.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += ind.getCromosomas().get(i).getClase() * g;
                        den += g;
                    }// for ind
                    mediaClase[i] = (int)(num / den);
                }// for i
                break;
            case 'L':
                for(int i = 0; i < mediaClase.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfL() - population.get(population.size()-1).getfL()) + 1;
                        num += ind.getCromosomas().get(i).getClase() * g;
                        den += g;
                    }// for ind
                    mediaClase[i] = (int)(num / den);
                }// for i
                break;
            default:
                for(int i = 0; i < mediaClase.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += ind.getCromosomas().get(i).getClase() * g;
                        den += g;
                    }// for ind
                    mediaClase[i] = (int)(num / den);
                }// for i
                break;
        }//switch

    }// fin calculaMediaClase

    @Override
    public void calculaSigmaClase(List<Individuo> population, int[] media, Character obj) {
        sigmaClase = new int[population.get(0).getCromosomas().size()];
        double g;  //g -> g→(x)
        switch (obj){
            case 'H':
                for (int i = 0; i < sigmaClase.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getClase() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaClase[i] = (int)(num / (den + 1));
                }// for i
                break;
            case 'C':
                for (int i = 0; i < sigmaClase.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getClase() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaClase[i] = (int)(num / (den + 1));
                }// for i
                break;
            case 'L':
                for (int i = 0; i < sigmaClase.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfL() - population.get(population.size()-1).getfL()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getClase() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaClase[i] = (int)(num / (den + 1));
                }// for i
                break;
            default:
                for (int i = 0; i < sigmaClase.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getClase() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaClase[i] = (int)(num / (den + 1));
                }// for i
                break;
        }// switch

    }// fin calculaSigmaClase

    // Calcula los estadisticos por Color

    @Override
    public void calculaMediaColor(List<Individuo> population, Character obj) {
        mediaColor = new int[population.get(0).getCromosomas().size()];
        double g; // g = g→(x)
        switch (obj){
            case 'H':
                for(int i = 0; i < mediaColor.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += ind.getCromosomas().get(i).getColor() * g;
                        den += g;
                    }// for ind
                    mediaColor[i] =(int)(num / den);
                }// for i
                break;
            case 'C':
                for(int i = 0; i < mediaColor.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += ind.getCromosomas().get(i).getColor() * g;
                        den += g;
                    }// for ind
                    mediaColor[i] =(int)(num / den);
                }// for i
                break;
            case 'L':
                for(int i = 0; i < mediaColor.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getfL() - population.get(population.size()-1).getfL()) + 1;
                        num += ind.getCromosomas().get(i).getColor() * g;
                        den += g;
                    }// for ind
                    mediaColor[i] =(int)(num / den);
                }// for i
                break;
            default:
                for(int i = 0; i < mediaColor.length; i++){
                    double num = 0, den = 0; // numerador y denminador de la formula
                    for (Individuo ind :population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += ind.getCromosomas().get(i).getColor() * g;
                        den += g;
                    }// for ind
                    mediaColor[i] =(int)(num / den);
                }// for i
                break;

        }// switch
    }// fin calculaMediaColor

    @Override
    public void calculaSigmaColor(List<Individuo> population, int[] media, Character obj) {
        sigmaColor = new int[population.get(0).getCromosomas().size()];
        double g;  //g -> g→(x)
        switch (obj){
            case 'H':
                for (int i = 0; i < sigmaColor.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfH() - population.get(population.size()-1).getfH()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getColor() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaColor[i] = (int)(num / (den + 1));
                }// for i
                break;
            case 'C':
                for (int i = 0; i < sigmaColor.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfC() - population.get(population.size()-1).getfC()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getColor() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaColor[i] = (int)(num / (den + 1));
                }// for i
                break;
            case 'L':
                for (int i = 0; i < sigmaColor.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getfH() - population.get(population.size()-1).getfL()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getColor() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaColor[i] = (int)(num / (den + 1));
                }// for i
                break;
            default:
                for (int i = 0; i < sigmaColor.length; i++) {
                    double num = 0, den = 0; // num y den -> numerador y denominador de la formula
                    for (Individuo ind : population) {
                        g = (ind.getFitness() - population.get(population.size()-1).getFitness()) + 1;
                        num += g * Math.pow((ind.getCromosomas().get(i).getColor() - media[i]), 2);
                        den += g;
                    }// for ind
                    sigmaColor[i] = (int)(num / (den + 1));
                }// for i
                break;
        }// switch
    }// fin calculaSigmaColor

    // gettes and settes
    @Override
    public int[] getMediaColor() { return mediaColor; }
    @Override
    public void setMediaColor(int[] mediaColor) { this.mediaColor = mediaColor; }
    @Override
    public int[] getMediaClase() { return mediaClase; }
    @Override
    public void setMediaClase(int[] mediaClase) { this.mediaClase = mediaClase; }
    @Override
    public int[] getSigmaColor() { return sigmaColor; }
    @Override
    public void setSigmaColor(int[] sigmaColor) { this.sigmaColor = sigmaColor; }
    @Override
    public int[] getSigmaClase() { return sigmaClase; }
    @Override
    public void setSigmaClase(int[] sigmaClase) { this.sigmaClase = sigmaClase; }

    @Override
    public int[] getMedia() { return this.media; }
    @Override
    public void setMedia(int[] media) { this.media = media; }
    @Override
    public int[] getSigma() { return sigma; }
    @Override
    public void setSigma(int[] sigma) { this.sigma = sigma; }
}//-->
