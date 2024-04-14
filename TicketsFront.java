import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class TicketsFront implements ActionListener {
    User user = new User();
    Font font = new Font("Serif", Font.BOLD, 25);
    JFrame frame = new JFrame();
    // JPanel panel;

    JPasswordField TicketsID;
    JTextField userEmailID;

    JLabel ID;
    JLabel emailLabel;

    JPanel panelDialog;
    JButton submitButton;

    TicketsFront() {

        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel LabelTitle = new JLabel();
        LabelTitle.setText("Tickets Page");
        LabelTitle.setHorizontalAlignment(JLabel.CENTER);
        LabelTitle.setVerticalAlignment(JLabel.CENTER);
        LabelTitle.setFont(new Font("Serif", Font.BOLD, 40));
        LabelTitle.setForeground(ColorApp.font_Color);
        LabelTitle.setBounds(0, 0, 1000, 80);
        LabelTitle.setBackground(ColorApp.cyan_Red);
        LabelTitle.setOpaque(true);
        frame.add(LabelTitle);
        frame.setLayout(null);
        frame.setUndecorated(false);

        TicketsID = new JPasswordField();

        TicketsID.setPreferredSize(new Dimension(400, 50));
        TicketsID.setBounds(350, 160, 300, 50);
        TicketsID.setFont(font);

        ////

        userEmailID = new JTextField();

        userEmailID.setPreferredSize(new Dimension(400, 50));
        userEmailID.setBounds(350, 280, 300, 50);
        userEmailID.setFont(font);

        ////

        ID = new JLabel(" USER ID : ");
        ID.setBounds(130, 156, 350, 50);
        ID.setFont(font);
        ID.setForeground(ColorApp.font_Color);
        //
        emailLabel = new JLabel(" Your Email : ");
        emailLabel.setBounds(110, 280, 350, 50);
        emailLabel.setFont(font);
        emailLabel.setForeground(ColorApp.font_Color);
        //
        frame.add(ID);
        frame.add(emailLabel);

        frame.add(userEmailID);
        frame.add(TicketsID);

        submitButton = new JButton("Confirm");
        submitButton.setForeground(ColorApp.font_Color);
        submitButton.setFont(new Font("Serif", Font.BOLD, 30));
        submitButton.addActionListener(this);
        submitButton.setFocusPainted(false);

        submitButton.setBounds(380, 400, 240, 60);
        submitButton.setBackground(ColorApp.cyan_Red);
        frame.add(submitButton);

        frame.getContentPane().setBackground(ColorApp.backColor);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // تحويل السلسلة إلى عدد صحيح
        // System.out.println(number);
        try {
            int number = 0;
            char[] User_ID = TicketsID.getPassword();
            String numberString = new String(User_ID);
            String username = userEmailID.getText();
            if (e.getSource() == submitButton) {
                if (User_ID.length == 0 || username.length() == 0)
                JOptionPane.showMessageDialog(panelDialog, "fill boxes", "Error",
                JOptionPane.INFORMATION_MESSAGE);
                else {
                    number = Integer.parseInt(numberString);
                    user.getTickets(number);
                     BufferedReader emailFile = new BufferedReader(new FileReader("D:\\Cinema project\\Users\\user-" + number + "\\Email.txt"));
                    if (user.userTickets.size() == 0 && emailFile.readLine().equals(username)) {
                        JOptionPane.showMessageDialog(panelDialog, "You dont have tickits", "Error",
                                JOptionPane.INFORMATION_MESSAGE);
                       // frame.dispose();
                    } else {
                       
                        if (emailFile.readLine().equals(username)) {
                            frame.dispose();
                            new UserTickets(number);
                            System.out.println(number);

                        } else {
                            JOptionPane.showMessageDialog(panelDialog, "check your id and email", "Error",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                        emailFile.close();

                    }

                }
            }
        } catch (Exception e1) {

            JOptionPane.showMessageDialog(panelDialog, "Fill box", "Error",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
