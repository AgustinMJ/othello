package main.domini.classes;

/**
 * La classe HeuristicaNombrePeces representa l'heurística on es busca menjar el màxim nombre de peces. Hereda de la classe abstracta Heuristica.
 *
 * @author Guillem Garcia Gil
 */
public class HeuristicaNombrePeces extends FuncioHeuristica{
	/**
	 * Constructora per defecte.
	 */
    public HeuristicaNombrePeces() {
    }

	/**
	 * Obté el valor després d'aplicar l'heurística.
	 * @param t Taulell on s'aplica l'heurística.
	 * @param colorAlg Color de l'algorisme que aplica l'heurística.
	 * @param colorEnemic Color de l'enemix
	 * @return Un double indicant el valor de l'heurística.
	 */
    @Override
    public double retornaValor(Taulell t, Color colorAlg, Color colorEnemic) {
        double nPecesColorAlg = 0;

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (t.getCasella(i, j).equals(colorAlg)) ++nPecesColorAlg;

            }
        }
        return nPecesColorAlg;
    }
}
