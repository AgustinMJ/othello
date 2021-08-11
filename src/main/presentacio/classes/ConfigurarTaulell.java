package main.presentacio.classes;

import main.domini.classes.Taulell;
import main.presentacio.controladors.CtrlPresentacioPartida;
import main.utils.PartidaAcabada;
import main.utils.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * La classe ConfigurarTaulell és un JPanel que permet configurar un taulell al crear una partida. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */

public class ConfigurarTaulell extends JPanel {
    CrearPartida crearPartida;

    private JPanel contentPane;
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
    private final CtrlPresentacioPartida cpp  = new CtrlPresentacioPartida();

    private JLabel tornEt = new JLabel("Torn:");
    private JLabel tornVal;


    private String taulellDefecte =
            "????????" +
            "????????" +
            "????????" +
            "???BN???" +
            "???NB???" +
            "????????" +
            "????????" +
            "????????" ;
    private String taulell = taulellDefecte;

    private String torn = "Blanc";
    private JButton botoAccepta, botoCancela, botoEnrere;

    private char[][] matriu = new char[8][8];

    private Stack<String> pilaTaulell = new Stack<String>();



    /**
     * Constructora que inicialitza la pantalla.
     * @param crearPartida Classe CrearPartida.
     * @param taulell Taulell configurat fins el moment.
     * @param torn Torn configurat fins el moment.
     */
    public ConfigurarTaulell(CrearPartida crearPartida, String taulell, String torn) {
        this.crearPartida = crearPartida;
        this.torn = torn;
        this.taulell = taulell;
        this.inicialitzarComponents();
        iniciaTorn();
    }
    /**
     * Incialitza els components del panell
     */
    private void inicialitzarComponents() {


        this.setBackground(Color.decode("#d0f0c0"));

        pilaTaulell.push(taulell);

        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        c00 = new JLabel("");
        c00.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 2;
        add(c00, gbc_lblNewLabel);

        c01 = new JLabel("");
        c01.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 2;
        add(c01, gbc_lblNewLabel_2);

        c02 = new JLabel("");
        c02.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 2;
        add(c02, gbc_lblNewLabel_2);

        c03 = new JLabel("");
        c03.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 2;
        add(c03, gbc_lblNewLabel_2);

        c04 = new JLabel("");
        c04.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 2;
        add(c04, gbc_lblNewLabel_2);

        c05 = new JLabel("");
        c05.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 2;
        add(c05, gbc_lblNewLabel_2);

        c06 = new JLabel("");
        c06.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 2;
        add(c06, gbc_lblNewLabel_2);

        c07 = new JLabel("");
        c07.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 2;
        add(c07, gbc_lblNewLabel_2);


        c10 = new JLabel("");
        c10.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 3;
        add(c10, gbc_lblNewLabel);

        c11 = new JLabel("");
        c11.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 3;
        add(c11, gbc_lblNewLabel_2);

        c12 = new JLabel("");
        c12.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 3;
        add(c12, gbc_lblNewLabel_2);

        c13 = new JLabel("");
        c13.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 3;
        add(c13, gbc_lblNewLabel_2);

        c14 = new JLabel("");
        c14.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 3;
        add(c14, gbc_lblNewLabel_2);

        c15 = new JLabel("");
        c15.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 3;
        add(c15, gbc_lblNewLabel_2);

        c16 = new JLabel("");
        c16.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 3;
        add(c16, gbc_lblNewLabel_2);

        c17 = new JLabel("");
        c17.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 3;
        add(c17, gbc_lblNewLabel_2);

        c20 = new JLabel("");
        c20.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 4;
        add(c20, gbc_lblNewLabel);

        c21 = new JLabel("");
        c21.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 4;
        add(c21, gbc_lblNewLabel_2);

        c22 = new JLabel("");
        c22.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 4;
        add(c22, gbc_lblNewLabel_2);

        c23 = new JLabel("");
        c23.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 4;
        add(c23, gbc_lblNewLabel_2);

        c24 = new JLabel("");
        c24.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 4;
        add(c24, gbc_lblNewLabel_2);

        c25 = new JLabel("");
        c25.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 4;
        add(c25, gbc_lblNewLabel_2);

        c26 = new JLabel("");
        c26.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 4;
        add(c26, gbc_lblNewLabel_2);

        c27 = new JLabel("");
        c27.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 4;
        add(c27, gbc_lblNewLabel_2);


        c30 = new JLabel("");
        c30.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 5;
        add(c30, gbc_lblNewLabel);

        c31 = new JLabel("");
        c31.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 5;
        add(c31, gbc_lblNewLabel_2);

        c32 = new JLabel("");
        c32.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 5;
        add(c32, gbc_lblNewLabel_2);

        c33 = new JLabel("");
        c33.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/fitxaBlanca.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 5;
        add(c33, gbc_lblNewLabel_2);

        c34 = new JLabel("");
        c34.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/fitxaNegra.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 5;
        add(c34, gbc_lblNewLabel_2);

        c35 = new JLabel("");
        c35.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 5;
        add(c35, gbc_lblNewLabel_2);

        c36 = new JLabel("");
        c36.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 5;
        add(c36, gbc_lblNewLabel_2);

        c37 = new JLabel("");
        c37.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 5;
        add(c37, gbc_lblNewLabel_2);


        c40 = new JLabel("");
        c40.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 6;
        add(c40, gbc_lblNewLabel);

        c41 = new JLabel("");
        c41.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 6;
        add(c41, gbc_lblNewLabel_2);

        c42 = new JLabel("");
        c42.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 6;
        add(c42, gbc_lblNewLabel_2);

        c43 = new JLabel("");
        c43.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/fitxaNegra.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 6;
        add(c43, gbc_lblNewLabel_2);

        c44 = new JLabel("");
        c44.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/fitxaBlanca.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 6;
        add(c44, gbc_lblNewLabel_2);

        c45 = new JLabel("");
        c45.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 6;
        add(c45, gbc_lblNewLabel_2);

        c46 = new JLabel("");
        c46.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 6;
        add(c46, gbc_lblNewLabel_2);

        c47 = new JLabel("");
        c47.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 6;
        add(c47, gbc_lblNewLabel_2);


        c50 = new JLabel("");
        c50.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 7;
        add(c50, gbc_lblNewLabel);

        c51 = new JLabel("");
        c51.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 7;
        add(c51, gbc_lblNewLabel_2);

        c52 = new JLabel("");
        c52.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 7;
        add(c52, gbc_lblNewLabel_2);

        c53 = new JLabel("");
        c53.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 7;
        add(c53, gbc_lblNewLabel_2);

        c54 = new JLabel("");
        c54.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 7;
        add(c54, gbc_lblNewLabel_2);

        c55 = new JLabel("");
        c55.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 7;
        add(c55, gbc_lblNewLabel_2);

        c56 = new JLabel("");
        c56.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 7;
        add(c56, gbc_lblNewLabel_2);

        c57 = new JLabel("");
        c57.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 7;
        add(c57, gbc_lblNewLabel_2);


        c60 = new JLabel("");
        c60.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 8;
        add(c60, gbc_lblNewLabel);

        c61 = new JLabel("");
        c61.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 8;
        add(c61, gbc_lblNewLabel_2);

        c62 = new JLabel("");
        c62.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 8;
        add(c62, gbc_lblNewLabel_2);

        c63 = new JLabel("");
        c63.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 8;
        add(c63, gbc_lblNewLabel_2);

        c64 = new JLabel("");
        c64.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 8;
        add(c64, gbc_lblNewLabel_2);

        c65 = new JLabel("");
        c65.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 8;
        add(c65, gbc_lblNewLabel_2);

        c66 = new JLabel("");
        c66.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 8;
        add(c66, gbc_lblNewLabel_2);

        c67 = new JLabel("");
        c67.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 8;
        add(c67, gbc_lblNewLabel_2);


        c70 = new JLabel("");
        c70.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 9;
        add(c70, gbc_lblNewLabel);

        c71 = new JLabel("");
        c71.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 3;
        gbc_lblNewLabel_2.gridy = 9;
        add(c71, gbc_lblNewLabel_2);

        c72 = new JLabel("");
        c72.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 4;
        gbc_lblNewLabel_2.gridy = 9;
        add(c72, gbc_lblNewLabel_2);

        c73 = new JLabel("");
        c73.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 9;
        add(c73, gbc_lblNewLabel_2);

        c74 = new JLabel("");
        c74.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 9;
        add(c74, gbc_lblNewLabel_2);

        c75 = new JLabel("");
        c75.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 7;
        gbc_lblNewLabel_2.gridy = 9;
        add(c75, gbc_lblNewLabel_2);

        c76 = new JLabel("");
        c76.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 8;
        gbc_lblNewLabel_2.gridy = 9;
        add(c76, gbc_lblNewLabel_2);

        c77 = new JLabel("");
        c77.setIcon(new ImageIcon("/Users/carles/IdeaProjects/subgrup-prop3-1/dades/imatges/posicioBuida.png"));
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 9;
        gbc_lblNewLabel_2.gridy = 9;
        add(c77, gbc_lblNewLabel_2);

        tornVal = new JLabel(torn);

        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 6;
        gbc_lblNewLabel_2.gridy = 10;
        add(tornVal, gbc_lblNewLabel_2);

        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 5;
        gbc_lblNewLabel_2.gridy = 10;
        add(tornEt, gbc_lblNewLabel_2);

        botoEnrere = new JButton("Desfer");
        botoEnrere.addActionListener(
                //Quan és clickat crida a change panel per canviar de pantalla
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        tiraEnrere();

                    }
                });
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 10;
        gbc_lblNewLabel_2.gridy = 10;
        add(botoEnrere, gbc_lblNewLabel_2);

        botoCancela = new JButton("Cancela");

        botoCancela.addActionListener(
                //Quan és clickat crida a change panel per canviar de pantalla
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            crearPartida.changePanel(1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 10;
        add(botoCancela, gbc_lblNewLabel_2);

        botoAccepta = new JButton("Accepta");

        botoAccepta.addActionListener(
                //Quan és clickat crida a change panel per canviar de pantalla
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        crearPartida.setTaulell(taulell, torn);
                        try {
                            crearPartida.changePanel(1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.gridx = 11;
        gbc_lblNewLabel_2.gridy = 10;
        add(botoAccepta, gbc_lblNewLabel_2);


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

        /* Set torn igual a 1 */


        for (JLabel l : etiquetes) {
            l.addMouseListener(
                    new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            int x = Character.getNumericValue(l.getName().charAt(0));
                            int y = Character.getNumericValue(l.getName().charAt(1));

                            if (clickValid(x, y)) setCasella(new Tuple<Integer, Integer>(x, y));


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

    /**
     * Mostra els possibles moviments del primer torn;
     */
    private void iniciaTorn() {
        matriu = cpp.parseTaulell(taulell);
        actualitzaTaulell();
    }

    /**
     * Assigna a la casella seleccionada el color que li pertoca (el del torn), modifica els colors de les caselles pertinents, canvia el torn i mostra els nous moviments possibles.
     * @param mov Tupla amb dos enters que indiquen la posició de la casella a moure.
     */
    public void setCasella(Tuple<Integer, Integer> mov) {


        JLabel mo = etiquetes[mov.x*8 + mov.y];

        // PER SABER SI CLICA EN UNA POSICIO VALID

        List<Tuple<Integer, Integer>> presuccessors  = cpp.getSuccessors(taulell, torn, "Classic");

        int count = 0;
        boolean found = false;
        while (count < presuccessors.size() && !found) {

            found = (presuccessors.get(count).x.equals(mov.x) && presuccessors.get(count).y.equals(mov.y));
            ++count;
        }
        if (found) {

            if (torn.equals("Blanc")) mo.setIcon(new ImageIcon(pathBlanca));
            else mo.setIcon(new ImageIcon(pathNegra));


            matriu = cpp.colocarFitxaModeCreacio(mov.x, mov.y, taulell, torn);
            taulell = "";
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    taulell = taulell + matriu[i][j];
                    if (matriu[i][j] == 'B') {
                        etiquetes[i*8+j].setIcon(new ImageIcon(pathBlanca));
                    }
                    else if (matriu[i][j] == 'N') etiquetes[i*8 + j].setIcon(new ImageIcon(pathNegra));
                    else etiquetes[i*8+j].setIcon(new ImageIcon(pathBuida));
                }
            }
            pilaTaulell.push(taulell);


        }
        List<Tuple<Integer, Integer>> successorsoppo  = cpp.getSuccessors(taulell, torn, "Classic");

        if (torn == "Blanc") torn = "Negre";
        else torn = "Blanc";
        tornVal.setText(torn);

        List<Tuple<Integer, Integer>> successors  = cpp.getSuccessors(taulell, torn, "Classic");

        for (Tuple<Integer, Integer> m : successors) {
            etiquetes[m.x*8 + m.y].setIcon(new ImageIcon(pathPossible));
        }
        if (successors.size() == 0) {
            for (Tuple<Integer, Integer> m : successorsoppo) {
                etiquetes[m.x*8 + m.y].setIcon(new ImageIcon(pathPossible));
            }
            if (torn == "Blanc") torn = "Negre";
            else torn = "Blanc";
            tornVal.setText(torn);
        }

        //actualitzaTaulell();
    }

    /**
     * Actualitza el taulell actualitzant les etiquetes.
     */
    public void actualitzaTaulell() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char tmp = matriu[i][j];
                if (tmp == 'B') etiquetes[i*8+j].setIcon(new ImageIcon(pathBlanca));
                else if (tmp == 'N') etiquetes[i*8 + j].setIcon(new ImageIcon(pathNegra));
                else etiquetes[i*8+j].setIcon(new ImageIcon(pathBuida));
            }
        }
        List<Tuple<Integer, Integer>> successors  = cpp.getSuccessors(taulell, torn, "Classic");

        for (Tuple<Integer, Integer> m : successors) {
            etiquetes[m.x*8 + m.y].setIcon(new ImageIcon(pathPossible));
        }

	    validate();
	    update(getGraphics());
    }

    /**
     * Comprova si la casella que se li passa per paràmetre és un moviment possible.
     * @param x Fila del taulell de la casella seleccionada.
     * @param y Columna del taulell de la casella seleccionada.
     * @return Booleà que indica si la casella donada és un moviment possible o no.
     */
    public boolean clickValid(int x, int y) {
        List<Tuple<Integer, Integer>> presuccessors  = cpp.getSuccessors(taulell, torn, "Classic");
        int count = 0;
        boolean found = false;
        while (count < presuccessors.size() && !found) {

            found = (presuccessors.get(count).x.equals(x) && presuccessors.get(count).y.equals(y));
            ++count;
        }
        return found;
    }

    /**
     * Retorna un moviment enrere el taulell
     */
    private void tiraEnrere() {
        if (pilaTaulell.size()<=1) {
            JOptionPane.showMessageDialog(new JDialog(), "No es pot tirar més enrere", "Error configurar taulell",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            pilaTaulell.pop();

            taulell = pilaTaulell.peek();
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    if (taulell.charAt(i * 8 + j) == 'B') {
                        etiquetes[i * 8 + j].setIcon(new ImageIcon(pathBlanca));
                    } else if (taulell.charAt(i * 8 + j) == 'N') etiquetes[i * 8 + j].setIcon(new ImageIcon(pathNegra));
                    else etiquetes[i * 8 + j].setIcon(new ImageIcon(pathBuida));
                }
            }
            if (torn == "Blanc") torn = "Negre";
            else torn = "Blanc";
            tornVal.setText(torn);

            List<Tuple<Integer, Integer>> successors = cpp.getSuccessors(taulell, torn, "Classic");

            for (Tuple<Integer, Integer> m : successors) {
                etiquetes[m.x * 8 + m.y].setIcon(new ImageIcon(pathPossible));
            }
        }
    }

}