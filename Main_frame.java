import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;

public class Main_frame extends JFrame implements ActionListener {

    movies movi = new movies();
    HashMap<User, Integer> hashlabel = new HashMap<>();
    int arrayThreeFilm[] = { 0, 1, 2 };
    static JFrame frame = new JFrame();
    JButton addmovies;
    JButton moviesbuton;
    JButton tickets;
    JButton refrafrashbuttom;
    JButton upButton;
    JButton dButton;
    JPanel panel;
    int i = 0;
    Color buttoncolor  ;
    JLabel label;
static Boolean mood =true;
    Main_frame() {
 buttoncolor = ColorApp.cyan_Red;
        // movi.Reset();

        Font font = new Font("Impact", Font.BOLD, 60);
        Font fonttop = new Font("Impact", Font.BOLD, 40);
        Font buttFont = new Font("Impact", Font.BOLD, 30);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 710);
        frame.setResizable(false);
        // frame.setTitle("ITED");
        frame.setLayout(null);
        frame.getContentPane().setBackground(ColorApp.backColor);
        frame.setLocationRelativeTo(null);
        // menue
        addmovies = new JButton("Maneger ");
        addmovies.setBounds(0, 80, 400, 70);
        addmovies.setBackground(buttoncolor);
        addmovies.setForeground(ColorApp.font_Color);

        addmovies.setFont(buttFont);
        addmovies.addActionListener(this);
        addmovies.setFocusable(false);

        moviesbuton = new JButton("Movies");
        moviesbuton.setBounds(400, 80, 400, 70);
        moviesbuton.setBackground(buttoncolor);
        moviesbuton.setForeground(ColorApp.font_Color);

        moviesbuton.setFont(buttFont);
        moviesbuton.addActionListener(this);
        moviesbuton.setFocusable(false);

        tickets = new JButton("Tickets");
        tickets.setBounds(800, 80, 400, 70);
        tickets.setBackground(buttoncolor);
        tickets.setForeground(ColorApp.font_Color);

        tickets.addActionListener(this);
        tickets.setFont(buttFont);
        tickets.setFocusable(false);

        JLabel labelcolor = new JLabel();
        labelcolor.setBackground(new Color(44, 44, 44));
        labelcolor.setBounds(0, 70, 1200, 50);
        labelcolor.setLayout(null);
        labelcolor.setOpaque(true);

        JLabel labeltop1 = new JLabel("t o p  1");
        labeltop1.setBounds(560, 600, 110, 50);
        labeltop1.setForeground(ColorApp.black_WhiteMainLabel);
        labeltop1.setFont(fonttop);

        JLabel labeltop2 = new JLabel("t o p  2");
        labeltop2.setBounds(140, 560, 110, 50);
        labeltop2.setForeground(ColorApp.black_WhiteMainLabel);
        labeltop2.setFont(fonttop);

        JLabel labeltop3 = new JLabel("t o p  3");
        labeltop3.setBounds(950, 560, 110, 50);
        labeltop3.setForeground(ColorApp.black_WhiteMainLabel);
        labeltop3.setFont(fonttop);

        frame.add(labeltop1);
        frame.add(labeltop2);
        frame.add(labeltop3);
        frame.add(addmovies);
        frame.add(moviesbuton);
        frame.add(tickets);
        frame.add(labelcolor);
        // frame.add(refrafrashbuttom);

        // frame.add(acounnts);
        // frame.add(upButton);
        // frame.add(dButton);
        ///////////////////////////////////////////////////////////
        //// images
        /////////////////////////////////////////////////////////// C:\\Users\\LENOVO\\Desktop\\Cinema-main\\back\\films\\film1\\picture.JFIF
        int cont = 0;
        int num_Movi = 4;

        //////////////////////

