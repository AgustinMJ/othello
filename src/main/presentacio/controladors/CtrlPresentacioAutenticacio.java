package main.presentacio.controladors;

import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlUsuari;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import java.io.IOException;

/**
 * La classe CtrlPresentacioAutenticació és el controlador encarregat de demanar a la capa de domini que s'executin les diferents funcionalitats per autenticar usuaris i demanar-li dades.
 *
 * @author Guillem Garcia Gil
 */
public class CtrlPresentacioAutenticacio {

    private CtrlDomini ctrlDomini;

    /**
     * Constructora amb un controlador de domini.
     * @param ctrlDomini Controlador de domini.
     */
    public CtrlPresentacioAutenticacio(CtrlDomini ctrlDomini) {
        this.ctrlDomini = ctrlDomini;
    }

    /**
     * Autentica a un usuari.
     * @param nom String amb el nom de l'usuari a autenticar.
     * @param pwd String amb la contrasenya de l'usuari a autenticar.
     * @return Retorna cert si l'usuari s'ha autenticat, altrament retorna fals.
     */
    public boolean autenticaUsuari(String nom, String pwd) {
        CtrlUsuari cu = new CtrlUsuari(ctrlDomini);
        return cu.logIn(nom, pwd);
    }

    /**
     * Autentica a un usuari que jugarà una partida.
     * @param nom String amb el nom de l'usuari a autenticar.
     * @param pwd String amb la contrasenya de l'usuari a autenticar.
     * @return Retorna cert si l'usuari s'ha autenticat, altrament retorna fals.
     */
	public boolean autenticaUsuariPartida(String nom, String pwd) {
		CtrlUsuari cu = new CtrlUsuari(ctrlDomini);
		return cu.autenticaUsuari(nom, pwd);
	}

    /**
     * A partir d'un nom i una contrasenya crea un usuari.
     * @param nom String amb el nom de l'usuari a crear.
     * @param pwd String amb la contrasenya de l'usuari a crear.
     */
    public void creaUsuari(String nom, String pwd) throws IOException, UsuariJaExistex, MaquinaJaExisteix {
        CtrlUsuari cu = new CtrlUsuari(ctrlDomini);
        cu.creaUsuari(nom, pwd);
    }

    /**
     * Retorna el nom de l'usuari que no està autenticat.
     * @param p1 String amb el nom de l'usuari 1.
     * @param p2 String amb nom de l'usuari 2.
     * @return Retorna el nom de l'usuari no autenticat.
     */
    public String getUsuariNoAutenticat(String p1, String p2) {

        if (p1.equals(ctrlDomini.getSessio()))return p2;
        else return p1;
    }
}
