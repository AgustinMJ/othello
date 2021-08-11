package main.domini.classes;

import main.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Algorisme és una classe abstracta que representa un algorisme. Aquesta classe l'heredaran els diferents algorismes que tenim en el joc.
 *
 * @author Guillem Garcia Gil
 */
public abstract class Algorisme {
    protected int profunditat;
    protected FuncioHeuristica fh;

	/**
	 * Busca els possibles moviments.
	 * @param t Taulell on es busquen els moviments.
	 * @param c Color del juagdor que busca els moviments.
	 * @return Una llista de tuples d'enters indicant les possibles posicions on el jugador pot col·locar una peça.
	 */
    protected abstract List getSuccessors(Taulell t, Color c);

	/**
	 * Executa l'algorisme
	 * @param t Taulell on s'executa l'algorisme.
	 * @return Una tupla d'enters indicant on l'algorisme vol col·locar la peça.
	 */
    public abstract Tuple execute(Taulell t);

    /**
     * Realitza una copia de taulell.
     * @param t Taulell a realitzar la copia.
     * @return Una matriu de Color amb el contingut del taulell.
     */
    protected Color[][] copiaTaulell (Taulell t) {

        Color[][] matriu = new Color[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                matriu[i][j] = t.getCasella(i, j);
            }
        }
        return matriu;
    }
}
