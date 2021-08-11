package main.presentacio.classes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * La classe TaulaRanking és una taula que mostra un ranking del joc. Hereda de la classe JScrollPane.
 *
 * @author Agustín Millán Jiménez
 */
public class TaulaRanking extends JScrollPane {
	final String[] columnes = { "Posició", "Nom", "Tipus", "Partides Guanyades", "Partides Perdudes", "Partides Empatades", "Puntuació" };

	/**
	 * Constructora que inicialitza la taula.
	 * @param dades Dades que mostrarà la taula.
	 */
	public TaulaRanking(Object[][] dades) {
		super();

		//Creem la taula
		JTable taula = new JTable();

		//Fem que no sigui editable
		DefaultTableModel modelTaula = new DefaultTableModel(dades, columnes) {

			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		taula.setModel(modelTaula);
		taula.setDragEnabled(false);

		//Posem l'amplada de cada columna
		TableColumn columna = null;
		for (int i = 0; i < 7; i++) {
			columna = taula.getColumnModel().getColumn(i);
			if (i == 0 || i == 2) {
				columna.setPreferredWidth(60);
			} else {
				columna.setPreferredWidth(100);
			}
		}

		taula.getTableHeader().setBackground(Color.decode("#50c878"));
		taula.setShowGrid(true);
		taula.setGridColor(Color.BLACK);
		taula.setShowVerticalLines(false);
		taula.setRowHeight(30);
		this.setViewportView(taula);

		this.setBorder(createEmptyBorder());
	}
}
