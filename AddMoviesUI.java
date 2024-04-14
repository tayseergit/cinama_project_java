import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
// شرح لمسار الصورة 

class AddMoviesUI extends JFrame implements ActionListener {
    JButton addButton;
    JPanel panel = new JPanel();
    String movieName = "", imagePath = "", time_Month = "", AM_Time = "", PM_Time = "", movieType = "Action";
    String hostHall = "Hall-1";
    int ticketPriceValue;
    JLabel titleLabel;
    JTextField namefield;
    JLabel nameLabel;
    JTextField imagePathField;
    JButton browseButton;
    JLabel typeLabel;
    JTextField fieldTime_Month_Day;
    JTextField fieldTime_AM_Hours;
    JTextField ticketPriceField;
    JTextField fieldTime_PM_Hours;
    JLabel time_AM_Label;
    JLabel time_PM_Hours;
    JLabel ticketPriceLabel;
    JLabel timeTowHours;
    JComboBox HallComboBox;
    JLabel hostHallLabel;
    JLabel filmTypeLabel;
    JComboBox typeComboBox;

    AddMoviesUI() {

        this.setSize(1200, 700);
        this.setLocationRelativeTo(null);

        titleLabel = new JLabel("ADD MOVIE");
        titleLabel.setBackground(ColorApp.cyan_Red);
        titleLabel.setForeground(ColorApp.font_Color);
        titleLabel.setBounds(0, 0, 1200, 50);
        titleLabel.setOpaque(true);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 28));

        nameLabel = new JLabel("");
        nameLabel = new JLabel("Movie name : ");
        nameLabel.setBounds(60, 100, 160, 40);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        nameLabel.setOpaque(false);
        nameLabel.setForeground(ColorApp.font_Color);
        nameLabel.setBackground(Color.green);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        // حقل العنوان
        namefield = new JTextField();
        namefield.setFont(new Font("Serif", Font.BOLD, 24));
        namefield.setBounds(240, 100, 250, 40);

        typeLabel = new JLabel("Image path : ");
        typeLabel.setBounds(735, 100, 160, 40);
        typeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        typeLabel.setOpaque(false);
        typeLabel.setForeground(ColorApp.font_Color);

        imagePathField = new JTextField();
        imagePathField.setFont(new Font("Serif", Font.BOLD, 24));
        imagePathField.setBounds(180 + 700, 100, 250, 40);

        /// my addition...
        browseButton = new JButton("Browse");
        browseButton.setFont(new Font("Serif", Font.BOLD, 24));
        browseButton.setBounds(1135, 105, 45, 30);
        browseButton.setBackground(ColorApp.cyan_Red);
        browseButton.setForeground(ColorApp.font_Color);

        browseButton.setFocusPainted(false);
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                    imagePathField.setText(selectedFile);
                }
            }
        });

        fieldTime_Month_Day = new JTextField();
        fieldTime_Month_Day.setFont(new Font("Serif", Font.BOLD, 24));

        fieldTime_Month_Day.setBounds(240, 400, 70, 40);
        fieldTime_AM_Hours = new JTextField();
        fieldTime_AM_Hours.setFont(new Font("Serif", Font.BOLD, 24));

        fieldTime_AM_Hours.setBounds(440, 400, 55, 40);

        ticketPriceField = new JTextField();
        // ticketPriceValue = Integer.parseInt(ticketPriceField.getText());
        ticketPriceField.setFont(new Font("Serif", Font.BOLD, 24));

        ticketPriceField.setBounds(150 + 900, 400, 80, 40);

        fieldTime_PM_Hours = new JTextField();
        fieldTime_PM_Hours.setFont(new Font("Serif", Font.BOLD, 24));

        fieldTime_PM_Hours.setBounds(640, 400, 55, 40);

        time_AM_Label = new JLabel("Month \\ Day : ");
        time_AM_Label.setBounds(75, 400, 200, 40);
        time_AM_Label.setFont(new Font("Serif", Font.BOLD, 24));
        time_AM_Label.setOpaque(false);
        time_AM_Label.setForeground(ColorApp.font_Color);

        time_PM_Hours = new JLabel("AM :");
        time_PM_Hours.setBounds(380, 400, 200, 40);
        time_PM_Hours.setFont(new Font("Serif", Font.BOLD, 24));
        time_PM_Hours.setOpaque(false);
        time_PM_Hours.setForeground(ColorApp.font_Color);

        ticketPriceLabel = new JLabel("The Ticket Price : ");
        ticketPriceLabel.setBounds(850, 400, 200, 40);
        ticketPriceLabel.setFont(new Font("Serif", Font.BOLD, 24));
        ticketPriceLabel.setOpaque(false);
        ticketPriceLabel.setForeground(ColorApp.font_Color);

        timeTowHours = new JLabel("PM :");
        timeTowHours.setBounds(575, 400, 200, 40);
        timeTowHours.setFont(new Font("Serif", Font.BOLD, 24));
        timeTowHours.setOpaque(false);
        timeTowHours.setForeground(ColorApp.font_Color);

        String[] Halls = new String[] { "Hall-1", "Hall-2", "Hall-3" };
        HallComboBox = new JComboBox<>(Halls);
        HallComboBox.setFont(new Font("Serif", Font.BOLD, 24));
        HallComboBox.setBounds(240, 250, 250, 42);
        HallComboBox.setAlignmentX(CENTER_ALIGNMENT);
        HallComboBox.setAlignmentY(CENTER_ALIGNMENT);
        HallComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hostHall = HallComboBox.getSelectedItem().toString();
                System.out.println(hostHall);
            }
        }

        );

        String[] filmType = new String[] { "Action", "Comedy", "Drama", "Horror", "Science Fiction", "Romance",
                "Adventure", "Thriller", "Fantasy", "Documentary", "Mystery", "Biography", "Animation", "Classic" };
        typeComboBox = new JComboBox<>(filmType);
        typeComboBox.setFont(new Font("Serif", Font.BOLD, 24));
        typeComboBox.setBounds(880, 250, 250, 42);
        typeComboBox.setAlignmentX(CENTER_ALIGNMENT);
        typeComboBox.setAlignmentY(CENTER_ALIGNMENT);

        typeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                movieType = (String) typeComboBox.getSelectedItem();
                System.out.println(movieType);
            }
        }

        );

        hostHallLabel = new JLabel("Host Hall : ");
        hostHallLabel.setBounds(100, 250, 200, 40);
        hostHallLabel.setFont(new Font("Serif", Font.BOLD, 24));
        hostHallLabel.setOpaque(false);
        hostHallLabel.setForeground(ColorApp.font_Color);

        filmTypeLabel = new JLabel("Movie Type : ");
        filmTypeLabel.setBounds(728, 250, 200, 40);
        filmTypeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        filmTypeLabel.setOpaque(false);
        filmTypeLabel.setForeground(ColorApp.font_Color);

        addButton = new JButton("ADD MOVIE");
        addButton.setFont(new Font("Serif", Font.BOLD, 26));
        addButton.setBounds(450, 525, 300, 70);
        addButton.setBackground(ColorApp.cyan_Red);
        addButton.setForeground(ColorApp.font_Color);

        addButton.setFocusPainted(false);
        addButton.addActionListener(this);

        this.add(titleLabel);
        this.add(namefield);
        this.add(nameLabel);
        this.add(addButton);
        this.add(ticketPriceField);
        this.add(filmTypeLabel);
        this.add(hostHallLabel);
        this.add(HallComboBox);
        this.add(typeComboBox);
        this.add(imagePathField);
        this.add(browseButton);
        this.add(typeLabel);
        this.add(fieldTime_Month_Day);
        this.add(fieldTime_AM_Hours);
        this.add(fieldTime_PM_Hours);
        this.add(time_AM_Label);
        this.add(time_PM_Hours);
        this.add(ticketPriceLabel);
        this.add(timeTowHours);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(ColorApp.backColor);
        this.setVisible(true);

    }

    boolean datafalse() {

        if (movieName.length() < 1) {
            System.out.println("1");
            return false;
        }
        if (imagePath.length() < 1) {
            System.out.println("2");
            return false;
        }
        if (time_Month.length() < 1) {
            System.out.println("3");
            return false;
        }
        if (AM_Time.length() < 1) {
            System.out.println("4");
            return false;
        }
        if (PM_Time.length() < 1) {
            System.out.println("5");
            return false;
        }

        if (ticketPriceValue == 0) {
            System.out.println("5");

            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {

        movieName = namefield.getText();
        movieType = (String) typeComboBox.getSelectedItem();
        imagePath = imagePathField.getText();
        time_Month = fieldTime_Month_Day.getText();
        AM_Time = fieldTime_AM_Hours.getText();
        PM_Time = fieldTime_PM_Hours.getText();

        hostHall = (String) HallComboBox.getSelectedItem();

        // System.out.println("fun " + allDataRight());
        if (e.getSource() == addButton) {
            String s = ticketPriceField.getText();
            if (s.length() == 0 || datafalse())

                JOptionPane.showMessageDialog(panel, " Fill all boxes", "Error",
                        JOptionPane.ERROR_MESSAGE);
            else {
                movieName = namefield.getText();
                imagePath = imagePathField.getText();
                time_Month = fieldTime_Month_Day.getText();
                AM_Time = fieldTime_AM_Hours.getText();
                PM_Time = fieldTime_PM_Hours.getText();

                ticketPriceValue = Integer.parseInt(ticketPriceField.getText());
                ShowTimes morningTime = new ShowTimes(time_Month, AM_Time, false);
                ShowTimes eveningTime = new ShowTimes(time_Month, PM_Time, true);
                List<ShowTimes> listshowtime = new ArrayList<>();
                listshowtime.add(morningTime);
                listshowtime.add(eveningTime);
                System.out.println("name " + movieName);
                System.out.println("type " + movieType);
                System.out.println("month " + time_Month);
                System.out.println("pm " + PM_Time);
                System.out.println("am " + AM_Time);
                System.out.println("price " + ticketPriceValue);
                System.out.println("image path " + imagePath);
                System.out.println("hall " + hostHall);

                movies movi = new movies(movieName, movieType, listshowtime, hostHall,
                        ticketPriceValue);
                movi.addMovie(imagePath);
                this.dispose();
            }
        }
    }
}

// C:\\Users\\LENOVO\\Desktop\\img.JPG