package main.domini.classes;

/**
 * La classe Jugador representa un jugador del joc. És una classe abstracta de la qual heredaran els usuaris i màquines.
 *
 * @author Carles Ureta Boza
 */
public abstract class Jugador {
    protected String nom;
    protected Records records;

	/**
	 * Constructora que inicialitza un jugador sense records.
	 * @param nom Nom del jugador.
	 */
    public Jugador (String nom) {
    	this.nom = nom;
    }

	/**
	 * Constructora que inicialitza un jugador amb records.
	 * @param nom Nom del jugador.
	 * @param records Records del jugador.
	 */
    public Jugador (String nom, Records records) {
        this.nom = nom;
        this.records = records;
    }

	/**
	 * Obté el nom del jugador.
	 * @return El nom del jugador.
	 */
    public String getNom() {
        return this.nom;
    }

	/**
	 * Canvia el nom del jugador.
	 * @param nom Nom del jugador.
	 */
    public void setNom(String nom) {
        this.nom = nom;
    }

	/**
	 * Obté els records del jugador.
	 * @return Els records del jugador.
	 */
    public Records getRecords() {
    	return this.records;
    }
}

