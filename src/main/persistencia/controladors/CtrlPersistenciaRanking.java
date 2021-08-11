package main.persistencia.controladors;

import main.persistencia.classes.PersistenciaRanking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe CtrlPersistenciaRanking és el controlador encarregat de demanar a la persistència dels rankings les dades i servir-les a la capa de domini.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPersistenciaRanking {
	PersistenciaRanking persistenciaRanking;

	/**
	 * Constructora per defecte.
	 */
	public CtrlPersistenciaRanking() {
		persistenciaRanking = new PersistenciaRanking();
	}

	/**
	 * Demana a la persistència dels rankings els rankings.
	 * @return Un mapa de llistes de mapes strings representant els rankings.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRanking() throws FileNotFoundException {
		return this.persistenciaRanking.getRanking();
	}

	/**
	 * Demana a la persistència dels rankings els rankings del jugador donat.
	 * @return Un mapa de mapes strings representant els rankings del jugadpor.
	 */
	public Map<String, Map<String, String>> getRankingJugador(String nom) throws FileNotFoundException {
		return this.persistenciaRanking.getRankingJugador(nom);
	}

	/**
	 * Demana a la persistència dels rankings que actualitzi el ranking d'un mode i total amb els paràmetres obtinguts.
	 * @param filaMode Mapa de strings representant la fila del ranking del mode de joc a actualitzar.
	 * @param filaTotal Mapa de strings representant la fila del ranking total a actualitzar.
	 * @param mode Mode de joc a actualitzar.
	 * @param oldPosMode Antiga posició de la fila en el ranking del mode de joc.
	 * @param oldPosTotal Antiga posició de la fila en el ranking total.
	 */
	public void updateRanking(Map<String, String> filaMode, Map<String, String> filaTotal, String mode, int oldPosTotal, int oldPosMode) throws IOException {
		this.persistenciaRanking.updateRanking(filaMode, filaTotal, mode, oldPosTotal, oldPosMode);
	}

	/**
	 * Demana a la persistència dels rankings els ranking del mdoe de joc especificat i el ranking total.
	 * @param mode Mode de joc desitjat.
	 * @return Un mapa de llistes de mapes strings representant els ranking del mode de joc i total.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRankingByMode(String mode) throws FileNotFoundException {
		return this.persistenciaRanking.getRankingByMode(mode);
	}
}
