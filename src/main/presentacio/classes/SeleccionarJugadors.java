package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioPartida;
import main.presentacio.controladors.CtrlPresentacioUsuari;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * La classe SeleccionarJugadors permet escollir els jugadors blanc i negre entre tots els jugadors existents per a configurar una nova partida. Hereda de la classe JPanel.
 *
 * @author Carles Ureta Boza
 */
public class SeleccionarJugadors extends JPanel {
    CrearPartida crearPartida;

    String[] llistaJugadors;
	CtrlPresentacioPartida ctrlPresentacioPartida;
    JList<String> llistaJugadorsBlanc;
    JList<String> llistaJugadorsNegre;

    private JLabel Label = new JLabel("Seleccionar jugadors");
    private JButton botoCancela = new JButton("Cancelar");
    private JButton botoAccepta = new JButton("Acceptar");
    private String nomJugadorBlanc;
    private String nomJugadorNegre;

    /**
     * Creadora de la classe que inicialitza la pantalla.
     * @param crearPartida Classe CrearPartida a la que pertany.
     * @param ctrlPresentacioPartida Controlador de presentació de partida.
     */
    public SeleccionarJugadors(CrearPartida crearPartida, CtrlPresentacioPartida ctrlPresentacioPartida) {
        this.crearPartida = crearPartida;
        this.ctrlPresentacioPartida = ctrlPresentacioPartida;
	    {
		    try {
			    llistaJugadors = ctrlPresentacioPartida.getJugadors();
		    } catch (FileNotFoundException e) {
			    e.printStackTrace();
		    }
	    }
	    this.llistaJugadorsBlanc = new JList(llistaJugadors);
	    this.llistaJugadorsNegre = new JList(llistaJugadors);
        try {
            this.inicialitzarComponents();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Incialitza els components del panell
     */
    private void inicialitzarComponents() throws FileNotFoundException {

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

        botoAccepta.addActionListener(
                //Quan és clickat crida a change panel per canviar de pantalla
                new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {

                            if (getJugadorBlanc()[0].equals(getJugadorNegre()[0])) {

                                JOptionPane.showMessageDialog(new JDialog(), "No es pot crear una partida amb el mateix jugador", "Error seleccionar jugadors",
                                        JOptionPane.ERROR_MESSAGE);
                            }

                            else if (getJugadorBlanc()[1].equals("Usuari") || getJugadorNegre()[1].equals("Usuari")) {
                                if (!comprovaJugadorSessio(getJugadorBlanc()[0]) && !comprovaJugadorSessio(getJugadorNegre()[0])) {
                                    JOptionPane.showMessageDialog(new JDialog(), "Vostè ha de ser participant de la partida", "Error seleccionar jugadors",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                                else {
                                    setJugadors(getJugadorBlanc()[0], getJugadorBlanc()[1], getJugadorNegre()[0], getJugadorNegre()[1]);
                                    crearPartida.changePanel(1);
                                }
                            }
                            else {
                                setJugadors(getJugadorBlanc()[0], getJugadorBlanc()[1], getJugadorNegre()[0], getJugadorNegre()[1]);
                                crearPartida.changePanel(1);
                            }


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }


                    }
                });


        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Jugador blanc");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        add(lblNewLabel, gbc_lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Jugador negre");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 3;
        gbc_lblNewLabel_1.gridy = 1;
        add(lblNewLabel_1, gbc_lblNewLabel_1);








        GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.anchor = GridBagConstraints.NORTHEAST;
        gbc_list.insets = new Insets(0, 0, 5, 5);
        gbc_list.gridx = 1;
        gbc_list.gridy = 2;
        add(llistaJugadorsBlanc, gbc_list);


        GridBagConstraints gbc_list_1 = new GridBagConstraints();
        gbc_list_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_list_1.insets = new Insets(0, 0, 5, 5);
        gbc_list_1.gridx = 3;
        gbc_list_1.gridy = 2;
        add(llistaJugadorsNegre, gbc_list_1);


        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 5;
        add(botoCancela, gbc_btnNewButton);


        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
        gbc_btnNewButton_1.gridx = 4;
        gbc_btnNewButton_1.gridy = 5;
        add(botoAccepta, gbc_btnNewButton_1);


        this.setBackground(Color.decode("#d0f0c0"));


    }

    /**
     * Assigna a CrearPartida els noms i tipus dels jugadors blanc i negre escollits.
     * @param nomJugadorBlanc Nom del jugador blanc.
     * @param tipusJugadorBlanc Tipus del jugador blanc.
     * @param nomJugadorNegre Nom del jugador negre.
     * @param tipusJugadorNegre Tipus del jugador negre.
     */
    private void setJugadors(String nomJugadorBlanc, String tipusJugadorBlanc, String nomJugadorNegre, String tipusJugadorNegre) throws FileNotFoundException {
        crearPartida.setJugadorBlanc(nomJugadorBlanc, tipusJugadorBlanc);
        crearPartida.setJugadorNegre(nomJugadorNegre, tipusJugadorNegre);
    }

    /**
     * Obté el nom i tipus del jugador blanc seleccionat a la llista.
     * @return Retorna un vector de Strings amb dos Strings, el nom i el tipus del jugador blanc.
     */
    private String[] getJugadorBlanc() {
        String jugador = llistaJugadorsBlanc.getSelectedValue().toString();
        String[] jug = jugador.split(" ");
        return jug;
    }

    /**
     * Obté el nom i tipus del jugador negre seleccionat a la llista.
     * @return Retorna un vector de Strings amb dos Strings, el nom i el tipus del jugador negre.
     */
    private String[] getJugadorNegre() {
        String jugador = llistaJugadorsNegre.getSelectedValue().toString();
        String [] jug = jugador.split(" ");

        return jug;
    }

    /**
     * Comprova si el nom passat per paràmetre és el mateix que el nom de l'usuari que ha iniciat sessió.
     * @return Retorna un vector de Strings amb dos Strings, el nom i el tipus del jugador negre.
     */
    private boolean comprovaJugadorSessio(String nomJugador) {
        return ctrlPresentacioPartida.comprovaJugadorSessio(nomJugador);
    }


}
