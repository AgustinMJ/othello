package main.domini.classes;

/**
 * La classe FuncioHeuristica és una classe abstracta que representa una funció heurística. Aquesta classe l'heredaran les diferents funcions heruístiques del joc.
 *
 * @author Guillem Garcia Gil
 */
public abstract class FuncioHeuristica {

	/**
	 * Obté el valor després d'aplicar l'heurística.
	 * @param t Taulell on s'aplica l'heurística.
	 * @param colorAlg Color de l'algorisme que aplica l'heurística.
	 * @param colorEnemic Color de l'enemix
	 * @return Un double indicant el valor de l'heurística.
	 */
    public abstract double retornaValor(Taulell t, Color colorAlg, Color colorEnemic);
}
