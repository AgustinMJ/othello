package main.presentacio.classes;

import main.domini.controladors.CtrlPartida;
import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioPartida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * La classe CrearPartida gestiona els diferents panells per configurar partida nova. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */
public class CrearPartida extends JPanel {
	CtrlPresentacio ctrlPresentacio;
	JPanel panelContenidor = new JPanel();

	String torn = "Blanc";
	String taulell =
			"????????" +
			"????????" +
			"????????" +
			"???BN???" +
			"???NB???" +
			"????????" +
			"????????" +
			"????????" ;

	CtrlPresentacioPartida ctrlPresentacioPartida;


	ConfigurarTaulell panelConfigurarTaulell;
	SeleccionarJugadors panelSeleccionarJugadors;

	CrearPartidaSub panelCrearPartidaSub = new CrearPartidaSub(this);

	/**
	 * Creadora de la classe que inicialitza la pantalla.
	 * @param ctrlPresentacioPartida Controlador de presentació de partida.
	 * @param ctrPresentacio Controlador de presentació.
	 */
	public CrearPartida(CtrlPresentacio ctrPresentacio, CtrlPresentacioPartida ctrlPresentacioPartida) {
		this.ctrlPresentacio = ctrPresentacio;
		this.ctrlPresentacioPartida = ctrlPresentacioPartida;
		this.panelSeleccionarJugadors = new SeleccionarJugadors(this, ctrlPresentacioPartida);
		this.setBackground(Color.decode("#d0f0c0"));
		this.inicialitzarComponents();
	}

	/**
	 * Incialitza els components del panell
	 */
	private void inicialitzarComponents() {
		this.add(panelCrearPartidaSub);

	}

	/**
	 * Canvia entre els diferents panells en funció del paràmetre.
	 * @param idPanel Identificador del panell que es vol mostrar.
	 */
	public void changePanel(int idPanel) throws FileNotFoundException {
		JPanel panel = null;
		switch (idPanel) {
			case 1: //pantalla crear partida
				panel = panelCrearPartidaSub;
				break;

			case 2: //pantalla configurar taulell
				panel = new ConfigurarTaulell(this, taulell, torn);
				break;

			case 3: //pantalla seleccionar Jugadors
				panel = panelSeleccionarJugadors;
				break;
		}

		this.removeAll();
		this.add(panel);
		this.validate();
		update(getGraphics());
	}

	/**
	 * Comunica al panell CrearPartidaSub que el nom i tipus del jugador blanc.
	 * @param nom Nom del jugador blanc.
	 * @param tipus Tipus del jugador blanc.
	 */
	public void setJugadorBlanc(String nom, String tipus) throws FileNotFoundException {
		panelCrearPartidaSub.setJugadorBlanc(nom, tipus);
	}

	/**
	 * Comunica al panell CrearPartidaSub que el nom i tipus del jugador negre.
	 * @param nom Nom del jugador negre.
	 * @param tipus Tipus del jugador negre.
	 */
	public void setJugadorNegre(String nom, String tipus) throws FileNotFoundException {
		panelCrearPartidaSub.setJugadorNegre(nom, tipus);
	}

	/**
	 * Comunica al panell CrearPartidaSub el taulell creat.
	 * @param taulell Taulell configurat.
	 * @param torn Torn inicial de la partida.
	 */
	public void setTaulell(String taulell, String torn) {
		panelCrearPartidaSub.setTaulell(taulell, torn);
		this.taulell = taulell;
		this.torn = torn;
	}



	/**
	 * Demana al controlador de presentació de partida que creï una nova partida amb els paràmetres seleccionats.
	 * @param jugadorBlanc Nom del jugador blanc.
	 * @param jugadorNegre Nom del jugador negre.
	 * @param tipusBlanc Tipus del jugador blanc.
	 * @param tipusNegre Tipus del jugador negre.
	 * @param taulell Taulell de la partida.
	 * @param modeDeJoc Mode de joc seleccionat.
	 * @param torn Torn inicial de la partida.
	 */
	public void creaPartida(String jugadorBlanc, String jugadorNegre, String tipusBlanc, String tipusNegre, String taulell, String modeDeJoc, String torn) {
		try {
			if(ctrlPresentacioPartida.getSuccessors(taulell, torn, modeDeJoc).size() > 0) {
				ctrlPresentacioPartida.creaPartida(jugadorBlanc, jugadorNegre, tipusBlanc, tipusNegre, taulell, modeDeJoc, torn);
				this.ctrlPresentacio.changePanel(TipusVistaPrincipal.VISTA_PARTIDA);
			} else {
				JOptionPane.showMessageDialog(new JDialog(), "No es pot crear una partida amb un taulell sense moviments possibles", "Error configurar taulell",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "No s'ha pogut crear la partida amb els paràmtres seleccionats", "Error al crear partida ",
					JOptionPane.ERROR_MESSAGE);

		}

	}
}

