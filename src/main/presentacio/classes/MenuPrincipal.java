package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe MenuConsultaUsuari és un menú que mostra les diferents opcions que es poden fer en el nostre joc. Hereda de la classe JMenuBar.
 *
 * @author Agustín Millán Jiménez
 */
public class MenuPrincipal extends JMenuBar {
	CtrlPresentacio ctrlPresentacio;

	private JMenu menuUsuari = new JMenu("Gestió d'usuari");
	private JMenuItem menuitemConsultarUsuari = new JMenuItem("Consultar usuari");
	private JMenuItem menuitemModificarUsuari = new JMenuItem("Modificar usuari");

	private JMenu menuPartida = new JMenu("Gestió de partida");
	private JMenuItem menuitemCrearPartida = new JMenuItem("Crear partida");
	private JMenuItem menuitemCarregarPartida = new JMenuItem("Carregar partida");
	private JMenuItem menuitemHistorialPartides = new JMenuItem("Historial de partides");

	private JMenuItem menuRanking = new JMenuItem("Ranking");
	private JMenuItem menuMaquines = new JMenuItem("Màquines");
	private JMenuItem menuPrincipal = new JMenuItem("Pantalla principal");

	/**
	 * Constructora que inicialitza el menú.
	 * @param ctrlPresentacio El controlador de presentació.
	 */
	public MenuPrincipal(CtrlPresentacio ctrlPresentacio) {
		this.ctrlPresentacio = ctrlPresentacio;
		this.inicialitzarComponents();
	}

	/**
	 * Pinta el component. Es fa override per poder donar color al menú.
	 * @param g Els gràfics.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	/**
	 * Inicialitza els components del menú.
	 */
	private void inicialitzarComponents() {
		//Defineix el menu de ranking
		menuitemConsultarUsuari.addActionListener(
				//Quan és clickat crida a change panel per canviar de pantalla
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.CONSULTAR_USUARI);
					}
				});

		menuitemModificarUsuari.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.MODIFICAR_USUARI);
					}
				});

		menuitemCrearPartida.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.CREAR_PARTIDA);
					}
				});

		menuitemCarregarPartida.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.CARREGAR_PARTIDA);
					}
				});
		menuitemHistorialPartides.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.HISTORIAL_PARTIDES);
					}
				});
		menuRanking.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.RANKING);
					}
				});
		menuMaquines.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.MAQUINES);
					}
				});
		menuPrincipal.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						ctrlPresentacio.changePanel(TipusVistaPrincipal.PANTALLA_PRINCIPAL);
					}
				});

		this.setBackground(new Color(102, 205, 170));
		this.setForeground(new Color(105, 105, 105));

		//Assigna cada item del menu a cada menu
		menuUsuari.add(menuitemConsultarUsuari);
		menuUsuari.add(menuitemModificarUsuari);
		menuUsuari.setOpaque(false);
		menuUsuari.setBackground(Color.yellow);
		menuPartida.add(menuitemCrearPartida);
		menuPartida.add(menuitemCarregarPartida);
		menuPartida.add(menuitemHistorialPartides);
		menuPartida.setOpaque(false);

		this.add(Box.createHorizontalGlue());
		this.add(menuUsuari);
		this.add(Box.createHorizontalGlue());
		this.add(menuMaquines);
		this.add(Box.createHorizontalGlue());
		this.add(menuPartida);
		this.add(Box.createHorizontalGlue());
		this.add(menuRanking);
		this.add(Box.createHorizontalGlue());
		this.add(menuPrincipal);
		this.add(Box.createHorizontalGlue());

		this.setOpaque(true);
	}

}
