package main.presentacio.classes;

import main.presentacio.controladors.CtrlPresentacioAutenticacio;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe AutenticacioPartida és una vista encarregada d'autenticar a un usuari no autenticat.
 *
 * @author Guillem García Gil
 */
public class AutenticacioPartida extends JDialog {
    private final JPanel contentPanel = new JPanel();
    private JPasswordField passwordField;
    private JButton okButton;
    private JButton cancelButton;
    private VistaPartida myVP;
    private JLabel logName;
    private CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio;

    /**
     * Constructora que crea la vista AutenticacioPartida.
     * @param vp Objecte de la classe VistaPrincipal
     * @param player1 Nom del jugador 1.
     * @param player1 Nom del jugador 2.
     * @param ctrlPresentacioAutenticacio Controlador de presentació autenticació.
     */
    public AutenticacioPartida(VistaPartida vp, String player1, String player2, CtrlPresentacioAutenticacio ctrlPresentacioAutenticacio) {
        this.myVP = vp;
        this.ctrlPresentacioAutenticacio = ctrlPresentacioAutenticacio;
        setBounds(100, 100, 450, 293);
        this.setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        SpringLayout sl_contentPanel = new SpringLayout();
        contentPanel.setLayout(sl_contentPanel);

        JLabel nameLabel = new JLabel("Nom del jugador:");
        sl_contentPanel.putConstraint(SpringLayout.WEST, nameLabel, 96, SpringLayout.WEST, contentPanel);
        nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, nameLabel.getFont().getSize()));
        contentPanel.add(nameLabel);

        logName = new JLabel(ctrlPresentacioAutenticacio.getUsuariNoAutenticat(player1, player2));
        sl_contentPanel.putConstraint(SpringLayout.EAST, nameLabel, -32, SpringLayout.WEST, logName);
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, logName, -107, SpringLayout.SOUTH, contentPanel);
        contentPanel.add(logName);

        JLabel passwordLabel = new JLabel("Contrasenya:");
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, nameLabel, -11, SpringLayout.NORTH, passwordLabel);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordLabel, 120, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, nameLabel);
        sl_contentPanel.putConstraint(SpringLayout.WEST, passwordLabel, 96, SpringLayout.WEST, contentPanel);
        passwordLabel.setFont(new Font(passwordLabel.getFont().getName(), Font.BOLD, passwordLabel.getFont().getSize()));
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        sl_contentPanel.putConstraint(SpringLayout.EAST, logName, 0, SpringLayout.EAST, passwordField);
        sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 115, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 247, SpringLayout.WEST, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, -57, SpringLayout.EAST, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.WEST, logName, 0, SpringLayout.WEST, passwordField);
        contentPanel.add(passwordField);

        Component verticalGlue = Box.createVerticalGlue();
        sl_contentPanel.putConstraint(SpringLayout.NORTH, verticalGlue, 63, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.WEST, verticalGlue, 143, SpringLayout.WEST, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.SOUTH, verticalGlue, -64, SpringLayout.NORTH, nameLabel);
        sl_contentPanel.putConstraint(SpringLayout.EAST, verticalGlue, 164, SpringLayout.WEST, contentPanel);
        contentPanel.add(verticalGlue);

        JLabel titleLabel = new JLabel("Log In");
        sl_contentPanel.putConstraint(SpringLayout.NORTH, titleLabel, 27, SpringLayout.NORTH, contentPanel);
        sl_contentPanel.putConstraint(SpringLayout.EAST, titleLabel, -184, SpringLayout.EAST, contentPanel);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 30));
        contentPanel.add(titleLabel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("Autentica");
                okButton.setBackground(new Color(0, 205, 0));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                buttonPane.setOpaque(true);
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        inicialitzaComponents();
    }

    /**
     * Inicialitza els paràmetres de la classe.
     */
    private void inicialitzaComponents() {
        okButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (autenticaJugador()) {

                            myVP.autentica();
                            dispose();
                        } else {
                            //COntrasenya incorrecte
                        }


                    }
                }
        );
        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                }
        );
    }

    /**
     * Autentica el jugador que no està autenticat.
     * @return Retorna cert si el jugador s'ha autenticat, altrament retorna fals.
     */
    private boolean autenticaJugador() {

        char[] pwd = passwordField.getPassword();
        if (pwd.length > 0) {

            // reconstruim password
            String password = "";
            for (int i = 0; i < pwd.length; ++i) password += String.valueOf(pwd[i]);


            boolean autentica = ctrlPresentacioAutenticacio.autenticaUsuariPartida(logName.getText().toString(), password);

            return autentica;

        }
        else return false;
    }

}
