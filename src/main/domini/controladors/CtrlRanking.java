package main.domini.controladors;

import main.domini.classes.*;
import main.persistencia.controladors.CtrlPersistenciaRanking;
import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.Tuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe CtrlRanking és un controlador de domini que s'encarrega d'executar funcionalitats dels rankings, demanar dades a la persistència i donar-les a la presentació.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlRanking {
	CtrlPersistenciaRanking ctrlPersistenciaRanking;

	/**
	 * Constructora que inicialitza el controlador.
	 */
	public CtrlRanking() {
		this.ctrlPersistenciaRanking = new CtrlPersistenciaRanking();
	}

	/**
	 * Transforma una fila en un mapa de strings.
	 * @param f Fila a transformar.
	 * @param pos Posició de la fila en el ranking.
	 * @return La fila en forma de mapa de strings.
	 */
	private Map<String, String> rawFila(Fila f, int pos) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("pos", String.valueOf(pos));
		map.put("jugador", String.valueOf(f.getJugador().getNom()));
		map.put("tipus", f.getJugador() instanceof Usuari ? "Usuari" : "Maquina");
		map.put("numPartidesGuanyades", String.valueOf(f.getNumPartidesGuanyades()));
		map.put("numPartidesPerdudes", String.valueOf(f.getNumPartidesPerdudes()));
		map.put("numPartidesEmpatades", String.valueOf(f.getNumPartidesEmpatades()));
		map.put("puntuacio",String.valueOf(f.getPuntuacio()));

		return map;
	}

	/**
	 * Transforma un mapa de strings representant una fila en un objecte Fila.
	 * @param f mapa de strings representant la fila a transformar.
	 * @return La fila.
	 */
	private Fila parseFila(Map<String, String> f) {
		String nomJugador = f.get("jugador");
		return new Fila(
			f.get("tipus").equals("Usuari") ? new Usuari(nomJugador) : new Maquina(nomJugador),
			Integer.parseInt(f.get("numPartidesGuanyades")),
			Integer.parseInt(f.get("numPartidesPerdudes")),
			Integer.parseInt(f.get("numPartidesEmpatades")),
			Integer.parseInt(f.get("puntuacio"))
		);
	}

	/**
	 * Transforma una llista de mapes de strings representant un conjunt de files en un objecte Ranking.
	 * @param rawRanking Conjunt de files a transformar.
	 * @return El ranking.
	 */
	private Ranking parseRanking(ArrayList<Map<String, String>> rawRanking) {
		Files<Fila> r = new Files<Fila>();
		for(Map<String, String> fh : rawRanking) {
			r.add(parseFila(fh));
		}

		return new Ranking(r);
	}

	/**
	 * Demana a la capa de persistència els rankings.
	 * @return Un mapa de llistes de mapes de strings representant els diferents rankings.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRanking() throws FileNotFoundException {
		return this.ctrlPersistenciaRanking.getRanking();
	}

	/**
	 * Demana a la capa de persistència el ranking del mode de joc donat i el ranking total.
	 * @param mode El mode de joc desitjat.
	 * @return Un mapa de llistes de mapes de strings representant els dos rankings obtinguts.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRankingByMode(String mode) throws FileNotFoundException {
		return this.ctrlPersistenciaRanking.getRankingByMode(mode);
	}

	/**
	 * Demana a la capa de persistència els rankings del jugador donat.
	 * @param nom El nom del jugador del qual volem els rankings.
	 * @return Un mapa de mapes de strings representant les files del jugador en els diferents rankings.
	 */
	public Map<String, Map<String, String>> getRankingJugador(String nom) throws FileNotFoundException {
		return this.ctrlPersistenciaRanking.getRankingJugador(nom);
	}

	/**
	 * Actualitza el ranking de dos jugadors donats i després demana a la persistència que guardi els canvis.
	 * @param mode El mode de la partida que s'ha jugat.
	 * @param guanyador Nom del jugador guanyador.
	 * @param perdedor Nom del jugador perdedor.
	 * @param tipusGuanyador Tipus de jugador del jugador guanyador.
	 * @param tipusPerdedor Tipus de jugador del jugador perdedor.
	 * @param empat Indica si la partida ha acabat en empat.
	 */
	public void updateRanking(String mode, String guanyador, String perdedor, String tipusGuanyador, String tipusPerdedor, boolean empat) throws IOException, FilaEsTotal, FilaNoTotal {
		Map<String,  ArrayList<Map<String, String>>> rankings = this.getRankingByMode(mode);
		Ranking rankingMode = parseRanking(rankings.get("mode"));
		Ranking rankingTotal = parseRanking(rankings.get("total"));

		//Actualitzem el ranking del mode
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> posMode = rankingMode.updateRanking(
				tipusGuanyador.equals("Usuari") ? new Usuari(guanyador) : new Maquina(guanyador),
				tipusPerdedor.equals("Usuari") ? new Usuari(perdedor) : new Maquina(perdedor),
				empat
		);

		//Actualitzem el ranking total
		Tuple<Tuple<Integer, Integer>, Tuple<Integer, Integer>> posTotal = rankingTotal.updateRanking(
				tipusGuanyador.equals("Usuari") ? new Usuari(guanyador) : new Maquina(guanyador),
				tipusPerdedor.equals("Usuari") ? new Usuari(perdedor) : new Maquina(perdedor),
				empat
		);

		//Propaguem els canvis a la persistencia
		this.ctrlPersistenciaRanking.updateRanking(rawFila(rankingMode.getFiles().get(posMode.x.y), posMode.x.y), rawFila(rankingTotal.getFiles().get(posTotal.x.y), posTotal.x.y), mode, posTotal.x.x, posMode.x.x);
		this.ctrlPersistenciaRanking.updateRanking(rawFila(rankingMode.getFiles().get(posMode.y.y), posMode.y.y), rawFila(rankingTotal.getFiles().get(posTotal.y.y), posTotal.y.y), mode, posTotal.y.x, posMode.y.x);
	}
}
