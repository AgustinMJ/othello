package main.persistencia.classes;

import main.utils.CountingSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * La classe PersistenciaRanking és l'encarregada de llegir i escriure els fitxers dels rankings.
 *
 * @author Agustín Millán Jiménez
 */
public class PersistenciaRanking {
	final String path = "./dades/ranking/";
	final String pathHoritzontal = path + "horitzontal/";
	final String pathVertical = path + "vertical/";
	final String pathDiagonal = path + "diagonal/";
	final String pathClassic = path + "classic/";
	final String pathTotal = path + "total/";

	/**
	 * Constructora per defecte.
	 */
	public PersistenciaRanking() {}

	/**
	 * Llegeix una fila d'un fitxer.
	 * @param dadesFila Fitxer de la fila a llegir.
	 * @return Una llista de strings amb les dades de la fila.
	 */
	private ArrayList<String> llegeixFila(File dadesFila) throws FileNotFoundException {
		ArrayList<String> dades = new ArrayList<String>();
		dades.add(dadesFila.getName().split(" ")[0]);
		Scanner scanner = new Scanner(dadesFila);
		while(scanner.hasNextLine()) {
			dades.add(scanner.nextLine());
		}
		scanner.close();
		return dades;
	}

	/**
	 * Llegeix totes les files d'un ranking.
	 * @param carpeta Array de fitxers fila del ranking.
	 * @return Una llista de mapes de string representant les files del ranking.
	 */
	private ArrayList<Map<String, String>> llegeixFiles(File[] carpeta) throws FileNotFoundException {
		ArrayList<Map<String, String>> res = new ArrayList<Map<String, String>>();

		for(File f : carpeta) {
			ArrayList<String> dadesArray = llegeixFila(f);
			res.add(construeixMapaFila(dadesArray));
		}

		return res;
	}

	/**
	 * Llegeix una fila d'un jugador en un ranking.
	 * @param carpeta Array de fitxers fila del ranking.
	 * @param nom Nom del jugador.
	 * @return Un mapa de strings representant la fila del jugador.
	 */
	private Map<String, String> llegeixFilaJugador(File[] carpeta, String nom) throws FileNotFoundException {
		Map<String, String> res = new HashMap<String, String>();

		for(File f : carpeta) {
			if(f.getName().split(" ")[1].equals(nom + ".txt")) {
				ArrayList<String> dadesArray = llegeixFila(f);
				return construeixMapaFila(dadesArray);
			}
		}

		return res;
	}

	/**
	 * Llegeix un ranking
	 * @param path Path del ranking
	 * @return Un array de fitxers fila.
	 */
	private File[] llegeixRanking(String path) {
		File carpeta = new File(path);
		File[] files = carpeta.listFiles();

		new CountingSort().sort(files, files.length);

		return files;
	}

	/**
	 * Construeix un mapa de strings a partir de les dades de la fila en forma d'array de strings.
	 * @param dades Array de strings amb les dades de la fila.
	 * @return Un mapa de strings representant la fila.
	 */
	private Map<String, String> construeixMapaFila(ArrayList<String> dades) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("pos", dades.get(0));
		map.put("jugador", dades.get(1));
		map.put("tipus", dades.get(2));
		map.put("numPartidesGuanyades", dades.get(3));
		map.put("numPartidesPerdudes", dades.get(4));
		map.put("numPartidesEmpatades", dades.get(5));
		map.put("puntuacio", dades.get(6));

