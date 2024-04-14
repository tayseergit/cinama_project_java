
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class UserTickets extends JFrame implements ActionListener {
    User user = new User();
    Thread tuser = new Thread(user);
    movies movi = new movies();
    JFrame frame = new JFrame();
    JLabel label, label1, label2, label3, label4;
    Tickets tickets = new Tickets();
    List<Tickets> tList = new ArrayList<>();
    JPanel panel;
    ImageIcon image;
    JLabel imalabel;

    int ticketsNum;
    JButton button = new JButton();
    List<JButton> list_of_Buttons = new ArrayList<>();

    Font font1 = new Font("Serif", Font.BOLD, 40);

    UserTickets(int numebr) {

        Font font = new Font("Serif", Font.BOLD, 20);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 710);
        frame.setResizable(false);
        frame.setLayout(null);

        user.getTickets(numebr);

        Tickets tic[] = new Tickets[user.userTickets.size()];
        int countoftickit_AfterThree = user.userTickets.size() - 3;
        int sizepanel = countoftickit_AfterThree;
        int sizeSecrool;
        if (user.userTickets.size() == 1)
            sizeSecrool = 100;
        else if (user.userTickets.size() == 2)
            sizeSecrool = 150;
        else
            sizeSecrool = 270 + 270 * countoftickit_AfterThree;

        panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setBounds(20, 60, 1200, 1500 + 250 * sizepanel);
        panel.setLayout(null);
        int spacy = 0;

        List<String[]> ticitList = new ArrayList<>();
        String ticdataString[] = new String[6];

        for (int i = 0; i < tic.length; i++) {
            tic[i] = user.userTickets.get(i);
            ticdataString = tic[i].ToString();
            ticitList.add(ticdataString);

        }
        int sapcx = 200;
        int anospacy = 30;
        for (int i = 1; i <= tic.length; i++) {
            String ticdata[] = ticitList.get(i - 1);

            JPanel panelTicket = new JPanel();
            panelTicket.setBounds(25, spacy, 1100, 260);
            panelTicket.setLayout(null);
            ///// image "C:\\Users\\LENOVO\\Desktop\\img.jpg"

            image = new ImageIcon(movi.path.get(Integer.parseInt(ticdata[5])) + "\\img.jpg");
            Image ssImage = image.getImage().getScaledInstance(180, 240, Image.SCALE_SMOOTH);
            image = new ImageIcon(ssImage);
            imalabel = new JLabel();
            imalabel.setIcon(image);
            imalabel.setOpaque(true);
            imalabel.setHorizontalAlignment(JLabel.RIGHT);
            imalabel.setVerticalAlignment(JLabel.CENTER);
            imalabel.setBounds(10, 7, 180, 240);

            // name
            label = new JLabel();
            label.setText(ticdata[0]);// name
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setForeground(ColorApp.font_Color);
            label.setFont(font);
            label.setBounds(sapcx, anospacy, 400, 40);
            label.setOpaque(false);
            ////////////////////////
            /// time
            label1 = new JLabel();
            label1.setText(ticdata[1]);// time
            label1.setVerticalAlignment(JLabel.CENTER);
            label1.setHorizontalAlignment(JLabel.CENTER);
            label1.setForeground(ColorApp.font_Color);
            label.setVisible(true);
            label1.setFont(font);
            label1.setBounds(sapcx, 120 + anospacy, 400, 40);
            label1.setOpaque(false);
            /////////////////////////
            ///// chair
            label2 = new JLabel();
            label2.setText(ticdata[3]);// chair num
            label2.setVerticalAlignment(JLabel.CENTER);
            label2.setHorizontalAlignment(JLabel.CENTER);
            label2.setFont(font);
            label2.setForeground(ColorApp.font_Color);

            label2.setBounds(330 + sapcx, 120 + anospacy, 400, 40);
            label2.setOpaque(false);

            /////////////////////////
            // chair
            label3 = new JLabel();
            label3.setText(ticdata[2]);// host hall
            label3.setVerticalAlignment(JLabel.CENTER);
            label3.setHorizontalAlignment(JLabel.CENTER);
            label3.setForeground(ColorApp.font_Color);

            label3.setFont(font);
            label3.setBounds(330 + sapcx, anospacy, 400, 40);
            label3.setOpaque(false);
            //////////////////////
            //// id
            label4 = new JLabel();
            label4.setText(ticdata[4]);// id
            label4.setVerticalAlignment(JLabel.CENTER);
            label4.setHorizontalAlignment(JLabel.CENTER);
            label4.setForeground(ColorApp.font_Color);

            label4.setFont(font);
            label4.setBounds(750 + sapcx, anospacy, 100, 40);
            label4.setOpaque(false);
            //////////////

            JButton button = new JButton("Delete");
            button.setFont(font);
            button.setBackground(ColorApp.buttonTicketDelete);
            button.setForeground(ColorApp.font_Color);
            button.setFocusPainted(false);
            button.setBounds(685 + sapcx, 155 + anospacy, 200, 60);

            list_of_Buttons.add(button);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    int buttonindex = list_of_Buttons.indexOf(button);
                    user.deleteTicket(numebr, buttonindex);
                    if (user.userTickets.size() == 0)
                        frame.dispose();
                    else
                        new UserTickets(numebr);
                    frame.dispose();
                }

            });

            panelTicket.setBackground(ColorApp.backColor);

            spacy += 270;
            panelTicket.add(imalabel);
            panelTicket.add(label);
            panelTicket.add(label1);
            panelTicket.add(label2);
            panelTicket.add(label3);
            panelTicket.add(label4);
            panelTicket.add(button);
            panel.add(panelTicket);

        }
        panel.setBackground(ColorApp. ligtTicketBack);

        JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 80, 0, sizeSecrool);

        verticalScrollBar.setBounds(0, 60, 22, 615);
        verticalScrollBar.setBackground(new Color(100, 100, 100));
        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int scrollValue = e.getValue() - 60;
                panel.setLocation(panel.getX(), -scrollValue);
                // panel.revalidate(); // Ensure the panel is redrawn after location change
            }
        });

        JLabel LabelTitle = new JLabel();

        LabelTitle.setText("User Tickets");
        LabelTitle.setHorizontalAlignment(JLabel.CENTER);
        LabelTitle.setVerticalTextPosition(JLabel.CENTER);
        LabelTitle.setFont(font1);
        LabelTitle.setForeground(ColorApp.font_Color);
        LabelTitle.setBackground(ColorApp.cyan_Red);
        LabelTitle.setOpaque(true);
        LabelTitle.setBounds(0, 0, 1200, 60);
        frame.add(LabelTitle);

        frame.setLocationRelativeTo(null);
        frame.add(LabelTitle);
        frame.add(panel);
        frame.add(verticalScrollBar);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {

        }

    }
}
