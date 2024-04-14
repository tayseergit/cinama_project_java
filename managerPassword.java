import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class managerPassword extends JFrame implements ActionListener {

    Font font = new Font("Impact", Font.BOLD, 30);
    JPanel panelDialog;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;
    JButton submitButton;

    managerPassword() {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setUndecorated(false);
        this.getContentPane().setBackground(ColorApp.backColor);

        JLabel LabelTitle = new JLabel();
        LabelTitle.setText("Maneger Page");
        LabelTitle.setHorizontalAlignment(JLabel.CENTER);
        LabelTitle.setVerticalAlignment(JLabel.CENTER);
        LabelTitle.setFont(font);
        LabelTitle.setBounds(0, 0, 1000, 100);
        LabelTitle.setBackground(ColorApp.cyan_Red );
        LabelTitle.setForeground(ColorApp.font_Color);
        LabelTitle.setOpaque(true);

        passwordField = new JPasswordField();
        passwordField.setFont(font);
        passwordField.setBounds(380, 200, 280, 40);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(font);
        confirmPasswordField.setBounds(380, 300, 280, 40);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(245, 200, 280, 40);
        passwordLabel.setFont(new Font("Serif", Font.BOLD, 26));
        passwordLabel.setForeground(ColorApp.font_Color);

        confirmPasswordLabel = new JLabel("Confirm Password :");
        confirmPasswordLabel.setBounds(150, 300, 280, 40);
        confirmPasswordLabel.setFont(new Font("Serif", Font.BOLD, 26));
        confirmPasswordLabel.setForeground(ColorApp.font_Color);

        submitButton = new JButton("Confirm");
        submitButton.setBounds(350, 420,340,60);
        submitButton.setBackground(ColorApp.cyan_Red );
        submitButton.setForeground(ColorApp.font_Color);
        submitButton.setFont(new Font("Serif", Font.BOLD, 26) );
        submitButton.addActionListener(this);

        this.add(passwordLabel);
        this.add(passwordField);
        this.add(confirmPasswordLabel);
        this.add(confirmPasswordField);
        this.add(submitButton);
        // Add an action listener to the submit button to check if the passwords match
        this.add(LabelTitle);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        char[] password = passwordField.getPassword();
        char[] confirmPassword = confirmPasswordField.getPassword();
        char[] correct_password = { '1' };
        if (Arrays.equals(password, confirmPassword) && Arrays.equals(password, correct_password)) {
            if (e.getSource() == submitButton) {
                this.dispose();
                new MangerChoise();
            }
        } else {
            // Passwords do not match, display an error message
            JOptionPane.showMessageDialog(panelDialog, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

}
