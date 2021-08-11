package main.domini.classes;

/**
 * La classe Records representa els records d'un jugador.
 *
 * @author Agustín Millán Jiménez
 */
public class Records {
	int pecesGirades;
	int partidesBlanquesGuanyades;
	int partidesNegresGuanyades;

	/**
	 * Constructora que inicialitza els records.
	 * @param pecesGirades Peces girades pel jugador al llarg de totes les seves partides.
	 * @param partidesBlanquesGuanyades Partides guanyades pel jugador amb el color blanc.
	 * @param partidesNegresGuanyades Partides guanyades pel jugador amb el color negre.
	 */
	public Records(int pecesGirades, int partidesBlanquesGuanyades, int partidesNegresGuanyades) {
		this.pecesGirades = pecesGirades;
		this.partidesBlanquesGuanyades = partidesBlanquesGuanyades;
		this.partidesNegresGuanyades = partidesNegresGuanyades;
	}

	/**
	 * Obté el nombre de peces girades.
	 * @return El nombre de peces girades.
	 */
	public int getPecesGirades() {
		return this.pecesGirades;
	}

	/**
	 * Incrementa el nombre de peces girades en les unitats indicades.
	 * @param peces el nombre de peces girades a incrementar.
	 */
	public void incrementaPecesGirades(int peces) {
		this.pecesGirades += peces;
	}

	/**
	 * Obté el nombre de partides guanyades amb el color blanc.
	 * @return El nombre de partides guanyades amb el color blanc.
	 */
	public int getPartidesBlanquesGuanyades() {
		return this.partidesBlanquesGuanyades;
	}

	/**
	 * Incrementa el nombre de partides guanyades amb el color blanc en una unitat.
	 */
	public void incrementaParitdesBlanquesGuanyades() {
		this.partidesBlanquesGuanyades++;
	}

	/**
	 * Obté el nombre de partides guanyades amb el color negre.
	 * @return El nombre de partides guanyades amb el color negre.
	 */
	public int getPartidesNegresGuanyades() {
		return this.partidesNegresGuanyades;
	}

	/**
	 * Incrementa el nombre de partides guanyades amb el color negre en una unitat.
	 */
	public void incrementaParitdesNegresGuanyades() {
		this.partidesNegresGuanyades++;
	}
}
