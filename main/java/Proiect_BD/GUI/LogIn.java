package Proiect_BD.Front_End;

import Proiect_BD.Objects.RelatieOneToOne;
import Proiect_BD.System.ContDAO;
import Proiect_BD.System.RelatieOneToOneDAO;
import Proiect_BD.System.TimeManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.sql.Date.valueOf;
import static javax.swing.JOptionPane.showMessageDialog;

public class LogIn extends JFrame
{
    public static JFrame window;

    // Declararea componentelor ferestrei
    private JTextField tfNumarCont;
    private JPasswordField tfPinCont;
    private JTextField tfCVV;
    private JTextField tfDataExpirarii;
    private JButton btnLogIn;
    private JButton btnRegister;


    public static boolean loggedIn = false;
    public static boolean register = false;
    public static int cont_id = 0;
    public static int client_id = 0;



    public LogIn()
    {
        ImageIcon image = new ImageIcon("src/Logo.png"); // de gasit logo cu un porcusor de bani
        setIconImage(image.getImage());
        setVisible(true);
        window=this;
        tfNumarCont = new JTextField(10);
        tfPinCont = new JPasswordField(10);
        tfCVV = new JTextField(10);
        tfDataExpirarii = new JTextField(10);
        btnLogIn = new JButton("Log In");
        btnRegister = new JButton("Register");

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register=true;
                showMessageDialog(null,"Bine ati venit la panoul de inregistrare!" +
                        "Va rugam sa va introduceti datele pentru a va inregistra.");
                new Register();
                window.dispose();
            }
        });

        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cod pentru Log In
                int nc = Integer.parseInt(tfNumarCont.getText());
                int cvv = Integer.parseInt(tfCVV.getText());
                loggedIn = ContDAO.verify(nc, tfPinCont.getText(), cvv, TimeManager.convertFromStringToDate(tfDataExpirarii.getText()));
                if(loggedIn) {
                    RelatieOneToOne uc = RelatieOneToOneDAO.findByNumarCont(nc);
                    assert uc != null;
                    client_id = uc.getClient_id();
                    showMessageDialog(null, "Bine ai venit!");
                    new MainMenu(client_id, nc);
                    window.dispose();
                }
                else
                {
                    showMessageDialog(null, "Una sau mai multe din valorile introduse sunt incorecte! Va rugam reinceercati!");
                }
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,20,20));
        panel.add(new JLabel("Numar Cont:",SwingConstants.CENTER));
        panel.add(tfNumarCont);
        panel.add(new JLabel("Pin:",SwingConstants.CENTER));
        panel.add(tfPinCont);
        panel.add(new JLabel("CVV:",SwingConstants.CENTER));
        panel.add(tfCVV);
        panel.add(new JLabel("Data Expirarii cardului [AAAA-LL-ZZ]:",SwingConstants.CENTER));
        panel.add(tfDataExpirarii);
        panel.add(btnLogIn);
        panel.add(btnRegister);
        add(panel, BorderLayout.CENTER);
        /*
        btnInsert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cod pentru operatia de insert
                Cont contnou = new Cont();
                contnou.setUsername(tfUserName.getText());
                contnou.setParola(tfParola.getText());
                cont_id =ConturiDAO.create(contnou);
                loggedIn=true;

            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cod pentru operatia de update
                if(loggedIn)
                {
                    Cont contnou = new Cont();
                    contnou.setUsername(tfUserName.getText());
                    contnou.setParola(tfParola.getText());
                    ConturiDAO.update(cont_id,contnou);
                }
            }
        });
         */

        // Setarea proprietatilor ferestrei
        setTitle("Log In");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
