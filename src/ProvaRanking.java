import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import java.awt.Panel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ProvaRanking extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ProvaRanking() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Crear Màquina");
		panel_10.add(lblNewLabel);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("Tornar");
		panel_11.add(btnNewButton_1);
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12, BorderLayout.EAST);
		
		Component horizontalStrut = Box.createHorizontalStrut(70);
		panel_12.add(horizontalStrut);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		panel_1.add(verticalBox);
		
		JPanel panel_2 = new JPanel();
		verticalBox.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		verticalBox.add(panel_3);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		
		JPanel panel_4 = new JPanel();
		verticalBox.add(panel_4);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Minimax");
		panel_4.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("AlfaBeta");
		panel_4.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("MonteCarlo");
		panel_4.add(rdbtnNewRadioButton_2);
		
		JPanel panel_5 = new JPanel();
		verticalBox.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Profunditat/Iteracions");
		panel_5.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		panel_5.add(spinner);
		
		JPanel panel_6 = new JPanel();
		verticalBox.add(panel_6);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("HeuristicaTaulell");
		rdbtnNewRadioButton_3.setToolTipText("Heurística on cada posició del taulell té una puntuació. L'algorisme col·locarà la peça a la posició possible del taulell amb més puntuació.");
		panel_6.add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("HeuristicaNombrePeces");
		rdbtnNewRadioButton_4.setToolTipText("Heurística on es busca menjar el màxim nombre de peces.");
		panel_6.add(rdbtnNewRadioButton_4);
		
		JPanel panel_7 = new JPanel();
		verticalBox.add(panel_7);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut_1);
		
		JPanel panel_8 = new JPanel();
		verticalBox.add(panel_8);
		
		JLabel lblNewLabel_3 = new JLabel("Descripció");
		panel_8.add(lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(10);
		panel_8.add(textArea);
		
		JPanel panel_9 = new JPanel();
		verticalBox.add(panel_9);
		
		JButton btnNewButton = new JButton("Crear Màquina");
		panel_9.add(btnNewButton);
	}
}
