package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioUsuari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * La classe ModificarUsuari és la pantalla on es pot modificar l'usuari autenticat. Hereda de la classe JPanel.
 *
 * @author Agustín Millán Jiménez
 */
public class ModificarUsuari extends JPanel {
	JPasswordField pwd;
	JPasswordField rePwd;
	CtrlPresentacioUsuari ctrlPresentacioUsuari;
	CtrlPresentacio ctrlPresentacio;

	/**
	 * Constructora que inicialitza la pantalla.
	 * @param ctrlPresentacio El controlador de presentació
	 * @param ctrlPresentacioUsuari El controlador de presentació dels usuaris.
	 */
	public ModificarUsuari(CtrlPresentacio ctrlPresentacio, CtrlPresentacioUsuari ctrlPresentacioUsuari) {
		this.ctrlPresentacio = ctrlPresentacio;
		this.ctrlPresentacioUsuari = ctrlPresentacioUsuari;
		this.setBackground(Color.decode("#d0f0c0"));
		this.inicialitzarComponents();
	}

	/**
	 * Inicialitza els components de la pantalla.
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

		JLabel lblNewLabel = new JLabel("Modifica l'usuari");
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(30f));
		panel_3.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1, BorderLayout.CENTER);

		Box verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setOpaque(false);
		panel_1.add(verticalBox_2);

		Box verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setOpaque(false);
		verticalBox_2.add(verticalBox_3);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		verticalBox_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		Box verticalBox_4 = Box.createVerticalBox();
		verticalBox_4.setOpaque(false);
		panel_6.add(verticalBox_4, BorderLayout.CENTER);

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		verticalBox_4.add(panel_7);

		JLabel lblNewLabel_2 = new JLabel("Contrasenya");
		panel_7.add(lblNewLabel_2);

		Component horizontalStrut = Box.createHorizontalStrut(51);
		panel_7.add(horizontalStrut);

		pwd = new JPasswordField();
		panel_7.add(pwd);
		pwd.setColumns(10);

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		verticalBox_4.add(panel_8);

		JLabel lblNewLabel_3 = new JLabel("Repeteix contrasenya");
		panel_8.add(lblNewLabel_3);

		rePwd = new JPasswordField();
		panel_8.add(rePwd);
		rePwd.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_6.add(panel_10, BorderLayout.NORTH);

		JLabel lblNewLabel_5 = new JLabel("Canvia la contrasenya");
		lblNewLabel_5.setFont(lblNewLabel.getFont().deriveFont(20f));
		panel_10.add(lblNewLabel_5);

		JPanel panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_6.add(panel_12, BorderLayout.SOUTH);

		JButton btnNewButton_2 = new JButton("Canvia contrasenya");
		btnNewButton_2.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						canviaContrasenya();
					}
				});
		panel_12.add(btnNewButton_2);

		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		verticalBox_3.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_4.add(panel_5, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Eliminar el compte");
		lblNewLabel_1.setFont(lblNewLabel.getFont().deriveFont(20f));
		panel_5.add(lblNewLabel_1);

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_4.add(panel_9, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Eliminar compte");
		btnNewButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						int op = JOptionPane.showConfirmDialog(new JDialog(), "Segur que vols borrar el teu compte?\n No podras recuperar les teves dades!","Eliminar compte",
								JOptionPane.YES_NO_OPTION);
						if(op == 0) eliminaUsuari();
					}
				});
		panel_9.add(btnNewButton);
	}

	/**
	 * Comporva que la contrasenya no es buida i coincideix amb el camp de repetir la contrasenya.
	 * @return booleà indicant si la contrasenya es correcte o no.
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
	 * Demana al controlador de presntació dels usuaris que canvii la contrasenya de l'usuari autenticat
	 */
	public void canviaContrasenya() {
		String contrasenya = "";
		for(char c : pwd.getPassword()) contrasenya += c;
		if(this.comprovaContrasenya()) {
			this.ctrlPresentacioUsuari.canviarContrasenya(contrasenya);
			JOptionPane.showMessageDialog(new JDialog(), "La contrasenya s'ha canviat correctament","Canvi de contrasenya",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Demana al controlador de presntació dels usuaris que elimini usuari autenticat
	 */
	public void eliminaUsuari() {
		try {
			this.ctrlPresentacioUsuari.eliminarUsuari();
			this.ctrlPresentacio.changePanel(TipusVistaPrincipal.AUTENTICACIO);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Error eliminant l'usuari","Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
