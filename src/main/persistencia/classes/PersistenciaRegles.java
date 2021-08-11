package main.persistencia.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La classe PersistenciaRecords és l'encarregada de llegir el fitxer de les regles.
 *
 * @author Agustín Millán Jiménez
 */
public class PersistenciaRegles {
	String path = "./dades/regles.txt";

	/**
	 * Constructora per defecte.
	 */
	public PersistenciaRegles() {
	}

	/**
	 * Obté les regles del fitxer de regles.
	 * @return una llista de strings representant les regles.
	 */
	public ArrayList<String> getRegles() throws FileNotFoundException {
		File regles = new File(path);

		ArrayList<String> dades = new ArrayList<String>();

		Scanner scanner = new Scanner(regles);
		while(scanner.hasNextLine()) {
			dades.add(scanner.nextLine());
		}
		scanner.close();
		return dades;
	}
}
