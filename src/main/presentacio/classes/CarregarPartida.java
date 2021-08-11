package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioPartida;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 * La classe CarregarPartida és un JPanel que permet escollir una partida no acabada de l'usuari i carregar-la de nou. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */
public class CarregarPartida extends JPanel {
	CtrlPresentacioPartida ctrlPresentacioPartida;
	CtrlPresentacio ctrlPresentacio;

	int id;
	String nomJugadorBlanc;
	String nomJugadorNegre;
	String taulellPartida;
	String modeDeJoc;

	JList llistaPartides;
	JButton iniciaPartida = new JButton("Inicia");

	/**
	 * Constructora que inicialitza la pantalla.
	 * @param ctrlPresentacio Controlador de presentació.
	 * @param ctrlPresentacioPartida Controlado de presentació de partida.
	 */
	public CarregarPartida(CtrlPresentacio ctrlPresentacio, CtrlPresentacioPartida ctrlPresentacioPartida) {
		this.ctrlPresentacio = ctrlPresentacio;
		this.ctrlPresentacioPartida = ctrlPresentacioPartida;
		llistaPartides = new JList(getPartides());
		this.inicialitzarComponents();
	}

	/**
	 * Incialitza els components del panell
	 */
	private void inicialitzarComponents() {

		iniciaPartida.addActionListener(
				//Quan és clickat crida a change panel per canviar de pantalla
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (llistaPartides.getSelectedValue() == null) {
							JOptionPane.showMessageDialog(new JDialog(), "No has escollit cap partida vàlida", "Escolleix una partida",
									JOptionPane.ERROR_MESSAGE);
						}
						else {
							String partida = llistaPartides.getSelectedValue().toString();
							String[] partidaVec;
							partidaVec = partida.split(" ");

								Map<String, String> partidaSeleccionada = getPartida(partidaVec[0]);
							try {
								ctrlPresentacioPartida.setPartida(partidaSeleccionada.get("id"), partidaSeleccionada.get("jugadorBlanc"),
										partidaSeleccionada.get("jugadorNegre"), partidaSeleccionada.get("tipusBlanc"),
										partidaSeleccionada.get("tipusNegre"), partidaSeleccionada.get("taulell"),
										partidaSeleccionada.get("modeDeJoc"), partidaSeleccionada.get("torn"));
							} catch (IOException e) {
								JOptionPane.showMessageDialog(new JDialog(), "Hi ha hagut un error llegint els fitxers de l'usuari", "Error lectura fitxers",
										JOptionPane.ERROR_MESSAGE);
							}
							ctrlPresentacio.changePanel(TipusVistaPrincipal.VISTA_PARTIDA);


						}


					}
				});
		llistaPartides = new JList(getPartides());

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Carregar partida");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 7;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		JList list = llistaPartides;
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 7;
		gbc_list.gridy = 2;
		add(list, gbc_list);

		JButton btnNewButton = iniciaPartida;
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 9;
		gbc_btnNewButton.gridy = 7;
		add(btnNewButton, gbc_btnNewButton);


		this.setBackground(Color.decode("#d0f0c0"));
	}


	/**
	 * Demana al control de presentació de partida les partides no finalitzades de l'usuari
	 * @return Un array de strings amb el id, el nom del jugador blanc i el nom del jugador negre  de totes les partides no finalitzades de l'usuari.
	 */
	private String[] getPartides() {
		String[] partides = new String[0];
		try {
			ArrayList<Map<String, String>> partidesArray = ctrlPresentacioPartida.getPartidesNoFinalitzadesUsuari();
			partides = new String [partidesArray.size()];
			for (int i = 0; i < partidesArray.size(); ++i) {
				Map<String ,String> mapPartida = partidesArray.get(i);
				String partida = mapPartida.get("id") + " " + mapPartida.get("jugadorBlanc") + " " +  mapPartida.get("jugadorNegre");
				partides[i] = partida;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Hi ha hagut un error llegint les partides de l'usuari", "Error en l'accés a les partides",
					JOptionPane.ERROR_MESSAGE);
		}

		return partides;
	}

	/**
	 * Obté la partida a partir de l'id donat a través del controlador de presentació de partida
	 * @param id Identificador de la partida.
	 * @return Mapa amb tots els paràmetres de la partida amb l'identificador donat.
	 */
	private Map<String, String> getPartida(String id) {


		try {
			ArrayList<Map<String, String>> partidesArray = ctrlPresentacioPartida.getPartidesNoFinalitzadesUsuari();
			for (int i = 0; i < partidesArray.size(); ++i) {
				Map<String ,String> mapPartida = partidesArray.get(i);
				if (mapPartida.get("id").equals(id)) {

					return mapPartida;
				}

			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Hi ha hagut un error llegint la partida", "Error en l'accés a la partida",
					JOptionPane.ERROR_MESSAGE);
		}

		return null;
	}
}
