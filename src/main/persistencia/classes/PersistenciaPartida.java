package main.persistencia.classes;

import java.io.*;
import java.util.*;

/**
 * La classe PersistenciaPartida és l'encarregada de llegir i escriure ls fitxers de les partides.
 *
 * @author Agustín Millán Jiménez
 */
public class PersistenciaPartida {
	final String path = "./dades/partides/";
	final String pathCtrl = path + "control.txt";
	final String pathPartidesEnProgres = path + "partides en progres/";
	final String pathPartidesAcabades = path + "partides finalitzades/";

	/**
	 * Constructora per defecte.
	 */
	public PersistenciaPartida() {}

	/**
	 * Llegeix la informació de control.
	 * @return Un enter indicant l'id de la última partida creada.
	 */
	private int llegeixCtrl() throws FileNotFoundException {
		File dadesCtrl = new File(pathCtrl);
		Scanner scanner = new Scanner(dadesCtrl);
		int ids = scanner.nextInt();
		scanner.close();
		return ids;
	}

	/**
	 * Llegeix una partida d'un fitxer.
	 * @param dadesPartida Fitxer de la partida a llegir.
	 * @return Una llista de strings amb les dades de la partida.
	 */
	private ArrayList<String> llegeixPartida(File dadesPartida) throws FileNotFoundException {
		ArrayList<String> dades = new ArrayList<String>();
		Scanner scanner = new Scanner(dadesPartida);
		while(scanner.hasNextLine()) {
			dades.add(scanner.nextLine());
		}
		scanner.close();
		return dades;
	}

	/**
	 * Llegeix totes les partides.
	 * @param path Path on es troben els fitxers de les partides.
	 * @return Un array amb els fitxers de les partides.
	 */
	private File[] llegeixPartides(String path) {
		File carpeta = new File(path);
		return carpeta.listFiles();
	}

	/**
	 * Construeix un mapa de strings a partir de les dades de la partida en forma d'array de strings.
	 * @param dades Array de strings amb les dades de la partida.
	 * @return Un mapa de strings representant la partida.
	 */
	private Map<String, String> construeixMapa(ArrayList<String> dades) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("id", dades.get(0));
		map.put("modeDeJoc", dades.get(1));
		map.put("torn", dades.get(2));
		map.put("finalitzada", dades.get(3));
		map.put("jugadorBlanc", dades.get(4));
		map.put("tipusBlanc", dades.get(5));
		map.put("jugadorNegre", dades.get(6));
		map.put("tipusNegre", dades.get(7));
		map.put("taulell", dades.get(8));

