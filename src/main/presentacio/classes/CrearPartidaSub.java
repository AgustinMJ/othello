package main.presentacio.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * La classe CrearPartidaSub és un JPanel que permet crear una partida nova. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */
public class CrearPartidaSub extends JPanel{
    CrearPartida crearPartida;

    String nomJugadorBlanc = null;
    String nomJugadorNegre = null;
    String taulellPartida =
            "????????" +
            "????????" +
            "????????" +
            "???BN???" +
            "???NB???" +
            "????????" +
            "????????" +
            "????????" ;
    String tipusJugadorBlanc, tipusJugadorNegre;
    String modeDeJoc = null;
    String torn = "Blanc";

    private JButton configurarTaulell = new JButton("Configurar Taulell");
    private JButton escollirJugadors = new JButton("Escollir Jugadors");

    private ButtonGroup ModeDeJoc = new ButtonGroup();
    private JRadioButton classic = new JRadioButton("Clàssic");
    private JRadioButton vertical = new JRadioButton("Vertical");
    private JRadioButton horitzontal = new JRadioButton("Horitzontal");
    private JRadioButton diagonal = new JRadioButton("Diagonal");

    private final JLabel lblNewLabel = new JLabel("Selecciona un mode de joc:");
    private final JLabel lblNewLabel_1 = new JLabel("Nom jugador blanc:");
    private final JLabel lblNewLabel_2 = new JLabel("Nom jugador negre:");
    private final JLabel lblNomJugadorBlanc = new JLabel(nomJugadorBlanc);
    private final JLabel lblNomJugadorNegre = new JLabel(nomJugadorNegre);
    private final JButton iniciaPartida = new JButton("Iniciar");

    /**
     * Creadora de la classe que inicialitza la pantalla.
     * @param crearPartida Classe CrearPartida.
     */
    public CrearPartidaSub (CrearPartida crearPartida) {
        this.crearPartida = crearPartida;

        this.inicialitzarComponents();
    }

    /**
     * Incialitza els components del panell
     */
    private void inicialitzarComponents() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);




        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 1;
        add(configurarTaulell, gbc_btnNewButton);
        //	this.add(escollirJugadors);
        //this.add(new JLabel("Crear partida"));
        configurarTaulell.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            crearPartida.changePanel(2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });


        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton_1.gridx = 4;
        gbc_btnNewButton_1.gridy = 2;
        add(escollirJugadors, gbc_btnNewButton_1);

        escollirJugadors.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            crearPartida.changePanel(3);
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
        );

        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 4;
        gbc_lblNewLabel.gridy = 3;
        add(lblNewLabel, gbc_lblNewLabel);


        GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_3.anchor = GridBagConstraints.WEST;
        gbc_rdbtnNewRadioButton_3.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton_3.gridx = 4;
        gbc_rdbtnNewRadioButton_3.gridy = 4;
        ModeDeJoc.add(classic);
        classic.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setModeDeJoc("Classic");
                    }
                }
        );
        add(classic, gbc_rdbtnNewRadioButton_3);


        GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_2.anchor = GridBagConstraints.WEST;
        gbc_rdbtnNewRadioButton_2.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton_2.gridx = 4;
        gbc_rdbtnNewRadioButton_2.gridy = 5;
        ModeDeJoc.add(horitzontal);
        horitzontal.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setModeDeJoc("Horitzontal");
                    }
                }
        );
        add(horitzontal, gbc_rdbtnNewRadioButton_2);


        GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
        gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
        gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton_1.gridx = 4;
        gbc_rdbtnNewRadioButton_1.gridy = 6;
        ModeDeJoc.add(vertical);
        vertical.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setModeDeJoc("Vertical");
                    }
                }
        );
        add(vertical, gbc_rdbtnNewRadioButton_1);


        GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
        gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
        gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
        gbc_rdbtnNewRadioButton.gridx = 4;
        gbc_rdbtnNewRadioButton.gridy = 7;
        ModeDeJoc.add(diagonal);
        diagonal.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setModeDeJoc("Diagonal");
                    }
                }
        );
        add(diagonal, gbc_rdbtnNewRadioButton);

        this.setBackground(Color.decode("#d0f0c0"));
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 8;
        add(lblNewLabel_1, gbc_lblNewLabel_1);

        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.gridx = 3;
        gbc_lblNewLabel_3.gridy = 8;
        lblNomJugadorBlanc.setText(nomJugadorBlanc);
        add(lblNomJugadorBlanc, gbc_lblNewLabel_3);

        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 9;

        add(lblNewLabel_2, gbc_lblNewLabel_2);

        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.gridx = 3;
        gbc_lblNewLabel_4.gridy = 9;
        lblNomJugadorNegre.setText(nomJugadorNegre);
        add(lblNomJugadorNegre, gbc_lblNewLabel_4);

        GridBagConstraints gbc_btnNewButton2 = new GridBagConstraints();
        gbc_btnNewButton2.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton2.gridx = 7;
        gbc_btnNewButton2.gridy = 9;
        iniciaPartida.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (nomJugadorBlanc == null || nomJugadorNegre == null) {
                            JOptionPane.showMessageDialog(new JDialog(), "Selecciona dos participants per la partida", "Error creant la partida",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        else if (modeDeJoc == null) {
                            JOptionPane.showMessageDialog(new JDialog(), "Selecciona un mode de joc", "Error creant la partida",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
	                        crearPartida.creaPartida(nomJugadorBlanc, nomJugadorNegre, tipusJugadorBlanc, tipusJugadorNegre, taulellPartida, modeDeJoc, torn);
                        }
                    }
                });
        add(iniciaPartida, gbc_btnNewButton2);
    }

    /**
     * Assigna el nom i tipus al jugador blanc.
     * @param nom Nom del jugador blanc.
     * @param tipus Tipus del jugador blanc.
     */
    public void setJugadorBlanc(String nom, String tipus) throws FileNotFoundException {
        nomJugadorBlanc = nom;
        tipusJugadorBlanc = tipus;
        lblNomJugadorBlanc.setText(nomJugadorBlanc);
        crearPartida.changePanel(1);

    }

    /**
     * Assigna el nom i tipus al jugador negre.
     * @param nom Nom del jugador negre.
     * @param tipus Tipus del jugador negre.
     */
    public void setJugadorNegre(String nom, String tipus) throws FileNotFoundException {
        nomJugadorNegre = nom;
        tipusJugadorNegre = tipus;
        lblNomJugadorNegre.setText(nomJugadorNegre);

        crearPartida.changePanel(1);
    }

    /**
     * Assigna el mode de joc a la partida.
     * @param modeDeJoc Mode de joc seleccionat.
     */
    private void setModeDeJoc(String modeDeJoc) {
        this.modeDeJoc = modeDeJoc;
    }

    /**
     * Assigna el taulell configurat al taulell de la partida.
     * @param taulell Taulell configurat.
     * @param torn Torn inicial de la partida.
     */
    public void setTaulell(String taulell, String torn) {
        this.taulellPartida = taulell;
        this.torn = torn;
    }
}
