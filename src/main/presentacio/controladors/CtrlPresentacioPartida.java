package main.presentacio.controladors;

import main.domini.controladors.CtrlDomini;
import main.domini.controladors.CtrlMaquina;
import main.domini.controladors.CtrlPartida;
import main.domini.controladors.CtrlUsuari;
import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.Tuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe CtrlPresentacioPartida és el controlador encarregat de demanar a la capa de domini que s'executin les diferents funcionalitats de la partida i demanar-li dades.
 *
 * @author Carles Ureta Boza
 */
public class CtrlPresentacioPartida {
    CtrlPartida ctrlPartida = new CtrlPartida();
    CtrlMaquina ctrlMaquina = new CtrlMaquina();
    CtrlUsuari ctrlUsuari = new CtrlUsuari();
    CtrlDomini ctrlDomini;

	/**
	 * Constructora per defecte
	 */
    public CtrlPresentacioPartida() {}

	/**
	 * Constructora amb un controlador de domini.
	 * @param ctrlDomini Controlador de domini.
	 */
    public CtrlPresentacioPartida(CtrlDomini ctrlDomini) {
    	this.ctrlDomini = ctrlDomini;
    }

	/**
	 * Transforma un taulell en forma de string a una matriu de caràcters.
	 * @param taulell Taulell en forma de string.
	 * @return El taulell en forma de matriu de caràcters.
	 */
    public char[][] parseTaulell(String taulell) {
	    char[][] caselles = new char[8][8];
	    char[] tc = taulell.toCharArray();

	    for(int i = 0; i < tc.length; i++) {
		    caselles[i/8][i%8] = tc[i];
	    }

	    return caselles;
    }

	/**
	 * Demana al controlador de partides de domini les possibles posicions del taulell de la partida.
	 * @return Una llista de tuples d'enters indicant les possibles posicions del taulell on es pot col·locar una peça.
	 */
    public List<Tuple<Integer, Integer>> getSuccessors() {
    	return this.ctrlPartida.getSuccessors();
    }

	/**
	 * Demana al controlador de partides de domini les possibles posicions d'un taulell i torn donat.
	 * @param taulell El taulell del qual s'han d'obtenir les posicions.
	 * @param torn El torn.
	 * @param mode Mode de joc.
	 * @return Una llista de tuples d'enters indicant les possibles posicions del taulell on es pot col·locar una peça.
	 */
    public List<Tuple<Integer, Integer>> getSuccessors(String taulell, String torn, String mode) {
        return this.ctrlPartida.getSuccessors(taulell, torn, mode);
    }

	/**
	 * Demana al controlador de partides de domini que crei una partida amb els paràmetres donats.
	 * @param nomJugadorBlanc El nom del jugador blanc.
	 * @param nomJugadorNegre El nom del jugador negre.
	 * @param tipusJugadorBlanc El tipus de jugador del jugador blanc.
	 * @param tipusJugadorNegre El tipus de jugador del jugador negre.
	 * @param modeDeJoc El mode de joc de la partida.
	 * @param taulell El taulell de la partida en forma de string.
	 * @param torn Torn inicial de la partida.
	 */
    public void creaPartida(String nomJugadorBlanc, String nomJugadorNegre, String tipusJugadorBlanc, String tipusJugadorNegre, String taulell, String modeDeJoc, String torn) throws IOException {
        ctrlPartida.creaPartida(nomJugadorBlanc, nomJugadorNegre, tipusJugadorBlanc, tipusJugadorNegre, taulell, modeDeJoc, torn);
    }

	/**
	 * Demana al controlador de partides les partides de l'usuari autenticat.
	 * @return una llista de mapes de strings representant les partides de l'usuari autenticat.
	 */
    public ArrayList<Map<String, String>> getPartidesUsuari() throws FileNotFoundException {
        return ctrlPartida.getPartidesUsuari(this.ctrlDomini.getSessio());
    }

	/**
	 * Demana al controlador de partides les partides no finalitzades de l'usuari autenticat.
	 * @return Una llista de mapes de strings representant les partides no finalitzades de l'usuari autenticat.
	 */
    public ArrayList<Map<String, String>> getPartidesNoFinalitzadesUsuari() throws IOException {
        return ctrlPartida.getPartidesNoFinalitzadesUsuari(this.ctrlDomini.getSessio());
    }

