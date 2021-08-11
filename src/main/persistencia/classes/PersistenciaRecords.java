package main.persistencia.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * La classe PersistenciaRecords és l'encarregada de llegir i escriure els fitxers dels records.
 *
 * @author Agustín Millán Jiménez
 */
public class PersistenciaRecords {
	String path = "./dades/records/";

	/**
	 * Constructora per defecte.
	 */
	public PersistenciaRecords() {

	}

	/**
	 * Crea el fitxer de records de l'usuari donat.
	 * @param nom Nom del jugador.
	 * @return Un mapa d'enters representant els records del jugador.
	 */
	private Map<String, Integer> creaRecords(String nom) throws IOException {
		String filePath = path + nom + ".txt";

		File f = new File(filePath);
		f.createNewFile();

		FileWriter fw = new FileWriter(filePath);
		fw.write(0 + "\n" + 0 + "\n" + 0);
		fw.close();

		return construeixMapa(new ArrayList<String>(Arrays.asList("0", "0", "0")));
	}

	/**
	 * Llegeix els records d'un fitxer.
	 * @param dadesRecords Fitxer dels records a llegir.
	 * @return Una llista de strings amb les dades dels records.
	 */
	private ArrayList<String> llegeixRecords(File dadesRecords) throws FileNotFoundException {
		ArrayList<String> dades = new ArrayList<String>();
		Scanner scanner = new Scanner(dadesRecords);
		while(scanner.hasNextLine()) {
			dades.add(scanner.nextLine());
		}
		scanner.close();
		return dades;
	}

	/**
	 * Construeix un mapa d'enters a partir de les dades dels records en forma d'array de strings.
	 * @param dades Array de strings amb les dades dels records.
	 * @return Un mapa d'enters representant els records.
	 */
	private Map<String, Integer> construeixMapa(ArrayList<String> dades) {
		Map<String, Integer>  map = new HashMap<String, Integer>();
		map.put("pecesGirades", Integer.parseInt(dades.get(0)));
		map.put("partidesBlanquesGuanyades", Integer.parseInt(dades.get(1)));
		map.put("partidesNegresGuanyades", Integer.parseInt(dades.get(2)));

		return map;
	}

	/**
	 * Obté els records del jugador indicat.
	 * @param nom Nom del jugador.
	 * @return Un mapa d'enters representant els records del jugador
	 */
	public Map<String, Integer> getRecordsJugador(String nom) throws IOException {
		File records = new File(path + nom + ".txt");

		Map<String, Integer> parsedRecords;
		try {
			parsedRecords = construeixMapa(llegeixRecords(records));
		} catch (FileNotFoundException e) {
			parsedRecords = creaRecords(nom);
		}

		return parsedRecords;
	}

	/**
	 * Actualitza els records del jugador indicat.
	 * @param nom Nom del jugador.
	 * @param records Mapa d'enters representant els records de l'usuari.
	 */
	public void setRecords(String nom, Map<String, Integer> records) throws IOException {
		String path = this.path + nom + ".txt";

		FileWriter f = new FileWriter(path);
		f.write(records.get("pecesGirades") + "\n" + records.get("partidesBlanquesGuanyades") + "\n" + records.get("partidesNegresGuanyades"));
		f.close();
	}

	/**
	 * Elimina els records del jugador donat.
	 * @param nom Nom del jugador.
	 */
	public void eliminaRecordsJugador(String nom) {
		File r = new File(path + nom + ".txt");
		r.delete();
	}
}
