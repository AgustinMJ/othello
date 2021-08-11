package main.persistencia.controladors;


import main.domini.classes.Usuari;
import main.persistencia.classes.*;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * La classe CtrlPersistenciaUsuari és el controlador encarregat de demanar a la persistència els usuaris i servir-los a la capa de domini.
 *
 * @author Carles Ureta Boza
 */

public class CtrlPersistenciaUsuari {
    private PersistenciaUsuari usuari = new PersistenciaUsuari();
	private PersistenciaMaquina maquina = new PersistenciaMaquina();
    private PersistenciaRanking rankingUsuari = new PersistenciaRanking();
    private PersistenciaPartida partidesUsuari = new PersistenciaPartida();
    private PersistenciaRecords persistenciaRecords = new PersistenciaRecords();

    /**
     * Constructora per defecte
     */

    public CtrlPersistenciaUsuari() {}

    /**
     * Demana a la persistència de usuari que creï un nou usuari amb els paràmetres donats.
     * @param nom Nom de l'usuari.
     * @param contrasenya Contrasenya del jugador.
     */
    public  void creaUsuari(String nom, String contrasenya) throws IOException, UsuariJaExistex, MaquinaJaExisteix {
    	if(maquina.existeixMaquina(nom)) throw new MaquinaJaExisteix();
        usuari.creaUsuari(nom, contrasenya);
    }

    /**
     * Demana a la persistència que comprovi que un usuari ha estat autenticat correctament i mira que s'hagi creat una sessió correctament.
     * @param nom nom del usuari a autenticar
     * @param contrasenya contrasenya del usauri a autenticar
     * @return una llista de strings representant les regles.
     */
    public  boolean autenticaUsuari(String nom, String contrasenya) {
        return usuari.autenticaUsuari(nom, contrasenya);
    }


    /**
     * Elimina l'usuari que passem per paràmetre.
     * @param nom nom del usuari a eliminar.
     */
    public  void eliminaUsuari(String nom) throws IOException {
        usuari.eliminaUsuari(nom);
        rankingUsuari. eliminaRankingJugador(nom);
        partidesUsuari.eliminaPartidesusuari(nom);
        persistenciaRecords.eliminaRecordsJugador(nom);
    }

    /**
     * Canvia la contrasenya del usuari que passem per paràmetre.
     * @param nom nom del usuari al que li canviem la contrasenya.
     * @param contrasenya  contrasenya nova a establir.
     */
    public  void canviaContrasenya(String nom, String contrasenya) {
        usuari.canviaContrasenya(nom, contrasenya);
    }

    /**
     * Demana a la persistència de l'usuari una llista amb els usuaris del sistema.
     * @return una llista de strings representant els noms dels usuaris.
*/
    public  List<String> getUsuaris() throws FileNotFoundException {
        return usuari.getUsuaris();
    }

}
