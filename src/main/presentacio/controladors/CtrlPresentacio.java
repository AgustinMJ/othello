package main.presentacio.controladors;

import main.domini.controladors.CtrlDomini;
import main.presentacio.classes.*;

import javax.swing.*;
import java.awt.*;

/**
 * La classe CtrlPresentacio és el controlador principal de la capa de presentació.
 *
 * @author Agustín Millán Jiménez
 */
public class CtrlPresentacio {
	private VistaPrincipal vistaPrincipal;
	CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;
	CtrlPresentacioMaquines ctrlPresentacioMaquines;
	CtrlPresentacioRanking ctrlPresentacioRanking;
	CtrlPresentacioPartida ctrlPresentacioPartida;
	CtrlPresentacioUsuari ctrlPresentacioUsuari;
	CtrlDomini ctrlDomini;

	/**
	 * Inicialitza la capa de presentació.
	 */
	public void inicializarPresentacio() {
		this.ctrlDomini = new CtrlDomini();
		this.ctrlPresentacioUsuari = new CtrlPresentacioUsuari(this.ctrlDomini);
		this.ctrlPresentacioAutenticacio = new CtrlPresentacioAutenticacio(this.ctrlDomini);
		this.ctrlPresentacioRanking = new CtrlPresentacioRanking();
		this.ctrlPresentacioMaquines = new CtrlPresentacioMaquines();

		this.ctrlPresentacioPartida = new CtrlPresentacioPartida(ctrlDomini);

		vistaPrincipal = new VistaPrincipal(this);

		this.changePanel(TipusVistaPrincipal.AUTENTICACIO);
	}

	/**
	 * Canvia la pantalla del joc.
	 * @param t Tipus de vista principal que es vol veure.
	 */
	public void changePanel(TipusVistaPrincipal t) {
		JPanel panel = null;

		switch (t) {
			case CONSULTAR_USUARI:
				panel = new ConsultarUsuari(this.ctrlPresentacioUsuari);
				break;
			case MODIFICAR_USUARI:
				panel = new ModificarUsuari(this, this.ctrlPresentacioUsuari);
				break;
			case CREAR_PARTIDA:
				panel = new CrearPartida(this, this.ctrlPresentacioPartida);
				break;
			case CARREGAR_PARTIDA:
				panel = new CarregarPartida(this, this.ctrlPresentacioPartida);
				break;
			case HISTORIAL_PARTIDES:
				panel = new HistorialPartides(this.ctrlPresentacioPartida);
				break;
			case RANKING:
				panel = new ConsultarRanking(this.ctrlPresentacioRanking);;
				break;
			case PANTALLA_PRINCIPAL:
				panel = new PantallaPrincipal(this);
				break;
			case VISTA_PARTIDA:
				vistaPrincipal.setResizable(false);
				panel = new VistaPartida(this, this.ctrlPresentacioPartida, this.ctrlPresentacioAutenticacio);
				break;
			case AUTENTICACIO:
				panel = new VistaAutenticacio(this, this.ctrlPresentacioAutenticacio);
				break;
			case MAQUINES:
				panel = new Maquines();
				break;
		}
		this.vistaPrincipal.changePanel(panel);
	}

	/**
	 * Demana al control de deomini que elimini l'usuari de la sessió i canvia a la pantalla d'autenticació.
	 */
	public void tancaSessio() {
		this.ctrlDomini.removeSessio();
		this.changePanel(TipusVistaPrincipal.AUTENTICACIO);
	}

	/**
	 * Tanca el joc.
	 */
	public void sortir() {
		System.exit(0);
	}
}

