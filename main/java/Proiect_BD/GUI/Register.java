package Proiect_BD.Front_End;

import Proiect_BD.Objects.Cont;
import Proiect_BD.Objects.Client;
import Proiect_BD.Objects.RelatieOneToOne;
import Proiect_BD.System.*;

import java.util.Objects;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Register extends JFrame {
    private JTextField tfNume, tfPrenume, tfEmail, tfNumarTelefon, tfAdresa, tfDataNasterii, tfTipCont;
    private JPasswordField tfPinCont, tfAgainPin;
    public static JFrame window;
    public static int numar_cont;
    public static int client_id;

    public static boolean back = false;
    public static boolean create = false;
    private JButton btnBack;
    private JButton btnCreate;


    public Register()
    {
        ImageIcon image = new ImageIcon("src/Logo.png");
        setIconImage(image.getImage());
        //Mereu la inceput
        setVisible(true);
        window=this;
        JPanel panel = new JPanel();
        //---------------------

        tfNume = new JTextField(10);
        tfPrenume = new JTextField(10);
        tfEmail = new JTextField(10);
        tfNumarTelefon = new JTextField(10);
        tfDataNasterii = new JTextField(10);
        tfTipCont = new JTextField(10);
        tfPinCont = new JPasswordField(10);
        tfAgainPin = new JPasswordField(10);
        tfAdresa = new JTextField(10);

        panel.setLayout(new GridLayout(10,2,20,20));
        panel.add(new JLabel("Nume:",SwingConstants.CENTER));
        panel.add(tfNume);
        panel.add(new JLabel("Prenume:",SwingConstants.CENTER));
        panel.add(tfPrenume);
        panel.add(new JLabel("Email:",SwingConstants.CENTER));
        panel.add(tfEmail);
        panel.add(new JLabel("Numar de telefon:",SwingConstants.CENTER));
        panel.add(tfNumarTelefon);
        panel.add(new JLabel("Data nasterii(AAAA-LL-ZZ):",SwingConstants.CENTER));
        panel.add(tfDataNasterii);
        panel.add(new JLabel("Adresa:",SwingConstants.CENTER));
        panel.add(tfAdresa);
        panel.add(new JLabel("Tip Cont:",SwingConstants.CENTER));
        panel.add(tfTipCont);
        panel.add(new JLabel("Pin:",SwingConstants.CENTER));
        panel.add(tfPinCont);
        panel.add(new JLabel("Reintroduceti Pin:",SwingConstants.CENTER));
        panel.add(tfAgainPin);

        btnBack = new JButton("Back");
        btnCreate = new JButton("Create account");

        btnBack.addActionListener(new ActionListener() {
            //Merge inapoi la Log In
            public void actionPerformed(ActionEvent e) {
                back =true;
                new LogIn();
                window.dispose();
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cod pentru Create
                if(Objects.equals(tfPinCont.getText(), "")||
                        Objects.equals(tfNume.getText(), "")||
                        Objects.equals(tfPrenume.getText(), "")||
                        Objects.equals(tfEmail.getText(), "")||
                        Objects.equals(tfNumarTelefon.getText(), "")||
                        Objects.equals(tfDataNasterii.getText(), "")||
                        Objects.equals(tfTipCont.getText(), "")||
                        Objects.equals(tfAdresa.getText(), "")||
                        Objects.equals(tfAgainPin.getText(), ""))
                {
                    showMessageDialog(null, "Completati toate campurile!");
                    return;
                }
                if(!Objects.equals(tfPinCont.getText(), tfAgainPin.getText()))
                {
                    showMessageDialog(null, "Pin este gresita!");
                    return;
                }
                if(!VerifyManager.isValidPhoneNumber(tfNumarTelefon.getText()))
                {
                    showMessageDialog(null, "Numarul de telefon este incorect!");
                    return;
                }
                if(!VerifyManager.isValidName(tfNume.getText()))
                {
                    showMessageDialog(null, "Numele nu trebuie sa contina cifre " +
                            "sau caractere speciale");
                    return;
                }
                if(!VerifyManager.isValidName(tfPrenume.getText()))
                {
                    showMessageDialog(null, "Prenumele nu trebuie sa contina cifre " +
                            "sau caractere speciale");
                    return;
                }
                if(!VerifyManager.isValidDate(tfDataNasterii.getText()))
                {
                    showMessageDialog(null, "Data de nastere invalida!");
                    return;
                }
                if(!VerifyManager.isValidEmail(tfEmail.getText()))
                {
                    showMessageDialog(null, "Email invalid!");
                    return;
                }

                Cont contNou = new Cont();
                contNou.setTip_cont(tfTipCont.getText());
                contNou.setPin_cont(tfPinCont.getText());
                contNou.setCvv(ContDAO.getCVV() + 1);
                contNou.setData_expirarii(TimeManager.convertFromStringToDate(VerifyManager.getDate()));
                numar_cont = ContDAO.create(contNou);
                Client utilizatorNou = new Client();
                utilizatorNou.setNume(tfNume.getText());
                utilizatorNou.setPrenume(tfPrenume.getText());
                utilizatorNou.setEmail(tfEmail.getText());
                utilizatorNou.setNumar_telefon(tfNumarTelefon.getText());
                utilizatorNou.setAdresa(tfAdresa.getText());
                utilizatorNou.setData_nasterii(TimeManager.convertFromStringToDate(tfDataNasterii.getText()));

                client_id = ClientDAO.create(utilizatorNou);
                RelatieOneToOne uc = new RelatieOneToOne();
                uc.setClient_id(client_id);
                uc.setNumar_cont(numar_cont);
                RelatieOneToOneDAO.create(uc);

                showMessageDialog(null, "Bine ai venit in meniu!");
                new MainMenu(client_id,numar_cont);
                window.dispose();
            }
        });


        //Mereu la final
        panel.add(btnBack);
        panel.add(btnCreate);
        add(panel, BorderLayout.CENTER);
        setTitle("Register");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
