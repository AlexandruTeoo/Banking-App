package Proiect_BD.Front_End;

import Proiect_BD.Objects.Locatie;
import Proiect_BD.System.LocatieDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowBanks extends JFrame {
    public static JFrame window;
    public static Locatie[] locations;
    private static DefaultTableModel locatie_banci;
    private static JTable tabel_banci;
    private JButton btnBack;


    public ShowBanks(int client_id, int numar_cont)
    {
        ImageIcon image = new ImageIcon("src/Logo.png");
        setVisible(true);
        window=this;
        JPanel panel = new JPanel();
        btnBack = new JButton("Catre meniul principal");
        String[] nume = {"Locatie Banca"};

        locatie_banci = new DefaultTableModel(nume, 0);
        tabel_banci = new JTable(locatie_banci);
        JScrollPane scrollPane = new JScrollPane(tabel_banci);
        panel.add(new JLabel("Locatiile sucursalelor sunt: "));
        panel.add(scrollPane);
        panel.add(btnBack);

        locations = LocatieDAO.findAll();

        assert locations != null;
        for (Locatie x:locations)
        {
            locatie_banci.addRow(new Object[] {x.getStrada_locatie()});
        }

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MainMenu(client_id, numar_cont);
                window.dispose();
            }
        });
        add(panel, BorderLayout.CENTER);
        setTitle("Sucursale Banca");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
