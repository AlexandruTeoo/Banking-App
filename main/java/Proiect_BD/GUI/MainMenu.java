package Proiect_BD.Front_End;

import Proiect_BD.Objects.*;
import Proiect_BD.System.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class MainMenu extends JFrame {
    private JButton btnDelete, btnLoan, btnRefresh, btnBanksLocations, btnLoans;
    private JTextField tfSumaImprumutata;
    private static JTable table_client;
    private static DefaultTableModel model_client;
    public static JFrame window;
    public static JPanel panel;
    public static Tranzactie[] tranzactii;
    public MainMenu(int client_id, int numar_cont)
    {
        ImageIcon image = new ImageIcon("src/Logo.png");
        window = this;
        btnLoan = new JButton("Loan");
        btnLoans = new JButton("My loans");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        btnBanksLocations = new JButton("Banks Locations");

        setVisible(true);
        Client client = ClientDAO.findByID(client_id);
        Cont cont = ContDAO.findByNumarCont(numar_cont);
        assert client != null;
        assert cont != null;

        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        add(panel, BorderLayout.CENTER);

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new GridLayout(6,1));
        panel.add(panelInfo);
        panelInfo.add(new JLabel("Hello " + client.getNume() + " " + client.getPrenume(),SwingConstants.CENTER));
        panelInfo.add(new JLabel("Sold: " + cont.getSuma_bani(),SwingConstants.CENTER));
        panelInfo.add(new JLabel("Numar_cont:  " + cont.getNumar_cont(),SwingConstants.CENTER));
        panelInfo.add(new JLabel("Data expirarii: " + cont.getData_expirarii(),SwingConstants.CENTER));
        panelInfo.add(new JLabel("CVV: " + cont.getCvv(),SwingConstants.CENTER));
        panelInfo.add(btnLoans);

        JPanel panelTranzactii = new JPanel();
        panel.add(panelTranzactii);
        panelTranzactii.setLayout(new GridLayout(2,1));
        panelTranzactii.add(new JLabel("Tranzactiile tale: "));
        String[] colNamesClient = {"ID", "Tip Tranzactie", "Suma", "Dat"};
        model_client = new DefaultTableModel(colNamesClient, 0);
        table_client = new JTable(model_client);
        JScrollPane scrollPaneUtil = new JScrollPane(table_client);
        panelTranzactii.add(scrollPaneUtil);

        tranzactii = TranzactieDAO.findAllByCont(numar_cont);
        assert tranzactii != null;
        for(Tranzactie x : tranzactii)
        {
            model_client.addRow(new Object[] {x.getTranzactie_id(), x.getTip_tranzactie(), x.getSuma(), x.getData_tranzactie()});
        }

        JPanel panelButoane = new JPanel();
        panelButoane.setLayout(new GridLayout(3,1));
        panel.add(panelButoane);
        panelButoane.setLayout(new GridLayout(2,4,20,20));
        tfSumaImprumutata = new JTextField(10);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,1));
        p1.add(new JLabel("Suma Imprumutata"));
        p1.add(tfSumaImprumutata);
        panelButoane.add(p1);
        panelButoane.add(btnLoan);
        panelButoane.add(btnDelete);
        panelButoane.add(btnBanksLocations);

        setTitle("Main Menu");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model_client.setRowCount(0);
                tranzactii = TranzactieDAO.findAllByCont(numar_cont);
                assert tranzactii != null;
                for(Tranzactie x : tranzactii)
                {
                    model_client.addRow(new Object[] {x.getTranzactie_id(), x.getTip_tranzactie(), x.getSuma(), x.getData_tranzactie()});
                }
                panel.repaint();
            }
        });

        btnLoan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!VerifyManager.isValidNumber(tfSumaImprumutata.getText()))
                {
                    showMessageDialog(null, "Suma imprumutata invalida!");
                    return;
                }
                if(Integer.parseInt(tfSumaImprumutata.getText()) == 0)
                {
                    showMessageDialog(null, "Nu poti imprumuta 0 lei!");
                    return;
                }
                Cont cont = ContDAO.findByNumarCont(numar_cont);
                assert cont != null;
                cont.setSuma_bani(cont.getSuma_bani() + Integer.parseInt(tfSumaImprumutata.getText()));
                ContDAO.update(numar_cont, cont);
                Imprumut x = new Imprumut();
                x.setNumar_cont(numar_cont);
                x.setDobanda(2);
                x.setSuma_imprumutata(Integer.parseInt(tfSumaImprumutata.getText()));
                x.setData_imprumut(TimeManager.getCurrentDate());
                ImprumutDAO.create(x);
                window.dispose();
                new MainMenu(client_id, numar_cont);

            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = showConfirmDialog(null, "Esti sigur ca vrei sa stergi contul?");
                if(result==JOptionPane.YES_OPTION)
                {
                    TranzactieDAO.delete(numar_cont);
                    ClientDAO.delete(client_id);
                    ContDAO.delete(numar_cont);
                    window.dispose();
                    new LogIn();
                }
            }
        });

        btnBanksLocations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowBanks(client_id, numar_cont);
                window.dispose();
            }
        });

        btnLoans.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ShowImprumut(client_id, numar_cont);
                window.dispose();
            }
        });
    }
}
