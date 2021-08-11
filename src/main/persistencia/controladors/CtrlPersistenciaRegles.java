package main.persistencia.controladors;

import main.persistencia.classes.PersistenciaRegles;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * La classe CtrlPersistenciaRecords és el controlador encarregat de demanar a la persistència de les regles les regles i servir-les a la capa de domini.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPersistenciaRegles {
	PersistenciaRegles persistenciaRegles;

	/**
	 * Constructora per defecte.
	 */
	public CtrlPersistenciaRegles() {
		this.persistenciaRegles = new PersistenciaRegles();
	}

	/**
	 * Demana a la persistència de les regles les regles.
	 * @return una llista de strings representant les regles.
	 */
	public ArrayList<String> getRegles() throws FileNotFoundException {
		return this.persistenciaRegles.getRegles();
	}
}
