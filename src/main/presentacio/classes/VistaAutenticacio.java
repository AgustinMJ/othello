package main.presentacio.classes;

import main.domini.classes.ModeDeJoc;
import main.domini.classes.Partida;
import main.domini.classes.Taulell;
import main.domini.classes.Usuari;
import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioAutenticacio;

import javax.swing.*;
import java.awt.*;
/**
 * La classe VistaAutenticació és una vista de presentació encarregada de mostrar la pantalla d'autenticació del usuari. Hereda de JPanel
 *
 * @author María Hernández Baeza
 */
public class VistaAutenticacio extends JPanel {
	CtrlPresentacio ctrlPresentacio;
	CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;
	SignUp signup;
	Login login;
	JPanel panelContenidor = new JPanel();

	/**
	 * Constructora que inicialitza la pantalla d'autenticació
	 * @param ctrlPresentacio
	 * @param ctrlPresentacioAutenticacio
	 */

	public VistaAutenticacio(CtrlPresentacio ctrlPresentacio, CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio){
		this.ctrlPresentacio = ctrlPresentacio;
		this.ctrlPresentacioAutenticacio = ctrlPresentacioAutenticacio;
		this.login = new Login(this, ctrlPresentacioAutenticacio);
		this.signup = new SignUp(this, ctrlPresentacioAutenticacio);
		panelContenidor.add(login);
		this.add(panelContenidor);
		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Canvia de vista cap a la pantalla de SignUp o de LogIn
	 * @param t TipusVistaAutenticació que ens indica a quina vista volem canviar.
	 */

	public void canviarVista(TipusVistaAutenticacio t){
		JPanel panel = null;
		switch(t){
			case SIGNUP:
				panel = signup;
				break;
			case LOGIN:
				panel = login;
				break;
		}
		panelContenidor.removeAll();
		panelContenidor.add(panel, BorderLayout.CENTER);
		panelContenidor.validate();
		validate();
		update(getGraphics());
	}

	/**
	 * Canvia de vista i mostra la pantalla principal de l'aplicació.
	 */
	public void mostraPantallaPrincipal(){
		this.ctrlPresentacio.changePanel(TipusVistaPrincipal.PANTALLA_PRINCIPAL);
	}
}
