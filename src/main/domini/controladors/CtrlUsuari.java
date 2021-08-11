package main.domini.controladors;

import main.persistencia.classes.PersistenciaUsuari;
import main.persistencia.controladors.CtrlPersistenciaUsuari;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * La classe CtrlUsuari és un controlador de domini que s'encarrega d'executar funcionalitats del usuari, demanar dades a la persistència i donar-les a la presentació.
 *
 * @author Carles Ureta Boza
 */

public class CtrlUsuari {

    private CtrlPersistenciaUsuari usuari = new CtrlPersistenciaUsuari();
    private CtrlDomini domini;

    /**
     * Constructora que inicialitza el controlador.
     */
	public CtrlUsuari() {
		this.domini = new CtrlDomini() ;
	}
    /**
     * Constructora que inicialitza el controlador amb dades de CtrlDomini
     */
    public CtrlUsuari(CtrlDomini ctrlDomini) {
    	this.domini = ctrlDomini;
    }

    /**
     * Crea un Usuari amb un nom i una contrasenya determinats.
     * @param nom nom del usuari a crear
     * @param contrasenya contrasenya del usuari a crear
     */
    public void creaUsuari(String nom, String contrasenya) throws IOException, UsuariJaExistex, MaquinaJaExisteix {
        usuari.creaUsuari(nom, contrasenya);
    }

    /**
     * Mètode que comprova que un usuari hagi sigut autenticat exitosament i es pugui fer un inici de sessió.
     * @param nom nom del usuari a iniciar-li la sessió
     * @param contrasenya contrasenya del usuari a iniciar-li la sessió
     * @return retorna un bool que comnprova si l'usuari s'ha autenticat correctament i s'ha iniciat una sessió.
     */
    public boolean logIn(String nom, String contrasenya) {
        boolean autenticat = usuari.autenticaUsuari(nom, contrasenya);
        if (autenticat) {
            domini.setSessio(nom);
        }
        return autenticat;
    }

    /**
     * Comprova que l'usuari amb nom determinat i contrasenya determinada existeix en el nostre sistema
     * @param nom nom del usuari a autenticar
     * @param pwd contrasenya del usuari a autenticar
     * @return retorna un bool que es true si l'usuari exiteix i false si no existeix
     */
    public boolean autenticaUsuari(String nom, String pwd) {
        return  usuari.autenticaUsuari(nom, pwd);
    }

    /**
     * Elimina l'usuari que li passem per paràmetre del nostre sistema
     * @param nom nom del usuari a eliminar
     */
    public void eliminaUsuari(String nom) throws IOException {
        usuari.eliminaUsuari(nom);
    }

    /**
     * Canvia la contrasenya del usuari per una contrasenya nova.
     * @param nom nom del usuari al que li canviem la contrasneya.
     * @param contrasenya contrasenya nova a instaurar.
     */
    public void canviaContrasenya(String nom, String contrasenya) {
        usuari.canviaContrasenya(nom, contrasenya);
    }

    /**
     * Demana a la capa de persistència el llistat d'usuaris del sistema.
     * @return Una llista amb els noms dels usuaris
     */

    public List<String> getUsuaris() throws FileNotFoundException {

        return usuari.getUsuaris();
    }





}
