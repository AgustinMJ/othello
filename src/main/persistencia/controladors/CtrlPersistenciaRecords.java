package main.persistencia.controladors;

import main.persistencia.classes.PersistenciaRecords;

import java.io.IOException;
import java.util.Map;

/**
 * La classe CtrlPersistenciaRecords és el controlador encarregat de demanar a la persistència dels records les dades i servir-les a la capa de domini.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPersistenciaRecords {
	PersistenciaRecords persistenciaRecords;

	/**
	 * Constructora per defecte.
	 */
	public CtrlPersistenciaRecords() {
		this.persistenciaRecords = new PersistenciaRecords();
	}

	/**
	 * Demana a la persistencia dels records els records del jugador indicat.
	 * @param nom Nom del jugador.
	 * @return Un mapa d'enters representant els records del jugador
	 */
	public Map<String , Integer> getRecordsJugador(String nom) throws IOException {
		return this.persistenciaRecords.getRecordsJugador(nom);
	}

	/**
	 * Demana a la persistencia dels records que actualitzi els records del jugador indicat.
	 * @param nom Nom del jugador.
	 * @param r Mapa d'enters representant els records de l'usuari.
	 */
	public void setRecordsJugador(String nom, Map<String, Integer> r) throws IOException {
		this.persistenciaRecords.setRecords(nom, r);
	}
}
