package main.presentacio.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe MenuConsultaUsuari és un menú que mostra les diferents opcions per consultar els rankings de l'usuari. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */
public class MenuConsultaUsuari extends JPanel {
	ConsultarUsuari consultarUsuari;

	/**
	 * Constructora que inicialitza el menú.
	 * @param consultarUsuari La pantalla pare del menú.
	 */
	public MenuConsultaUsuari(ConsultarUsuari consultarUsuari) {
		this.consultarUsuari = consultarUsuari;
		this.inicialitzarComponents();
		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Inicialitza els components del menú.
	 */
	private void inicialitzarComponents() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JMenuItem menuHoritzontal = new JMenuItem("Horitzontal");
		JMenuItem menuVertical = new JMenuItem("Vertical");
		JMenuItem menuDiagonal = new JMenuItem("Diagonal");
		JMenuItem menuClassic = new JMenuItem("Clàssic");
		JMenuItem menuTotal = new JMenuItem("Total");

		menuHoritzontal.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						consultarUsuari.mostraRanking(TipusRanking.HORITZONTAL);
						menuHoritzontal.setForeground(Color.white);
						menuVertical.setForeground(Color.black);
						menuDiagonal.setForeground(Color.black);
						menuClassic.setForeground(Color.black);
						menuTotal.setForeground(Color.black);
					}
				});
		menuHoritzontal.setBackground(Color.decode("#d0f0c0"));
		this.add(menuHoritzontal);

		menuVertical.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						consultarUsuari.mostraRanking(TipusRanking.VERTICAL);
						menuHoritzontal.setForeground(Color.black);
						menuVertical.setForeground(Color.white);
						menuDiagonal.setForeground(Color.black);
						menuClassic.setForeground(Color.black);
						menuTotal.setForeground(Color.black);
					}
				});
		menuVertical.setBackground(Color.decode("#d0f0c0"));
		this.add(menuVertical);

		menuDiagonal.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						consultarUsuari.mostraRanking(TipusRanking.DIAGONAL);
						menuHoritzontal.setForeground(Color.black);
						menuVertical.setForeground(Color.black);
						menuDiagonal.setForeground(Color.white);
						menuClassic.setForeground(Color.black);
						menuTotal.setForeground(Color.black);
					}
				});
		menuDiagonal.setBackground(Color.decode("#d0f0c0"));
		this.add(menuDiagonal);

		menuClassic.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						consultarUsuari.mostraRanking(TipusRanking.CLASSIC);
						menuHoritzontal.setForeground(Color.black);
						menuVertical.setForeground(Color.black);
						menuDiagonal.setForeground(Color.black);
						menuClassic.setForeground(Color.white);
						menuTotal.setForeground(Color.black);
					}
				});
		menuClassic.setBackground(Color.decode("#d0f0c0"));
		this.add(menuClassic);

		menuTotal.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						consultarUsuari.mostraRanking(TipusRanking.TOTAL);
						menuHoritzontal.setForeground(Color.black);
						menuVertical.setForeground(Color.black);
						menuDiagonal.setForeground(Color.black);
						menuClassic.setForeground(Color.black);
						menuTotal.setForeground(Color.white);
					}
				});

		menuHoritzontal.setForeground(Color.white);
		menuTotal.setBackground(Color.decode("#d0f0c0"));
		menuTotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(menuTotal);
	}
}