		return map;
	}

	/**
	 * Crea una partida a partir dels paràmetres obtinguts.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusJugadorBlanc Tipus de jugador del jugador blanc.
	 * @param tipusJugadorNegre Tipus de jugador del jugador negre.
	 * @param taulell Taulell en forma de string.
	 * @param mode Mode de joc de la partida.
	 * @param torn Torn inicial de la partida.
	 * @return L'id de la partida creada.
	 */
	public int creaPartida(String jugadorBlanc, String jugadorNegre, String tipusJugadorBlanc, String tipusJugadorNegre, String taulell, String mode, String torn) throws IOException {
		int lastId = 0;

		//Obté l'id de la última partida
		lastId = llegeixCtrl();

		//Incrementa en 1 l'id de la informació de control
		FileWriter dadesCtrl = new FileWriter(pathCtrl);
		dadesCtrl.write(String.valueOf(lastId + 1));
		dadesCtrl.close();

		String filePath = pathPartidesEnProgres + String.valueOf(lastId + 1) + " " + jugadorBlanc + " " + jugadorNegre + ".txt";

		//Crea un nou fitxer
		File f = new File(filePath);
		f.createNewFile();


		//Escriu la partida al nou fitxer
		FileWriter fw = new FileWriter(filePath);
		fw.write(String.valueOf(lastId + 1) + "\n" + mode + "\n" + torn + "\n"
				+ "0" + "\n" + jugadorBlanc + "\n" + tipusJugadorBlanc + "\n" + jugadorNegre + "\n" + tipusJugadorNegre
				+ "\n" + taulell + "\n" + taulell + "\n" + torn);
		fw.close();

		return lastId + 1;
	}

	/**
	 * Guarda una partida amb els paràmetres obtinguts.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param taulell Taulell de la partida en forma de string.
	 * @param torn Torn de la partida.
	 * @param finalitzada Indica si la partida ha finalitzat.
	 */
	public void guardaPartida(String id, String jugadorBlanc, String jugadorNegre, String taulell, String torn, boolean finalitzada) throws IOException {
		String path = pathPartidesEnProgres + id  + " " + jugadorBlanc + " " + jugadorNegre + ".txt";
		ArrayList<String> dadesArray = new ArrayList<String>();

		File partida = new File(path);
		dadesArray = llegeixPartida(partida);

		if(finalitzada) {
			path = pathPartidesAcabades + id  + " " + jugadorBlanc + " " + jugadorNegre + ".txt";
			partida.delete();

			File f = new File(path);
			f.createNewFile();
		}

		FileWriter f = new FileWriter(path);
		f.write(dadesArray.get(0) + "\n" + dadesArray.get(1) + "\n" + torn + "\n"
				+ finalitzada + "\n" + dadesArray.get(4) + "\n" + dadesArray.get(5) + "\n" + dadesArray.get(6) + "\n" + dadesArray.get(7)
				+ "\n" + taulell + "\n" + dadesArray.get(9) + "\n" + dadesArray.get(10));
		f.close();
	}

	/**
	 * Elimina la partida amb l'id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param finalitzada Indica si la partida ha finalitzat.
	 */
	public void eliminaPartida(String id, String jugadorBlanc, String jugadorNegre, boolean finalitzada) {
		String path = (finalitzada ? pathPartidesAcabades : pathPartidesEnProgres) + id + jugadorBlanc + jugadorNegre + ".txt";
		File partida = new File(path);
		partida.delete();
	}

	/**
	 * Obté la partida amb l'id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return Un mapa de strings representant la partida.
	 */
	public Map<String, String> getPartida(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		File partida = new File(pathPartidesEnProgres + id + " " + jugadorBlanc +  " " + jugadorNegre + ".txt");
		return construeixMapa(llegeixPartida(partida));
	}

	/**
	 * Obté les partides acabades de l'usuari indicat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides acabades de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesUsuari(String nom) throws FileNotFoundException {
		//Llista que contindrà les partides acabades de l'usuari
		ArrayList<Map<String, String>> partides = new ArrayList<Map<String, String>>() ;

		//Obté les partides acabades
		File[] fPartidesAcabades = llegeixPartides(pathPartidesAcabades);
		Arrays.asList(fPartidesAcabades).sort(Comparator.comparing(File::getName));

		for(File p : fPartidesAcabades) {
			if (p.getName().split(" ")[1].equals(nom) || p.getName().split(" ")[2].equals(nom + ".txt")) {
				ArrayList<String> dadesArray = llegeixPartida(p);
				partides.add(construeixMapa(dadesArray));
			}
		}

		return partides;
	}

	/**
	 * Obté les partides no finalitzades de l'usuari indicat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides no finalitzades de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesNoFinalitzadesUsuari(String nom) throws FileNotFoundException {
		//Llista que contindrà les partides de l'usuari
		ArrayList<Map<String, String>> partides = new ArrayList<Map<String, String>>() ;

		//Obté les partides en progrés
		File[] fPartidesEnProgres = llegeixPartides(pathPartidesEnProgres);
		Arrays.asList(fPartidesEnProgres).sort(Comparator.comparing(File::getName));

		for(File p : fPartidesEnProgres) {
			if (p.getName().split(" ")[1].equals(nom) || p.getName().split(" ")[2].equals(nom + ".txt")) {
				ArrayList<String> dadesArray = llegeixPartida(p);
				partides.add(construeixMapa(dadesArray));
			}
		}

		return partides;
	}

	/**
	 * Elimina totes les partides de l'usuari donat.
	 * @param nom Nom de l'usuari.
	 */
	public void eliminaPartidesusuari(String nom) {
		//Obté les partides en progrés
		File[] fPartidesEnProgres = llegeixPartides(pathPartidesEnProgres);

		//Obté les partides acabades
		File[] fPartidesAcabades = llegeixPartides(pathPartidesAcabades);

		for(File pp : fPartidesEnProgres) {
			pp.delete();
		}

		for(File pa : fPartidesAcabades) {
			pa.delete();
		}
	}

	/**
	 * Obté el taulell inicial de la partida amb id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return El taulell en forma de string.
	 */
	public String getTaulellInicial(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		File p = new File(pathPartidesEnProgres + id  + " " + jugadorBlanc + " "  + jugadorNegre + ".txt");
		Scanner s = new Scanner(p);
		for(int i = 0; i < 9; i++) s.nextLine();
		return s.nextLine();
	}

	/**
	 * Obté el torn inicial de la partida amb id i jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @return El torn en forma de string.
	 */
	public String getTornInicial(String id, String jugadorBlanc, String jugadorNegre) throws FileNotFoundException {
		File p = new File(pathPartidesEnProgres + id  + " " + jugadorBlanc + " "  + jugadorNegre + ".txt");
		Scanner s = new Scanner(p);
		for(int i = 0; i < 10; i++) s.nextLine();
		return s.nextLine();
	}
}
