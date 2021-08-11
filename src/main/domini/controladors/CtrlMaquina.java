package main.domini.controladors;

import main.domini.classes.*;
import main.persistencia.controladors.CtrlPersistenciaMaquina;
import main.utils.MaquinaJaExisteix;
import main.utils.Tuple;
import main.utils.UsuariJaExistex;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe CtrlMaquina és un controlador de domini que s'encarrega d'executar funcionalitats de les màquines, demanar dades a la persistència i donar-les a la presentació.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlMaquina {
	CtrlPersistenciaMaquina ctrlPersistenciaMaquina;

	/**
	 * Constructora que inicialitza el controlador.
	 */
	public CtrlMaquina() {
		this.ctrlPersistenciaMaquina = new CtrlPersistenciaMaquina();
	}

	/**
	 * Transforma un string amb els paràmetres de l'algorisme, un color de l'algorisme i un mode de joc en un objecte Algorisme.
	 * @param nom Paràmetres de l'algorisme.
	 * @param c Color de l'algorisme.
	 * @param m Mode de joc de la partida on s'aplica l'algorisme.
	 * @return L'algorisme corresponent als paràmetres indicats.
	 */
	private Algorisme parseAlgorisme(String nom, Color c, ModeDeJoc m) {
		String[] parametres = nom.split(" ");

		String nomAlgorisme = parametres[0];
		int profunditat = Integer.parseInt(parametres[1]);
		String nomHeuristica = parametres[2];

		FuncioHeuristica fh = nomHeuristica.equals("HeuristicaNombrePeces") ? new HeuristicaNombrePeces() : new HeuristicaTaulell();

		return nomAlgorisme.equals("Minimax") ? new Minimax(profunditat, m, c, fh) : new AlfaBeta(profunditat, m, c, fh);
	}

	/**
	 * Demana una màquina a la capa de persistència.
	 * @param nom Nom de la màquina a demanar.
	 * @return Un mapa amb els atributs de la màquina en forma de string.
	 */
	public Map<String, String> getMaquina(String nom) throws FileNotFoundException {
		return this.ctrlPersistenciaMaquina.getMaquina(nom);
	}

	/**
	 * Demana la llista de màquines a la capa de persistència.
	 * @return Una llista de mapes amb els atributs de les màquines en forma de string.
	 */
	public ArrayList<Map<String, String>> getMaquines() throws FileNotFoundException {
		return this.ctrlPersistenciaMaquina.getMaquines();
	}

	/**
	 * Demana l'algorisme corresponent a una màquina a la capa de persistència.
	 * @param nom Nom de la màquina.
	 * @return Un string amb els paràmetres de l'algorisme.
	 */
	public String getAlgorismeMaquina(String nom) throws FileNotFoundException {
		return this.ctrlPersistenciaMaquina.getAlgorismeMaquina(nom);
	}

	/**
	 * Executa un algorisme.
	 * @param t Taulell on s'executa l'algorisme.
	 * @param c Color de l'algorisme.
	 * @param m Mode de joc de la partida.
	 * @param nomAlgorisme Paràmetres de l'algorisme en forme de string.
	 * @return Una tuple d'enters indicant on l'algorisme vol colocar la peça.
	 */
	public Tuple<Integer, Integer> calculaMoviment(Taulell t, Color c, ModeDeJoc m, String nomAlgorisme) {
		return this.parseAlgorisme(nomAlgorisme, c, m).execute(t);
	}

	/**
	 * Demana a la capa de persistència que crei una màquina.
	 * @param nom Nom de la màquina.
	 * @param algorisme String amb els paràmetres de l'algorisme de la màquina.
	 * @param descripcio Descripció de la màquina
	 */
	public void creaMaquina(String nom, String algorisme, String descripcio) throws MaquinaJaExisteix, IOException, UsuariJaExistex {
		this.ctrlPersistenciaMaquina.creaMaquina(nom, algorisme, descripcio);
	}

	/**
	 * Demana a la capa de persistència que elimini una màquina
	 * @param nom Nom de la màquina a eliminar.
	 */
	public void eliminaMaquina(String nom) throws IOException {
		this.ctrlPersistenciaMaquina.eliminaMaquina(nom);
	}
}
