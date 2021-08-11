package main.persistencia.classes;

import main.utils.MaquinaJaExisteix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * La classe PersistenciaMaquina és l'encarregada de llegir i escriure els fitxers de les màquines.
 *
 * @author Agustín Millán Jiménez
 */
public class PersistenciaMaquina {
	final String path = "./dades/maquines/";

	/**
	 * Constructora per defecte.
	 */
	public PersistenciaMaquina () {
	}

	/**
	 * Llegeix una màquina d'un fitxer.
	 * @param dadesMaquina Fitxer de la màquina a llegir.
	 * @return Una llista de strings amb les dades de la màquina.
	 */
	private ArrayList<String> llegeixMaquina(File dadesMaquina) throws FileNotFoundException {
		ArrayList<String> dades = new ArrayList<String>();
		Scanner scanner = new Scanner(dadesMaquina);
		while(scanner.hasNextLine()) {
			dades.add(scanner.nextLine());
		}
		scanner.close();
		return dades;
	}

	/**
	 * Llegeix totes les màquines.
	 * @param path Path on es troben els fitxers de les màquines.
	 * @return Un array amb els fitxers de les màquines.
	 */
	private File[] llegeixMaquines(String path) {
		File carpeta = new File(path);
		return carpeta.listFiles();
	}

	/**
	 * Construeix un mapa de strings a partir de les dades de la màquina en forma d'array de strings.
	 * @param dades Array de strings amb les dades de la màquina.
	 * @return Un mapa de strings representant la màquina.
	 */
	private Map<String, String> construeixMapa(ArrayList<String> dades) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("nom", dades.get(0));
		map.put("algorisme", dades.get(1));
		map.put("descripcio", dades.get(2));

		return map;
	}

	/**
	 * Obté la màquina amb el nom donat.
	 * @param nom Nom de la màquina
	 * @return Un mapa de strings representant la màquina.
	 */
	public Map<String, String> getMaquina(String nom) throws FileNotFoundException {
		File maquina = new File(path + nom + ".txt");
		return construeixMapa(llegeixMaquina(maquina));
	}

	/**
	 * Obté totes les màquines
	 * @return Una llista de mapes de strings representant les màquines.
	 */
	public ArrayList<Map<String, String>> getMaquines() throws FileNotFoundException {
		ArrayList<Map<String, String>> maquines = new ArrayList<Map<String, String>>() ;

		File[] carpetaMaquines = llegeixMaquines(path);
		for(File fMaquina : carpetaMaquines) {
			if(!fMaquina.getName().equals(".DS_Store")) {
				ArrayList<String> maquina = llegeixMaquina(fMaquina);
				maquines.add(construeixMapa(maquina));
			}
		}

		return maquines;
	}

	/**
	 * Comprova si existeix la màquina amb el nom donat.
	 * @param nom Nom de la màquina.
	 * @return Un booleà indicant si existeix la màquina.
	 */
	public boolean existeixMaquina(String nom) {
		return Files.exists(Path.of(path + nom + ".txt"));
	}

	/**
	 * Obté l'algorisme de la màquina amb el nom donat.
	 * @param nom Nom de la màquina.
	 * @return Un string representant els paràmetres de l'algorisme.
	 */
	public String getAlgorismeMaquina(String nom) throws FileNotFoundException {
		File maquina = new File(path + nom + ".txt");
		return llegeixMaquina(maquina).get(1);
	}

	/**
	 * Crea una màquina a partir dels paràmetres obtinguts.
	 * @param nom Nom de la màquina.
	 * @param algorisme Paràmetres de l'algorisme.
	 * @param descripcio Descripció de la màquina.
	 */
	public void creaMaquina(String nom, String algorisme, String descripcio) throws MaquinaJaExisteix, IOException {
		String filePath = path + nom + ".txt";

		if(existeixMaquina(nom)) throw new MaquinaJaExisteix();

		File f = new File(filePath);
		f.createNewFile();

		FileWriter fw = new FileWriter(filePath);
		fw.write(nom+"\n"+algorisme+"\n"+descripcio);
		fw.close();
	}

	/**
	 * Elimina la màquina amb el nom donat.
	 * @param nom Nom de la màquina.
	 */
	public void eliminaMaquina(String nom) {
		if (existeixMaquina(nom)) {
			String filePath = path + nom + ".txt";
			File f = new File(filePath);
			f.delete();
		}
	}
}
