package main.domini.classes;

/**
 * La classe Partida representa una partida del joc.
 *
 * @author Agustín Millán Jiménez
 */
public class Partida {
    int id;
    ModeDeJoc modeDeJoc;
    Jugador jugadorBlanc;
    Jugador jugadorNegre;
    Taulell taulell;
    Color torn;
    boolean finalitzada;

	/**
	 * Constructora que inicialitza la partida.
	 * @param id Id de la partida.
	 * @param modeDeJoc Mode de joc de la partida.
	 * @param jugadorBlanc Jugador amb el color blanc.
	 * @param jugadorNegre Juagdor amb el color negre.
	 * @param taulell Taulell on es juga la partida.
	 * @param torn Color del jugador que ha de jugar el torn.
	 * @param finalitzada Indica si la partida ha finalitzat.
	 */
    public Partida(int id, ModeDeJoc modeDeJoc, Jugador jugadorBlanc, Jugador jugadorNegre, Taulell taulell, Color torn, boolean finalitzada) {
        this.id = id;
        this.modeDeJoc = modeDeJoc;
        this.jugadorNegre = jugadorNegre;
        this.jugadorBlanc = jugadorBlanc;
        this.taulell = taulell;
        this.torn = torn;
        this.finalitzada = finalitzada;
    }

	/**
	 * Obté l'id de la partida.
	 * @return Un enter indicant l'id de la partida.
	 */
    public int getId() {
        return this.id;
    }

	/**
	 * Obté el mode de joc de la partida.
	 * @return El mode de joc de la partida
	 */
    public ModeDeJoc getModeDeJoc() {
        return this.modeDeJoc;
    }

	/**
	 * Obté el jugador negre de la partida.
	 * @return El jugador negre de la partida.
	 */
    public Jugador getJugadorNegre() {
        return this.jugadorNegre;
    }

	/**
	 * Obté el jugador blanc de la partida.
	 * @return El jugador blanc de la partida.
	 */
    public Jugador getJugadorBlanc() {
        return this.jugadorBlanc;
    }

	/**
	 * Obté el taulell de la partida.
	 * @return El taulell de la partida.
	 */
    public Taulell getTaulell() {
        return this.taulell;
    }

	/**
	 * Assigna el taulell donat a la partida.
	 * @param t El taulell de la partida.
	 */
    public void setTaulell(Taulell t) {
    	this.taulell = t;
    }

	/**
	 * Obté el torn de la partida.
	 * @return El torn de la partida.
	 */
    public Color getTorn() { return this.torn; }

	/**
	 * Canvia el torn de la partida al color contrari.
	 */
    public void setTorn() { this.torn = this.torn == Color.BLANC ? Color.NEGRE : Color.BLANC; }

	/**
	 * Canvia el torn de la partida al color donat.
	 * @param c Color a canviar.
	 */
	public void setTorn(Color c) { this.torn = c; }

	/**
	 * Comprova si la partida ha finalitzat.
	 * @return Un booleà indicant si la partida ha finalitzat.
	 */
    public boolean isFinalitzada() { return this.finalitzada; }

	/**
	 * Comprova si la partida ha acabat en empat.
	 * @return Un booleà indicant si la partida ha acabat en empat.
	 */
    public boolean isEmpat() {
    	return this.taulell.getNumPeces(Color.BLANC) == this.taulell.getNumPeces(Color.NEGRE);
    }
}
