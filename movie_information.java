import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import java.util.TreeSet;

import javax.swing.*;

public class movie_information implements ActionListener {
    movies m = new movies();
    JFrame frame;
    JPanel panel = new JPanel(new FlowLayout());
    JComboBox box;
    String path;
    JLabel label;
    JLabel label1;
    JLabel label2;
    JLabel imalabel;
    ImageIcon image;
    JButton rigiterButton;
    JTextField emailTextField;

    String selectShowTime;
    String selectpay;
    String selectchair;
    String priceOFTikit = "";
    int chair;

    private int indexOfFilm;
    Font font = new Font("Serif", Font.BOLD, 20);

    movie_information(int i) throws IOException {

        indexOfFilm = i;

        //// fill ombobox fo chairs

        m.fillChairChecklist(i + 1);
        String chairString[] = new String[m.chairCheck.length];
        int contOFfList = 0;
        for (int k = 0; k < m.chairCheck.length; k++) {
            if (!m.chairCheck[k]) {
                int seleAsint = k;
                chairString[contOFfList] = (Integer.toString(seleAsint + 1));
                contOFfList++;
            }
        }

        label = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        imalabel = new JLabel();
        frame = new JFrame();

        Color colorlabel = ColorApp.cyan_Red;

        ///// print data

        String filmdata[] = m.ToString(i);
        priceOFTikit = filmdata[3].substring(17, 20);

        System.out.println("prce is " + priceOFTikit);
        image = new ImageIcon(m.path.get(i) + "\\img.JPG");
        Image ssImage = image.getImage().getScaledInstance(200, 260, Image.SCALE_SMOOTH);
        image = new ImageIcon(ssImage);
        imalabel.setIcon(image);
        imalabel.setOpaque(true);
        imalabel.setHorizontalAlignment(JLabel.RIGHT);
        imalabel.setVerticalAlignment(JLabel.CENTER);
        imalabel.setBounds(750, 10, 200, 260);

        int spacy = 0;
        for (int j = 0; j < filmdata.length; j++) {
            JLabel label = new JLabel();
            label.setText("  " + filmdata[j]);
            label.setBackground(colorlabel);
            label.setForeground(ColorApp.font_Color);
            label.setBounds(10, 10 + spacy, 700, 40);
            label.setOpaque(true);
            label.setFont(font);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setVerticalAlignment(JLabel.CENTER);
            frame.add(label);
            spacy += 45;

        }

        rigiterButton = new JButton("book a ticket");
        rigiterButton.setFont(new Font("Serif", Font.BOLD, 25));
        rigiterButton.setFocusPainted(false);
        rigiterButton.setBounds(703, 365, 275, 90);
        rigiterButton.setBackground(ColorApp.cyan_Red);
        rigiterButton.setForeground (ColorApp.font_Color);

        rigiterButton.addActionListener(this);

        ////// email field

        JLabel emailLabel = new JLabel(" Entre your Email  :");
        emailLabel.setBounds(10, 300, 300, 40);
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        emailLabel.setForeground(ColorApp.font_Color);
        emailTextField = new JTextField(100); // 20 columns wide
        emailTextField.setFont(font);

        emailTextField.setBounds(200, 300, 400, 40);

        //////// comboboxes labels

        String[] Times = { "morning Show Time", "evening Show Time" };
        String[] paymethod = { "visa card", "paybale", "perfect money" };
        JLabel comboboxJLabel = new JLabel(" Choose Time :");
        JLabel comboboxJLabelpay = new JLabel("Paying method :");
        JLabel comboboxJLabelchairs = new JLabel(" choose a chair :");

        comboboxJLabel.setBounds(50, 350, 200, 40);
        comboboxJLabel.setFont(new Font("Serif", Font.BOLD, 20));
        comboboxJLabel.setForeground(ColorApp.font_Color);
        comboboxJLabelpay.setBounds(35, 400, 200, 40);
        comboboxJLabelpay.setFont(new Font("Serif", Font.BOLD, 20));
        comboboxJLabelpay.setForeground(ColorApp.font_Color);
        comboboxJLabelchairs.setBounds(635, 300, 200, 40);
        comboboxJLabelchairs.setFont(new Font("Serif", Font.BOLD, 21));
        comboboxJLabelchairs.setForeground(ColorApp.font_Color);

        ////////////// comboboxes container
        JComboBox<String> comboBox = new JComboBox<>(Times);
        JComboBox<String> comboBoxpay = new JComboBox<>(paymethod);
        JComboBox<String> comboBoxchair = new JComboBox<>(chairString);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(font);
        comboBox.setBounds(200, 350, 300, 40);
        comboBox.setFocusable(false);
        comboBox.setSelectedIndex(0);

        comboBoxpay.setSelectedIndex(0);
        comboBoxpay.setFont(font);
        comboBoxpay.setBounds(200, 400, 300, 40);
        comboBoxpay.setFocusable(false);
        comboBoxpay.setSelectedIndex(0);

        comboBoxchair.setSelectedIndex(0);
        comboBoxchair.setFont(font);
        comboBoxchair.setBounds(800, 300, 100, 40);
        comboBoxchair.setFocusable(false);
        comboBoxchair.setSelectedIndex(0);

        //// addition

        frame.add(comboBox);
        frame.add(comboboxJLabel);
        frame.add(comboBoxpay);
        frame.add(comboboxJLabelpay);
        frame.add(comboboxJLabelchairs);
        frame.add(comboBoxchair);

        frame.add(emailTextField);
        frame.add(imalabel);
        frame.add(emailLabel);
        frame.add(rigiterButton);
        frame.add(rigiterButton);

        frame.add(label);
        frame.add(label1);
        frame.add(label2);

        frame.setLayout(null);
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(ColorApp.backColor);
        frame.setVisible(true);

        selectShowTime = (String) comboBox.getSelectedItem();
        selectpay = (String) comboBoxpay.getSelectedItem();
        selectchair = (String) comboBoxchair.getSelectedItem();
        chair = Integer.parseInt(selectchair);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the combo box
                selectShowTime = (String) comboBox.getSelectedItem();

            }
        });

        comboBoxpay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectpay = (String) comboBoxpay.getSelectedItem();

            }
        });
        comboBoxchair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected item from the combo box
                selectchair = (String) comboBoxchair.getSelectedItem();
                chair = Integer.parseInt(selectchair);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Email = emailTextField.getText();

        if (e.getSource() == rigiterButton) {
            if (Email.length() < 8)
                JOptionPane.showMessageDialog(panel, "Email is short", "Error", JOptionPane.ERROR_MESSAGE);
            else {

                emailTextField.setText("");
                frame.dispose();
                new PayingMethodUI(Email, selectpay, indexOfFilm + 1, selectShowTime, chair, priceOFTikit, selectpay);
                // User user = new User(Email, selectpay, indexOfFilm + 1, selectShowTime,
                // chair);
                // Thread tuser = new Thread(user);
                // tuser.run();
                // int ShowID = user.getUserID(Email);

                // {
                // JOptionPane.showMessageDialog(panel, "your id is " +
                // ( Integer.toString(ShowID)), "ID",
                // JOptionPane.DEFAULT_OPTION);

                // frame.dispose();
                // }

                System.out.println("Selected item: " + selectShowTime);
                System.out.println("booked\n" + Email);
                System.out.println(selectpay);
                System.out.println();
                System.out.println(selectchair);

            }
        }

    }

}
