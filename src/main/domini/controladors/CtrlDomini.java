package main.domini.controladors;

import main.domini.classes.Records;
import main.persistencia.controladors.CtrlPersistenciaRecords;
import main.persistencia.controladors.CtrlPersistenciaRegles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * La classe CtrlDomini és el controlador principal de la capa de domini.
 *
 * @author Guillem García Gil
 */
public class CtrlDomini {
	String usuariSessio;

    /**
     * Donat un record crea un mapa amb tota la informació d'aquest.
     * @param r Objecte record
     * @return Retorna un mapa amb l'informació del record r.
     */
	private Map<String, Integer> rawRecords(Records r) {
		Map<String, Integer>  map = new HashMap<String, Integer>();
		map.put("pecesGirades", r.getPecesGirades());
		map.put("partidesBlanquesGuanyades", r.getPartidesBlanquesGuanyades());
		map.put("partidesNegresGuanyades", r.getPartidesNegresGuanyades());

		return map;
	}
    /**
     * Crea a la capa de persistencia un nou record amb l'informació r.
     * @param r Mapa que conté l'informació del nou record.
     * @return Retorna un mapa amb l'informació del record r.
     */
	private Records parseRecords(Map<String, Integer> r) {
		return new Records(
				r.get("pecesGirades"),
				r.get("partidesBlanquesGuanyades"),
				r.get("partidesNegresGuanyades")
		);
	}
    /**
     * Obté l'identificador de la sessió de l'usuari.
     * @return Retorna un String amb la sessió de l'usuari.
     */
    public String getSessio() {

        return this.usuariSessio;
    }

    /**
     * Modifica la sessió de l'usuari.
     * @param userSessio Identificador del nou usuari.
     */
    public void setSessio(String userSessio) {
        this.usuariSessio = userSessio;
    }

    /**
     * Elimina la sessió actual.
     */
    public void removeSessio() {
        this.usuariSessio = null;
    }

    /**
     * Solicita a la capa de persistència les regles del joc.
     * @return Retorna una llista de strings amb les regles.
     */
    public List<String> getRegles() throws FileNotFoundException {

    	return new CtrlPersistenciaRegles().getRegles();

    }

    /**
     * Solicita a la capa de persistencia la creació d'un nou record d'un cert jugador.
     * @param  nom String amb el nom del jugador.
     * @return Retorna el nou record del jugador nom.
     */
    public Records getRecordsJugador(String nom) throws IOException {
    	return parseRecords(new CtrlPersistenciaRecords().getRecordsJugador(nom));
    }

    /**
     * Solicita a la capa de persistencia la creació d'un nou record d'un cert jugador.
     * @param  nom String amb el nom del jugador.
     * @return Retorna el nou record del jugador nom.
     */
    public Map<String, Integer> getRawRecordsJugador (String nom) throws IOException {
	    return new CtrlPersistenciaRecords().getRecordsJugador(nom);
    }
    /**
     * Solicita a la capa de persistencia la modificació d'un record d'un cert jugador.
     * @param  nom String amb el nom del jugador.
     * @param r Objecte record.
     */
    public void setRecordsJugador(String nom, Records r) throws IOException {
    	new CtrlPersistenciaRecords().setRecordsJugador(nom, rawRecords(r));
    }
}
