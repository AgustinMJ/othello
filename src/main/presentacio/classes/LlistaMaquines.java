package main.presentacio.classes;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * La classe LlistaMaquines és una vista encarregada de mostrar totes les màquines del sistema, amb la seva informació.
 *
 * @author Guillem García Gil
 */
public class LlistaMaquines extends JPanel {
	private JLabel infoLabel;
	private JButton returnButton, deleteButton;
	private JList list;
	private List<String> information;
	private Maquines pantallaMaquines;
	private CtrlPresentacioMaquines pm = new CtrlPresentacioMaquines();

	/**
	 * Constructora que crea la vista LlistaMaquines.
	 * @param pantallaMaquines Objecte de la classe Maquines
	 */
	public LlistaMaquines(Maquines pantallaMaquines) {
		this.pantallaMaquines = pantallaMaquines;
		this.setBackground(Color.decode("#d0f0c0"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 169, 167, 43, 0};
		gridBagLayout.rowHeights = new int[]{30, 16, 263, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel label1 = new JLabel("Llista de Maquines");
		GridBagConstraints gbc_label1 = new GridBagConstraints();
		gbc_label1.insets = new Insets(0, 0, 5, 5);
		gbc_label1.gridx = 1;
		gbc_label1.gridy = 1;
		add(label1, gbc_label1);

		JLabel label2 = new JLabel("Informació");
		GridBagConstraints gbc_label2 = new GridBagConstraints();
		gbc_label2.insets = new Insets(0, 0, 5, 5);
		gbc_label2.gridx = 2;
		gbc_label2.gridy = 1;
		add(label2, gbc_label2);

		list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 2;
		add(list, gbc_list);

		infoLabel = new JLabel("Selecciona una maquina");
		GridBagConstraints gbc_infoLabel = new GridBagConstraints();
		gbc_infoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_infoLabel.gridx = 2;
		gbc_infoLabel.gridy = 2;
		add(infoLabel, gbc_infoLabel);

		deleteButton = new JButton("Elimina");
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.insets = new Insets(0, 0, 0, 5);
		gbc_deleteButton.gridx = 1;
		gbc_deleteButton.gridy = 3;
		add(deleteButton, gbc_deleteButton);

		returnButton = new JButton("Crea");
		GridBagConstraints gbc_returnButton = new GridBagConstraints();
		gbc_returnButton.insets = new Insets(0, 0, 0, 5);
		gbc_returnButton.gridx = 2;
		gbc_returnButton.gridy = 3;
		add(returnButton, gbc_returnButton);

		inicialitzarComponents();
	}
	/**
	 * Inicialitza els paràmetres de la classe.
	 */
	private void inicialitzarComponents() {

		DefaultListModel<String> msmachines = new DefaultListModel<>();
		information = new ArrayList<>();
		List<Map<String, String>> machines = getMaquines();

		for (int i = 0; i < machines.size(); ++i) {
			String name = machines.get(i).get("nom");
			String descp = machines.get(i).get("algorisme");
			information.add(descp);
			msmachines.add(i, name);
		}


		list.setModel(msmachines);

        returnButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pantallaMaquines.canviarVista(TipusVistaMaquines.CREACIO);
                    }
                }
        );

        deleteButton.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String deletedMachine = list.getSelectedValue().toString();
						try {
							pm.eliminaMaquina(deletedMachine);
							DefaultListModel<String> newModel = new DefaultListModel<>();
							List<Map<String, String>> machines = getMaquines();

							for (int i = 0; i < machines.size(); ++i) {
								newModel.add(i, machines.get(i).get("nom"));
							}
							information.remove(list.getSelectedIndex());
							list.setModel(newModel);

						} catch (IOException ioException) {
							JOptionPane.showMessageDialog(new JDialog(), "Error a l'intentar eliminar una maquina", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
		);

		list.addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting()) {
							int i = list.getSelectedIndex();
							String selected = information.get(i);
							String[] info = selected.split(" ");
							String algorisme = "Algorisme: " + info[0];
							String depth = "Profunditat: " + info[1];
							String heur = "Heurística: " + info[2];
							Map<String, String> maquina = machines.get(i);
							try {
								Map<String, Integer> records = pm.getRecordsMaquina(maquina.get("nom"));
								infoLabel.setText("<html><body>"+algorisme+"<br>"+depth+"<br>"+heur+"<br>Descripcio: "+maquina.get("descripcio")+"<br>Peces girades: "+records.get("pecesGirades")+"<br>Partides guanyades (Blanc): "+records.get("partidesBlanquesGuanyades") +"<br>Partides guanyades (Negre): " +records.get("partidesNegresGuanyades")+"</body></html>");
							} catch (IOException ioException) {
								JOptionPane.showMessageDialog(new JDialog(), "Error carregant els records de la maquina", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}
				}
		);


	}

	/**
	 * Retorna el conjunt de màquines al sistema.
	 * @return El conjunt de totes les màquines del sistema.
	 */
	private List<Map<String, String>> getMaquines() {

		try {
			return pm.getMaquines();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Error llegint les maquines", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
