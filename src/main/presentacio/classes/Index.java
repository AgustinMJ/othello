package main.presentacio.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe Index és l'Índex de l'aplicació. Hereda de la classe JPanel.
 *
 * @author María Hernández Baeza
 */
public class Index extends JPanel {
	PantallaPrincipal pantallaPrincipal;

	/**
	 * Constructora que inicialitza l'Índex.
	 * @param pantallaPrincipal La pantalla contenidora de Índex.
	 */
	public Index(PantallaPrincipal pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		this.inicialitzarComponents();
		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Inicialitza els components de la pantalla.
	 */
	private void inicialitzarComponents() {
		setLayout(new BorderLayout(0, 0));

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(false);
		add(verticalBox);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		verticalBox.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		verticalBox.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel.add(panel_4, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("OTHELLO MASTERS");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(50f));
		panel_4.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		verticalBox.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_1.add(panel_5, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("Consultar regles");
		btnNewButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						pantallaPrincipal.canviarVista(TipusPantallaPrincipal.REGLES);
					}
				});
		panel_5.add(btnNewButton);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_2.add(panel_6, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("Tancar sessió");
		btnNewButton_1.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						pantallaPrincipal.tancaSessio();
					}
				});
		panel_6.add(btnNewButton_1);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_2.add(panel_7, BorderLayout.EAST);

		JButton btnNewButton_2 = new JButton("Sortir");
		btnNewButton_2.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						pantallaPrincipal.sortir();
					}
				});
		panel_7.add(btnNewButton_2);
	}
}
