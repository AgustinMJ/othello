package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * La classe PantallaPrincipal és la pantalla principal del joc. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */
public class PantallaPrincipal extends JPanel {
	JPanel panellContenidor = new JPanel();
	Index index;
	Regles regles;
	CtrlPresentacio ctrlPresentacio;

	/**
	 * Constructora que inicialitza la pantalla.
	 * @param ctrlPresentacio El controlador de presentació
	 */
	public PantallaPrincipal(CtrlPresentacio ctrlPresentacio) {
		this.ctrlPresentacio = ctrlPresentacio;
		this.index = new Index(this);
		this.regles = new Regles(this);
		this.inicialitzarComponents();
		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Inicialitza els components de la pantalla.
	 */
	private void inicialitzarComponents() {
		panellContenidor.setLayout(new FlowLayout());
		panellContenidor.add(index);
		this.add(panellContenidor);
	}

	/**
	 * Canvia el contingut que es veu en aquesta pantalla.
	 * @param t El tipus de pantalla que es vol veure.
	 */
	public void canviarVista(TipusPantallaPrincipal t) {
		JPanel p = null;
		switch (t) {
			case INDEX:
				p = index;
				break;
			case REGLES:
				p = regles;
				break;
		}
		panellContenidor.removeAll();
		panellContenidor.add(p, BorderLayout.CENTER);
		panellContenidor.validate();
		validate();
		update(getGraphics());
	}

	/**
	 * Demana al controlador de presentació que tanqui la sessió.
	 */
	public void tancaSessio() {
		this.ctrlPresentacio.tancaSessio();
	}

	/**
	 * Demana al controlador de presentació que tanqui el joc.
	 */
	public void sortir() {
		this.ctrlPresentacio.sortir();
	}
}
