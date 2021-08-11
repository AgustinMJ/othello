package main.presentacio.controladors;

import main.domini.controladors.CtrlDomini;

import java.io.FileNotFoundException;
import java.util.List;


/**
 * La classe CtrlPresentacioRanking és el controlador encarregat de demanar a la capa de domini les regles del joc.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPresentacioRegles {
	/**
	 * Constructora per defecte.
	 */
	public CtrlPresentacioRegles() {
	}

	/**
	 * Demana a la capa de domini les regles del joc.
	 * @return una llista de strings amb les diferents regles del joc.
	 */
	public List<String> getRegles() throws FileNotFoundException {
		return new CtrlDomini().getRegles();
	}


}
