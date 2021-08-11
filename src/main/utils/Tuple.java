package main.utils;

/**
 * La classe Tuple representa una tupla.
 *
 * @author Agustín Millán Jiménez
 */
public class Tuple<X, Y> {
    public final X x;
    public final Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}