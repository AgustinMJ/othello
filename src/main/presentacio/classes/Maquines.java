package main.presentacio.classes;

import main.domini.classes.Maquina;
import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioMaquines;
import main.utils.Tuple;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La classe Maquines és una vista encarregada de canviar de vistes a la llista de màquines del sistema o a la creació d'una nova.
 *
 * @author Guillem García Gil
 */

public class Maquines extends JPanel {

    LlistaMaquines llistaMaquines;
    CrearMaquina crearMaquina;

	/**
	 * Constructora de la classe.
	 */
    public Maquines() {
    	this.setLayout(new BorderLayout());
    	this.canviarVista(TipusVistaMaquines.LLISTA);
    }

	/**
	 * Mètode encarregat de canviar de vista segons quina sigui solicitada.
	 * @param t Tipus de vista a la que es vol canviar.
	 */
	public void canviarVista(TipusVistaMaquines t){
		JPanel panel = null;
		switch(t){
			case LLISTA:
				panel = new LlistaMaquines(this);
				break;
			case CREACIO:
				panel = new CrearMaquina(this);
				break;
		}
		this.removeAll();
		this.add(panel, BorderLayout.CENTER);
		this.validate();
		this.update(getGraphics());
	}
}
