package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;

import java.io.File;

/**
 * La classe Main és la classe principal del joc que s'encarrega d'iniciar la capa de presentació.
 *
 * @author Agustín Millán Jiménez
 */
public class Main {
	/**
	 * El punt d'entrada del programa.
	 * @param args Els arguments del programa.
	 */
	public static void main (String[] args) {
		borraDsStores("./dades");
		javax.swing.SwingUtilities.invokeLater (
				new Runnable() {
					public void run() {
						CtrlPresentacio ctrlPresentacion =
								new CtrlPresentacio();
						ctrlPresentacion.inicializarPresentacio();
					}
				}
		);
	}

	/**
	 * Elimina tots els .DS_Store dels directoris de dades.
	 * @param path El path a comprovar.
	 */
	private static void borraDsStores(String path) {
		File dades = new File(path);
		File[] files = dades.listFiles();
		for(File file : files) {
			if(file.isDirectory()) borraDsStores(file.getPath());
			else if(file.getName().equals(".DS_Store")) file.delete();
		}
	}
}