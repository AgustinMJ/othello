package main.domini.classes;

import main.utils.PartidaAcabada;
import main.utils.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AlfaBeta representa l'algorisme Minimax amb podes AlfaBeta. Hereta de la classe abstracta Algorisme.
 *
 * @author Guillem Garcia Gil
 */
public class AlfaBeta extends Algorisme {
    Color colorAlg, colorAltre;
    ModeDeJoc gameMode;

	/**
	 * Constructora que inicialitza l'algorisme.
	 * @param profunditat Profunditat de l'algorisme. Indica quants nivells baixarà l'algorisme en l'arbre de casos.
	 * @param gameMode Mode de joc de la partida on s'executa l'algorisme.
	 * @param c Color de l'algorisme.
	 * @param f Funció heurística que utilitzarà l'algorisme per tal de fer les desicions.
	 */
    public AlfaBeta (int profunditat, ModeDeJoc gameMode, Color c, FuncioHeuristica f) {
        this.profunditat = profunditat;
        this.colorAlg = c;
        if (c.equals(Color.BLANC)) this.colorAltre = Color.NEGRE;
        this.gameMode = gameMode;
        this.fh = f;
    }

	/**
	 * Busca un buit en el taulell de la partida.
	 * @param x_dir Direcció, de la coordenada x, en la que s'estan buscant els buits.
	 * @param y_dir Direcció, de la coordenada y, en la que s'estan buscant els buits.
	 * @param x_pos Posició, de la coordenada x, en la que es busca el buit.
	 * @param y_pos Posició, de la coordenada y, en la que es busca el buit.
	 * @param t Taulell on es busquen els buits.
	 * @return Una tupla d'enters indicant la posició on es troba el buit. En cas de no haver buits, retorna una tupla (-1, -1).
	 */
    private Tuple busca_buit(int x_dir, int y_dir, int x_pos, int y_pos, Taulell t) {

        if (x_pos <= 7 && x_pos >= 0 && y_pos >= 0 && y_pos <=7 && t.getCasella(x_pos, y_pos).equals(Color.BUIT)) return new Tuple(x_pos, y_pos);
        if (x_pos+x_dir < 0 || x_pos+x_dir > 7) return new Tuple(-1, -1);
        if (y_pos+y_dir < 0 || y_pos+y_dir > 7) return new Tuple(-1, -1);
        return busca_buit(x_dir, y_dir, x_pos+x_dir, y_pos+y_dir, t);
    }

	/**
	 * Comprova si el jugador té un moviment vàlid.
	 * @param c Color del juagdor que busca el moviment.
	 * @param x_dir Direcció, de la coordenada x, en la que s'està buscant el moviment.
	 * @param y_dir Direcció, de la coordenada y, en la que s'estan buscant els buits.
	 * @param x_pos Posició, de la coordenada x, en la que s'estan buscant els buits.
	 * @param y_pos Posició, de la coordenada y, en la que s'estan buscant els buits.
	 * @param t Taulell on es busquen els buits.
	 * @return Una tupla d'enters indicant la posició on es troba el moviment vàlid. En cas de no haver buits, retorna una tupla (-1, -1).
	 */
    private Tuple moviment_valid(Color c, int x_dir, int y_dir, int x_pos, int y_pos, Taulell t) {

        Color enemy;
        if (c.name().equals("BLANC")) enemy = Color.NEGRE;
        else enemy = Color.BLANC;
        if (x_pos+x_dir > 7 || x_pos+x_dir < 0) return new Tuple(-1, -1);
        if (y_pos+y_dir > 7 || y_pos+y_dir < 0) return new Tuple(-1, -1);
        if (t.getCasella(x_pos+x_dir, y_pos+y_dir).equals(enemy)) {
            return busca_buit(x_dir, y_dir, x_pos+2*x_dir, y_pos+2*y_dir, t);
        }
        else return new Tuple(-1, -1);
    }