		return map;
	}

	/**
	 * Incrementa en una unitat la posició del jugador.
	 * @param nom Nom del jugador
	 * @return Un string amb el nou nom del fitxer fila del jugador.
	 */
	private String incrementaPos(String nom) {
		String[] a = nom.split(" ");
		return String.valueOf(Integer.parseInt(a[0]) + 1) + " " + a[1];
	}

	/**
	 * Decrementa en una unitat la posició del jugador.
	 * @param nom Nom del jugador
	 * @return Un string amb el nou nom del fitxer fila del jugador.
	 */
	private String decrementaPos(String nom) {
		String[] a = nom.split(" ");
		return String.valueOf(Integer.parseInt(a[0]) - 1) + " " + a[1];
	}

	/**
	 * Elimina la fila del jugador en un ranking donat.
	 * @param carpeta Array de fitxers fila del ranking.
	 * @param nom Nom del jugador
	 */
	private void eliminaFilaJugador(File[] carpeta, String nom) throws IOException {
		boolean continua = true;
		for(int i = 0; i < carpeta.length && continua; i++) {
			if(carpeta[i].getName().split(" ")[1].equals(nom + ".txt")) {
				//Parem de buscar
				continua = false;

				//Tots els jugadors que anaven després pujaran una posició del ranking
				Path fila;
				for(int j = i + 1; j < carpeta.length; j++) {
					fila = Paths.get(carpeta[j].getPath());
					String nouNom = decrementaPos(carpeta[j].getName());
					Files.move(fila, fila.resolveSibling(nouNom));
				}

				//Eliminem el jugador dessitjat
				carpeta[i].delete();
			}
		}
	}

	/**
	 * Afegeix una fila al ranking indicat.
	 * @param path Path del ranking.
	 * @param fila Mapa de strings representant la fila a afegir.
	 */
	private void afegeixFila(String path, Map<String, String> fila) throws IOException {
		//Crea un nou fitxer
		File f = new File(path);
		f.createNewFile();

		//Escriu la partida al nou fitxer
		FileWriter fw = new FileWriter(path);
		fw.write(fila.get("jugador") + "\n" + fila.get("tipus") + "\n"
				+ fila.get("numPartidesGuanyades") + "\n" + fila.get("numPartidesPerdudes") + "\n" + fila.get("numPartidesEmpatades")
				+ "\n" + fila.get("puntuacio"));
		fw.close();
	}

	/**
	 * Actualitza la fila del ranking de la posició donada.
	 * @param path Path del ranking.
	 * @param fila Mapa de strings representant la fila actualitzada.
	 * @param oldPos Posició que tenia la fila en el ranking.
	 */
	private void updateFila(String path, Map<String, String> fila, int oldPos) throws IOException {
		Path pathFila;
		String pathJugador = "";

		if(oldPos == -1) {
			pathJugador = fila.get("pos") + " " + fila.get("jugador") + ".txt";
			pathFila = Paths.get(path + pathJugador);
			afegeixFila(pathFila.toString(), fila);
		} else {
			File[] carpeta = new File(path).listFiles();
			for(File f : carpeta) {
				if(f.getName().split(" ")[1].equals(fila.get("jugador") + ".txt")) {
					pathJugador = f.getName();
				}
			}

			pathFila = Paths.get(path + pathJugador);
			//Llegim les estadistiques del jugador
			File dadesFila = new File(path + pathJugador);
			ArrayList<String> dadesArray = llegeixFila(dadesFila);

			//Actualitzem les estadistiques del jugador
			FileWriter f = new FileWriter(path + pathJugador);
			f.write(dadesArray.get(1) + "\n" + dadesArray.get(2) + "\n"
					+ fila.get("numPartidesGuanyades") + "\n" + fila.get("numPartidesPerdudes") + "\n" + fila.get("numPartidesEmpatades")
					+ "\n" + fila.get("puntuacio"));
			f.close();

			//Canviem la posició del jugador
			if(Integer.parseInt(fila.get("pos")) != oldPos) {
				Files.move(pathFila, pathFila.resolveSibling(fila.get("pos") + " " + fila.get("jugador") + ".txt"));
			}
		}
	}

	/**
	 * Obté els rankings.
	 * @return Un mapa de llistes de mapes strings representant els rankings.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRanking() throws FileNotFoundException {
		ArrayList<Map<String, String>> rankingHoritzontal = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> rankingVertical = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> rankingDiagonal = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> rankingClassic = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> rankingTotal = new ArrayList<Map<String, String>>();

		File[] carpetaHoritzontal = llegeixRanking(pathHoritzontal);
		rankingHoritzontal = llegeixFiles(carpetaHoritzontal);

		File[] carpetaVertical = llegeixRanking(pathVertical);
		rankingVertical = llegeixFiles(carpetaVertical);

		File[] carpetaDiagonal = llegeixRanking(pathDiagonal);
		rankingDiagonal = llegeixFiles(carpetaDiagonal);

		File[] carpetaClassic = llegeixRanking(pathClassic);
		rankingClassic = llegeixFiles(carpetaClassic);

		File[] carpetaTotal = llegeixRanking(pathTotal);
		rankingTotal = llegeixFiles(carpetaTotal);

		Map<String, ArrayList<Map<String, String>>> rankings = new HashMap<String, ArrayList<Map<String, String>>>();
		rankings.put("Horitzontal", rankingHoritzontal);
		rankings.put("Vertical", rankingVertical);
		rankings.put("Diagonal", rankingDiagonal);
		rankings.put("Classic", rankingClassic);
		rankings.put("total", rankingTotal);

		return rankings;
	}

	/**
	 * Obté els rankings del jugador donat.
	 * @return Un mapa de mapes strings representant els rankings del jugadpor.
	 */
	public Map<String, Map<String, String>> getRankingJugador(String nom) throws FileNotFoundException {
		Map<String, String> rankingHoritzontal = new HashMap<String, String>();
		Map<String, String> rankingVertical = new HashMap<String, String>();
		Map<String, String> rankingDiagonal = new HashMap<String, String>();
		Map<String, String> rankingClassic = new HashMap<String, String>();
		Map<String, String> rankingTotal = new HashMap<String, String>();

		File[] carpetaHoritzontal = llegeixRanking(pathHoritzontal);
		rankingHoritzontal = llegeixFilaJugador(carpetaHoritzontal, nom);

		File[] carpetaVertical = llegeixRanking(pathVertical);
		rankingVertical = llegeixFilaJugador(carpetaVertical, nom);

		File[] carpetaDiagonal = llegeixRanking(pathDiagonal);
		rankingDiagonal = llegeixFilaJugador(carpetaHoritzontal, nom);

		File[] carpetaClassic = llegeixRanking(pathClassic);
		rankingClassic = llegeixFilaJugador(carpetaClassic, nom);

		File[] carpetaTotal = llegeixRanking(pathTotal);
		rankingTotal = llegeixFilaJugador(carpetaTotal, nom);

		Map<String, Map<String, String>> rankings = new HashMap<String, Map<String, String>>();
		rankings.put("Horitzontal", rankingHoritzontal);
		rankings.put("Vertical", rankingVertical);
		rankings.put("Diagonal", rankingDiagonal);
		rankings.put("Classic", rankingClassic);
		rankings.put("total", rankingTotal);

		return rankings;
	}

	/**
	 * Actualitza el ranking d'un mode i total amb els paràmetres obtinguts.
	 * @param filaMode Mapa de strings representant la fila del ranking del mode de joc a actualitzar.
	 * @param filaTotal Mapa de strings representant la fila del ranking total a actualitzar.
	 * @param mode Mode de joc a actualitzar.
	 * @param oldPosMode Antiga posició de la fila en el ranking del mode de joc.
	 * @param oldPosTotal Antiga posició de la fila en el ranking total.
	 */
	public void updateRanking(Map<String, String> filaMode, Map<String, String> filaTotal, String mode, int oldPosTotal, int oldPosMode) throws IOException {
		//Update ranking total
		File[] rankingTotal = llegeixRanking(pathTotal);

		//Incrementem en 1 la posició dels jugadors adelantats
		Path fila;
		int oldPos = oldPosTotal == -1 ? rankingTotal.length : oldPosTotal;
		for(int i = Integer.parseInt(filaTotal.get("pos")); i < oldPos; i++) {
			fila = Paths.get(rankingTotal[i].getPath());
			String nom = rankingTotal[i].getName();
			if(nom.split(" ")[1].equals(filaTotal.get("jugador") + ".txt")) continue;
			String nouNom = incrementaPos(nom);
			Files.move(fila, fila.resolveSibling(nouNom));
		}

		updateFila(pathTotal, filaTotal, oldPosTotal);

		String pathMode = mode.equals("Horitzontal") ? pathHoritzontal : mode.equals("Vertical") ? pathVertical : mode.equals("Diagonal") ? pathDiagonal : pathClassic;
		//Update ranking del mode
		File[] rankingMode = llegeixRanking(pathMode);

		//Incrementem en 1 la posició dels jugadors adelantats
		oldPos = oldPosMode == -1 ? rankingMode.length : oldPosMode;
		for(int i = Integer.parseInt(filaMode.get("pos")); i < oldPos; i++) {
			fila = Paths.get(rankingMode[i].getPath());
			String nom = rankingMode[i].getName();
			if(nom.split(" ")[1].equals(filaMode.get("jugador") + ".txt")) continue;
			String nouNom = incrementaPos(nom);
			Files.move(fila, fila.resolveSibling(nouNom));
		}

		updateFila(pathMode, filaMode, oldPosMode);
	}

	/**
	 * Elimina el ranking del jugador amb el nom donat.
	 * @param nom Nom del jugador.
	 */
	public void eliminaRankingJugador(String nom) throws IOException {
		File[] carpetaHoritzontal = llegeixRanking(pathHoritzontal);
		eliminaFilaJugador(carpetaHoritzontal, nom);

		File[] carpetaVertical = llegeixRanking(pathVertical);
		eliminaFilaJugador(carpetaVertical, nom);

		File[] carpetaDiagonal = llegeixRanking(pathDiagonal);
		eliminaFilaJugador(carpetaDiagonal, nom);

		File[] carpetaClassic = llegeixRanking(pathClassic);
		eliminaFilaJugador(carpetaClassic, nom);

		File[] carpetaTotal = llegeixRanking(pathTotal);
		eliminaFilaJugador(carpetaTotal, nom);
	}

	/**
	 * Obté els ranking del mdoe de joc especificat i el ranking total.
	 * @param mode Mode de joc desitjat.
	 * @return Un mapa de llistes de mapes strings representant els ranking del mode de joc i total.
	 */
	public Map<String, ArrayList<Map<String, String>>> getRankingByMode(String mode) throws FileNotFoundException {
		String pathMode = mode.equals("Horitzontal") ? pathHoritzontal : mode.equals("Vertical") ? pathVertical : mode.equals("Diagonal") ? pathDiagonal : pathClassic;

		ArrayList<Map<String, String>> rankingMode = new ArrayList<Map<String, String>>();
		ArrayList<Map<String, String>> rankingTotal = new ArrayList<Map<String, String>>();

		File[] carpetaMode = llegeixRanking(pathMode);
		rankingMode = llegeixFiles(carpetaMode);

		File[] carpetaTotal = llegeixRanking(pathTotal);
		rankingTotal = llegeixFiles(carpetaTotal);

		Map<String, ArrayList<Map<String, String>>> rankings = new HashMap<String, ArrayList<Map<String, String>>>();
		rankings.put("mode", rankingMode);
		rankings.put("total", rankingTotal);

		return rankings;
	}
}
