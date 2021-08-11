package main.domini.classes;

import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.Tuple;

/**
 * La classe Ranking representa un ranking del joc.
 *
 * @author Agustín Millán Jiménez
 */
public class Ranking {
	Files<Fila> files;

	/**
	 * Constructora que inicialitza el ranking.
	 * @param f Files del ranking.
	 */
	public Ranking(Files<Fila> f){
		this.files = f;
	}

	/**
	 * Obté les files del ranking.
	 * @return Les files del ranking.
	 */
	public Files<Fila> getFiles() {
		return this.files;
	}

	/**
	 * Actualitza el ranking de dos jugadors.
	 * @param guanyador jugador guanyador de la partida.
	 * @param perdedor jugador perdedor de la partida.
	 * @param empat indica si la partida ha acabat en empat.
	 * @return Una tupla de tuples d'enters indicant les posicions antigues i noves dels jugadors.
	 */
	public Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> updateRanking(Jugador guanyador, Jugador perdedor, boolean empat) throws FilaEsTotal, FilaNoTotal {
		Tuple<Integer, Integer> posGuanyador;
		Tuple<Integer, Integer> posPerdedor;
		if (!empat) {
			posGuanyador = this.files.updateFila(guanyador, Accions.VICTORIA);
			posPerdedor = this.files.updateFila(perdedor, Accions.DERROTA);
		} else{
			posGuanyador = this.files.updateFila(guanyador, Accions.EMPAT);
			posPerdedor = this.files.updateFila(perdedor, Accions.EMPAT);
		}

		return new Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> (posGuanyador, posPerdedor);
	}
}