	/**
	 * Busca els possibles moviments.
	 * @param t Taulell on es busquen els moviments.
	 * @param c Color del juagdor que busca els moviments.
	 * @return Una llista de tuples d'enters indicant les possibles posicions on el jugador pot col·locar una peça.
	 */
    protected List<Tuple> getSuccessors(Taulell t, Color c) {
        List<Tuple> successors = new ArrayList();

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {

                if (t.getCasella(i, j).equals(c)) {
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("HORITZONTAL")) {
                        Tuple left = moviment_valid(c, 0, -1, i, j, t);
                        Tuple right = moviment_valid(c, 0, 1, i, j, t);
                        if ((Integer) left.x != -1) successors.add(left);
                        if ((Integer) right.x != -1) successors.add(right);
                    }
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("VERTICAL")) {
                        Tuple up = moviment_valid(c, -1, 0, i, j, t);
                        Tuple down = moviment_valid(c, 1, 0, i, j, t);
                        if ((Integer) up.x != -1) successors.add(up);
                        if ((Integer) down.x != -1) successors.add(down);
                    }
                    if (gameMode.name().equals("CLASSIC") || gameMode.name().equals("DIAGONAL")) {
                        Tuple up_left = moviment_valid(c, -1, -1, i, j, t);
                        Tuple up_right = moviment_valid(c, -1, 1, i, j, t);
                        Tuple down_left = moviment_valid(c, 1, -1, i, j, t);
                        Tuple down_right = moviment_valid(c, 1, 1, i, j, t);
                        if ((Integer) up_left.x != -1) successors.add(up_left);
                        if ((Integer) down_left.x != -1) successors.add(down_left);
                        if ((Integer) up_right.x != -1) successors.add(up_right);
                        if ((Integer) down_right.x != -1) successors.add(down_right);
                    }
                }
            }
        }
        return successors;
    }

	/**
	 * Aplica el moviment del jugador.
	 * @param t Taulell on es vol aplicar el moviment.
	 * @param pos Tupla d'enters indicant la posició del taulell on es vol aplicar el moviment.
	 * @param c Color del jugador que aplica el moviment.
	 * @return El taulell després d'aplicar el moviment.
	 */
    private Taulell aplicaMoviment(Taulell t, Tuple pos, Color c) {

        try {
            t.colocarFitxa((Integer) pos.x, (Integer) pos.y, c, this.gameMode);
            return t;
        } catch (Exception | PartidaAcabada e) {

        }
        return t;
    }

	/**
	 * Busca el minim valor del jugador enemic
	 * @param val Valor del node pare.
	 * @param t Taulell on es busca el mínim valor.
	 * @param depth Valor de la profunditat del node.
	 * @param alpha Valor de la poda alpha.
	 * @param beta Valor de la poda beta.
	 * @return El mínim valor obtingut en la recursivitat.
	 */
    private double minimValor(double val, Taulell t, int depth, double alpha, double beta) {

        List<Tuple> movsPossibles = getSuccessors(t,colorAltre);
        if (movsPossibles.size() == 0 || depth == 0) return val;
        else {
            for (Object i : movsPossibles) {
                Taulell estat = new Taulell(copiaTaulell(t));
                estat = aplicaMoviment(estat, (Tuple) i, colorAlg);
                beta = Math.min(beta, maximValor(fh.retornaValor(estat, colorAltre, colorAlg), estat, depth - 1, alpha, beta));
                if (alpha >= beta) return alpha;
            }
            return beta;
        }
    }

	/**
	 * Busca el minim valor del jugador que executa l'algorisme
	 * @param val Valor del node pare.
	 * @param t Taulell on es busca el màxim valor.
	 * @param depth Valor de la profunditat del node.
	 * @param alpha Valor de la poda alpha.
	 * @param beta Valor de la poda beta.
	 * @return El màxim valor obtingut en la recursivitat.
	 */
    private double maximValor(double val, Taulell t, int depth, double alpha, double beta) {

        List<Tuple> movsPossibles = getSuccessors(t, colorAlg);
        if (movsPossibles.size() == 0 || depth == 0) return val;
        else {

            for (Object i : movsPossibles) {
                Taulell estat = new Taulell(copiaTaulell(t));
                estat = aplicaMoviment(estat, (Tuple) i, colorAlg);
                alpha = Math.max(alpha, minimValor(fh.retornaValor(estat, colorAlg, colorAltre), estat, depth - 1, alpha, beta));
                if (alpha >= beta) return beta;
            }
            return alpha;
        }
    }

    /**
     * Executa l'algorisme minimax amb podes alpha-beta per obtenir el millor següent moviment.
     * @param t Taulell on es busca el màxim valor.
     * @return Una tupla amb el moviment a realitzar.
     */
    @Override
    public Tuple execute(Taulell t) {

        double alpha = Double.NEGATIVE_INFINITY;
        double beta = Double.POSITIVE_INFINITY;
        List<Tuple> movsPossibles = getSuccessors(t, colorAlg);
        Tuple bestMove = new Tuple(-1, -1);
        double max = Double.NEGATIVE_INFINITY;
        for (Object i : movsPossibles) {
            Taulell possible = new Taulell(copiaTaulell(t));
            possible = aplicaMoviment(possible, (Tuple) i, colorAlg);
            double subAlpha = minimValor(fh.retornaValor(possible, colorAlg, colorAltre), possible, this.profunditat, alpha, beta);
            if (subAlpha >= alpha && subAlpha < beta) {
                alpha = subAlpha;
                bestMove = (Tuple) i;
            }
        }

        return bestMove;
    }
}
