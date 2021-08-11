package main.presentacio.classes;


import main.presentacio.controladors.CtrlPresentacio;
import main.presentacio.controladors.CtrlPresentacioAutenticacio;
import main.presentacio.controladors.CtrlPresentacioPartida;
import main.utils.FilaEsTotal;
import main.utils.FilaNoTotal;
import main.utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * La classe VistaPartida és una vista de presentació encarregada de mostrar la partida entre els dos jugadors que l'inicien.
 *
 * @author Guillem García Gil
 */
public class VistaPartida extends JPanel {

    private MouseListener listener;
    private JDialog dialegAutenticacio;
    private JPanel contentPane;
    private JButton abandonButton, closeButton, restartButton, mainMenuButton;
    private JLabel nomJugadorBlanc, nomJugadorNegre, turnLabel;
    private JLabel c00, c01, c02, c03, c04, c05, c06, c07,
            c10, c11, c12, c13, c14, c15, c16, c17,
            c20, c21, c22, c23, c24, c25, c26, c27,
            c30, c31, c32, c33, c34, c35, c36, c37,
            c40, c41, c42, c43, c44, c45, c46, c47,
            c50, c51, c52, c53, c54, c55, c56, c57,
            c60, c61, c62, c63, c64, c65, c66, c67,
            c70, c71, c72, c73, c74, c75, c76, c77;
    private JLabel[] etiquetes;
    private final String pathBuida = "./dades/imatges/posicioBuida.png";
    private final String pathBlanca = "./dades/imatges/fitxaBlanca.png";
    private final String pathNegra = "./dades/imatges/fitxaNegra.png";
    private final String pathPossible = "./dades/imatges/PosicioPosible.png";
    private Integer ntorn;
    private String torn;
    private Map<String, String> partida;
    private String SnomJugadorBlanc, SnomJugadorNegre, StipusJugadorBlanc, StipusJugadorNegre;
    private char[][] matriu = new char[8][8];
    private boolean MaquinaVSMaquina;
    private final CtrlPresentacio cp;
    private final CtrlPresentacioPartida cpp;
    private boolean autenticat;
    private CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;
    private boolean tornMaquina, noMaquina, noUsuari, noAcabat;
    private JLabel timeLabel;
    private JOptionPane op;
    /**
     * Constructora que crea la vista VistaPartida.
     * @param ctrlPresentacio Controlador de presentació.
     * @param cpp Controlador de presentació de partida.
     * @param ctrlPresentacioAutenticacio Controlador de presentació d'autenticació.
     */
    public VistaPartida(CtrlPresentacio ctrlPresentacio, CtrlPresentacioPartida cpp, CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio) {
        this.cp = ctrlPresentacio;
        this.ctrlPresentacioAutenticacio = ctrlPresentacioAutenticacio;
        this.cpp = cpp;
        this.partida = cpp.getPartida();
        SnomJugadorBlanc = partida.get("jugadorBlanc");
        SnomJugadorNegre = partida.get("jugadorNegre");
        StipusJugadorBlanc = partida.get("tipusBlanc");
        StipusJugadorNegre = partida.get("tipusNegre");
        MaquinaVSMaquina = StipusJugadorBlanc.equals("Maquina") && StipusJugadorNegre.equals("Maquina");


        ///
        if (StipusJugadorBlanc.equals("Usuari") && StipusJugadorNegre.equals("Usuari")) {
            autenticat = false;
            autenticaJugadors();
            dialegAutenticacio.setVisible(true);
        } else {
            inicialitzarComponents();
            iniciaTorn();
        }

    }

    /**
     * Inicialitza els paràmetres de la classe.
     */
    public void inicialitzarComponents() {

        SpringLayout sl_contentPane = new SpringLayout();
        this.setLayout(sl_contentPane);

        torn = partida.get("torn");

        nomJugadorBlanc = new JLabel(SnomJugadorBlanc);
        sl_contentPane.putConstraint(SpringLayout.WEST, nomJugadorBlanc, 52, SpringLayout.WEST, this);
        sl_contentPane.putConstraint(SpringLayout.EAST, nomJugadorBlanc, 345, SpringLayout.WEST, this);
        nomJugadorBlanc.setFont(new Font(nomJugadorBlanc.getFont().getFontName(), Font.BOLD, 30));
        add(nomJugadorBlanc);

        nomJugadorNegre = new JLabel(SnomJugadorNegre);
        sl_contentPane.putConstraint(SpringLayout.EAST, nomJugadorNegre, -26, SpringLayout.EAST, this);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, nomJugadorBlanc, 0, SpringLayout.SOUTH, nomJugadorNegre);
        sl_contentPane.putConstraint(SpringLayout.NORTH, nomJugadorBlanc, 0, SpringLayout.NORTH, nomJugadorNegre);
        sl_contentPane.putConstraint(SpringLayout.NORTH, nomJugadorNegre, 10, SpringLayout.NORTH, this);
        nomJugadorNegre.setFont(new Font(nomJugadorNegre.getFont().getFontName(), Font.BOLD, 30));
        this.add(nomJugadorNegre);

