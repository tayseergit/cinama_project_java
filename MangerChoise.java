import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MangerChoise extends JFrame implements ActionListener {
    JLabel titleLabel;

    JButton addMoviesButton;
    JButton restButton;

    MangerChoise() {

        this.setSize(1200, 700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        titleLabel = new JLabel("Select Option ");
        titleLabel.setBackground(ColorApp.cyan_Red);
        titleLabel.setForeground(ColorApp.font_Color);
        titleLabel.setBounds(0, 0, 1200, 80);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("MV Boli", Font.BOLD, 30));

        addMoviesButton = new JButton("ADD MOVIE");
        addMoviesButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        addMoviesButton.setBounds(450, 225, 300, 70);
        addMoviesButton.setBackground(ColorApp.cyan_Red);
        addMoviesButton.setForeground (ColorApp. font_Color);
        addMoviesButton.setFocusPainted(false);
        addMoviesButton.addActionListener(this);

        restButton = new JButton("Rest the APP");
        restButton.setFont(new Font("MV Boli", Font.BOLD, 25));
        restButton.setBounds(450, 425, 300, 70);
        restButton.setBackground(ColorApp.buttonTicketDelete);
        restButton.setForeground(ColorApp.font_Color);
        restButton.setFocusPainted(false);
        restButton.addActionListener(this);
        // restButton.setForeground(Color.WHITE);
        this.add(restButton);
        this.add(addMoviesButton);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(ColorApp.backColor);
        this.add(titleLabel);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if (e.getSource() == addMoviesButton) {
            // here add movie page
            this.dispose();
            new AddMoviesUI();

        }
        if (e.getSource() == restButton) {
            // here add reset
            this.dispose();
            movies movi = new movies();
            movi.Reset();
            Main_frame mf = new Main_frame(0);
            mf.destroyproject();
            //Main_frame p1 = new Main_frame();
            System.exit(0);
        }

        //throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
