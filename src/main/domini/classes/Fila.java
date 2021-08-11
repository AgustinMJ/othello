package main.domini.classes;

import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;

/**
 * La classe Fila representa una fila del ranking.
 *
 * @author María Hernández Baeza
 */
public class Fila {
	int numPartidesGuanyades;
	int numPartidesPerdudes;
	int numPartidesEmpatades;
	int puntuacio;
	Jugador jugador;

	/**
	 * Constructora per defecte.
	 */
	public Fila() {}

	/**
	 * Constructora que inicialitza la fila, amb les estadístiques a 0, pel jugador donat.
	 * @param jugador Jugador que pertany a la fila.
	 */
	public Fila(Jugador jugador) {
		this.numPartidesGuanyades = 0;
		this.numPartidesPerdudes = 0;
		this.numPartidesEmpatades = 0;
		this.puntuacio = 0;
		this.jugador = jugador;
	}

	/**
	 * Constructora que inicialitza la fila amb les estadístiques i el jugador donats.
	 * @param jugador Jugador que pertany a la fila.
	 * @param numPartidesGuanyades Número de partides guanyades pel jugador.
	 * @param numPartidesPerdudes Número de partides perdudes pel jugador.
	 * @param numPartidesEmpatades Número de partides empatades pel jugador.
	 * @param puntuacio Puntuació del jugador.
	 */
	public Fila(Jugador jugador, int numPartidesGuanyades, int numPartidesPerdudes, int numPartidesEmpatades, int puntuacio){
		this.numPartidesGuanyades = numPartidesGuanyades;
		this.numPartidesPerdudes = numPartidesPerdudes;
		this.numPartidesEmpatades = numPartidesEmpatades;
		this.puntuacio = puntuacio;
		this.jugador = jugador;
	}

	/**
	 * Obté el nombre de partides guanyades de la fila.
	 * @return Una enter indicant el nombre de partides guanyades de la fila.
	 */
	public int getNumPartidesGuanyades() {
		return this.numPartidesGuanyades;
	}

	/**
	 * Obté el nombre de partides perdudes de la fila.
	 * @return Una enter indicant el nombre de partides perdudes de la fila.
	 */
	public int getNumPartidesPerdudes(){ return this.numPartidesPerdudes; }

	/**
	 * Obté el nombre de partides empatades de la fila.
	 * @return Una enter indicant el nombre de empatades guanyades de la fila.
	 */
	public int getNumPartidesEmpatades(){ return this.numPartidesEmpatades; }

	/**
	 * Obté el jugador de la fila.
	 * @return El jugador de la fila.
	 */
	public Jugador getJugador() { return this.jugador; }

	/**
	 * Obté la puntuació de la fila.
	 * @return Una enter indicant la puntuació de la fila.
	 */
	public int getPuntuacio() {
		return this.puntuacio;
	}

	/**
	 * Aplica una victoria a la fila
	 */
	public void victoria() throws FilaEsTotal {
		this.numPartidesGuanyades++;
		this.puntuacio += 3;
	}

	/**
	 * Aplica una derrota a la fila
	 */
	public void derrota() throws FilaEsTotal {
		this.numPartidesPerdudes++;
	}

	/**
	 * Aplica un empat a la fila
	 */
	public void empat() throws FilaEsTotal {
		this.numPartidesEmpatades++;
		this.puntuacio += 1;
	}
}
