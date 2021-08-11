package main.presentacio.controladors;


import main.domini.controladors.CtrlUsuari;
import main.domini.controladors.CtrlRanking;
import main.domini.controladors.CtrlDomini;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe CtrlPresentacioUsuari és el controlador encarregat de demanar a la capa de domini informació sobre els usuaris.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPresentacioUsuari {
	CtrlUsuari ctrlUsuari;
	CtrlDomini ctrlDomini;

	/**
	 * Constructora que inicialitza el controlador amb un controlador de domini.
	 * @param ctrlDomini El controlador de domini.
	 */
	public CtrlPresentacioUsuari(CtrlDomini ctrlDomini) {
		this.ctrlUsuari = new CtrlUsuari(ctrlDomini);
		this.ctrlDomini = ctrlDomini;
	}

	/**
	 * Obté el nom de l'usuari de la sessió.
	 * @return El nom de l'usuari de la sessió
	 */
	public String getNomUsuari() {
		return ctrlDomini.getSessio();
	}

	/**
	 * Obté els records de l'usuari donat.
	 * @param nom El nom de l'usuari.
	 * @return Un mapa d'enters representant el records de l'usuari.
	 */
	public Map<String, Integer> getRecordsUsuari(String nom) throws IOException {
		return this.ctrlDomini.getRawRecordsJugador(nom);
	}

	/**
	 * Canvia la contrasenya de l'usuari de la sessió.
	 * @param pwd La nova contrasenya.
	 */
	public void canviarContrasenya (String pwd){
		String nom = getNomUsuari();
		ctrlUsuari.canviaContrasenya(nom, pwd);
	}

	/**
	 * Demana a domini que s'elimini l'usuari a la sessió del joc i de la sessió.
	 */
	public void eliminarUsuari() throws IOException {
		String nom = getNomUsuari();
		this.ctrlUsuari.eliminaUsuari(nom);
		this.ctrlDomini.removeSessio();
	}
}