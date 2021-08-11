package main.presentacio.controladors;

import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlMaquina;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe CtrlPresentacioMaquines es un controlador que intercanvia informació i dades entre les capes de presentació i domini.
 *
 * @author Guillem Garcia Gil
 */
public class CtrlPresentacioMaquines {

    /**
     * Obté de la capa de domini una llista amb tota l'informació de les màquines del sistema.
     * @return Retorna una llista amb tota l'informació de les màquines.
     */
    public ArrayList<Map<String, String>> getMaquines() throws FileNotFoundException {
        CtrlMaquina cm = new CtrlMaquina();
        return cm.getMaquines();
    }

    /**
     * Crida a la capa de domini per a la creació d'una nova màquina.
     * @param nom String amb el nom de la nova màquina.
     * @param algorisme String amb l'algorisme de la nova màquina.
     * @param descripcio String amb la descripció de la nova màquina.
     */
    public void creaMaquina(String nom, String algorisme, String descripcio) throws MaquinaJaExisteix, IOException, UsuariJaExistex {
    	new CtrlMaquina().creaMaquina(nom, algorisme, descripcio);
    }

    /**
     * Crida a la capa de domini per a l'eliminació d'una màquina.
     * @param nom String amb el nom de la màquina a eliminar.
     */
	public void eliminaMaquina(String nom) throws IOException {
    	new CtrlMaquina().eliminaMaquina(nom);
	}

    /**
     * Crida a la capa de domini per obtindre els records d'una màquina.
     * @param nom String amb el nom de la màquina.
     * @return Un mapa amb la informació dels records de la màquina seleccionada.
     */
	public Map<String, Integer> getRecordsMaquina(String nom ) throws IOException {
        return new CtrlDomini().getRawRecordsJugador(nom);
    }
}
