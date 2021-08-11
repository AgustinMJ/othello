package main.domini.controladors;

import main.domini.classes.*;
import main.persistencia.controladors.CtrlPersistenciaPartida;
import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.PartidaAcabada;
import main.utils.Tuple;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * La classe CtrlPartida és un controlador de domini que s'encarrega d'executar funcionalitats de les partides, demanar dades a la persistència i donar-les a la presentació.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPartida {
	Partida partida;
	CtrlPersistenciaPartida ctrlPersistenciaPartida;
	CtrlDomini ctrlDomini;
	CtrlMaquina ctrlMaquina;

	/**
	 * Constructora que inicialitza el controlador.
	 */
	public CtrlPartida() {
		this.ctrlPersistenciaPartida = new CtrlPersistenciaPartida();
		this.ctrlDomini = new CtrlDomini();
		this.ctrlMaquina = new CtrlMaquina();
	}

	/**
	 * Transforma un taulell en un string.
	 * @param t Taulell a transformar.
	 * @return El taulell en forma de string.
	 */
	private String rawTaulell(Taulell t) {
		String taulellString = "";
		Color casella;

		for(int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				casella = t.getCasella(i, j);
				taulellString += casella == Color.BLANC ? "B" : casella == Color.NEGRE ? "N" : "?";
			}
		}

		return taulellString;
	}

	/**
	 * Transforma un string representant un taulell en un objecte Taulell.
	 * @param t String representant el taulell a transformar.
	 * @return El taulell.
	 */
	private Taulell parseTaulell(String t) {
		Color[][] caselles = new Color[8][8];
		char[] tc = t.toCharArray();

		for(int i = 0; i < tc.length; i++) {
			caselles[i/8][i%8] = tc[i] == 'B' ? Color.BLANC : tc[i] == 'N' ? Color.NEGRE : Color.BUIT;
		}

		return new Taulell(caselles);
	}

	/**
	 * Transforma una partida en un mapa de strings.
	 * @param p Partida a transformar.
	 * @return La partida en forma de mapa de strings.
	 */
	private Map<String, String> rawPartida(Partida p) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("id",String.valueOf(p.getId()));
		ModeDeJoc m = p.getModeDeJoc();
		map.put("modeDeJoc", m == ModeDeJoc.HORITZONTAL ? "Horitzontal" : m == ModeDeJoc.VERTICAL ? "Vertical" : m == ModeDeJoc.DIAGONAL ? "Diagonal" : "Classic");
		map.put("torn", p.getTorn() == Color.BLANC ? "Blanc" : p.getTorn() == Color.NEGRE ? "Negre" : "Empat");
		map.put("finalitzada", p.isFinalitzada() ? "1" : "0");
		map.put("jugadorBlanc", p.getJugadorBlanc().getNom());
		map.put("tipusBlanc", p.getJugadorBlanc() instanceof Usuari ? "Usuari" : "Maquina");
		map.put("jugadorNegre", p.getJugadorNegre().getNom());
		map.put("tipusNegre", p.getJugadorNegre() instanceof Usuari ? "Usuari" : "Maquina");
		map.put("taulell", rawTaulell(p.getTaulell()));

		return map;
	}

	/**
	 * Transforma un mapa de string representant una partida en un objecte Partida.
	 * @param p El mapa representant la partida a transformar.
	 * @return La partida.
	 */
	private Partida parsePartida(Map<String, String> p) throws IOException {
		String m = p.get("modeDeJoc");
		String nomBlanc = p.get("jugadorBlanc");
		String nomNegre = p.get("jugadorNegre");

		Records recordsBlanc = this.ctrlDomini.getRecordsJugador(nomBlanc);
		Records recordsNegre = this.ctrlDomini.getRecordsJugador(nomNegre);

		Jugador jugadorBlanc = p.get("tipusBlanc").equals("Usuari")
				?
				new Usuari(nomBlanc, recordsBlanc)
				:
				new Maquina(nomBlanc, recordsNegre,ctrlMaquina.getAlgorismeMaquina(nomBlanc));
		Jugador jugadorNegre =p.get("tipusNegre").equals("Usuari")
				?
				new Usuari(nomNegre, recordsNegre)
				:
				new Maquina(nomNegre, recordsNegre, ctrlMaquina.getAlgorismeMaquina(nomNegre));

		return new Partida(
				Integer.parseInt(p.get("id")),
				m.equals("Horitzontal") ? ModeDeJoc.HORITZONTAL : m.equals("Vertical") ? ModeDeJoc.VERTICAL : m.equals("Diagonal") ? ModeDeJoc.DIAGONAL : ModeDeJoc.CLASSIC,
				jugadorBlanc,
				jugadorNegre,
				parseTaulell(p.get("taulell")),
				p.get("torn").equals("Blanc") ? Color.BLANC : p.get("torn").equals("Negre") ? Color.NEGRE : Color.BUIT,
				!p.get("finalitzada").equals("0")
		);
	}

	/**
	 * Construeix una partida a partir dels paràmetres donats.
	 * @param id Id de la partida.
	 * @param nomBlanc Nom del jugador blanc.
	 * @param nomNegre Nom del jugador negre.
	 * @param tipusBlanc Tipus de jugador del jugador blanc.
	 * @param tipusNegre Tipus de jugador del jugador negre.
	 * @param torn Torn de la partida.
	 * @param taulell Taulell de la partida.
	 * @param modeDeJoc Mode de joc de la partida.
	 * @param finalitzada Indica si la partida ha finalitzat
	 * @return La partida.
	 */
	private Partida parsePartida(String id, String nomBlanc, String nomNegre, String tipusBlanc, String tipusNegre, String torn, String taulell, String modeDeJoc, String finalitzada) throws IOException {
		Records recordsBlanc = this.ctrlDomini.getRecordsJugador(nomBlanc);
		Records recordsNegre = this.ctrlDomini.getRecordsJugador(nomNegre);

		Jugador jugadorBlanc = tipusBlanc.equals("Usuari")
				?
				new Usuari(nomBlanc, recordsBlanc)
				:
				new Maquina(nomBlanc, recordsBlanc,ctrlMaquina.getAlgorismeMaquina(nomBlanc));
		Jugador jugadorNegre =tipusNegre.equals("Usuari")
				?
				new Usuari(nomNegre, recordsNegre)
				:
				new Maquina(nomNegre, recordsNegre,ctrlMaquina.getAlgorismeMaquina(nomNegre));

		return new Partida(
				Integer.parseInt(id),
				modeDeJoc.equals("Horitzontal") ? ModeDeJoc.HORITZONTAL : modeDeJoc.equals("Vertical") ? ModeDeJoc.VERTICAL : modeDeJoc.equals("Diagonal") ? ModeDeJoc.DIAGONAL : ModeDeJoc.CLASSIC,
				jugadorBlanc,
				jugadorNegre,
				parseTaulell(taulell),
				torn.equals("Blanc") ? Color.BLANC : torn.equals("Negre") ? Color.NEGRE : Color.BUIT,
				!finalitzada.equals("0")
		);
	}

	/**
	 * Obté les posicions possibles del taulell de la partida.
	 * @return Una llista de tuples d'enters indicant les posicions possibles del taulell de la partida.
	 */
	public List<Tuple<Integer, Integer>> getSuccessors() {
		return this.partida.getTaulell().getSuccessors(this.partida.getTorn(), this.partida.getModeDeJoc());
	}

	/**
	 * Obté les posicions possibles d'un taulell i un torn donats.
	 * @param taulell El taulell del qual s'han d'obtenir les posicions.
	 * @param torn El torn.
	 * @param mode Mode de joc.
	 * @return La partida.
	 */
	public List<Tuple<Integer, Integer>> getSuccessors(String taulell, String torn, String mode) {
		Taulell t = parseTaulell(taulell);
		Color tornC;
		ModeDeJoc m = mode.equals("Horitzontal") ? ModeDeJoc.HORITZONTAL : mode.equals("Vertical") ? ModeDeJoc.VERTICAL : mode.equals("Diagonal") ? ModeDeJoc.DIAGONAL : ModeDeJoc.CLASSIC;
		if (torn.equals("Blanc")) tornC = Color.BLANC;
		else tornC = Color.NEGRE;

		return t.getSuccessors(tornC, m);
	}

	/**
	 * Assigna la partida a jugar en el domini.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusBlanc Tipus de jugador del jugador blanc.
	 * @param tipusNegre Tipus de jugador del jugador negre.
	 * @param torn Torn de la partida.
	 * @param taulell Taulell de la partida.
	 * @param modeDeJoc Mode de joc de la partida.
	 */
	public void setPartida(String id, String jugadorBlanc, String jugadorNegre, String tipusBlanc, String tipusNegre, String taulell, String modeDeJoc, String torn) throws IOException {
		this.partida = parsePartida(id, jugadorBlanc, jugadorNegre, tipusBlanc, tipusNegre, torn, taulell, modeDeJoc, "0");
	}

	/**
	 * Demana a la capa de persistencia que crei una partida amb els paràmetres rebuts.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusBlanc Tipus de jugador del jugador blanc.
	 * @param tipusNegre Tipus de jugador del jugador negre.
	 * @param taulell Taulell de la partida.
	 * @param modeDeJoc Mode de joc de la partida.
	 * @param torn Torn inicial de la partida.
	 */
	public void creaPartida(String jugadorBlanc, String jugadorNegre, String tipusBlanc, String tipusNegre, String taulell, String modeDeJoc, String torn) throws IOException {
		String id = String.valueOf(this.ctrlPersistenciaPartida.creaPartida(jugadorBlanc, jugadorNegre, tipusBlanc, tipusNegre, taulell, modeDeJoc, torn));
		this.partida = parsePartida(id, jugadorBlanc, jugadorNegre, tipusBlanc, tipusNegre, torn, taulell, modeDeJoc, "0");
	}

	/**
	 * Demana a la capa de persistència la partida amb id i nom dels jugadors donats.
	 * @param id Id de la partida.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 */
	public void getPartida(int id, String jugadorBlanc, String jugadorNegre) throws IOException {
		this.partida = parsePartida(this.ctrlPersistenciaPartida.getPartida(String.valueOf(id), jugadorBlanc, jugadorNegre));
	}

	/**
	 * Obté la partida que s'està jugant en forma de mapa de strings.
	 * @return La partida en forma de mapa de strings.
	 */
	public Map<String, String> getPartida() {
		return this.rawPartida(this.partida);
	}

	/**
	 * Comprova qui ha sigut el guanyador de la partida.
	 * @return Un string indicant el guanyador o si hi ha hagut un empat.
	 */
	public String getGuanyador() {
		String resultat;

		Taulell t = this.partida.getTaulell();
		int nBlanques = 0;
		int nNegres = 0;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (t.getCasella(i, j).equals(Color.BLANC)) ++nBlanques;
				else if (t.getCasella(i, j).equals(Color.NEGRE)) ++nNegres;
			}
		}

		if (nBlanques > nNegres) resultat = "Blanc";
		else if (nBlanques < nNegres) resultat = "Negre";
		else resultat = "Empat";

		return resultat;
	}

	/**
	 * Demana a la capa de persistència que guardi la partida que s'està jugant. Si la partida ha finalitzat, actualitza els records dels jugadors i els rankings.
	 * @param finalitzada Indica si la partida ha finalitzat
	 * @param abandonada Booleà indicant si la partida ha estat abandonada per algún jugador.
	 * @param winner String amb el color del jugador guanyador en cas d'abandonar la partida.
	 * @return String indicant el color del guanyador, en cas de que hi hagi un guanyador, "Empat", si és un empat, o null, si la partida no ha acabat.
	 */
	public String guardaPartida(boolean finalitzada, boolean abandonada, String winner) throws IOException, FilaEsTotal, FilaNoTotal {
		String resultat = null;
		Map<String, String> rawP = rawPartida(this.partida);

		if(finalitzada && !abandonada) {
			this.partida.setTorn();

			Jugador guanyador;
			Jugador perdedor;
			String tipusGuanyador;
			String tipusPerdedor;

			resultat = this.getGuanyador();

			if (resultat == "Blanc") {
				this.partida.setTorn(Color.BLANC);
			} else if (resultat == "Negre") {
				this.partida.setTorn(Color.NEGRE);
			} else {
				this.partida.setTorn(Color.BUIT);
			}

			if(this.partida.getTorn() == Color.BLANC) {
				guanyador = this.partida.getJugadorBlanc();
				tipusGuanyador = this.partida.getJugadorBlanc() instanceof Usuari ? "Usuari" : "Maquina";
				perdedor = this.partida.getJugadorNegre();
				tipusPerdedor = this.partida.getJugadorNegre() instanceof Usuari ? "Usuari" : "Maquina";
				guanyador.getRecords().incrementaParitdesBlanquesGuanyades();
			} else {
				guanyador = this.partida.getJugadorNegre();
				tipusGuanyador = this.partida.getJugadorNegre() instanceof Usuari ? "Usuari" : "Maquina";
				perdedor = this.partida.getJugadorBlanc();
				tipusPerdedor = this.partida.getJugadorBlanc() instanceof Usuari ? "Usuari" : "Maquina";
				guanyador.getRecords().incrementaParitdesNegresGuanyades();
			}

			Records recordsGuanyador = guanyador.getRecords();
			Records recordsPerdedor = perdedor.getRecords();

			this.ctrlDomini.setRecordsJugador(guanyador.getNom(), recordsGuanyador);
			this.ctrlDomini.setRecordsJugador(perdedor.getNom(), recordsPerdedor);

			new CtrlRanking().updateRanking(rawP.get("modeDeJoc"), guanyador.getNom(), perdedor.getNom(), tipusGuanyador, tipusPerdedor, resultat == "Empat");
		}

		else if(abandonada) {

			Jugador guanyador;
			Jugador perdedor;
			String tipusGuanyador;
			String tipusPerdedor;

			if(winner.equals(Color.BLANC.toString())) {
				guanyador = this.partida.getJugadorBlanc();
				tipusGuanyador = this.partida.getJugadorBlanc() instanceof Usuari ? "Usuari" : "Maquina";
				perdedor = this.partida.getJugadorNegre();
				tipusPerdedor = this.partida.getJugadorNegre() instanceof Usuari ? "Usuari" : "Maquina";
			} else {
				guanyador = this.partida.getJugadorNegre();
				tipusGuanyador = this.partida.getJugadorNegre() instanceof Usuari ? "Usuari" : "Maquina";
				perdedor = this.partida.getJugadorBlanc();
				tipusPerdedor = this.partida.getJugadorBlanc() instanceof Usuari ? "Usuari" : "Maquina";
			}

			Records recordsGuanyador = guanyador.getRecords();
			Records recordsPerdedor = perdedor.getRecords();

			if (winner.equals(Color.BLANC.toString())) recordsGuanyador.incrementaParitdesBlanquesGuanyades();
			else recordsGuanyador.incrementaParitdesNegresGuanyades();

			this.ctrlDomini.setRecordsJugador(guanyador.getNom(), recordsGuanyador);
			this.ctrlDomini.setRecordsJugador(perdedor.getNom(), recordsPerdedor);

			new CtrlRanking().updateRanking(rawP.get("modeDeJoc"), guanyador.getNom(), perdedor.getNom(), tipusGuanyador, tipusPerdedor, false);
		} else {
			this.ctrlDomini.setRecordsJugador(this.partida.getJugadorBlanc().getNom(), this.partida.getJugadorBlanc().getRecords());
			this.ctrlDomini.setRecordsJugador(this.partida.getJugadorNegre().getNom(), this.partida.getJugadorNegre().getRecords());
		}

		this.ctrlPersistenciaPartida.guardaPartida(rawP.get("id"), rawP.get("jugadorBlanc"), rawP.get("jugadorNegre"), rawP.get("taulell"), rawP.get("torn"), finalitzada);

		return resultat;
	}

	/**
	 * Demana a la capa de persistència les partides de l'usuari donat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesUsuari(String nom) throws FileNotFoundException {
		return this.ctrlPersistenciaPartida.getPartidesUsuari(nom);
	}

	/**
	 * Demana a la capa de persistència les partides no finalitzades de l'usuari donat.
	 * @param nom Nom de l'usuari.
	 * @return Una llista de mapes de strings representant les partides no finalitzades de l'usuari.
	 */
	public ArrayList<Map<String, String>> getPartidesNoFinalitzadesUsuari(String nom) throws IOException {
		return this.ctrlPersistenciaPartida.getPartidesNoFinalitzadesUsuari(nom);
	}

	/**
	 * Demana a CtrlMaquina que executi el torn de la màquina. Després, actualitza el nombre de peces girades de la màquina.
	 * @return Un string representant el taulell resultant després d'aplicar el torn de la màquina.
	 */
	public String jugaTornMaquina() {
		Color torn = partida.getTorn();
		Taulell taulell = partida.getTaulell();
		ModeDeJoc modeDeJoc = partida.getModeDeJoc();
		Maquina maquina = (Maquina) (torn == Color.BLANC ? partida.getJugadorBlanc() : partida.getJugadorNegre());
		String algorisme = maquina.getAlgorisme();

		Tuple<Integer, Integer> pos = ctrlMaquina.calculaMoviment(taulell, torn, modeDeJoc, maquina.getAlgorisme());

		int pecesGirades = 0;
		try {
			pecesGirades = taulell.colocarFitxa(pos.x, pos.y, torn, modeDeJoc);
		} catch (PartidaAcabada partidaAcabada) {
		}

		Jugador jugadorTorn = torn == Color.BLANC ? this.partida.getJugadorBlanc() : this.partida.getJugadorNegre();
		jugadorTorn.getRecords().incrementaPecesGirades(pecesGirades);

		partida.setTorn();
		return rawTaulell(this.partida.getTaulell());
	}

	/**
	 * Col·loca una peça en un taulell que s'està configurant.
	 * @param x Posició, en la coordenada x, on es vol col·locar la peça.
	 * @param y Posició, en al coordenada y, on es vol col·locar la peça.
	 * @param taulell Taulell en forma de string on es vol col·locar la peça.
	 * @param torn Color de la peça que es vol col·locar en el taulell.
	 * @return Un string representant el taulell després de col·locar la peça.
	 */
	public String colocarFitxaModeCreacio(int x, int y, String taulell, String torn) {
		Taulell t = parseTaulell(taulell);
		Color tornC;
		ModeDeJoc m = ModeDeJoc.CLASSIC;
		if (torn.equals("Blanc")) tornC = Color.BLANC;
		else tornC = Color.NEGRE;
		return rawTaulell(t.colocarFitxaModeCreacio(x, y, tornC, m));
	}

	/**
	 * Col·loca la peça en la posició indicada per l'usuari. Després, actualitza el nombre de peces girades de l'usuari.
	 * @return Un string representant el taulell resultant després d'aplicar el torn de l'usuari.
	 */
	public String jugaTornUsuari(int x, int y) {
		Color torn = partida.getTorn();
		Taulell taulell = partida.getTaulell();
		ModeDeJoc modeDeJoc = partida.getModeDeJoc();

		int pecesGirades = 0;
		try {
			pecesGirades = taulell.colocarFitxa(x, y, torn, modeDeJoc);
		} catch (PartidaAcabada partidaAcabada) {
		}
		Jugador jugadorTorn = torn == Color.BLANC ? this.partida.getJugadorBlanc() : this.partida.getJugadorNegre();
		jugadorTorn.getRecords().incrementaPecesGirades(pecesGirades);

		partida.setTorn();
		return rawTaulell(this.partida.getTaulell());
	}

	/**
	 * Demana a la capa de persistència el taulell incicial de la partida que s'està jugant.
	 * @return Una tupla de Strings. Sent la primera posició un string representant el taulell inicial de la partida i la segona un string representant el torn inicial.
	 */
	public Tuple<String, String> reiniciaPartida() throws FileNotFoundException {
		String taulellInicial = this.ctrlPersistenciaPartida.getTaulellInicial(String.valueOf(this.partida.getId()), this.partida.getJugadorBlanc().getNom(), this.partida.getJugadorNegre().getNom());
		String tornInicial = this.ctrlPersistenciaPartida.getTornInicial(String.valueOf(this.partida.getId()), this.partida.getJugadorBlanc().getNom(), this.partida.getJugadorNegre().getNom());
		this.partida.setTaulell(this.parseTaulell(taulellInicial));
		this.partida.setTorn(tornInicial.equals("Blanc") ? Color.BLANC : Color.NEGRE);
		return new Tuple<String, String>(taulellInicial, tornInicial);
	}

	/**
	 * Passa al següent torn de la partida.
	 */
	public void setTorn() {
		this.partida.setTorn();
	}
}
