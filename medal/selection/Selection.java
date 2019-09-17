package medal.selection;

import model.Individuo;

import java.util.LinkedList;

public interface Selection {
    LinkedList<Individuo> selection(LinkedList<Individuo> population, Integer nSelect);
}// -->