	/**
	 * Demana al controlador de partides que reinicii la partida.
	 * @return Una tupla de Strings. Sent la primera posició un string representant el taulell inicial de la partida i la segona un string representant el torn inicial.
	 */
    public Tuple<String, String> reiniciaPartida() throws FileNotFoundException {
        return ctrlPartida.reiniciaPartida();
    }

	/**
	 * Demana a la capa de domini la llista de jugadors.
	 * @return Un array de strings amb els noms dels jugadors.
	 */
    public String[] getJugadors() throws FileNotFoundException {

        List<String> usuaris = ctrlUsuari.getUsuaris();
        List<Map<String, String>> maquines = ctrlMaquina.getMaquines();
        String[] llista = new String [usuaris.size() + maquines.size()];
        int i;
        for (i=0;  i < usuaris.size(); ++i) {
            llista[i]=usuaris.get(i) + " Usuari";
        }


        i = 0;
        for (int j = usuaris.size(); j < llista.length; ++j) {
            llista[j]=maquines.get(i).get("nom") + " Maquina";
            ++i;
        }

        return llista;
    }

	/**
	 * Demana al controlador de partides que inicii la partida amb els paràmetres donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusBlanc Tipus de jugador del jugador blanc.
	 * @param tipusNegre Tipus de jugador del jugador negre.
	 * @param taulell Taulell de la partida en forma de string.
	 * @param torn Torn de la partida.
	 * @param modeDeJoc El mode de joc de la partida.
	 */
    public void setPartida(String id, String jugadorBlanc, String jugadorNegre, String tipusBlanc, String tipusNegre, String taulell, String modeDeJoc, String torn) throws IOException {

        ctrlPartida.setPartida(id, jugadorBlanc, jugadorNegre, tipusBlanc, tipusNegre, taulell, modeDeJoc, torn);
    }

	/**
	 * Demana al controlador de partides la partida que s'està jugant.
	 * @return Un mapa de strings representant la partida.
	 */
    public Map<String, String> getPartida() {
        return this.ctrlPartida.getPartida();
    }

	/**
	 * Demana al controlador de partides que col·loqui una peça en mode creació en un taulell i torn donats.
	 * @param x Posició, en la coordenada x, on es vol col·locar la peça.
	 * @param y Posició en la coordenada y, on es vol col·locar la peça.
	 * @param taulell Taulell en el qual es vol col·locar la peça.
	 * @param torn Color de la peça que es vol col·locar.
	 * @return Una matriu de caràcters representant el taulell després de col·locar la peça.
	 */
    public char[][] colocarFitxaModeCreacio(int x, int y, String taulell, String torn) {
        return parseTaulell(this.ctrlPartida.colocarFitxaModeCreacio(x, y, taulell, torn));
    }

	/**
	 * Demana al controlador de partides que col·loqui una peça en la posició del taulell indicada perl'usuari.
	 * @param x Posició, en la coordenada x, on es vol col·locar la peça.
	 * @param y Posició en la coordenada y, on es vol col·locar la peça.
	 * @return Una matriu de caràcters representant el taulell després de col·locar la peça.
	 */
	public char[][] jugaTornUsuari(int x, int y) {
		return parseTaulell(this.ctrlPartida.jugaTornUsuari(x, y));
	}

	/**
	 * Demana al controlador de partides que jugui el torn de la màquina.
	 * @return Una matriu de caràcters representant el taulell després de col·locar la peça.
	 */
	public char[][] jugaTornMaquina() {
		return parseTaulell(this.ctrlPartida.jugaTornMaquina());
	}

	/**
	 * Demana al controlador de partides que guardi la partida que s'està jugant.
	 * @param finalitzada Booleà indicant si la partida ha acabat.
	 * @param abandonada Booleà indicant si la partida ha estat abandonada per algún jugador.
	 * @param guanyador String amb el color del jugador guanyador en cas d'abandonar la partida.
	 * @return Resultat de la partida.
	 */
	public String guardaPartida(boolean finalitzada, boolean abandonada, String guanyador) throws IOException, FilaEsTotal, FilaNoTotal {
    	return this.ctrlPartida.guardaPartida(finalitzada, abandonada, guanyador);
	}

	/**
	 * Demana al controlador de partides que la partida passi de torn.
	 */
	public void setTorn() {
		this.ctrlPartida.setTorn();
	}

	public boolean comprovaJugadorSessio(String nomJugador) {
		return ctrlDomini.getSessio().equals(nomJugador);
	}
}
