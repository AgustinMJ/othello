package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioMaquines;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * La classe CrearMaquina és la pantalla on es pot crear una màquina. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */
public class CrearMaquina extends JPanel {
	CtrlPresentacioMaquines ctrlPresentacioMaquines;
	Maquines maquines;
	JTextField nom;
	JTextArea descripcio;
	JSpinner spinBox;
	ButtonGroup algorismes;
	ButtonGroup heuristiques;
	String algorisme;
	String heuristica;

	/**
	 * Constructora que inicialitza la pantalla.
	 * @param maquines La pantalla contenidora de CrearMaquina.
	 */
	public CrearMaquina(Maquines maquines) {
		this.ctrlPresentacioMaquines = new CtrlPresentacioMaquines();
		this.maquines = maquines;
		this.setBackground(Color.decode("#d0f0c0"));
		this.inicialitzarCompoents();
	}

	/**
	 * Inicialitza els components de la pantalla.
	 */
	private void inicialitzarCompoents() {
		setLayout(new BorderLayout(0, 0));

		algorismes = new ButtonGroup();
		heuristiques = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel.add(panel_10, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("Crear Màquina");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(30f));
		panel_10.add(lblNewLabel);

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel.add(panel_11, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("Tornar");
		btnNewButton_1.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						maquines.canviarVista(TipusVistaMaquines.LLISTA);
					}
				}
		);
		panel_11.add(btnNewButton_1);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel.add(panel_12, BorderLayout.EAST);

		Component horizontalStrut = Box.createHorizontalStrut(70);
		panel_12.add(horizontalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);

		Box verticalBox = Box.createVerticalBox();
		panel.setOpaque(false);
		panel_1.add(verticalBox);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("Nom");
		panel_2.add(lblNewLabel_1);

		nom = new JTextField();
		panel_2.add(nom);
		nom.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		verticalBox.add(panel_3);

		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox.add(panel_4);

		JRadioButton minimax = new JRadioButton("Minimax");
		minimax.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						algorisme = "Minimax";
					}
				}
		);
		panel_4.add(minimax);
		algorismes.add(minimax);

		JRadioButton alfaBeta = new JRadioButton("AlfaBeta");
		alfaBeta.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						algorisme = "AlfaBeta";
					}
				}
		);
		panel_4.add(alfaBeta);
		algorismes.add(alfaBeta);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		verticalBox.add(panel_5);

		JLabel lblNewLabel_2 = new JLabel("Profunditat");
		panel_5.add(lblNewLabel_2);

		spinBox = new JSpinner();
		spinBox.setModel(new SpinnerNumberModel(1, 1, null, 1));
		panel_5.add(spinBox);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		verticalBox.add(panel_6);

		JRadioButton heuristicaTaulell = new JRadioButton("HeuristicaTaulell");
		heuristicaTaulell.setToolTipText("Heurística on cada posició del taulell té una puntuació. L'algorisme col·locarà la peça a la posició possible del taulell amb més puntuació.");
		heuristicaTaulell.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						heuristica = "HeuristicaTaulell";
					}
				}
		);
		panel_6.add(heuristicaTaulell);
		heuristiques.add(heuristicaTaulell);

		JRadioButton heuristicaNombrePeces = new JRadioButton("HeuristicaNombrePeces");
		heuristicaNombrePeces.setToolTipText("Heurística on es busca menjar el màxim nombre de peces.");
		heuristicaNombrePeces.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						heuristica = "HeuristicaNombrePeces";
					}
				}
		);
		panel_6.add(heuristicaNombrePeces);
		heuristiques.add(heuristicaNombrePeces);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		verticalBox.add(panel_7);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut_1);

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		verticalBox.add(panel_8);

		JLabel lblNewLabel_3 = new JLabel("Descripció");
		panel_8.add(lblNewLabel_3);

		descripcio = new JTextArea();
		descripcio.setColumns(20);
		descripcio.setRows(10);
		panel_8.add(descripcio);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		verticalBox.add(panel_9);

		JButton btnNewButton = new JButton("Crear Màquina");
		btnNewButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						creaMaquina();
					}
				}
		);
		panel_9.add(btnNewButton);
	}

	/**
	 * Demana al controlador de presentació de les màquines que es crei una màquina amb els paràmetres introduits per l'usuari.
	 */
	private void creaMaquina() {
		if(this.comprova() && this.comprovaNom()) {
			try {
				this.ctrlPresentacioMaquines.creaMaquina(nom.getText(), algorisme + " " + String.valueOf(spinBox.getValue()) + " " + heuristica, descripcio.getText());
				JOptionPane.showMessageDialog(new JDialog(), "La màquina ha sigut creada correctament","Màquina creada",
						JOptionPane.INFORMATION_MESSAGE);
				this.maquines.canviarVista(TipusVistaMaquines.LLISTA);
			} catch (MaquinaJaExisteix |UsuariJaExistex maquinaJaExisteix) {
				JOptionPane.showMessageDialog(new JDialog(), "Ja existeix un jugador amb aquest nom","Jugador ja existeix",
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(new JDialog(), "Error creant la màquina","Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Comprova que l'usuari ha introduit tots els camps.
	 * @return un booleà indicant si l'usuari ha introduit tots els camps.
	 */
	private boolean comprova() {
		if(!(nom.getText() != null && nom.getText().length() > 0 && descripcio.getText() != null && descripcio.getText().length() > 0 && algorisme != null && heuristica != null && spinBox.getValue() != null)) {
			JOptionPane.showMessageDialog(new JDialog(), "Tot els camps són requerits","Valors incorrectes",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Comprova que el nom introduit per l'usuari no conté espais.
	 */
	private boolean comprovaNom() {
		if(nom.getText().split(" ").length > 1 || nom.getText().startsWith(" ")) {
			JOptionPane.showMessageDialog(new JDialog(), "El nom de la màquina no pot contenir espais","Nom incorrecte",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
