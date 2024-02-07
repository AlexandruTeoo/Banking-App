package Proiect_BD.Front_End;

import Proiect_BD.Objects.Imprumut;
import Proiect_BD.System.ImprumutDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowImprumut extends JFrame {
    public static JFrame window;
    public static Imprumut[] loans;
    private static DefaultTableModel detailsLoans;
    private static JTable tabel_imprumuturi;
    private JButton btnLoans;


    public ShowImprumut(int client_id, int numar_cont)
    {
        ImageIcon image = new ImageIcon("src/Logo.png");
        setVisible(true);
        window=this;
        JPanel panel = new JPanel();
        btnLoans = new JButton("Catre meniul principal");
        String[] nume = {"Suma Imprumutata", "Dobanda","Data Imprumutului"};

        detailsLoans = new DefaultTableModel(nume, 0);
        tabel_imprumuturi = new JTable(detailsLoans);
        JScrollPane scrollPane = new JScrollPane(tabel_imprumuturi);
        panel.add(new JLabel("Imprumuturile tale sunt: "));
        panel.add(scrollPane);
        panel.add(btnLoans);

        loans = ImprumutDAO.findByNumarCont(numar_cont);

        assert loans != null;
        for (Imprumut x:loans)
        {
            detailsLoans.addRow(new Object[] {x.getSuma_imprumutata(), x.getDobanda(), x.getData_imprumut()});
        }

        btnLoans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainMenu(client_id, numar_cont);
                window.dispose();
            }
        });
        add(panel, BorderLayout.CENTER);
        setTitle("Imprumuturile mele");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
