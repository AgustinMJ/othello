package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * La classe VistaPrincipal és la finestra del joc. Hereda de la classe JFrame.
 *
 * @author Agustín Millán Jiménez
 */
public class VistaPrincipal extends JFrame{
	private CtrlPresentacio ctrlPresentacio;
	final private JPanel panelContenidor = new JPanel();
	final private JMenuBar menubarVista;

	/**
	 * Constructora que inicialitza la finestra.
	 * @param ctrlPresentacio El controlador de presentació
	 */
	public VistaPrincipal(CtrlPresentacio ctrlPresentacio) {
		this.ctrlPresentacio = ctrlPresentacio;
		this.menubarVista = new MenuPrincipal(this.ctrlPresentacio);
		this.inicialitzarComponents();
	}

	/**
	 * Canvia de pantalla a la pantalla d'autenticació
	 * @param p Panell al que es vol canviar la pantalla.
	 */
	public void changePanel(JPanel p) {
		setJMenuBar(menubarVista);

		if (p instanceof VistaAutenticacio) {
			setJMenuBar(null);
		}

		getContentPane().removeAll();
		getContentPane().add(p, BorderLayout.CENTER);
		getContentPane().validate();
		validate();
		update(getGraphics());
	}

	/**
	 * Inicialitza els components de la finestra.
	 */
	public void inicialitzarComponents() {
		panelContenidor.setLayout(new BorderLayout(0, 0));

		//Posa la barra del menu i el contingut de la pantalla principal
		setJMenuBar(menubarVista);
		setContentPane(panelContenidor);

		//Configuracions de la finestra
		setTitle("Othello Masters");
		setMinimumSize(new Dimension(1100,624));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
