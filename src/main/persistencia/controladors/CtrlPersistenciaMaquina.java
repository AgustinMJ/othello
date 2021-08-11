package main.persistencia.controladors;

import main.persistencia.classes.PersistenciaMaquina;
import main.persistencia.classes.PersistenciaRanking;
import main.persistencia.classes.PersistenciaRecords;
import main.persistencia.classes.PersistenciaUsuari;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe CtrlPersistenciaMaquina és el controlador encarregat de demanar a la persistència de les màquines les dades i servir-les a la capa de domini.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPersistenciaMaquina {
	PersistenciaMaquina persistenciaMaquina;
	PersistenciaUsuari persistenciaUsuari = new PersistenciaUsuari();
	PersistenciaRanking persistenciaRanking = new PersistenciaRanking();
	PersistenciaRecords persistenciaRecords = new PersistenciaRecords();

	/**
	 * Constructora per defecte.
	 */
	public CtrlPersistenciaMaquina() {
		this.persistenciaMaquina = new PersistenciaMaquina();
	}

	/**
	 * Demana a la persistència de les màquines la màquina amb el nom donat.
	 * @param nom Nom de la màquina
	 * @return Un mapa de strings representant la màquina.
	 */
	public Map<String, String> getMaquina(String nom) throws FileNotFoundException {
		return this.persistenciaMaquina.getMaquina(nom);
	}

	/**
	 * Demana a la persistència de les màquines totes les màquines
	 * @return Una llista de mapes de strings representant les màquines.
	 */
	public ArrayList<Map<String, String>> getMaquines() throws FileNotFoundException {
		return this.persistenciaMaquina.getMaquines();
	}

	/**
	 * Demana a la persistència de les màquines l'algorisme de la màquina amb el nom donat.
	 * @param nom Nom de la màquina.
	 * @return Un string representant els paràmetres de l'algorisme.
	 */
	public String getAlgorismeMaquina(String nom) throws FileNotFoundException {
		return this.persistenciaMaquina.getAlgorismeMaquina(nom);
	}

	/**
	 * Demana a la persistència de les màquines que crei una màquina a partir dels paràmetres obtinguts.
	 * @param nom Nom de la màquina.
	 * @param algorisme Paràmetres de l'algorisme.
	 * @param descripcio Descripció de la màquina.
	 */
	public void creaMaquina(String nom, String algorisme, String descripcio) throws UsuariJaExistex, IOException, MaquinaJaExisteix {
		if(persistenciaUsuari.existeixUsuari(nom)) throw new UsuariJaExistex();
		this.persistenciaMaquina.creaMaquina(nom, algorisme, descripcio);
	}

	/**
	 * Demana a la persistència de les màquines que elimini la màquina amb el nom donat.
	 * @param nom Nom de la màquina.
	 */
	public void eliminaMaquina(String nom) throws IOException {
		this.persistenciaMaquina.eliminaMaquina(nom);
		persistenciaRanking. eliminaRankingJugador(nom);
		persistenciaRecords.eliminaRecordsJugador(nom);
	}
}
