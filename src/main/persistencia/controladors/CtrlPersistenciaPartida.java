package main.persistencia.controladors;

import main.persistencia.classes.PersistenciaPartida;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe CtrlPersistenciaPartida és el controlador encarregat de demanar a la persistència de les partides les dades i servir-les a la capa de domini.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPersistenciaPartida {
	PersistenciaPartida persistenciaPartida;

	/**
	 * Constructora per defecte.
	 */
	public CtrlPersistenciaPartida() {
		this.persistenciaPartida = new PersistenciaPartida();
	}

	/**
	 * Demana a la persistència de les partides que crei una partida a partir dels paràmetres obtinguts.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusJugadorBlanc Tipus de jugador del jugador blanc.
	 * @param tipusJugadorNegre Tipus de jugador del jugador negre.
	 * @param taulell Taulell en forma de string.
	 * @param mode Mode de joc de la partida.
	 * @param torn Torn inicial de la partida.
	 * @return L'id de la partida creada.
	 */
	public int creaPartida(String jugadorBlanc, String jugadorNegre, String tipusJugadorBlanc, String tipusJugadorNegre, String taulell, String mode, String torn) throws IOException {
		return this.persistenciaPartida.creaPartida(jugadorBlanc, jugadorNegre, tipusJugadorBlanc, tipusJugadorNegre, taulell, mode, torn);
	}

	/**
	 * Demana a la persistència de les partides que guardi la partida amb els paràmetres obtinguts.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param taulell Taulell de la partida en forma de string.
	 * @param torn Torn de la partida.
	 * @param finalitzada Indica si la partida ha finalitzat.
	 */
	public void guardaPartida(String id, String jugadorBlanc, String jugadorNegre, String taulell, String torn, boolean finalitzada) throws IOException {
		this.persistenciaPartida.guardaPartida(id, jugadorBlanc, jugadorNegre, taulell, torn, finalitzada);
	}

	/**
	 * Demana a la persistència de les partides que elimini la partida amb l'id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param finalitzada Indica si la partida ha finalitzat.
	 */
	public void eliminaPartida(String id, String jugadorBlanc, String jugadorNegre, boolean finalitzada) {
		this.persistenciaPartida.eliminaPartida(id, jugadorBlanc, jugadorNegre, finalitzada);
	}

	/**
	 * Demana a la persistència de les partides la partida amb l'id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return Un mapa de strings representant la partida.
	 */
	public Map<String, String> getPartida(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		return this.persistenciaPartida.getPartida(id, jugadorBlanc, jugadorNegre);
	}

	/**
	 * Demana a la persistència les partides de l'usuari indicat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesUsuari(String nom) throws FileNotFoundException {
		return this.persistenciaPartida.getPartidesUsuari(nom);
	}

	/**
	 * Demana a la persistència les partides no finalitzades de l'usuari indicat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides no finalitzades de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesNoFinalitzadesUsuari(String nom) throws FileNotFoundException {
		return this.persistenciaPartida.getPartidesNoFinalitzadesUsuari(nom);
	}

	/**
	 * Demana a la persistència el taulell inicial de la partida amb id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return El taulell en forma de string.
	 */
	public String getTaulellInicial(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		return this.persistenciaPartida.getTaulellInicial(id, jugadorBlanc, jugadorNegre);
	}

	/**
	 * Demana a la persistència el torn inicial de la partida amb id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return El torn en forma de string.
	 */
	public String getTornInicial(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		return this.persistenciaPartida.getTornInicial(id, jugadorBlanc, jugadorNegre);
	}
}
