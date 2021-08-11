package main.domini.classes;

/**
 * La classe Maquina representa un jugador màquina. Hereda de la classe Jugador.
 *
 * @author Agustín Millán Jiménez
 */
public class Maquina extends Jugador {
    String algorisme;

	/**
	 * Constructora que inicialitza la màquina sense algorisme i sense records.
	 * @param nom Nom de la màquina.
	 */
	public Maquina (String nom) {
		super(nom);
	}

	/**
	 * Constructora que inicialitza la màquina sense records.
	 * @param nom Nom de la màquina.
	 * @param algorisme Parámetres per construir l'algorisme de la màquina. És a dir, nom de l'algorisme, profunditat/iteracions i descripció.
	 */
    public Maquina (String nom, String algorisme) {
        super(nom);
        this.algorisme = algorisme;
    }

	/**
	 * Constructora que inicialitza la màquina amb records.
	 * @param nom Nom de la màquina.
	 * @param algorisme Parámetres per construir l'algorisme de la màquina. És a dir, nom de l'algorisme, profunditat/iteracions i descripció.
	 * @param records Records de la màquina
	 */
	public Maquina (String nom, Records records, String algorisme) {
		super(nom, records);
		this.algorisme = algorisme;
	}

	/**
	 * Obté els parámetres de l'algorisme de la màquina.
	 * @return Els parámetres de l'algorisme de la màquina.
	 */
    public String getAlgorisme() { return this.algorisme; }
}
