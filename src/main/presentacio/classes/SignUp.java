package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioAutenticacio;
import main.utils.MaquinaJaExisteix;
import main.utils.UsuariJaExistex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * La classe SignUp és la pantalla de crear un compte del joc. Hereda de la classe JPanel.
 *
 * @author María Hernández Baeza
 */
public class SignUp extends JPanel {
	JTextField nom;
	JPasswordField pwd;
	JPasswordField rePwd;
	VistaAutenticacio vistaAutenticacio;
	CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;
	/**
	 * Constructora que inicialitza el SignUp.
	 * @param vistaAutenticacio Vista del SignUp
	 * @param ctrlPresentacioAutenticacio
	 */
	public SignUp(VistaAutenticacio vistaAutenticacio, CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio) {
		this.inicialitzarComponents();
		this.setBackground(Color.decode("#d0f0c0"));
		this.vistaAutenticacio = vistaAutenticacio;
		this.ctrlPresentacioAutenticacio = ctrlPresentacioAutenticacio;
	}
	/**
	 * Inicalitza els components de la aplicació
	 */
	private void inicialitzarComponents(){
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

		JLabel lblBenvingut = new JLabel("Crea un compte");
		lblBenvingut.setFont(lblBenvingut.getFont().deriveFont(25f));
		panel_8.add(lblBenvingut);

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_5.add(panel_10, BorderLayout.WEST);

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_5.add(panel_11, BorderLayout.EAST);

		Box verticalBox_2 = Box.createVerticalBox();
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

		Component horizontalStrut_1 = Box.createHorizontalStrut(59);
		panel_3.add(horizontalStrut_1);

		nom = new JTextField();
		panel_3.add(nom);
		nom.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox_1.add(panel_4);

		JLabel lblNewLabel_4 = new JLabel("Contrasenya");
		panel_4.add(lblNewLabel_4);

		Component horizontalStrut = Box.createHorizontalStrut(66);
		panel_4.add(horizontalStrut);

		pwd = new JPasswordField();
		panel_4.add(pwd);
		pwd.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		verticalBox.add(panel_6);

		JLabel lblNewLabel_5 = new JLabel("Repeteix la contrasenya");
		panel_6.add(lblNewLabel_5);

		rePwd = new JPasswordField();
		panel_6.add(rePwd);
		rePwd.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		verticalBox.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_2.add(panel_7, BorderLayout.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Ja tens un compte?");
		panel_7.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Autentica't");
		lblNewLabel_2.setForeground(Color.decode("#004225"));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				vistaAutenticacio.canviarVista(TipusVistaAutenticacio.LOGIN);
			}

		});
		panel_7.add(lblNewLabel_2);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_2.add(panel_9, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("SIGN UP");
		btnNewButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						creaUsuari();
					}
				});
		panel_9.add(btnNewButton);
	}

	/**
	 * Crea l'usuari amb els camps introduits per l'usuari
	 */
	private void creaUsuari() {
		if(this.comprovaContrasenya() && this.comprovaNom() && this.comprova()) {
			String contrasenya = "";
			for(char c : pwd.getPassword()) contrasenya += c;
			try {
				this.ctrlPresentacioAutenticacio.creaUsuari(nom.getText(), contrasenya);
				this.ctrlPresentacioAutenticacio.autenticaUsuari(nom.getText(), contrasenya);
				this.vistaAutenticacio.mostraPantallaPrincipal();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(new JDialog(), "Error intern al crear l'usuari","Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (UsuariJaExistex | MaquinaJaExisteix je) {
				JOptionPane.showMessageDialog(new JDialog(), "Ja existeix un jugador amb aquest nom","L'usuari ja existeix",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Comprova que la contrasneya i la comprovació de la contrasenya siguin correctes i no estiguin buides.
	 * @return retorna un bool fent aquesta comprovació
	 */
	private boolean comprovaContrasenya() {
		String contrasenya = "";
		for(char c : pwd.getPassword()) contrasenya += c;
		String contrasenya2 = "";
		for(char c : rePwd.getPassword()) contrasenya2 += c;
		if (pwd.getPassword().length < 1) {
			JOptionPane.showMessageDialog(new JDialog(), "La contrasenya no pot ser buida","Contrassenya incorrecte",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!contrasenya.equals(contrasenya2)) {
			JOptionPane.showMessageDialog(new JDialog(), "Les contrasenyes no coincideixen","Contrassenya incorrecte",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Fa comprovacions del usuari i la contrasenya, mira que l'usuari i la contrasenya no siguin null i que les contrasenyes concorden.
	 * @return retorna un bool que comprova si les credencials són correctes.
	 */
	private boolean comprova() {
		if(!(nom.getText() != null && nom.getText().length() > 0 && pwd.getPassword() != null & pwd.getPassword().length > 0)) {
			JOptionPane.showMessageDialog(new JDialog(), "Tot els camps són requerits","Valors incorrectes",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Comprova que el nom indicat és el correcte.
	 * @return retorna un bool que indica true o false segons s'hagi vist que el nom és el correcte o no.
	 */
	private boolean comprovaNom() {
		if(nom.getText().split(" ").length > 1) {
			JOptionPane.showMessageDialog(new JDialog(), "El nom de l'usuari no pot contenir espais","Nom incorrecte",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}