        turnLabel = new JLabel("Torn: ");
        sl_contentPane.putConstraint(SpringLayout.WEST, nomJugadorNegre, 60, SpringLayout.EAST, turnLabel);
        sl_contentPane.putConstraint(SpringLayout.NORTH, turnLabel, 10, SpringLayout.NORTH, this);
        sl_contentPane.putConstraint(SpringLayout.WEST, turnLabel, 34, SpringLayout.EAST, nomJugadorBlanc);
        sl_contentPane.putConstraint(SpringLayout.EAST, turnLabel, -379, SpringLayout.EAST, this);
        turnLabel.setFont(new Font(turnLabel.getFont().getFontName(), Font.BOLD, 30));
        this.add(turnLabel);

        restartButton = new JButton("Reiniciar Partida");
        sl_contentPane.putConstraint(SpringLayout.WEST, restartButton, 10, SpringLayout.WEST, this);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, restartButton, -45, SpringLayout.SOUTH, this);
        sl_contentPane.putConstraint(SpringLayout.EAST, restartButton, -926, SpringLayout.EAST, this);
        this.add(restartButton);

        closeButton = new JButton("Tancar Partida");
        sl_contentPane.putConstraint(SpringLayout.SOUTH, closeButton, 0, SpringLayout.SOUTH, this);
        sl_contentPane.putConstraint(SpringLayout.EAST, closeButton, 0, SpringLayout.EAST, this);
        this.add(closeButton);

        c00 = new JLabel("");
        c00.setIcon(new ImageIcon(pathBuida));
        this.add(c00);

        c01 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c00, 0, SpringLayout.NORTH, c01);
        sl_contentPane.putConstraint(SpringLayout.NORTH, c01, 119, SpringLayout.NORTH, this);
        c01.setIcon(new ImageIcon(pathBuida));
        this.add(c01);

        c02 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c02, 119, SpringLayout.NORTH, this);
        sl_contentPane.putConstraint(SpringLayout.EAST, c01, -6, SpringLayout.WEST, c02);
        c02.setIcon(new ImageIcon(pathBuida));

        this.add(c02);

        c03 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c03, 73, SpringLayout.SOUTH, nomJugadorBlanc);
        sl_contentPane.putConstraint(SpringLayout.EAST, c02, -6, SpringLayout.WEST, c03);
        c03.setIcon(new ImageIcon(pathBuida));

        this.add(c03);

        c04 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c04, 73, SpringLayout.SOUTH, nomJugadorBlanc);
        sl_contentPane.putConstraint(SpringLayout.EAST, c03, -6, SpringLayout.WEST, c04);
        sl_contentPane.putConstraint(SpringLayout.EAST, c04, -553, SpringLayout.EAST, this);
        c04.setIcon(new ImageIcon(pathBuida));
        this.add(c04);

        c05 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c05, 119, SpringLayout.NORTH, this);
        sl_contentPane.putConstraint(SpringLayout.WEST, c05, 6, SpringLayout.EAST, c04);
        c05.setIcon(new ImageIcon(pathBuida));
        this.add(c05);

        c06 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c06, 119, SpringLayout.NORTH, this);
        sl_contentPane.putConstraint(SpringLayout.WEST, c06, 6, SpringLayout.EAST, c05);
        c06.setIcon(new ImageIcon(pathBuida));
        this.add(c06);

        c07 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c07, 119, SpringLayout.NORTH, this);
        sl_contentPane.putConstraint(SpringLayout.WEST, c07, 6, SpringLayout.EAST, c06);
        c07.setIcon(new ImageIcon(pathBuida));
        this.add(c07);

        c10 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.NORTH, c10, 6, SpringLayout.SOUTH, c00);
        sl_contentPane.putConstraint(SpringLayout.WEST, c00, 0, SpringLayout.WEST, c10);
        c10.setIcon(new ImageIcon(pathBuida));
        this.add(c10);

        c20 = new JLabel("");
        c20.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c20, 6, SpringLayout.SOUTH, c10);
        this.add(c20);

        c30 = new JLabel("");
        c30.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c30, 6, SpringLayout.SOUTH, c20);
        this.add(c30);

        c40 = new JLabel("");
        c40.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c40, 6, SpringLayout.SOUTH, c30);
        this.add(c40);

        c50 = new JLabel("");
        c50.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c50, 6, SpringLayout.SOUTH, c40);
        this.add(c50);

        c60 = new JLabel("");
        c60.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c60, 6, SpringLayout.SOUTH, c50);
        this.add(c60);

        c70 = new JLabel("");
        c70.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c70, 6, SpringLayout.SOUTH, c60);
        this.add(c70);

        c11 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c10, -6, SpringLayout.WEST, c11);
        c11.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c11, 6, SpringLayout.SOUTH, c01);
        sl_contentPane.putConstraint(SpringLayout.EAST, c11, 0, SpringLayout.EAST, c01);
        this.add(c11);

        c12 = new JLabel("");
        c12.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c12, 6, SpringLayout.SOUTH, c02);
        sl_contentPane.putConstraint(SpringLayout.WEST, c12, 0, SpringLayout.WEST, c02);
        this.add(c12);

        c13 = new JLabel("");
        c13.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c13, 6, SpringLayout.SOUTH, c03);
        sl_contentPane.putConstraint(SpringLayout.EAST, c13, 0, SpringLayout.EAST, c03);
        this.add(c13);

        c14 = new JLabel("");
        c14.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.WEST, c14, 0, SpringLayout.WEST, c04);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c14, 0, SpringLayout.SOUTH, c10);
        this.add(c14);

        c15 = new JLabel("");
        c15.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c15, 6, SpringLayout.SOUTH, c05);
        sl_contentPane.putConstraint(SpringLayout.WEST, c15, 0, SpringLayout.WEST, c05);
        this.add(c15);

        c16 = new JLabel("");
        c16.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c16, 6, SpringLayout.SOUTH, c06);
        sl_contentPane.putConstraint(SpringLayout.EAST, c16, 0, SpringLayout.EAST, c06);
        this.add(c16);

        c17 = new JLabel("");
        c17.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.WEST, c17, 0, SpringLayout.WEST, c07);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c17, 0, SpringLayout.SOUTH, c10);
        this.add(c17);

        c21 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c20, -6, SpringLayout.WEST, c21);
        c21.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c21, 0, SpringLayout.SOUTH, c20);
        sl_contentPane.putConstraint(SpringLayout.EAST, c21, 0, SpringLayout.EAST, c01);
        this.add(c21);

        c22 = new JLabel("");
        c22.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c22, 0, SpringLayout.NORTH, c20);
        sl_contentPane.putConstraint(SpringLayout.EAST, c22, 0, SpringLayout.EAST, c02);
        this.add(c22);

        c23 = new JLabel("");
        c23.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c23, 0, SpringLayout.NORTH, c20);
        sl_contentPane.putConstraint(SpringLayout.WEST, c23, 0, SpringLayout.WEST, c03);
        this.add(c23);

        c24 = new JLabel("");
        c24.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c24, 0, SpringLayout.SOUTH, c20);
        sl_contentPane.putConstraint(SpringLayout.EAST, c24, 0, SpringLayout.EAST, c04);
        this.add(c24);

        c25 = new JLabel("");
        c25.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c25, 0, SpringLayout.NORTH, c20);
        sl_contentPane.putConstraint(SpringLayout.EAST, c25, 0, SpringLayout.EAST, c05);
        this.add(c25);

        c26 = new JLabel("");
        c26.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c26, 0, SpringLayout.SOUTH, c20);
        sl_contentPane.putConstraint(SpringLayout.EAST, c26, 0, SpringLayout.EAST, c06);
        this.add(c26);

        c27 = new JLabel("");
        c27.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c27, 0, SpringLayout.NORTH, c20);
        sl_contentPane.putConstraint(SpringLayout.WEST, c27, 0, SpringLayout.WEST, c07);
        this.add(c27);

        c31 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c30, -6, SpringLayout.WEST, c31);
        c31.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c31, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.WEST, c31, 0, SpringLayout.WEST, c01);
        this.add(c31);

        c32 = new JLabel("");
        c32.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c32, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.EAST, c32, 0, SpringLayout.EAST, c02);
        this.add(c32);

        c33 = new JLabel("");
        c33.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c33, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.EAST, c33, 0, SpringLayout.EAST, c03);
        this.add(c33);

        c34 = new JLabel("");
        c34.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c34, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.WEST, c34, 0, SpringLayout.WEST, c04);
        this.add(c34);

        c35 = new JLabel("");
        c35.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c35, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.WEST, c35, 0, SpringLayout.WEST, c05);
        this.add(c35);

        c36 = new JLabel("");
        c36.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c36, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.EAST, c36, 0, SpringLayout.EAST, c06);
        this.add(c36);

        c37 = new JLabel("");
        c37.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c37, 0, SpringLayout.NORTH, c30);
        sl_contentPane.putConstraint(SpringLayout.EAST, c37, 0, SpringLayout.EAST, c07);
        this.add(c37);

        c41 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c40, -6, SpringLayout.WEST, c41);
        c41.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.WEST, c41, 0, SpringLayout.WEST, c01);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c41, 0, SpringLayout.SOUTH, c40);
        this.add(c41);

        c42 = new JLabel("");
        c42.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c42, 0, SpringLayout.NORTH, c40);
        sl_contentPane.putConstraint(SpringLayout.EAST, c42, 0, SpringLayout.EAST, c02);
        this.add(c42);

        c43 = new JLabel("");
        c43.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c43, 0, SpringLayout.NORTH, c40);
        sl_contentPane.putConstraint(SpringLayout.WEST, c43, 0, SpringLayout.WEST, c03);
        this.add(c43);

        c44 = new JLabel("");
        c44.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c44, 0, SpringLayout.NORTH, c40);
        sl_contentPane.putConstraint(SpringLayout.WEST, c44, 0, SpringLayout.WEST, c04);
        this.add(c44);

        c45 = new JLabel("");
        c45.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c45, 0, SpringLayout.NORTH, c40);
        sl_contentPane.putConstraint(SpringLayout.EAST, c45, 0, SpringLayout.EAST, c05);
        this.add(c45);

        c46 = new JLabel("");
        c46.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c46, 0, SpringLayout.SOUTH, c40);
        sl_contentPane.putConstraint(SpringLayout.EAST, c46, 0, SpringLayout.EAST, c06);
        this.add(c46);

        c47 = new JLabel("");
        c47.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c47, 0, SpringLayout.NORTH, c40);
        sl_contentPane.putConstraint(SpringLayout.EAST, c47, 0, SpringLayout.EAST, c07);
        this.add(c47);

        c51 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c50, -6, SpringLayout.WEST, c51);
        c51.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c51, 0, SpringLayout.NORTH, c50);
        sl_contentPane.putConstraint(SpringLayout.WEST, c51, 0, SpringLayout.WEST, c01);
        this.add(c51);

        c52 = new JLabel("");
        c52.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c52, 0, SpringLayout.NORTH, c50);
        sl_contentPane.putConstraint(SpringLayout.EAST, c52, 0, SpringLayout.EAST, c02);
        this.add(c52);

        c53 = new JLabel("");
        c53.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c53, 0, SpringLayout.NORTH, c50);
        sl_contentPane.putConstraint(SpringLayout.EAST, c53, 0, SpringLayout.EAST, c03);
        this.add(c53);

        c54 = new JLabel("");
        c54.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c54, 0, SpringLayout.NORTH, c50);
        sl_contentPane.putConstraint(SpringLayout.EAST, c54, 0, SpringLayout.EAST, c04);
        this.add(c54);

        c55 = new JLabel("");
        c55.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c55, 0, SpringLayout.NORTH, c50);
        sl_contentPane.putConstraint(SpringLayout.WEST, c55, 0, SpringLayout.WEST, c05);
        this.add(c55);

        c56 = new JLabel("");
        c56.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.WEST, c56, 0, SpringLayout.WEST, c06);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c56, 0, SpringLayout.SOUTH, c50);
        this.add(c56);

        c57 = new JLabel("");
        c57.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c57, 0, SpringLayout.SOUTH, c50);
        sl_contentPane.putConstraint(SpringLayout.EAST, c57, 0, SpringLayout.EAST, c07);
        this.add(c57);

        c61 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c60, -6, SpringLayout.WEST, c61);
        c61.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c61, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.WEST, c61, 0, SpringLayout.WEST, c01);
        this.add(c61);

        c62 = new JLabel("");
        c62.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c62, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.WEST, c62, 0, SpringLayout.WEST, c02);
        this.add(c62);

        c63 = new JLabel("");
        c63.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c63, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.WEST, c63, 0, SpringLayout.WEST, c03);
        this.add(c63);

        c64 = new JLabel("");
        c64.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c64, 0, SpringLayout.SOUTH, c60);
        sl_contentPane.putConstraint(SpringLayout.EAST, c64, 0, SpringLayout.EAST, c04);
        this.add(c64);

        c65 = new JLabel("");
        c65.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c65, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.WEST, c65, 0, SpringLayout.WEST, c05);
        this.add(c65);

        c66 = new JLabel("");
        c66.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c66, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.WEST, c66, 0, SpringLayout.WEST, c06);
        this.add(c66);

        c67 = new JLabel("");
        c67.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c67, 0, SpringLayout.NORTH, c60);
        sl_contentPane.putConstraint(SpringLayout.EAST, c67, 0, SpringLayout.EAST, c07);
        this.add(c67);

        c71 = new JLabel("");
        sl_contentPane.putConstraint(SpringLayout.EAST, c70, -6, SpringLayout.WEST, c71);
        c71.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.WEST, c71, 0, SpringLayout.WEST, c01);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c71, 0, SpringLayout.SOUTH, c70);
        this.add(c71);

        c72 = new JLabel("");
        c72.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c72, 0, SpringLayout.NORTH, c70);
        sl_contentPane.putConstraint(SpringLayout.EAST, c72, 0, SpringLayout.EAST, c02);
        this.add(c72);

        c73 = new JLabel("");
        c73.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c73, 0, SpringLayout.NORTH, c70);
        sl_contentPane.putConstraint(SpringLayout.EAST, c73, 0, SpringLayout.EAST, c03);
        this.add(c73);

        c74 = new JLabel("");
        c74.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.SOUTH, c74, 0, SpringLayout.SOUTH, c70);
        sl_contentPane.putConstraint(SpringLayout.EAST, c74, 0, SpringLayout.EAST, c04);
        this.add(c74);

        c75 = new JLabel("");
        c75.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c75, 0, SpringLayout.NORTH, c70);
        sl_contentPane.putConstraint(SpringLayout.WEST, c75, 0, SpringLayout.WEST, c05);
        this.add(c75);

        c76 = new JLabel("");
        c76.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c76, 0, SpringLayout.NORTH, c70);
        sl_contentPane.putConstraint(SpringLayout.EAST, c76, 0, SpringLayout.EAST, c06);
        this.add(c76);

        c77 = new JLabel("");
        c77.setIcon(new ImageIcon(pathBuida));
        sl_contentPane.putConstraint(SpringLayout.NORTH, c77, 0, SpringLayout.NORTH, c70);
        sl_contentPane.putConstraint(SpringLayout.EAST, c77, 0, SpringLayout.EAST, c07);
        this.add(c77);

        abandonButton = new JButton("Abandonar Partida");
        sl_contentPane.putConstraint(SpringLayout.NORTH, abandonButton, 6, SpringLayout.SOUTH, restartButton);
        sl_contentPane.putConstraint(SpringLayout.WEST, abandonButton, 10, SpringLayout.WEST, this);
        sl_contentPane.putConstraint(SpringLayout.EAST, abandonButton, 177, SpringLayout.WEST, this);
        add(abandonButton);

        mainMenuButton = new JButton("Torna");
        sl_contentPane.putConstraint(SpringLayout.NORTH, mainMenuButton, 0, SpringLayout.NORTH, restartButton);
        sl_contentPane.putConstraint(SpringLayout.WEST, mainMenuButton, 0, SpringLayout.WEST, c03);
        mainMenuButton.setVisible(false);
        add(mainMenuButton);

        Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
        sl_contentPane.putConstraint(SpringLayout.NORTH, rigidArea, 59, SpringLayout.NORTH, c00);
        sl_contentPane.putConstraint(SpringLayout.WEST, rigidArea, 10, SpringLayout.EAST, c07);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, rigidArea, -91, SpringLayout.EAST, c07);
        sl_contentPane.putConstraint(SpringLayout.EAST, rigidArea, -6, SpringLayout.EAST, c07);
        add(rigidArea);

        Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
        sl_contentPane.putConstraint(SpringLayout.NORTH, rigidArea_1, 0, SpringLayout.NORTH, c00);
        sl_contentPane.putConstraint(SpringLayout.WEST, rigidArea_1, 6, SpringLayout.EAST, c07);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, rigidArea_1, 0, SpringLayout.EAST, c07);
        sl_contentPane.putConstraint(SpringLayout.EAST, rigidArea_1, 411, SpringLayout.EAST, c07);
        add(rigidArea_1);

        timeLabel = new JLabel("New label");
        sl_contentPane.putConstraint(SpringLayout.WEST, timeLabel, 490, SpringLayout.WEST, this);
        sl_contentPane.putConstraint(SpringLayout.SOUTH, timeLabel, -3, SpringLayout.NORTH, mainMenuButton);
        timeLabel.setVisible(false);
        add(timeLabel);


        this.setBackground(Color.decode("#d0f0c0"));

        etiquetes = new JLabel[]{
                c00, c01, c02, c03, c04, c05, c06, c07,
                c10, c11, c12, c13, c14, c15, c16, c17,
                c20, c21, c22, c23, c24, c25, c26, c27,
                c30, c31, c32, c33, c34, c35, c36, c37,
                c40, c41, c42, c43, c44, c45, c46, c47,
                c50, c51, c52, c53, c54, c55, c56, c57,
                c60, c61, c62, c63, c64, c65, c66, c67,
                c70, c71, c72, c73, c74, c75, c76, c77
        };

        /* Per a cada JLabel afegim la seva posicio"*/
        int index;
        int i = 0;
        int j = 0;
        for (index = 0; index < 64; ++index) {
            String i_s = String.valueOf(i);
            String j_s = String.valueOf(j);
            etiquetes[index].setName(i_s + j_s);
            ++j;
            if (j == 8) {
                j = 0;
                ++i;
            }
        }
        noAcabat = true;
        /* Set torn igual a 1 */
        if (!MaquinaVSMaquina) {
            for (JLabel l : etiquetes) {
                l.addMouseListener(
                        new MouseListener() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (noAcabat) {
                                    int x = Character.getNumericValue(l.getName().charAt(0));
                                    int y = Character.getNumericValue(l.getName().charAt(1));
                                    if (StipusJugadorBlanc.equals("Maquina") || StipusJugadorNegre.equals("Maquina")) {
                                        if (!tornMaquina) {
                                            if (cpp.getSuccessors().size() == 0) {
                                                if (noMaquina) {
                                                    noUsuari = true;
                                                    try {
                                                        mostraPartidaFinalitzada();
                                                        return;
                                                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                                                    }
                                                } else {
                                                    noUsuari = true;
                                                    cpp.setTorn();
                                                    tornMaquina = true;
                                                    jugaMaquina();
                                                    while (cpp.getSuccessors().size() == 0 && !noMaquina) {
                                                        tornMaquina = true;
                                                        cpp.setTorn();
                                                        jugaMaquina();
                                                    }
                                                    try {
                                                        mostraPartidaFinalitzada();
                                                        return;
                                                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                                                    }
                                                }
                                            } else if (clickValid(x, y)){
                                                noUsuari = false;
                                                matriu = cpp.jugaTornUsuari(x, y);
                                                actualitzaTaulell();
                                                tornMaquina = true;
                                                jugaMaquina();
                                                if (noMaquina && cpp.getSuccessors().size() == 0) {
                                                    try {
                                                        mostraPartidaFinalitzada();
                                                        return;
                                                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                                                    }
                                                }

                                            }
                                            if (noMaquina && noUsuari) {
                                                try {
                                                    mostraPartidaFinalitzada();
                                                    return;
                                                } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                                                }
                                            }
                                        }

                                    } else {
                                        try {
                                            if (clickValid(x, y)) {
                                                setCasella(new Tuple<>(x, y));
                                            }
                                        } catch (FilaEsTotal | FilaNoTotal | IOException e4 ) {
                                            e4.printStackTrace();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void mousePressed(MouseEvent e) {

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {

                            }

                            @Override
                            public void mouseEntered(MouseEvent e) {

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                            }
                        }
                );
            }
        }
        restartButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reiniciaPartida();
                    }
                }
        );
        closeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tancaPartida();
                    }
                }
        );
        abandonButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            abandonaPartida();
                        } catch (FilaEsTotal | FilaNoTotal | IOException e2) {

                        }
                    }
                }
        );
        mainMenuButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cp.changePanel(TipusVistaPrincipal.PANTALLA_PRINCIPAL);
                    }
                }
        );
    }

    /**
     * Juga el torn de la màquina.
     */
    private void jugaMaquina() {
        if (tornMaquina) {
            if (cpp.getSuccessors().size() == 0) {
                noMaquina = true;
                if (noUsuari) {
                    try {
                        mostraPartidaFinalitzada();
                        return;
                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                    }
                } else {
                    cpp.setTorn();
                    tornMaquina = false;
                    actualitzaTaulell();
                }
            } else {
                noMaquina = false;
                matriu = cpp.jugaTornMaquina();
                actualitzaTaulell();
                tornMaquina = false;
                while (cpp.getSuccessors().size() == 0 && !noMaquina) {
                    tornMaquina = true;
                    cpp.setTorn();
                    jugaMaquina();
                }
                if (noMaquina && cpp.getSuccessors().size() == 0) {

                    try {
                        mostraPartidaFinalitzada();
                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                    }
                }
            }
        }
    }
    /**
     * Inicia el primer torn de la partida.
     */
    private void iniciaTorn() {
        String tipusJugadorTorn = torn.equals("Blanc") ? StipusJugadorBlanc : StipusJugadorNegre;
        matriu = cpp.parseTaulell(partida.get("taulell"));
        if (comprovaFinalitzada()) {
            actualitzaTaulell();
            try {
                mostraPartidaFinalitzada();
            } catch (FilaEsTotal filaEsTotal) {
                filaEsTotal.printStackTrace();
            } catch (FilaNoTotal filaNoTotal) {
                filaNoTotal.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (MaquinaVSMaquina) {
                matriu = cpp.parseTaulell(partida.get("taulell"));
                simulateGame();
            }
            else {
                if (tipusJugadorTorn.equals("Maquina")) {
                    tornMaquina = true;
                    matriu = cpp.jugaTornMaquina();
                    turnLabel.setText("Torn: " + partida.get("torn"));
                    nomJugadorBlanc.setForeground(java.awt.Color.GREEN);
                    tornMaquina = false;
                } else {
                    tornMaquina = false;
                    turnLabel.setText("Torn: " + cpp.getPartida().get("torn"));
                    nomJugadorBlanc.setForeground(java.awt.Color.GREEN);
                    matriu = cpp.parseTaulell(partida.get("taulell"));
                }
                actualitzaTaulell();
            }
        }
    }
    /**
     * Coloca la peça clicada per l'usuari amb el seu color corresponent a la posició desitjada.
     * @param mov Tupla que conté el moviment del jugador.
     */
    public void setCasella(Tuple<Integer, Integer> mov) throws FilaEsTotal, FilaNoTotal, IOException {

        JLabel mo = etiquetes[mov.x*8 + mov.y];
        if (torn.equals("Blanc")) mo.setIcon(new ImageIcon(pathBlanca));
        else mo.setIcon(new ImageIcon(pathNegra));
        matriu = cpp.jugaTornUsuari(mov.x, mov.y);

        actualitzaTaulell();
    }
    /**
     * Autentica els jugadors pendents d'autenticació.
     */
    public void autenticaJugadors() {

        dialegAutenticacio = new AutenticacioPartida(this, SnomJugadorBlanc, SnomJugadorNegre, this.ctrlPresentacioAutenticacio);
    }

    /**
     * Mètode encarregat de tancar i guardar la partida.
     */
    public void tancaPartida() {
        try {
            String ignored = cpp.guardaPartida(false, false, "");
            JOptionPane.showMessageDialog(new JDialog(), "La partida s'ha guardat correctament!", "Titol dialeg", JOptionPane.WARNING_MESSAGE);
            cp.changePanel(TipusVistaPrincipal.PANTALLA_PRINCIPAL);

        } catch (IOException | FilaEsTotal | FilaNoTotal e) {
            //error
            JOptionPane.showMessageDialog(new JDialog(), e, "Titol dialeg",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Mètode encarregat de reinicar la partida amb el taulell inicial.
     */
    public void reiniciaPartida() {

        try {
            Tuple<String, String> dadesInicials = cpp.reiniciaPartida();
            String newTaulell = dadesInicials.x;
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    matriu[i][j] = newTaulell.charAt(i*8+j);
                }
            }
            torn = dadesInicials.y;
            actualitzaTaulell();

        } catch (Exception e) {
            // Do nothing...
            JOptionPane.showMessageDialog(new JDialog(), e, "Titol dialeg",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Mètode encarregat d'abandonar la partida i actualitzar totes les dades.
     */
    public void abandonaPartida() throws FilaEsTotal, FilaNoTotal, IOException {

        String currentTorn = cpp.getPartida().get("torn");
        String winner;
        if (currentTorn.equals("Blanc")) {
            turnLabel.setText("<html><body>"+SnomJugadorNegre+"<br>ha guanyat!</body></html>");

            winner = "Negre";
        }
        else {
            turnLabel.setText("<html><body>"+SnomJugadorBlanc+"<br>ha guanyat!</body></html>");
            winner = "Blanc";
        }
        nomJugadorBlanc.setVisible(false);
        nomJugadorNegre.setVisible(false);
        abandonButton.setVisible(false);
        restartButton.setVisible(false);
        closeButton.setVisible(false);
        mainMenuButton.setVisible(true);
        String ignored = cpp.guardaPartida(true, true, winner);
    }

    /**
     * Mètode encarregat d'actualitzar el taulell.
     */
    private void actualitzaTaulell() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char tmp = matriu[i][j];
                if (tmp == 'B') etiquetes[i*8+j].setIcon(new ImageIcon(pathBlanca));
                else if (tmp == 'N') etiquetes[i*8 + j].setIcon(new ImageIcon(pathNegra));
                else etiquetes[i*8+j].setIcon(new ImageIcon(pathBuida));
            }
        }
        if (!MaquinaVSMaquina) {
            List<Tuple<Integer, Integer>> successors  = cpp.getSuccessors();

            for (Tuple<Integer, Integer> m : successors) {
                etiquetes[m.x*8 + m.y].setIcon(new ImageIcon(pathPossible));
            }
        }
        if (cpp.getPartida().get("torn").equals("Negre")) {
            nomJugadorNegre.setForeground(java.awt.Color.GREEN);
            nomJugadorBlanc.setForeground(java.awt.Color.BLACK);
        }
        else {
            nomJugadorBlanc.setForeground(java.awt.Color.GREEN);
            nomJugadorNegre.setForeground(java.awt.Color.BLACK);
        }
        turnLabel.setText("Torn: " + cpp.getPartida().get("torn"));
        if (StipusJugadorBlanc.equals("Usuari") && StipusJugadorNegre.equals("Usuari")) {
            if (cpp.getSuccessors().size() == 0) {
                if (noUsuari) {
                    try {
                        mostraPartidaFinalitzada();
                        return;
                    } catch (FilaEsTotal | FilaNoTotal | IOException error) {

                    }
                } else {
                    noUsuari = true;
                    cpp.setTorn();
                    actualitzaTaulell();
                }
            }
        }
        validate();
        update(getGraphics());
    }

    /**
     * Mètode encarregat de simular el joc entre dos màquines.
     */
    private void simulateGame() {
	    JFrame dialegSimulant = new JFrame();
	    dialegSimulant.isAlwaysOnTop();
	    dialegSimulant.setLocationRelativeTo(this);
	    dialegSimulant.setTitle("Simulació en curs...");
	    dialegSimulant.setSize(300, 0);
	    dialegSimulant.setVisible(true);

        Date init, end;
        Calendar a, b;
        init = new Date();

        boolean noMovesWhite = false;
        boolean noMovesBlack = false;
        turnLabel.setText("Simulant partida...");
        boolean notEnded = (noMovesWhite && noMovesBlack);
        while (!notEnded) {
            if (cpp.getPartida().get("torn").equals("Blanc")) {
                if (cpp.getSuccessors().size() == 0) {
                    noMovesWhite = true;
                    cpp.setTorn();
                    actualitzaTaulell();
                }
                else {
                    matriu = cpp.jugaTornMaquina();
                    actualitzaTaulell();
                    noMovesWhite = false;

                }
            } else {
                if (cpp.getSuccessors().size() == 0) {
                    noMovesBlack = true;
                    cpp.setTorn();
                    actualitzaTaulell();
                }
                else {
                    matriu = cpp.jugaTornMaquina();
                    actualitzaTaulell();
                    noMovesBlack = false;

                }
            }
            notEnded = (noMovesWhite && noMovesBlack);
        }
        try {
            end = new Date();

            a = Calendar.getInstance();
            b = Calendar.getInstance();

            a.setTime(init);
            b.setTime(end);
            timeLabel.setText("Temps: " + (String.valueOf(b.getTimeInMillis()/1000 - a.getTimeInMillis()/1000) + "s"));
            timeLabel.setVisible(true);
            mostraPartidaFinalitzada();

        } catch (FilaNoTotal | FilaEsTotal | IOException e) {
            JOptionPane.showMessageDialog(new JDialog(), e, "Titol dialeg",
                    JOptionPane.WARNING_MESSAGE);
        }
	    dialegSimulant.dispose();
    }
    /**
     * Mètode encarregat de mostrar una partida finalitzada a la vista.
     */
    private void mostraPartidaFinalitzada() throws FilaEsTotal, FilaNoTotal, IOException {

        noAcabat = false;
        String gameStatus = cpp.guardaPartida(true, false, "");
        if (gameStatus.equals("Empat")) turnLabel.setText("<html><body>S'ha produit<br>un empat</body></html>");
        else if (gameStatus.equals("Blanc")) turnLabel.setText("<html><body>"+SnomJugadorBlanc+"<br>ha guanyat!</body></html>");
        else  turnLabel.setText("<html><body>"+SnomJugadorNegre+"<br>ha guanyat!</body></html>");
        nomJugadorBlanc.setVisible(false);
        nomJugadorNegre.setVisible(false);
        abandonButton.setVisible(false);
        restartButton.setVisible(false);
        closeButton.setVisible(false);
        mainMenuButton.setVisible(true);
        noMaquina = noUsuari = false;

    }
    /**
     * Inicia el torn després d'autenticar els jugadors.
     */
    public void autentica() {
        autenticat = true;
        inicialitzarComponents();
        iniciaTorn();
    }

    /**
     * Comprova que la posició clicada per l'usuari es vàlida.
     * @param x Posició x al taulell.
     * @param y Posició y al taulell.
     * @return Retorna cert si la posició es vàlida, altrament fals.
     */
    public boolean clickValid(int x, int y) {
        List<Tuple<Integer, Integer>> presuccessors  = cpp.getSuccessors();
        int count = 0;
        boolean found = false;
        while (count < presuccessors.size() && !found) {

            found = (presuccessors.get(count).x.equals(x) && presuccessors.get(count).y.equals(y));
            ++count;
        }
        return found;
    }

    public boolean comprovaFinalitzada() {
        if (cpp.getSuccessors().size() == 0) {
            cpp.setTorn();
            if (cpp.getSuccessors().size() == 0) {
                cpp.setTorn();
                return true;
            }
        }
        return false;
    }
}
