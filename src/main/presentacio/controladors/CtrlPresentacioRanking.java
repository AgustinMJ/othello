package main.presentacio.controladors;

import main.domini.controladors.CtrlRanking;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe CtrlPresentacioRanking és el controlador encarregat de demanar a la capa de domini que s'executin les diferents funcionalitats dels rankings i demanar-li dades.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPresentacioRanking {
	CtrlRanking ctrlRanking;

	/**
	 * Constructora per defecte.
	 */
	public CtrlPresentacioRanking() {
		this.ctrlRanking = new CtrlRanking();
	}

	/**
	 * Transforma una fila en forma de mapa de strings en un array d'objectes.
	 * @param pos Posició de la fila en el ranking.
	 * @param fila Mapa de strings representant la fila.
	 * @return Un array d'objectes representant la fila.
	 */
	private Object[] parseFila(int pos, Map<String, String> fila) {
		Object[] parsedFila = {
				pos != -1 ? pos + 1 : "-",
				fila.get("jugador"),
				fila.get("tipus"),
				fila.get("numPartidesGuanyades"),
				fila.get("numPartidesPerdudes"),
				fila.get("numPartidesEmpatades"),
				fila.get("puntuacio")
		};
		return parsedFila;
	}

	/**
	 * Transforma els rankings en forma de mapa de llistes de mapes de strings en una matriu d'objectes.
	 * @param rankings Un mapa de llistes de mapes de strings representant els rankings.
	 * @return Una matriu d'objetces respresentant els rankings.
	 */
	private Map<String, Object[][]> parseRankings(Map<String, ArrayList<Map<String, String>>> rankings) {
		Map<String, Object[][]> parsedRankings = new HashMap<String, Object[][]>();

		rankings.forEach((String key, ArrayList<Map<String, String>> ranking) -> {
			ArrayList<Object[]> files = new ArrayList<Object[]>();
			for(int i = 0; i < ranking.size(); i++) {
				files.add(parseFila(i, ranking.get(i)));
			}
			parsedRankings.put(key, files.toArray(Object[][]::new));
		});

		return parsedRankings;
	}

	/**
	 * Transforma els rankings de l'usuari autenticat en forma de mapa mapes de strings en una matriu d'objectes.
	 * @param rankings Un mapa de mapes de strings representant els rankings.
	 * @return Una matriu d'objetces respresentant els rankings de l'usuari autenticat.
	 */
	private Map<String, Object[][]> parseRankingsUsuari(Map<String, Map<String, String>> rankings) {
		Map<String, Object[][]> parsedRankings = new HashMap<String, Object[][]>();

		rankings.forEach((String key, Map<String, String> ranking) -> {
			ArrayList<Object[]> files = new ArrayList<Object[]>();
			files.add(parseFila(Integer.parseInt(ranking.size() > 0 ? ranking.get("pos") : "-1"), ranking));
			parsedRankings.put(key, files.toArray(Object[][]::new));
		});

		return parsedRankings;
	}

	/**
	 * Demana al controlador del ranking del domini els rankings.
	 * @return Un mapa de matrius d'objectes representant els rankings.
	 */
	public Map<String, Object[][]> getRanking() throws FileNotFoundException {
		Map<String, ArrayList<Map<String, String>>> rankings = this.ctrlRanking.getRanking();
		return this.parseRankings(rankings);
	}

	/**
	 * Demana al controlador del ranking del domini els rankings d'un jugador donat.
	 * @param nom El nom del jugador.
	 * @return Un mapa de matrius d'objectes representant els rankings del jugador donat.
	 */
	public Map<String, Object[][]> getRankingJugador(String nom) throws FileNotFoundException {
		Map<String, Map<String, String>> rankings = this.ctrlRanking.getRankingJugador(nom);
		return this.parseRankingsUsuari(rankings);
	}
}
