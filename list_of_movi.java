import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;

public class list_of_movi extends JFrame implements ActionListener {
    movies movi = new movies();
    String[] paths = new String[100];
    JPanel panel;
    ImageIcon image;
    int x = 0;

    list_of_movi() { 
        Font font = new Font("Serif", Font.BOLD, 45);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1200, 710);
        this.setResizable(false);
        this.setTitle("ITED");
        this.setLayout(null);
        int cont = 1;
       

        panel = new JPanel(new GridLayout(0, 3, 10, 10));
        panel.setBackground(Color.BLACK);

        ArrayList<JLabel> labelList = new ArrayList<>();
        int sapcx = 0, spacy = 0;
         int numfilm = movi.path.size();

        int sizepanel;
        if (numfilm % 3 == 0)
            sizepanel = 500 * (numfilm / 3);
        else
            sizepanel = 500 * (numfilm / 3 + 1);
        int sizeofsecrol;
        if (numfilm <= 3)
            sizeofsecrol = 49;
        else {

            if (numfilm % 3 == 0)
                sizeofsecrol = 51 *9 * (numfilm / 3 - 1);

            else
                sizeofsecrol = 51 * 9 * (numfilm / 3);
        }
        for (int i = 0; i < numfilm; i++) {
            paths[i] = movi.path.get(i) + "\\img.JPG";
            image = new ImageIcon(paths[i]);
            Image ssImage = image.getImage().getScaledInstance(395, 517, Image.SCALE_SMOOTH);
            image = new ImageIcon(ssImage);
            JLabel label = new JLabel();

            label.setIcon(image); 

            label.setHorizontalTextPosition(JLabel.CENTER);  
            label.setVerticalTextPosition(JLabel.TOP);   
            label.setIconTextGap(10);    
            label.setOpaque(true);  
            label.setVerticalAlignment(JLabel.CENTER);  
            label.setHorizontalAlignment(JLabel.CENTER);  
            if ((cont - 1) % 3 == 0 && cont > 1) {
                spacy += 400;
                sapcx = 0;
            }
            label.setBounds(50 + sapcx, 100 + spacy, 250, 350);
            sapcx += 320;
            labelList.add(label);
            x = i;

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getSource() == label) {
                        try {
                            int imageindex = labelList.indexOf(label);
                            new movie_information(imageindex);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }

            });
            this.add(label);
            panel.setBounds(20, 60, 1158, sizepanel);
            panel.add(label, BorderLayout.WEST);
            cont++;

        }
        panel.setBackground(Color.BLACK);

        JScrollBar verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 0, 49, 0, sizeofsecrol+numfilm*10);

        verticalScrollBar.setBounds(0, 70, 20, 602);
        verticalScrollBar.setBackground(new Color(100, 100, 100));
        verticalScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int scrollValue = e.getValue() - 60;
                panel.setLocation(panel.getX(), -scrollValue);
                panel.revalidate(); // Ensure the panel is redrawn after location change
            }
        });

        JLabel LabelTitle = new JLabel();

        LabelTitle.setText("MOVIES");
        LabelTitle.setHorizontalAlignment(JLabel.CENTER);
        LabelTitle.setVerticalTextPosition(JLabel.CENTER);
        LabelTitle.setFont(font);

        LabelTitle.setBackground(ColorApp.cyan_Red);
        LabelTitle.setForeground(ColorApp. font_Color );
        LabelTitle.setOpaque(true);
        LabelTitle.setBounds(0, 0, 1200, 70);
        this.add(LabelTitle);

        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);
        this.add(LabelTitle);
        this.add(panel);
        this.add(verticalScrollBar);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(e);
    }

}
