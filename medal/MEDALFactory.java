package medal;

import medal.distribution.Distribution;
import medal.muestreo.Muestreo;
import medal.population.Population;
import medal.selection.Selection;

/**
 * AbstractFactory (AbstractFactory Pattern). Declares an interface for operations that create abstract product objects.
 * Only declares an interface for creating products. It's up to ConcreteProduct subclasses to actually create them.
 *
 * [AbstractFactory (AbstractFactory Pattern). Declara una interfaz para las operaciones
 * que crean objetos de productos abstractos. Solo declara una interfaz para crear productos. Depende de las subclases
 * de ConcreteProduct crearlas realmente.]
 *    
 * Creator (Factory Method Pattern). Declares the factory method, which returns an object of type Product.
 * Creator may also define a default implementation of the factory method that returns a default ConcreteProduct.
 *
 * [Creador (Patrón de método de fábrica). Declara el método de fábrica, que devuelve un objeto de tipo Producto. El
 * creador también puede definir una implementación predeterminada del método de fábrica que devuelve un Producto
 * concreto predeterminado]
 *
 * Object. May call the factory method to create a Product object.
 * [Objeto. Puede llamar al método de fábrica para crear un objeto Producto.]
 */
public interface MEDALFactory {
    Population createPopulation(String population);
    Muestreo choiceMuestreo(String muestreo);
    Distribution choiceDistribution(String distribution);
    Selection chooiceSelection(String selection);
}// -->
