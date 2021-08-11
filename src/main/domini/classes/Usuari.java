package main.domini.classes;
/**
 * La classe Usuari representa un usuari del joc.
 *
 * @author María Hernández Baeza
 */
public class Usuari extends Jugador {
    /**
     * Constructora que crea l'Usuari.
     * @param nom Nom de l'Usuari.
     */
	public Usuari(String nom) {
		super(nom);
	}
    /**
     * Constructora que crea l'Usuari amb els records adients.
     * @param nom Nom de l'Usuari.
     * @param records records que corresponen a l'Usuari.
     */
    public Usuari(String nom, Records records) {
        super(nom, records);
    }
}
