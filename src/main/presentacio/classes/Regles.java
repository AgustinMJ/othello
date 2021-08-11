package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioRegles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
/**
 * La classe Regles és l'encarregada de gestionar i mostrar les regles del joc. Hereda de la classe JPanel.
 *
 * @author Maria Hernández Baeza
 */

public class Regles extends JPanel {
	PantallaPrincipal pantallaPrincipal;
	ArrayList<JLabel> regles = new ArrayList<JLabel>();

	/**
	 * Constructora que inicialitza la classe Regles.
	 * @param pantallaPrincipal la Pantalla Principal és la pantalla on podrem veure les regles.
	 */
	public Regles(PantallaPrincipal pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		this.getRegles();
		this.setBackground(Color.decode("#d0f0c0"));
		this.inicialitzarComponents();
	}
	/**
	 * Inicialitza els components de la classe Regles
	 */
	private void inicialitzarComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Regles");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(20f));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);

		Component horizontalStrut = Box.createHorizontalStrut(50);
		panel_3.add(horizontalStrut);

		JButton btnNewButton = new JButton("Torna");
		btnNewButton.addActionListener(
				//Quan és clickat crida a change panel per canviar de pantalla
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						pantallaPrincipal.canviarVista(TipusPantallaPrincipal.INDEX);
					}
				});
		panel_3.add(btnNewButton);

		panel_1.add(panel_3, BorderLayout.SOUTH);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(false);
		add(verticalBox, BorderLayout.CENTER);

		for(JLabel r : this.regles) {
			verticalBox.add(r);
		}

	}
	/**
	 * Obten el fitxer amb les regles del joc.
	 */
	private void getRegles() {
		List<String> llistaRegles = null;
		try {
			llistaRegles = new CtrlPresentacioRegles().getRegles();
			for(String r : llistaRegles) {
				this.regles.add(new JLabel(r));
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JDialog(), "No s'ha trobat el fitxer de regles", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
