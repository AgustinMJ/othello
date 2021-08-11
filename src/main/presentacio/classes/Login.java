package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioAutenticacio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

/**
 * La classe Login és la pantalla d'autenticació del joc. Hereda de la classe JPanel.
 *
 * @author María Hernández Baeza
 */
public class Login extends JPanel {
	JTextField nom;
	JPasswordField pwd;
	JButton botoSignUp;
	JButton botoLogin;
	VistaAutenticacio vistaAutenticacio;
	CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;

	/**
	 * Constructora que inicialitza el LogIn.
	 * @param vistaAutenticacio
	 * @param ctrlPresentacioAutenticacio
	 */

	public Login(VistaAutenticacio vistaAutenticacio, CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio) {
		this.inicialitzarComponents();
		this.vistaAutenticacio = vistaAutenticacio;
		this.ctrlPresentacioAutenticacio = ctrlPresentacioAutenticacio;
	}

	/**
	 * Inicialitza els components del LogIn
	 */
	private void inicialitzarComponents() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setOpaque(false);
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(verticalBox);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		verticalBox.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_5.add(panel_8, BorderLayout.SOUTH);

		JLabel lblBenvingut = new JLabel("Benvingut");
		lblBenvingut.setFont(lblBenvingut.getFont().deriveFont(25f));
		panel_8.add(lblBenvingut);

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_5.add(panel_10, BorderLayout.WEST);

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_5.add(panel_11, BorderLayout.EAST);

		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setOpaque(false);
		panel_5.add(verticalBox_2, BorderLayout.CENTER);

		JPanel panel_13 = new JPanel();
		panel_13.setOpaque(false);
		verticalBox_2.add(panel_13);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		verticalBox_2.add(panel_12);

		JLabel lblNewLabel = new JLabel("OTHELLO MASTERS");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(50f));
		panel_12.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		verticalBox.add(panel);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setOpaque(false);
		verticalBox.add(verticalBox_1);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		verticalBox_1.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("Nom d'usuari");
		lblNewLabel_3.setToolTipText("Contrasenya");
		panel_3.add(lblNewLabel_3);

		nom = new JTextField();
		panel_3.add(nom);
		nom.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox_1.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("Contrasenya");
		panel_4.add(lblNewLabel_4);

		Component horizontalStrut = Box.createHorizontalStrut(3);
		panel_4.add(horizontalStrut);

		pwd = new JPasswordField();
		panel_4.add(pwd);
		pwd.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		verticalBox.add(panel_6);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_2.add(panel_7, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("No tens un compte?");
		panel_7.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Registra't");
		lblNewLabel_2.setForeground(Color.decode("#004225"));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vistaAutenticacio.canviarVista(TipusVistaAutenticacio.SIGNUP);
			}
		});
		panel_7.add(lblNewLabel_2);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_2.add(panel_9, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("LOG IN");
		btnNewButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						autenticaUsuari();
					}
				});
		panel_9.add(btnNewButton);

		this.setBackground(Color.decode("#d0f0c0"));
	}

	/**
	 * Comprova que les credencials del usuari siguin correctes.
	 */
	private void autenticaUsuari(){
		String contrasenya = "";
		for(char c : pwd.getPassword()) contrasenya += c;
		if (ctrlPresentacioAutenticacio.autenticaUsuari(nom.getText(), contrasenya)) {
			vistaAutenticacio.mostraPantallaPrincipal();
		} else {
			JOptionPane.showMessageDialog(new JDialog(), "Els credencials no son correctes", "Usuari incorrecte",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}