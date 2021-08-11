package main.domini.classes;

/**
 * La classe HeuristicaTaulell representa l'heurística on cada posició del taulell té una puntuació, l'objectiu és col·locar la peça a la posició amb més puntuació. Hereda de la classe abstracta Heuristica.
 *
 * @author Guillem Garcia Gil
 */
public class HeuristicaTaulell extends FuncioHeuristica {
    private static int[][] valueMatrix = {
            {100, -20, 10, 5, 5, 10, -20, 100},
            {-20, -50, -2, -2, -2, -2, -50, -20},
            {10, -2, -1, -1, -1, -1, -2, -10},
            {5, -2, -1, -1, -1, -1, -2, 5},
            {5, -2, -1, -1, -1, -1, -2, 5},
            {10, -2, -1, -1, -1, -1, -2, -10},
            {-20, -50, -2, -2, -2, -2, -50, -20},
            {100, -20, 10, 5, 5, 10, -20, 100}
    };

	/**
	 * Constructora per defecte.
	 */
    public HeuristicaTaulell() {
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
        double heuristic = 0;

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (t.getCasella(i, j).equals(colorAlg)) heuristic += valueMatrix[i][j];
                else if (t.getCasella(i, j).equals(colorEnemic)) heuristic += -1*valueMatrix[i][j];
            }
        }

        return heuristic;
    }

}
