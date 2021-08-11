package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioRanking;
import main.presentacio.controladors.CtrlPresentacioUsuari;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * La classe ConsultaUsuari permet mirar tots els elements associats amb l'usuari. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */

public class ConsultarUsuari extends JPanel {


	final MenuConsultaUsuari menu = new MenuConsultaUsuari(this);
	CtrlPresentacioUsuari ctrlPresentacioUsuari;
	String nomUsuari;
	Map<String, Integer> records;
	JPanel panellRanking = new JPanel();
	TaulaRanking taulaHoritzontal;
	TaulaRanking taulaVertical;
	TaulaRanking taulaDiagonal;
	TaulaRanking taulaClassica;
	TaulaRanking taulaTotal;

	/**
	 * Constructora que inicialitza la classe
	 * @param ctrlPresentacioUsuari
	 */
	public ConsultarUsuari(CtrlPresentacioUsuari ctrlPresentacioUsuari) {
		this.ctrlPresentacioUsuari = ctrlPresentacioUsuari;
		this.nomUsuari = ctrlPresentacioUsuari.getNomUsuari();
		try {
			this.records = this.ctrlPresentacioUsuari.getRecordsUsuari(nomUsuari);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Error obtenint els records de l'usuari", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		this.inicialitzarRanking();
		this.setBackground(Color.decode("#d0f0c0"));
		this.inicialitzarComponents();
	}

	/**
	 * Inicialitza els components de la classe
	 */

	private void inicialitzarComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(false);
		panel.add(verticalBox);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		verticalBox.add(panel_3);

		JLabel lblNewLabel = new JLabel(nomUsuari);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(30f));
		panel_3.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setOpaque(false);
		panel_1.add(verticalBox_1);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox_1.add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("Peces girades:");
		panel_4.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(String.valueOf(records.get("pecesGirades")));
		panel_4.add(lblNewLabel_2);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		verticalBox_1.add(panel_5);

		JLabel lblNewLabel_3 = new JLabel("Partides blanques guanyades:");
		panel_5.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel(String.valueOf(records.get("partidesBlanquesGuanyades")));
		panel_5.add(lblNewLabel_4);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		verticalBox_1.add(panel_6);

		JLabel lblNewLabel_5 = new JLabel("Partides negres guanyades:");
		panel_6.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(String.valueOf(records.get("partidesNegresGuanyades")));
		panel_6.add(lblNewLabel_6);

		panellRanking.setLayout(new BorderLayout());
		panellRanking.add(menu, BorderLayout.NORTH);
		panellRanking.add(taulaHoritzontal, BorderLayout.CENTER);
		panellRanking.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setSize(20, 20);
		panel_7.add(panellRanking);
		verticalBox_1.add(panel_7);
	}

	/**
	 * Mostra el rànking de cada tipus de joc associat al usuari amb el que estem tractant.
	 * @param t Tipus de ranking a mostrar.
	 */

	public void mostraRanking(TipusRanking t) {
		JScrollPane taula = new JScrollPane();

		switch (t) {
			case HORITZONTAL:
				taula = taulaHoritzontal;
				break;
			case VERTICAL:
				taula = taulaVertical;
				break;
			case DIAGONAL:
				taula = taulaDiagonal;
				break;
			case CLASSIC:
				taula = taulaClassica;
				break;
			case TOTAL:
				taula = taulaTotal;
				break;
		}
		panellRanking.remove(1);
		panellRanking.add(taula, BorderLayout.CENTER);
		panellRanking.validate();
		update(getGraphics());
	}

	/**
	 * Inicialitza els rànkings del usuari per cada mode de joc.
	 */

	private void inicialitzarRanking() {
		try {
			Map<String, Object[][]> rankings = new CtrlPresentacioRanking().getRankingJugador(nomUsuari);
			this.taulaHoritzontal = new TaulaRanking(rankings.get("Horitzontal"));
			this.taulaVertical = new TaulaRanking(rankings.get("Vertical"));
			this.taulaDiagonal = new TaulaRanking(rankings.get("Diagonal"));
			this.taulaClassica = new TaulaRanking(rankings.get("Classic"));
			this.taulaTotal = new TaulaRanking(rankings.get("total"));
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Hi ha hagut un problema llegint els rankings", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
