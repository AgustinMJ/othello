package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioPartida;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * La classe HistorialPartides permet veure les partides acabades de l'usuari. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */
public class HistorialPartides extends JPanel {

	CtrlPresentacioPartida ctrlPresentacioPartida;


	JList llistaPartides;

	/**
	 * Creadora de la classe que inicialitza la pantalla.
	 * @param ctrlPresentacioPartida Controlador de presentació de partida.
	 */
	public HistorialPartides(CtrlPresentacioPartida ctrlPresentacioPartida) {
		this.ctrlPresentacioPartida = ctrlPresentacioPartida;
		this.inicialitzarComponents();
	}

	/**
	 * Incialitza els components del panell
	 */
	private void inicialitzarComponents() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0, 0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Historial de partides");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 5;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JList list = new JList(getPartides());
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 5;
		gbc_list.gridy = 1;
		add(list, gbc_list);


		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Obté les partides de l'usuari del controlador de presentació de partida.
	 * @return Array de strings amb el id i els noms del jugador blanc i negre per cada partida.
	 */
	private String [] getPartides() {
		String[] partides = new String[0];
		try {
			ArrayList<Map<String, String>> partidesArray = ctrlPresentacioPartida.getPartidesUsuari();
			partides = new String [partidesArray.size()];
			for (int i = 0; i < partidesArray.size(); ++i) {
				Map<String ,String> mapPartida = partidesArray.get(i);
				String partida = mapPartida.get("id") + " " + mapPartida.get("jugadorBlanc") + " " +  mapPartida.get("jugadorNegre");
				partides[i] = partida;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Hi ha hagut un error llegint les partides finalitzades de l'usuari", "Error en l'accés a les partides",
					JOptionPane.ERROR_MESSAGE);
		}

		return partides;
	}
}
