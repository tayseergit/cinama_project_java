
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PayingMethodUI extends JFrame implements ActionListener {

    String EmailString = "0", IDSTring = "0";
    JLabel titleLabel;
    JPanel panel = new JPanel();
    JButton bookButton;
    JTextField EmailField;
    JTextField IDField;
    JLabel EmailLabel, IDLabel, WithdrowLabel;

    JButton withdrowButton;

    private String Email;
    private String payingWay;
    private int movieID;
    private String showTime;
    private int chairNumber;

    PayingMethodUI(
            String email, String payingWay, int movieID, String showTime, int chairNumber, String prictickit,
            String filmname) {
        this.Email = email;
        this.payingWay = payingWay;
        this.movieID = movieID;
        this.showTime = showTime;
        this.chairNumber = chairNumber;

        this.setSize(500, 500);
        this.setResizable(false);

        titleLabel = new JLabel(filmname);
        titleLabel.setBackground(ColorApp.cyan_Red);
                titleLabel.setForeground (ColorApp.font_Color);

        titleLabel.setBounds(0, 0, 500, 60);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));

        EmailField = new JTextField();
        EmailField.setFont(new Font("Serif", Font.BOLD, 20));
        EmailField.setBounds(200, 120, 250, 40);

        IDField = new JTextField();
        IDField.setFont(new Font("Serif", Font.BOLD, 20));
        IDField.setBounds(200, 220, 250, 40);

        EmailLabel = new JLabel("Your Account : ");

        EmailLabel.setBounds(20, 120, 170, 40);
        EmailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        EmailLabel.setForeground(ColorApp.font_Color);
        IDLabel = new JLabel("Password : ");

        IDLabel.setBounds(55, 220, 170, 40);
        IDLabel.setFont(new Font("Serif", Font.BOLD, 20));
        IDLabel.setForeground(ColorApp.font_Color);

        WithdrowLabel = new JLabel("Will withdrow " + prictickit + " $ from your account,");
        WithdrowLabel.setBounds(60, 300, 450, 40);
        WithdrowLabel.setFont(new Font("Serif", Font.BOLD, 18));
        WithdrowLabel.setForeground(ColorApp.font_Color);

        withdrowButton = new JButton("Book");
        withdrowButton.setBackground(ColorApp.cyan_Red);
        withdrowButton.setForeground (ColorApp.font_Color);

        withdrowButton.setBounds(155, 370, 200, 50);
        withdrowButton.setFont(new Font("Serif", Font.BOLD, 20));
        withdrowButton.addActionListener(this);
        this.add(withdrowButton);

        this.add(IDLabel);
        this.add(WithdrowLabel);

        this.add(EmailLabel);

        this.add(EmailField);
        this.add(IDField);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(ColorApp.backColor);
        this.setLayout(null);
        this.add(titleLabel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // ما في داعي للشرط لأنو زر واحد
        EmailString = EmailField.getText();
        IDSTring = IDField.getText();

        if (e.getSource() == withdrowButton) {
            if (EmailString.length() < 2 || IDSTring.length() < 2) {
                JOptionPane.showMessageDialog(panel, " Fill the boxes ", "ID",
                        JOptionPane.DEFAULT_OPTION);
            } else {
                User user = new User(Email, payingWay, movieID, showTime, chairNumber);
                Thread tuser = new Thread(user);
                tuser.run();
                int ShowID = user.getUserID(Email);

                {
                    JOptionPane.showMessageDialog(panel, "your id is " +
                            (Integer.toString(ShowID)), "ID",
                            JOptionPane.DEFAULT_OPTION);

                    this.dispose();
                    // Main_frame  m ;
                    // Main_frame.destroyproject();
                    // new Main_frame();

                }

            }

            // انطينا تابع من خويك بلال يحجز تذكرة
            // throw new UnsupportedOperationException("Unimplemented method
            // 'actionPerformed'");
        }

    }
}