        // frame.getContentPane().add(scrollableTextArea);
        panel = new JPanel(new GridLayout(10, 4));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 70, 1000, 800);

        int sapcx = 0, spacy = 0;
        ArrayList<JLabel> labelList = new ArrayList<>();

        // if (framenum == 0)
        movies.theMostPopular();
        arrayThreeFilm = movi.mostPopularMovies;

        for (i = 0; i < 3; i++) {

            String Path = movi.path.get(arrayThreeFilm[i]) + "\\img.JPG";
            User user = new User();
            label = new JLabel();
            ImageIcon image = new ImageIcon(Path);
            Image ssImage;
            if (i == 1)
                ssImage = image.getImage().getScaledInstance(350, 420, Image.SCALE_SMOOTH);
            else
                ssImage = image.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
            image = new ImageIcon(ssImage);

            label.setIcon(image);
            label.setLayout(null);
            label.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT,CENTER, RIGHT of imageicon
            label.setVerticalTextPosition(JLabel.TOP); // set text TOP,CENTER, BOTTOM of imageicon
            label.setForeground(new Color(255, 0, 0)); // set font color of text
            label.setFont(new Font("MV Boli", Font.BOLD, 20)); // set font of text
            label.setIconTextGap(10); // set gap of text to image
            //
            label.setBackground(new Color(100, 200, 50)); // set background color
            label.setOpaque(true); // display background color
            label.setVerticalAlignment(JLabel.CENTER); // set vertical position of icon+text within label
            label.setHorizontalAlignment(JLabel.CENTER);
            if (i == 1)
                label.setBounds(sapcx + 30, 180, 350, 420);
            else
                label.setBounds(70 + sapcx, 210, 250, 350);
            sapcx += 400;
            hashlabel.put(user, arrayThreeFilm[i]);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        int imageindex = hashlabel.get(user);
                        new movie_information(imageindex);
                        System.out.println(imageindex);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }

            });
            frame.add(label);
        }

        ////////// label title

        JLabel LabelTitle = new JLabel();
        // LabelTitle.repaint();
        LabelTitle.setText("CENIMA SHOP");
        LabelTitle.setHorizontalAlignment(JLabel.CENTER);
        LabelTitle.setVerticalTextPosition(JLabel.CENTER);
        LabelTitle.setFont(font);

        LabelTitle.setBackground(buttoncolor);
        LabelTitle.setForeground(ColorApp.font_Color);
        LabelTitle.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mood=!mood;
                ColorApp.SwitchColor(mood);

                // LabelTitle.
                ////////////
                 buttoncolor = ColorApp.cyan_Red;

                tickets.setBackground(buttoncolor);
                moviesbuton.setBackground(buttoncolor);
                addmovies.setBackground(buttoncolor);
                moviesbuton.setForeground(ColorApp.font_Color);
                tickets.setForeground(ColorApp.font_Color);
                addmovies.setForeground(ColorApp.font_Color);

                label.setBackground(new Color(100, 200, 50)); // set background color
                label.setForeground(new Color(255, 0, 0)); // set font color of text
                label.setForeground(new Color(255, 0, 0)); // set font color of text
                labeltop3.setForeground(ColorApp.black_WhiteMainLabel);
                labeltop1.setForeground(ColorApp.black_WhiteMainLabel);
                labeltop2.setForeground(ColorApp.black_WhiteMainLabel);
                tickets.setForeground(ColorApp.font_Color);

                LabelTitle.setBackground(buttoncolor);
                LabelTitle.setForeground(ColorApp.font_Color);
                repaint();
                // frame.dispose();
                new Main_frame();

            }

        });

        LabelTitle.setOpaque(true);
        LabelTitle.setBounds(0, 0, 1200, 70);
        frame.add(LabelTitle);

        ////////// frame

        // frame.setUndecorated(true);

        frame.add(LabelTitle);

        // frame.add(verticalScrollBar);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addmovies) {

            new managerPassword();
        }
        if (e.getSource() == moviesbuton) {
            new list_of_movi();
        }
        if (e.getSource() == tickets) {
            new TicketsFront();
        }
        if (e.getSource() == refrafrashbuttom) {
            System.exit(0);
            frame.dispose();

        }
        // throw new UnsupportedOperationException("Unimplemented method
        // 'actionPerformed'");
    }

    static void destroyproject() {
        frame.dispose();
    }

    Main_frame(int i) {
        movi.Reset();
    }
}