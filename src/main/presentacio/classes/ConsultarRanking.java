package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioRanking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * La classe ConsultarRanking és la pantalla de consultar els rankings del nostre joc. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */
public class ConsultarRanking extends JPanel {
	CtrlPresentacioRanking ctrlPresentacioRanking;
	final MenuConsultarRanking menu = new MenuConsultarRanking(this);
	TaulaRanking taulaHoritzontal;
	TaulaRanking taulaVertical;
	TaulaRanking taulaDiagonal;
	TaulaRanking taulaClassica;
	TaulaRanking taulaTotal;

	/**
	 * Constructora que inicialitza la pantalla.
	 * @param ctrlPresentacioRanking Controlador de presentació del ranking
	 */
	public ConsultarRanking(CtrlPresentacioRanking ctrlPresentacioRanking) {
		super();
		this.ctrlPresentacioRanking = ctrlPresentacioRanking;
		this.inicialitzarRankings();
		this.inicialitzarComponents();
	}

	/**
	 * Canvia el ranking que es mostra a la pantalla.
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
		this.remove(1);
		this.add(taula, BorderLayout.CENTER);
		this.validate();
		update(getGraphics());
	}

	/**
	 * Inicialitza els components de la pantalla.
	 */
	private void inicialitzarComponents() {
		setLayout(new BorderLayout(0, 0));
		this.add(menu, BorderLayout.NORTH);
		this.add(taulaHoritzontal, BorderLayout.CENTER);

		this.setBorder(new EmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.decode("#004225"));
	}

	/**
	 * Inicialitza els rankings.
	 */
	private void inicialitzarRankings() {
		try {
			Map<String, Object[][]> rankings = this.ctrlPresentacioRanking.getRanking();
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
